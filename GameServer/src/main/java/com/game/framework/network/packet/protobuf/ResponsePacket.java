package com.game.framework.network.packet.protobuf;

import com.game.framework.network.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;

/**
 * 服务端返回的包
 * 
 * @author liguorui
 * @date 2016-01-06
 */
public abstract class ResponsePacket extends AbstractPacket {

	public abstract ByteBuf write();

}
