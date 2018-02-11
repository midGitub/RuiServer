package com.game.framework.threads.policy;


import com.game.framework.threads.Sprite;
import com.game.framework.threads.inter.IRequestToGod;

/**
 * 直接在当前调用线程中执行
 * 
 * @author liguorui
 * @email 
 * @date 2016-01-06
 */
public class DirectRunPolicy implements IRequestOverflowPolicy {

	@Override
	public void handle(Sprite universalSpokesmanOfGod, IRequestToGod request) {
		request.execute();
	}

}