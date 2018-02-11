package com.game.framework.threads;

import com.game.framework.threads.inter.IRequestToGod;
import com.game.framework.threads.policy.IRequestOverflowPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 精灵：神代言人的一种<br/>
 * 我能在固定时间周期处理固定数目的请求
 * 
 * @author liguorui
 * @date 2016-01-06
 */
public abstract class Sprite extends UniversalSpokesmanOfGod {

//	private final Log log					= LogFactory.getLog(Sprite.class);
	private static final Logger LOGGER	= LoggerFactory.getLogger(Sprite.class);

	/** 请求处理的事务 */
	private final ConcurrentLinkedQueue<IRequestToGod>	reuests				= new ConcurrentLinkedQueue<IRequestToGod>();
	/** 当前请求数目 */
	private final AtomicInteger							currentRequestNum	= new AtomicInteger();
	/** 超时时间 */
	private long										timeoutTime;

	public void add(IRequestToGod request) {
		if (isRunning()) {
			if (getMaxRequestNum() == -1 || currentRequestNum.incrementAndGet() < getMaxRequestNum()) {
				reuests.add(request);
			} else {
				currentRequestNum.decrementAndGet();
				LOGGER.error(getName() + " request overflow, currentSize=" + reuests.size() + ", maxRequestNum=" + getMaxRequestNum());
				IRequestOverflowPolicy overflowPolicy = getOverflowPolicy();
				if (overflowPolicy != null) {
					overflowPolicy.handle(this, request);
				}
			}
		}
	}

	@Override
	public void execute(boolean running) {
		int processCount = 0;
		IRequestToGod poll = null;
		timeoutTime = System.currentTimeMillis() + getProcessPeriod() - getMinSleepMillis();
		/**
		 ** @formatter off
		 ** 
		 **            <pre>
		 *  当同时满足两个条件时，循环会不停处理：<br/> 
		 *  1、getProcessNumPerPeriod() <= 0（周期没有处理数目限制）或 processCount<getProcessNumPerPeriod()(还没到达没周期处理数目)<br/>
		 *  2、getProcessPeriod() <= 0 (每次处理多久没限制) 或者 System.currentTimeMillis() < timeoutTime （超出处理时间）<br/>
		 ** </pre>
		 ** @formatter on
		 **/
		while ((getProcessNumPerPeriod() <= 0 || processCount < getProcessNumPerPeriod()) && (getProcessPeriod() <= 0 || System.currentTimeMillis() < timeoutTime)) {
			poll = poll();
			if (poll == null || !running)
				break;
			boolean count = true;
			try {
				count = poll.execute();
			} catch (Exception e) {
				LOGGER.error(getName() + " execute Packet[" + poll + "] error!", e);
			} finally {
				updateLastExecuteTimeMillis();
				if (count) {
					processCount++;
				}
			}
		}
	}

	/** 获取溢出处理策略 */
	public abstract IRequestOverflowPolicy getOverflowPolicy();

	/** 请求上限，超过这个上限，将使用溢出策略处理 */
	public abstract int getMaxRequestNum();

	/**
	 * 最小休息时间 如果这个值<=0表示不休眠 如果这个值>0
	 * 如果getProcessPeriod是不限制的，那么按照getMinSleepMillis进行休眠
	 * 如果getProcessPeriod做了限制，那么按照处理周期减去实际处理时间得到休眠时间
	 * 如果休眠时间<getMinSleepMillis,那么按getMinSleepMillis处理
	 */
	public abstract int getMinSleepMillis();

	/** 处理请求的周期:毫秒(<=0表示不限制处理时间) */
	public abstract int getProcessPeriod();

	/** 每周期处理的请求数目(<=0表示不限制处理数目) */
	public abstract int getProcessNumPerPeriod();

	public IRequestToGod poll() {
		IRequestToGod poll = reuests.poll();
		if (poll != null) {
			currentRequestNum.decrementAndGet();
		}
		return poll;
	}

	void addDirectly(IRequestToGod request) {
		if (isRunning()) {
			currentRequestNum.incrementAndGet();
			reuests.add(request);
		}
	}

	public int getRequestCount() {
		return reuests.size();
	}

	@Override
	public void init() {
		super.init();
		reuests.clear();
		currentRequestNum.set(0);
	}

	@Override
	public String toString() {
		return getName() + ":[RequestNum=" + reuests.size() + ", lastExecuteTime=" + new Date(getLastExecuteTimeMillis()) + ", lastExecuteTimeMillis=" + getLastExecuteTimeMillis() + "]";
	}
}