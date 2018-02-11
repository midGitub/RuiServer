package com.game.framework.network.packet;

import com.google.protobuf.ByteString;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

/**
 * 数据包抽象
 * @author liguorui
 * @date 2016-01-06
 */
public abstract class AbstractPacket {

	private int commandId;

	private ByteString protobufBytes;

	public abstract ByteString writeBytes();
	
	public ByteBuf createByteBuf(){
		return PooledByteBufAllocator.DEFAULT.buffer();
	}
	
	public ByteBuf createByteBuf(int capacity){
		return PooledByteBufAllocator.DEFAULT.buffer(capacity);
	}
	
	public ByteBuf createByteBuf(byte[] buff){
		if(buff==null || buff.length==0){
			return createByteBuf(0);
		}
		ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer(buff.length);
		buffer.writeBytes(buff);
		return buffer;
	}

	public int getCommandId() {
		return commandId;
	}

	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}

	public ByteString getProtobufBytes() {
		return protobufBytes;
	}

	public void setProtobufBytes(ByteString protobufBytes) {
		this.protobufBytes = protobufBytes;
	}
}