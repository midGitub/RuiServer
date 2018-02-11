package com.game.framework.threads;

import com.game.framework.threads.inter.ISpokesmanOfGod;
import com.game.framework.threads.inter.IUndeadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用神的代言人，理论上我什么都能搞
 * 
 * @author liguorui
 * @date 2016-1-05
 */
public abstract class UniversalSpokesmanOfGod implements ISpokesmanOfGod {

//	private final static Log LOGGER					= LogFactory.getLog(UniversalSpokesmanOfGod.class);

	private static final Logger LOGGER	= LoggerFactory.getLogger(UniversalSpokesmanOfGod.class);

	/** 是否还在运行 */
	private volatile boolean	running					= true;
	/** 绑定的线程(只有isService()返回为true时，才有绑定线程) */
	private Thread				bindThread;

	private long				lastExecuteTimeMillis	= System.currentTimeMillis();

	private final IUndeadConfig config					= new IUndeadConfig() {
															public boolean isDead() {
																return bindThread != null && !bindThread.isAlive();
															}
														};

	@Override
	public void run() {
		int cost = 0;
		int sleepMillis = 0;
		long lastTimeMillis = System.currentTimeMillis();

		if (isService() && bindThread == null) {
			bindThread = Thread.currentThread();
			bindThread.setName(getName());
		}

		while (running) {
			lastTimeMillis = System.currentTimeMillis();

			try {
				execute(running);
			} catch (Exception e) {
				LOGGER.error("Exception occure when execute in " + getName(), e);
			} catch (Throwable t) {
				LOGGER.error("Error occure when execute in " + getName(), t);
			}

			updateLastExecuteTimeMillis();

			cost = (int) (System.currentTimeMillis() - lastTimeMillis);
			if (cost > getProcessPeriod() && getProcessPeriod() > 0) {
				LOGGER.error(getName() + " cost to much time (" + cost + ")ms to execute the requests.\n");
			}

			if (getMinSleepMillis() > 0) {
				try {
					if (getProcessPeriod() <= 0) {
						sleepMillis = getMinSleepMillis();
					} else {
						sleepMillis = getProcessPeriod() - cost;
						if (sleepMillis < getMinSleepMillis()) {
							sleepMillis = getMinSleepMillis();
						}
					}
					if (sleepMillis > 0) {
						Thread.sleep(sleepMillis);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			onStop();
		} catch (Exception e) {
			LOGGER.error(	"Error occure when process scene's stop.Exception:{0}",
							e);
		}
		LOGGER.info("Stop " + getClass() + " which name is " + getName());
	}

	/**
	 * 
	 */
	protected void onStop() {
	}

	public abstract int getMinSleepMillis();

	public abstract int getProcessPeriod();

	/**
	 * 这个方法会在每个周期被调用一次
	 */
	public abstract void execute(boolean running);

	@Override
	public void stop() {
		running = false;
	}

	@Override
	public IUndeadConfig getUndeadConfig() {
		return config;
	}

	@Override
	public boolean isService() {
		return true;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	public Thread getBindThread() {
		return bindThread;
	}
	
	public void setBindThread(Thread bindThread) {
		this.bindThread = bindThread;
	}

	@Override
	public void init() {
		running = true;
		bindThread = null;
		updateLastExecuteTimeMillis();
	}

	public long getLastExecuteTimeMillis() {
		return lastExecuteTimeMillis;
	}

	public void updateLastExecuteTimeMillis() {
		lastExecuteTimeMillis = System.currentTimeMillis();
	}

}