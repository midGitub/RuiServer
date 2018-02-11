package com.game.framework.threads.business;

/**
 * 基础业务服务
 * 将场景无关的业务逻辑执行过程从场景线程分开，独立开启逻辑功能独有的线程
 * @author liguorui
 */
public interface IBusinessService extends Runnable{

	/**
	 * 线程名称
	 * @return
	 */
	String getName();
	
	/**
	 * 获取服务运行线程
	 * @return
	 */
	Thread getThread();
	
	/**
	 * 服务启动
	 */
	void start();
	
	/**
	 * 服务停止
	 */
	void stop();
	
	/**
	 * 每个周期最多处理多少个消息
	 * @return
	 */
	int getProcessNums();
	
	/**
	 * 推送消息
	 * @param message
	 * @return
	 */
	boolean pushMessage(IBusinessMessage message);
	
	/**
	 * 每个周期执行一次
	 */
	public void execute();
}
