package com.game.framework.network.netty;

import com.game.framework.network.packet.AbstractPacket;
import com.game.framework.network.session.SessionService;
import com.game.logic.base.GameSession;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读包处理
 * @author liguorui
 * @date 2016-01-06
 */
public class GamePacketHandler extends ChannelInboundHandlerAdapter {

	private static final Logger socketLog = LoggerFactory.getLogger("socketLog");

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.err.println("GamePacketHandler.channelRead.msg=======" + msg);
		GameSession gameSession = SessionService.getInstance().getSession(ctx.channel());
		if (gameSession == null) return;

		AbstractPacket createPacket = (AbstractPacket) msg;
		if (createPacket == null) return;

		if (socketLog.isDebugEnabled()) {
			socketLog.debug("Req Receive packet:{},class:{} playerId:{},bytes:{},size:{},channel:{}", createPacket.getCommandId(),createPacket.getClass().getSimpleName()
				   ,gameSession.getPlayerId() ,createPacket.getProtobufBytes(), createPacket.getProtobufBytes().size(), ctx.channel());
		}

		long srartTime = System.currentTimeMillis();

		GamePacketProcesser.process(createPacket, ctx.channel());

		long costTime = System.currentTimeMillis() - srartTime;

		if (costTime > 1000) {
			socketLog.debug("Req Receive cost time packet:{},class:{},costtime:{},playerId:{},bytes:{},size:{},channel:{}",
					createPacket.getCommandId(), createPacket.getClass().getSimpleName()
					, costTime, gameSession.getPlayerId(), createPacket.getProtobufBytes(), createPacket.getProtobufBytes().size(), ctx.channel());
		}
	}

	/**
	 * 退出连接 清除session
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.err.println("channelInactive server success ctx="+ctx.channel());
//		ListenerManager.getInstance().operateWith(IChannelListener.class, CLOSE_OPERATOR, ctx.channel());
		clearChannel(ctx.channel());
		//退出游戏世界场景
	}

	/**
	 * 建立连接后注册session(由channel创建，以后的读写操作就在这个channel里面，代表每一个连接的一个载体，每个连接channel都不一样)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.err.println("channelActive server  success ctx="+ctx.channel());
		if (SessionService.getInstance().register(ctx.channel(), new GameSession(ctx.channel()))) {
			socketLog.info("Connected client channel is " + ctx.channel());
			System.err.println("channelActive server  SessionService.getInstance().register="+ctx.channel());
		} else {
			ctx.channel().close();
			System.err.println("channelActive server  ctx.channel().close()="+ctx.channel());
			socketLog.error("Connected client exist! channel:" + ctx.channel());
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		GameSession gameSession = SessionService.getInstance().getSession(ctx.channel());
		Channel channel = ctx.channel();
		if (gameSession == null) {
			socketLog.error("exceptionCaught:"+cause);
		} else {
			socketLog.info("ip="+SessionService.getInstance().getIp(channel));
		}

		if (channel.isOpen() || channel.isActive()) {
			channel.close();
		}
	}

	private void clearChannel(Channel channel) {
		GameSession gameSession = SessionService.getInstance().getSession(channel);
		if (gameSession !=null) {
			SessionService.getInstance().clearChannel(channel);
		}
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("Client:"+incoming.remoteAddress() +"加入");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("Client:"+incoming.remoteAddress() +"离开");
	}
}