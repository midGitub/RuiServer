package com.game.framework.threads;

import com.game.framework.threads.inter.ISpokesmanOfGod;
import com.game.framework.threads.inter.IUndeadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 天使<br/>
 * 负责复活具备不死能力并且已经死亡的服务
 * 
 * @author liguorui
 * @date 2016-01-06
 */
public class Angle extends UniversalSpokesmanOfGod {

//	private final static Log log			= LogFactory.getLog(Angle.class);
	private static final Logger LOGGER	= LoggerFactory.getLogger(Angle.class);

	private final static Angle instance	= new Angle();

	private ConcurrentLinkedQueue<ISpokesmanOfGod>	services;

	private Angle() {
	}

	public static Angle getInstance() {
		return instance;
	}

	public void registerWorkers(final ConcurrentLinkedQueue<ISpokesmanOfGod> services) {
		this.services = services;
	}

	@Override
	public void execute(boolean running) {
		for (final ISpokesmanOfGod service : services) {
			IUndeadConfig undeadConfig = service.getUndeadConfig();
			if (undeadConfig != null) {
				if (undeadConfig.isDead()) {
					/*
					 * 如果发现死亡的线程，把它从世界场景的WorkerService中移除。
					 * 然后发送一个请求包到本线程的请求列表中，处理请求的时候,判断服务真正停止才把其加入服务。
					 */
					StringBuilder info = new StringBuilder();
					info.append("Service[")
						.append(service.getName())
						.append(" has be checked died.] at Date=")
						.append(new Date())
						.append(", time=")
						.append(System.currentTimeMillis())
						.append("]");
					info.append("\n\tBefore Remove:").append(services);

					service.stop();
					WorldScene.getInstance().removeWorker(service);

					info.append("\n\tAfter  Remove:").append(services);
					LOGGER.error(info.toString());

					WorldScene.getInstance().executeWorker(service);
				}
			}
		}
	}

	@Override
	public String getName() {
		return "Angle[Relife the UndeadService which has died]";
	}

	@Override
	public IUndeadConfig getUndeadConfig() {
		return null;
	}

	@Override
	public int getMinSleepMillis() {
		return 1000;
	}

	@Override
	public int getProcessPeriod() {
		return 5000;
	}

}