package com.game.framework.network.code;

import com.game.framework.network.packet.protobuf.ICacheablePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 游戏包编码(缓存)
 * @author liguorui
 * @date 2016-01-06
 */
public class GamePacketEncoderForResponseCacheWriteDataPacket extends MessageToByteEncoder<ICacheablePacket> {

	public GamePacketEncoderForResponseCacheWriteDataPacket() {
	}

	@Override
	protected void encode(	ChannelHandlerContext ctx,
							  ICacheablePacket msg,
							  ByteBuf out) throws Exception {
		byte[] array = msg.getCacheData();
		out.writeBytes(array);
	}

}
