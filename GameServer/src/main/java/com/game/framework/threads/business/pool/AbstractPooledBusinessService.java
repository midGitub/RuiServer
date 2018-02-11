package com.game.framework.threads.business.pool;

import com.game.framework.threads.business.IBusinessMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 新业务服务，由业务服务线程池执行，不绑定单一线程
 * 参考：PooledBussinessServiceExecutor
 * @author liguorui
 * @date 2017/7/1 12:47
 */
public abstract class AbstractPooledBusinessService implements  IPooledBusinessService,InitializingBean {

    public final static Logger log = LoggerFactory.getLogger(AbstractPooledBusinessService.class);

    /**
     * 消息队列
     */
    private Queue<IBusinessMessage> messages = new ConcurrentLinkedQueue<IBusinessMessage>();

    /**
     * 运行状态
     */
    private AtomicBoolean running = new AtomicBoolean(false);

    /**
     * 执行状态
     */
    private AtomicBoolean executing = new AtomicBoolean(false);

    @Override
    public void start() {
        running.compareAndSet(false,true);
    }

    @Override
    public void stop() {
        running.compareAndSet(true,false);
    }

    @Override
    public boolean pushMessage(IBusinessMessage message) {
        if (!isRunning()) {
            return false;
        }
        boolean success = messages.add(message);
        if (success) {
            startExecuting();
        }
        return success;
    }

    @Override
    public void doExecute() {
        int count = 0;
        while(isRunning() && count < getProcessNumPerPeriod()) {
            IBusinessMessage message = messages.poll();
            if (message == null) {
                break;
            }
            try {
                message.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    @Override
    public void startExecuting() {
        if (isExecuting()) {
            return;
        }
        if (isBusy() && executing.compareAndSet(false,true)) {
            PooledBusinessServiceExecutor.getInstance().submit(this);
        }
    }

    @Override
    public void stopExecuting() {
        if (executing.compareAndSet(true,false)) {
            startExecuting();
        }
    }

    @Override
    public void afterPropertiesSet()  throws  Exception{
        start();
    }

    @Override
    public boolean isExecuting(){
        return executing.get();
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    public boolean isBusy() {
        return !messages.isEmpty();
    }
}
