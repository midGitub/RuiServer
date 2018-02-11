package com.game.framework.threads.policy;


import com.game.framework.threads.Sprite;
import com.game.framework.threads.inter.IRequestToGod;

/**
 * 移除队头的请求并把最新的请求加入队尾
 * 
 * @author liguorui
 * @email 
 * @date 2016-01-06
 */
public class PollBeforeAddPolicy implements IRequestOverflowPolicy {

	@Override
	public void handle(Sprite universalSpokesmanOfGod, IRequestToGod request) {
		universalSpokesmanOfGod.poll();
		universalSpokesmanOfGod.add(request);
	}

}
