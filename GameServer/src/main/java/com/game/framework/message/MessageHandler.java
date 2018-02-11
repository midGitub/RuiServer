package com.game.framework.message;

import com.game.framework.threads.business.pool.SimpleThreadFactory;
import com.game.framework.time.ITriggerTaskExecutor;
import com.game.framework.time.TriggerFuture;
import com.game.framework.time.impl.TriggerTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liguorui
 * @date 2018/1/7 18:45
 */
public class MessageHandler<H extends IMessageHandler<?>> implements Runnable, IMessageHandler<H> {

    private static Logger profileLog = LoggerFactory.getLogger("profileLogger");

    private static ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newFixedThreadPool(100, new SimpleThreadFactory("MessageHandler-Worker"));

    private static ITriggerTaskExecutor DEFAULT_TRIGGER_TASK_EXECUTOR = new TriggerTaskExecutor(5, "MessageHandler-Scheduler");

    private ExecutorService executorService;

    private ITriggerTaskExecutor triggerTaskExecutor;

    private Queue<IMessage<H>> messages = new ConcurrentLinkedDeque<>();

    private AtomicInteger size = new AtomicInteger();

    private volatile Thread currentThread;

    public MessageHandler(ExecutorService executorService, ITriggerTaskExecutor triggerTaskExecutor) {
        super();
        this.executorService = executorService;
        this.triggerTaskExecutor = triggerTaskExecutor;
    }

    public MessageHandler() {
        this(DEFAULT_EXECUTOR_SERVICE, DEFAULT_TRIGGER_TASK_EXECUTOR);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        this.currentThread = Thread.currentThread();
        while(true) {
            IMessage<H> message = messages.poll();
            if (message == null) {
                break;
            }
            try {
                long st = System.currentTimeMillis();
                message.execute((H)this);
                long cost = System.currentTimeMillis() - st;
                if (cost > 100) {
                    profileLog.warn("ExecuteMessage {} cost {} ms", message.name(), cost);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            int curSize = size.decrementAndGet();
            if (curSize <= 0) {
                break;
            }
        }
    }

    @Override
    public void addMessage(IMessage<H> message) {
        messages.add(message);
        int curSize = size.incrementAndGet();
        if (curSize == 1) {
            executorService.execute(this);
        }
    }

    public TriggerFuture schedule(String taskName, final IMessage<H> message,
                                  long delay, TimeUnit unit) {
        TriggerFuture future = triggerTaskExecutor.schedule(taskName, new Runnable() {
            @Override
            public void run() {
                addMessage(message);
            }
        }, delay, unit);
        return future;
    }

    public TriggerFuture schedule(String taskName, final IMessage<H> message, long executeTime) {
        TriggerFuture future = triggerTaskExecutor.schedule(taskName, new Runnable() {
            @Override
            public void run() {
                addMessage(message);
            }
        }, executeTime);
        return future;
    }

    public TriggerFuture scheduleAtFixedRate(String taskName, final IMessage<H> message,
                                           long executeTime) {
        TriggerFuture future = triggerTaskExecutor.schedule(taskName, new Runnable() {
            @Override
            public void run() {
                addMessage(message);
            }
        }, executeTime);
        return future;
    }

    public TriggerFuture scheduleAtFixedRate(String taskName, final IMessage<H> message,
                                            long initialDelay, long period, TimeUnit unit ) {
        TriggerFuture future = triggerTaskExecutor.scheduleAtFixedRate(taskName, new Runnable() {
            @Override
            public void run() {
                addMessage(message);
            }
        }, initialDelay, period, unit);
        return future;
    }

    public TriggerFuture scheduleWithFixedDelay(String taskName, final IMessage<H> message,
                                                long initialDelay, long delay, TimeUnit unit) {
        TriggerFuture futrue = triggerTaskExecutor.scheduleWithFixedDelay(taskName, new Runnable() {
            @Override
            public void run() {
                addMessage(message);
            }
        }, initialDelay, delay, unit);
        return futrue;
    }

    public boolean isInThread() {
        return Thread.currentThread() == currentThread;
    }
}
