package com.game.framework.threads.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 基础业务线程服务
 * @author liguorui
 *
 */
public abstract class AbstractBusinessService implements IBusinessService, InitializingBean {

	protected final static Logger LOG = LoggerFactory.getLogger(AbstractBusinessService.class);
	
	/**
	 * 消息队列
	 */
	private BlockingQueue<IBusinessMessage> messagesQueue = new LinkedBlockingQueue<IBusinessMessage>();
	
	/**
	 * 状态
	 */
	private AtomicBoolean running = new AtomicBoolean(false);
	
	/**
	 * 消费线程
	 */
	private Thread consumerThread = null;
	
	@Override
	public void run() {
		while(running.get() || !messagesQueue.isEmpty()) {
			int count = 0;
			try {
				while(running.get() && count < getProcessNums()) {
					IBusinessMessage message = messagesQueue.poll();
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
				this.execute(); //线程始终会循环执行的操作，如果没有类似需求则不写实现
				Thread.sleep(getMinSleepMilis());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public Thread getThread() {
		return consumerThread;
	}

	/**
	 * 为每个子类开启一个线程，用于处理各自的业务逻辑
	 */
	@Override
	public void start() {
		if (running.compareAndSet(false, true)) {
			consumerThread = new Thread(this, this.getName());
			consumerThread.start();
		}
	}

	@Override
	public void stop() {
		if (running.compareAndSet(true, false)) {
			try {
				consumerThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected long getMinSleepMilis() {
		return 100L;
	}

	@Override
	public boolean pushMessage(IBusinessMessage message) {
		if (running.get()) {
			if (consumerThread == Thread.currentThread()) { //如果为当前业务逻辑线程
				message.execute();
				return true;
			}
			return messagesQueue.offer(message); //不是当前逻辑线程则排队处理
		}
		return false;
	}

	/**
	 * 初始化完spring执行
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		this.start();
	}
}
