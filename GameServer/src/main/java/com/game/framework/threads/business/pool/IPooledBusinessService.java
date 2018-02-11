package com.game.framework.threads.business.pool;

import com.game.framework.threads.business.IBusinessMessage;

/**
 * @author liguorui
 * @date 2017/7/1 12:40
 */
public interface IPooledBusinessService {

    /**
     * 服务器启动
     */
    public void start();

    /**
     * 服务器停止
     */
    public void stop();

    /**
     * 每个周期最多处理多少消息
     * @return
     */
    public int getProcessNumPerPeriod();

    /**
     * 推送消息
     * @param message
     * @return
     */
    public boolean pushMessage(IBusinessMessage message);

    /**
     * 执行
     */
    public void doExecute();

    /**
     * 服务器是否还在运行
     * @return
     */
    public boolean isRunning();

    /**
     * 服务器是否在忙
     * @return
     */
    public boolean isBusy();

    /**
     * 服务器是否正在执行消息
     * @return
     */
    public boolean isExecuting();

    /**
     * 开始执行消息
     */
    public void startExecuting();

    /**
     * 停止执行消息
     */
    public void stopExecuting();
}
