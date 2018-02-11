package com.game.framework.network.test.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端读包类
 * @author liguorui
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

	private final static Logger logger = LoggerFactory.getLogger(ServerHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("连接上服务端了！");
		logger.info("连接上服务端了！:{}", ctx.channel());
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("读取客户端返回的消息..");
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务端Receive size:"+body.length()+",内容："+body+",类名:"+new ServerHandler().getClass().getSimpleName()+",ctx.channel()="+ctx.channel());
        
        System.out.println("返回数据到客户端");
        byte[] resp = "测试服务端返回数据到客户端".getBytes();
		ByteBuf messageBuf = Unpooled.buffer(resp.length);
		messageBuf.writeBytes(resp);
		ctx.writeAndFlush(messageBuf);
	}
	
	/**
	 * 退出连接 清除session
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("服务端退出了！:{}", ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
