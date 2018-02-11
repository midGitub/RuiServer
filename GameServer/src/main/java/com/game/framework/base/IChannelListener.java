package com.game.framework.base;

import io.netty.channel.Channel;

/**
 * 通讯挂掉监听器
 * @author liguorui
 * @date 2016-01-06
 */
//@Listener
public interface IChannelListener {

	void onClose(Channel channel);

}
