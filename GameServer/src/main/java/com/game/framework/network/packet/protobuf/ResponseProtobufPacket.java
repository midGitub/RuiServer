package com.game.framework.network.packet.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import io.netty.buffer.ByteBuf;

/**
 * protobuf实现类
 * @author liguorui
 * @date 2016-01-06
 */
public abstract class ResponseProtobufPacket extends ResponsePacket {

	@Override
	public ByteBuf write() {
		AbstractMessageLite message = writeObject();
		System.out.println("ResponseProtobufPacket.message="+message);
		if (message == null) {
			return createByteBuf(0);
		}
		byte[] byteArray = message.toByteArray();
		return createByteBuf(byteArray);
	}

	@Override
	public ByteString writeBytes() {
		AbstractMessageLite message = writeObject();
		if (message == null) {
			return null;
		}
		return message.toByteString();
	}

	
	public abstract AbstractMessageLite writeObject(); //builder.build();
}
