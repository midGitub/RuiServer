package com.game.framework.time.impl;

import com.game.framework.logger.ExceptionUtils;
import com.game.framework.threads.CoreThreadFactory;
import com.game.framework.time.AbstractTriggerTaskExecutor;
import com.game.framework.time.TimeTriggerTask;
import com.game.framework.time.TriggerFuture;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 触发任务执行器
 * @author liguorui
 * @date 2018/1/14 18:29
 */
@Component
public class TriggerTaskExecutor extends AbstractTriggerTaskExecutor {

    private ExecutorService service;
//    private RefreshDelayQueue<DelayedTriggerFutureTask> taskDelayQueue = new RefreshDelayQueue<DelayedTriggerFutureTask>();

    public TriggerTaskExecutor() {
        this(Runtime.getRuntime().availableProcessors() * 2, "TriggerTaskExecutor");
    }

    public TriggerTaskExecutor(int threadNum, String threadPrefix) {
        service = Executors.newCachedThreadPool(new CoreThreadFactory(threadPrefix));
        for (int i = 0; i < threadNum; i++) {
            service.execute(new Runnable() {

                @Override
                public void run() {
//                    while (!service.isShutdown()) {
//                        try {
//                            DelayedTriggerFutureTask trigger = taskDelayQueue.take();
//                            LOGGER.debug("start task {}", trigger);
//                            trigger.task.trigger(trigger.task.getTriggerTime());
//                            LOGGER.debug("finish task {}", trigger);
//                            if (!trigger.isCanceled() && trigger.task.canTrigger()) {
//                                taskDelayQueue.add(trigger);
//                            }
//                        } catch (Exception e) {
//                            ExceptionUtils.log(e);
//                        }
//                    }
                }
            });
        }
    }

    public TriggerFuture addTask(TimeTriggerTask task) {
        try {
            if (!task.canTrigger()) {
                LOGGER.debug("{} 任务已失效", task);
                return TriggerFuture.FINISH_FUTURE;
            }
//            if (contains(task)) {
//                ExceptionUtils.log("{} task exit", task);
//                return TriggerFuture.FINISH_FUTURE;
//            }
            DelayedTriggerFutureTask futureTask = new DelayedTriggerFutureTask(task);
//            taskDelayQueue.add(futureTask);
            return futureTask;
        } catch (Exception e) {
            ExceptionUtils.log(e);
        }
        return TriggerFuture.FINISH_FUTURE;
    }

    public List<TriggerFuture> addAllTask(Collection<? extends TimeTriggerTask> taskCollection) {
        List<TriggerFuture> futureList = new ArrayList<TriggerFuture>(taskCollection.size());
        for (TimeTriggerTask task : taskCollection) {
            futureList.add(addTask(task));
        }
        return futureList;
    }

    public void refresh() {
//        taskDelayQueue.refresh();
    }

    public void shutdown(long timeout, TimeUnit unit) {
        service.shutdownNow();
        try {
            service.awaitTermination(timeout, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isShutdown() {
        return service.isShutdown();
    }

    public void removeTask(TimeTriggerTask task) {
//        taskDelayQueue.remove(new DelayedTriggerFutureTask(task));
    }

    public void clear() {
//        taskDelayQueue.clear();
    }

//    public boolean contains(TimeTriggerTask task) {
//        return taskDelayQueue.contains(new DelayedTriggerFutureTask(task));
//    }

    private class DelayedTriggerFutureTask extends AbstractDelayedTriggerFutureTask {

        private volatile boolean canceled;

        public DelayedTriggerFutureTask(TimeTriggerTask task) {
            super(task);
        }

        public void cancel() {
            canceled = true;
//            taskDelayQueue.remove(this);
        }

        public String toString() {
            return task.toString();
        }

        public boolean isCanceled() {
            return canceled;
        }
    }
}
