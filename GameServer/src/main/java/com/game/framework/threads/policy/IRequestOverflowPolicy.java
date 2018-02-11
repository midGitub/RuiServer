package com.game.framework.threads.policy;

import com.game.framework.threads.Sprite;
import com.game.framework.threads.inter.IRequestToGod;

/**
 * 请求溢出处理策略
 * 
 * @author liguorui
 * @email 
 * @date 2016-01-06
 */
public interface IRequestOverflowPolicy {

	void handle(Sprite universalSpokesmanOfGod, IRequestToGod request);

}