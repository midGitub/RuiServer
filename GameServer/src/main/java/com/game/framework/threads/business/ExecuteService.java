package com.game.framework.threads.business;

import com.game.framework.threads.business.pool.SimpleThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 游戏任务提交到线程池
 * @author liguorui
 * @date 2017/7/1 23:38
 */
@Component
public class ExecuteService {

    private ThreadPoolExecutor executor;

    public ExecuteService() {
        super();
        //25:核心线程池大小 25:线程池最大容量大小 600L:线程池空闲时，线程存活的时间 ThreadFactory 线程工厂 BlockingQueue任务队列
        executor = new ThreadPoolExecutor(25,25,600L,TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(), new SimpleThreadFactory("executor-service"));
        executor.allowCoreThreadTimeOut(true);
    }

    public void execute(Runnable command) {
        executor.execute(command);
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }
}
