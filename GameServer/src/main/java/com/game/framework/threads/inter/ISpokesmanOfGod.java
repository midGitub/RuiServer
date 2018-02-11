package com.game.framework.threads.inter;


public interface ISpokesmanOfGod extends Runnable {

	/** 停止运行 */
	public void stop();

	/** 是否还在运行 */
	public boolean isRunning();

	/** 是否是常驻服务 */
	public boolean isService();

	/** 服务名字 */
	public String getName();

	/** 不死配置:这个配置决定了怎样判断服务挂掉 */
	public IUndeadConfig getUndeadConfig();

	/** 执行前的初始化 */
	public void init();
}
