package com.game.framework.threads.business.pool;

import com.game.framework.threads.inter.IServerClose;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 业务服务执行线程管理者
 * @author liguorui
 * @date 2017/7/1 21:51
 */
//@Listener
public class PooledBusinessServiceExecutor implements IServerClose {

    //线程池大小配置
    private int threadNums = Runtime.getRuntime().availableProcessors() * 2;
    private ExecutorService executor = Executors.newFixedThreadPool(threadNums, new SimpleThreadFactory("pooled-service-executor"));
    private volatile boolean running = true;

    private PooledBusinessServiceExecutor() {}

    private static PooledBusinessServiceExecutor pooledBusinessServiceExecutor = new PooledBusinessServiceExecutor();

    public static PooledBusinessServiceExecutor getInstance() {
        return pooledBusinessServiceExecutor;
    }

    public void submit(IPooledBusinessService service) {
        if (!running) {
            return;
        }
        executor.submit(new InnerRunner(service));
    }

    public void stop() {
        running = false;
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class InnerRunner implements Runnable {
        IPooledBusinessService service;

        InnerRunner(IPooledBusinessService service) {
            this.service = service;
        }

        @Override
        public void run() {
            if (service.isRunning()) {
                service.doExecute();
                service.stopExecuting();
            }
        }
    }

    @Override
    public void onClose() {
        stop();
    }
}
