package com.game.framework.timer;

import com.game.framework.timer.domain.CronAndEventName;
import com.game.framework.timer.domain.ITimeEvent;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author liguorui
 * @date 2016年10月8日 下午5:01:24
 * 任务定时器类
 */
public class SchedulerService {

	private final static Logger LOGGER	= LoggerFactory.getLogger(SchedulerService.class);

	public static SchedulerService instance;

	private SchedulerService() {
	}

	public static SchedulerService getInstance() {
		if (instance == null) {
			instance = new SchedulerService();
		}
		return instance;
	}

	/**
	 * 
	 * @param cronAndEventNameList 定时器文件event.txt里面的各种时间配置
	 * @param eventNameToProcesser 实现定时器的子类map
	 * @throws SchedulerException
	 * @throws IOException
	 */
	public void startTimer(List<CronAndEventName> cronAndEventNameList, Map<String, ITimeEvent> eventNameToProcesser){
		try {
			Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
			for (CronAndEventName c : cronAndEventNameList) {
				ITimeEvent event = findEvent(c, eventNameToProcesser);
				if (event == null) continue;
				JobDataMap newJobDataMap = new JobDataMap();
				newJobDataMap.put("event", event);
				//添加定时任务
				JobDetail job = JobBuilder.newJob(GameTimeJob.class).setJobData(newJobDataMap).build();
				Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(c.getCron())).build();
				defaultScheduler.scheduleJob(job, trigger);
				LOGGER.info("Schedule Job : " + event.getClass().getSimpleName() + " at " + c.getCron());
			}
			defaultScheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param c
	 * @param beansOfType
	 * @return 根据event.txt里面配置的名字找到定时器子类(名字相同)
	 */
	private ITimeEvent findEvent(CronAndEventName c, Map<String, ITimeEvent> beansOfType) {
		for (ITimeEvent evt : beansOfType.values()) {
			if (evt.getEventName().trim().equals(c.getEvent())) {
				return evt;
			}
		}
		return null;
	}
}
