package com.game.logic.timer;

import com.game.framework.timer.domain.ITimeEvent;
import org.springframework.stereotype.Component;

/**
 * 定时器 每天5点重置玩家信息
 * @author liguorui
 *
 */
@Component
public class FiveProcessEvent implements ITimeEvent {

	@Override
	public String getEventName() {
		return "fiveProcess";
	}

	@Override
	public void executeEvent() {
		System.out.println("FiveProcessEvent.executeEvent");
	}

}
