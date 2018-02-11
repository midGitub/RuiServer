package com.game.framework.timer;

import com.game.framework.timer.domain.ITimeEvent;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author liguorui
 * @date 2015年10月8日 下午8:03:05
 */
public class GameTimeJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ITimeEvent event = (ITimeEvent) context.getMergedJobDataMap().get("event");
		System.out.println("GameTimeJob.event="+event);
		if (event != null) {
			event.executeEvent();
		}
	}
}