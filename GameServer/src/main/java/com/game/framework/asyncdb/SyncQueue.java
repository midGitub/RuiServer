package com.game.framework.asyncdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @author liguorui
 * @date 2018/1/21 21:08
 */
public class SyncQueue implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(SyncQueue.class);

    private static AsynDBEntity SHUTDOWN_ENTITY = new AsynDBEntity();

    private final int queueId;

    private volatile boolean stop = false;

    private volatile long syncCount = 0;

    private final BlockingQueue<AsynDBEntity> syncQueue = new LinkedTransferQueue<AsynDBEntity>();

    private ExceptionCallback callback;

    private ISyncStrategy syncStrategy;

    private volatile long preNum;

    public SyncQueue(int queueId, ExceptionCallback callback, ISyncStrategy syncStrategy) {
        this.callback = callback;
        this.queueId = queueId;
        this.syncStrategy = syncStrategy;
    }

    public boolean submit(AsynDBEntity synchronizable) {
        if (this.stop) {
            return false;
        }
        return this.syncQueue.add(synchronizable);
    }

    public boolean shutdown(long millis) throws InterruptedException {
        if (this.stop) {
            return false;
        }
        this.stop = true;
        this.syncQueue.add(SHUTDOWN_ENTITY);
        return true;
    }

    public int getWaitingSize() {
        return syncQueue.size();
    }

    public long getSyncCount() {
        return syncCount;
    }

    public int getQueueId() {
        return queueId;
    }

    public SyncStats stats() {
        long total = this.getSyncCount();
        long periodNum = total - this.preNum;
        this.preNum = total;
        int waiting = getWaitingSize();
        return new SyncStats(waiting, total, periodNum);
    }

    @Override
    public void run() {
        while (true) {
            int numEachLoop = this.syncStrategy.getNumEachLoop();
            int tryTime = this.syncStrategy.getTryTime();

            int waitingSize;
            for (waitingSize = 0; waitingSize < numEachLoop; ++waitingSize) {
                AsynDBEntity entity = null;

                try {
                    entity = (AsynDBEntity)this.syncQueue.take();
                } catch (InterruptedException e) {
                    logger.error("SyncQueueInterrupterException {}", queueId, e);
                }

                if (entity == null || entity == SHUTDOWN_ENTITY) {
                    break;
                }

                try {
                    entity.trySync(tryTime);
                    ++ this.syncCount;
                } catch (Exception e) {
                    try {
                        callback.onException(e);
                    } catch (Exception e1) {
                        logger.error("CallbackException", e1);
                    }
                }
            }

            if (this.stop) {
                if (this.syncQueue.isEmpty()) {
                    return;
                }
            } else {
                try {
                    waitingSize = this.syncQueue.size();
                    long sleeptime = this.syncStrategy.getSleepTime(waitingSize);
                    Thread.sleep(sleeptime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
