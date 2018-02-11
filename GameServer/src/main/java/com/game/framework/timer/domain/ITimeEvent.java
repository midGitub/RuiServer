package com.game.framework.timer.domain;


/**
 * @author liguorui
 * @date 2015年10月8日 下午5:52:53
 */
public interface ITimeEvent {

	public String getEventName();

	public void executeEvent();
}