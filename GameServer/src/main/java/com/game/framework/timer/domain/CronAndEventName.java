package com.game.framework.timer.domain;

/**
 * @author liguorui
 * @date 2016年01月8日 下午5:25:40
 */
public class CronAndEventName {

	private String	cron;
	private String	event;

	public CronAndEventName(String cron, String event) {
		super();
		this.cron = cron;
		this.event = event;
	}

	public String getCron() {
		return cron;
	}

	public String getEvent() {
		return event;
	}

	@Override
	public String toString() {
		return "CornAndEventName [cron=" + cron + ", event=" + event + "]";
	}
}
