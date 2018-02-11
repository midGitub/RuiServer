package com.game.framework.timer;

import com.game.framework.timer.domain.CronAndEventName;
import com.game.framework.timer.domain.CronAndEventNameParser;
import com.game.framework.timer.domain.ITimeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 定时任务入口 (quartz)
 * @author liguorui
 *
 */
@Component
public class TimerSchedulerHandler {

	public static TimerSchedulerHandler instance;

	private TimerSchedulerHandler() {
	}

	public static TimerSchedulerHandler getInstance() {
		if (instance == null) {
			instance = new TimerSchedulerHandler();
		}
		return instance;
	}
	
	/**
	 * @param context
	 * @throws IOException
	 *  找到event.txt文件里面的定时任务注册定时器操作
	 */
	public void taskStart(ApplicationContext context) {
		List<CronAndEventName> cronAndEventNameList = CronAndEventNameParser.loadFromEventFile(TimerSchedulerHandler.class.getClassLoader()
																											.getResourceAsStream("game/event.txt"));
		Map<String, ITimeEvent> eventNameToProcesser = filter(context);
		SchedulerService.getInstance().startTimer(cronAndEventNameList, eventNameToProcesser);
	}
	
	/**
	 * @param context
	 * @return
	 * 找到实现定时器类ITimeEvent的子类
	 */
	private static Map<String, ITimeEvent> filter(ApplicationContext context) {
		return context.getBeansOfType(ITimeEvent.class);
	}
}
