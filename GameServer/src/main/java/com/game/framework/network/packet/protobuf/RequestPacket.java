package com.game.framework.network.packet.protobuf;

import com.game.framework.network.packet.AbstractPacket;
import com.google.protobuf.ByteString;
import io.netty.channel.Channel;

/**
 * 客户端请求的包
 * 
 * @author liguorui
 * @date 2016-01-06
 */
public abstract class RequestPacket extends AbstractPacket {

	public abstract void execute(Channel channel);

	public ByteString writeBytes() {
		return getProtobufBytes();
	}
}
