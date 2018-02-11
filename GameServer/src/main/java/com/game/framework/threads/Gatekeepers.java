package com.game.framework.threads;

import com.game.framework.threads.policy.IRequestOverflowPolicy;

/**
 * 门卫，想进出游戏得通过我吧
 * 
 * @author liguorui
 * @date 2016-01-06
 */
public class Gatekeepers extends Sprite {

	private static int	count	= 0;
	private int			id		= ++count;

	@Override
	public String getName() {
		return "Gatekeepers#" + id;
	}

	@Override
	public IRequestOverflowPolicy getOverflowPolicy() {
		return null;
	}

	@Override
	public int getMaxRequestNum() {
		return -1;
	}

	@Override
	public int getMinSleepMillis() {
		return 10;
	}

	@Override
	public int getProcessPeriod() {
		return -1;
	}

	@Override
	public int getProcessNumPerPeriod() {
		return -1;
	}
}
