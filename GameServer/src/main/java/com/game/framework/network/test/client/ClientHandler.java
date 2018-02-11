package com.game.framework.network.test.client;

import com.game.framework.network.code.ProtoPacketMessage;
import com.game.logic.packet.PacketId;
import com.game.proto.login.LoginReqBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端读包类
 * @author liguorui
 *
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

	private final static Logger logger = LoggerFactory.getLogger(ClientHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("测试连接上客户端！:{}", ctx.channel());
		send(ctx.channel());
	}

	private void send(Channel channel) {
		try {
			LoginReqBuilder.LoginReq.Builder builder = LoginReqBuilder.LoginReq.newBuilder();
			builder.setAccount("李国锐测试");
			builder.setToken("liguoruiceshi");
			builder.setType(987);
			System.out.println("builder.build()="+builder.build()+"builder.build().toByteString()="+builder.build().toByteString());
			ProtoPacketMessage protoPacketMessage = ProtoPacketMessage.create((short) PacketId.REQ_CESHI, builder.build().toByteString());
			System.out.println("protoPacketMessage="+protoPacketMessage);
//			AbstractPacket createPacket = new AbstractPacket() {
//				@Override
//				public ByteString writeBytes() {
//					return builder.build().toByteString();
//				}
//			};
//			createPacket.setCommandId(PacketId.REQ_CESHI);
//			createPacket.setProtobufBytes(protoPacketMessage.getContent());
//			ProtobufHandler.sendProtobufPacket(channel, createPacket);
//			System.out.println("send.createPacket="+createPacket);
		} catch (Exception e) {
			e.printStackTrace();
			channel.close();
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("ClientHandler.channelRead.msg=="+msg);
		if (msg instanceof HttpResponse)
		{
			HttpResponse response = (HttpResponse) msg;
			System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaderNames.CONTENT_TYPE));
		}
		if(msg instanceof HttpContent)
		{
			HttpContent content = (HttpContent)msg;
			ByteBuf buf = content.content();
			System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));
			buf.release();
		}


	}
	
	/**
	 * 退出连接 清除session
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("客户端退出了！:{}", ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
	
}
