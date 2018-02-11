package com.game.framework.network.code;

import com.game.framework.network.packet.protobuf.ResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 游戏包编码(resp)
 * @author liguorui
 * @date 2016-01-06
 */
public class GamePacketEncoder extends MessageToByteEncoder<ResponsePacket> {

	private final static Logger LOGGER	= LoggerFactory.getLogger(GamePacketEncoder.class);

	private byte[]				headerBytes;

	public GamePacketEncoder(byte[] headerBytes) {
		if (headerBytes == null) {
			throw new IllegalArgumentException("Please offer a header.");
		}
		this.headerBytes = headerBytes;
	}

	protected GamePacketEncoder(Class<? extends ResponsePacket> outboundMessageType) {
		super(outboundMessageType);
	}

	@Override
	protected void encode(	ChannelHandlerContext ctx,
							ResponsePacket msg,
							ByteBuf out) throws Exception {
		System.err.println("GamePacketEncoder.encode="+out);
		ByteBuf write = msg.write();
		int packetId = msg.getCommandId();
		out.writeBytes(headerBytes);
		if (packetId > 100000) {
			if (write == null) {
				out.writeShort(2); //空包长度
				out.writeShort(packetId);
				out.writeBytes(write);
			} else {
				out.writeShort(write.readableBytes() + 2); //包长度+2
				out.writeShort(packetId);
				out.writeBytes(write); //out.writeBytes(write, 0, write.readableBytes());
			}
		} else { //固定返回 0：1状态的包 只有一个参数
			System.out.println("encode.write.readableBytes()="+write.readableBytes());
			out.writeBytes(write);
		}
//		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Resp Send packet {},{},size:{},channel:{}", msg.getCommandId(),msg.getClass().getSimpleName(),out.readableBytes(),ctx.channel());
//		}
	}
}