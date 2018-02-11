package com.game.framework.asyncdb;

import com.game.framework.threads.CoreThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liguorui
 * @date 2018/1/21 21:24
 */
public class SyncDBExecutor {

    private final static Logger logger = LoggerFactory.getLogger(SyncDBExecutor.class);

    private final int STEP;

    private final int SPEED;

    private final int TRY_TIME;

    private final int SIZE;

    private volatile boolean stop = true;

    private Thread sumitSyncDBTaskThread;

    private ExecutorService executorService;

    private AtomicLong countSync = new AtomicLong();

    private final BlockingQueue<AsynDBEntity> syncQueue = new LinkedTransferQueue<AsynDBEntity>();

    private ScheduledExecutorService monitor;

    private ExceptionCallback callback;

    public SyncDBExecutor(ExceptionCallback callback, int size, int step, int speed, int tryTime) {
        this.SPEED = speed;
        this.STEP = step;
        this.SIZE = size;
        this.TRY_TIME = tryTime;
        this.callback = callback;
        start();
    }

    public SyncDBExecutor(int size, int step, int speed, int tryTime) {
        this.SPEED = speed;
        this.STEP = step;
        this.SIZE = size;
        this.TRY_TIME = tryTime;
        this.start();
    }

    public boolean submit(AsynDBEntity synchronizable) {
        if (this.stop) {
            return false;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("submit synchronizable : {}", synchronizable);
        }
        return this.syncQueue.add(synchronizable);
    }

    public boolean shutdown() throws InterruptedException {
        if (this.stop) {
            return false;
        }

        logger.info("SyncDBExecutor shutdown");
        synchronized (this) {
            this.stop = true;
            this.sumitSyncDBTaskThread.join();
            logger.info("SyncDBExecutor executorService shutdowned");
            this.monitor.shutdown();
            return true;
        }
    }

    private ExecutorService createExecutorService() {
        return new ThreadPoolExecutor(this.SIZE, this.SIZE, 0L, TimeUnit.MILLISECONDS, new LinkedTransferQueue(), new CoreThreadFactory("SyncDbExecutor", false));
    }

    public boolean start() {
        if (this.stop) {
            synchronized (this) {
                this.executorService = createExecutorService();
                logger.info("SyncDBExecutor start");
                this.stop = false;
                this.sumitSyncDBTaskThread = new Thread(new SyncController(), "Sumit SyncDBTask Thread");
                this.sumitSyncDBTaskThread.start();
                this.monitor = Executors.newScheduledThreadPool(1, new CoreThreadFactory("SyncDBMonitor"));
                monitor.scheduleWithFixedDelay(new Runnable() {
                    private long preTime = 0L;

                    @Override
                    public void run() {
                        long thisTime = SyncDBExecutor.this.countSync.get();
                        ThreadPoolExecutor executorPool = (ThreadPoolExecutor) SyncDBExecutor.this.executorService;
                        if (logger.isInfoEnabled()) {
                            logger.info("sumit queue : {} # execute task number : {}"
                                            + " # sync total number : {} # exe number/min: {}",
                                    new Object[]{syncQueue.size(), executorPool.getQueue().size(),
                                            thisTime, thisTime - preTime});
                        }

                        this.preTime = thisTime;
                    }
                }, 0, 60L, TimeUnit.SECONDS);
                return true;
            }
        }
        return false;
    }

    private class SyncController implements Runnable {
        private SyncController() {
        }

        @Override
        public void run() {
            long rate = (long) (1000 / (SyncDBExecutor.this.SPEED / SyncDBExecutor.this.STEP));
            ExecutorService currentService = SyncDBExecutor.this.executorService;

            while (true) {
                ThreadPoolExecutor pool = (ThreadPoolExecutor) currentService;

                for (int i = 0; i < SyncDBExecutor.this.STEP; ++i) {
                    final AsynDBEntity synchronizable = (AsynDBEntity) SyncDBExecutor.this.syncQueue.poll();
                    if (synchronizable != null) {
                        currentService.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    synchronizable.trySync(SyncDBExecutor.this.TRY_TIME);
                                    if (SyncDBExecutor.this.countSync.get() == 9223372036854775807L) {
                                        SyncDBExecutor.this.countSync.compareAndSet(9223372036854775807L, 0L);
                                    }

                                    SyncDBExecutor.this.countSync.incrementAndGet();
                                } catch (Exception e) {
                                    SyncDBExecutor.this.callback.onException(e);
                                }
                            }
                        });
                    }
                }


                if (SyncDBExecutor.this.stop) {
                    if (SyncDBExecutor.this.syncQueue.isEmpty()) {
                        if (currentService.isShutdown()) {
                            while (!currentService.isTerminated()) {
                                try {
                                    Thread.sleep(100L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (pool.getQueue().size() != 0) {
                                SyncDBExecutor.logger.info("DataSyncService Sync not Complete, but IS Terminated !!");
                            }

                            return;
                        }

                        SyncDBExecutor.logger.info("SyncDBExecutor executorService shutdowning");
                        currentService.shutdown();
                    }
                } else {
                    try {
                        Thread.sleep(rate);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
