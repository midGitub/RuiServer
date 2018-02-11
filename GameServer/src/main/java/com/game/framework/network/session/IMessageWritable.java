package com.game.framework.network.session;


import com.game.framework.network.packet.protobuf.ResponsePacket;

/**
 * @author liguorui
 * @date 2016-01-06
 */
public interface IMessageWritable {

	public void write(ResponsePacket packet);

	public void writeAndFlush(ResponsePacket packet);
}
