package com.game.framework.network.packet;

import com.game.framework.network.packet.protobuf.ICacheablePacket;
import com.game.framework.network.packet.protobuf.ResponsePacket;
import com.google.protobuf.ByteString;
import io.netty.buffer.ByteBuf;

/**
 * 缓存ResponsePacket类,用于广播
 * @author liguorui
 * @date 2016-01-05
 */
public class ResponsePacketCacheWriteData extends ResponsePacket implements ICacheablePacket {

	private byte[]	array;

	public ResponsePacketCacheWriteData(ResponsePacket msg, byte[] headerBytes) {
		ByteBuf out = createByteBuf();
		out.writeBytes(headerBytes);
		ByteBuf write = msg.write();
		if (write == null) {
			out.writeShort(2);
			out.writeShort(msg.getCommandId());
		} else {
			out.writeShort(write.readableBytes() + 2);
			out.writeShort(msg.getCommandId());
			out.writeBytes(write);
		}
		array = new byte[out.readableBytes()];
		out.getBytes(0, array);
	}

	@Override
	public ByteBuf write() {
		return null;
	}

	@Override
	public ByteString writeBytes() {
		return ByteString.copyFrom(array);
	}

	@Override
	public byte[] getCacheData() {
		return array;
	}

}
