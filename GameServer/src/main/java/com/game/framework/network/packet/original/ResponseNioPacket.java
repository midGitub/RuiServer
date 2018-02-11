package com.game.framework.network.packet.original;

import com.game.framework.network.packet.protobuf.ResponsePacket;
import io.netty.buffer.ByteBuf;

/**
 * NIO实现类
 * @author liguorui
 * @date 2016-01-06
 *
 */
public abstract class ResponseNioPacket extends ResponsePacket {

	@Override
	public ByteBuf write() {
		Response response = ResponseFactory.getInstance().createResponse(getCommandId());
		doResponse(response);
		return response.getByteBuf();
	}
	
	public abstract void doResponse(Response response); 
}
