package com.game.logic.timer;

import com.game.framework.timer.domain.ITimeEvent;
import org.springframework.stereotype.Component;

/**
 * 每天0点定时器
 * @author liguorui
 * @date 2014年12月20日 下午8:00:59
 */
@Component
public class ZeroProcessEvent implements ITimeEvent {

	@Override
	public String getEventName() {
		return "zeroProcess";
	}

	@Override
	public void executeEvent() {
		System.out.println("ZeroProcessEvent.executeEvent");
	}

}
