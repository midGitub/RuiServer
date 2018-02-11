package com.game.framework.network.netty.sanqi;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  处理未进入场景前的所有业务逻辑，执行线程为Netty的IO线程，所有不能有重的业务逻辑在此处理
 * @author liguorui
 * @date 2018/1/14 15:15
 */
@ChannelHandler.Sharable
public class GameServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(GameServerHandler.class);

    private static final Logger socketLog = LoggerFactory.getLogger("socketLog");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        if (!ChannelUtils.addChannelSession(ctx.channel()), new GameSession(ctx.channel())) {
//            ctx.channel().close();
//            log.error("Duplicate session! Ip:{}", ChannelUtils.getIp(ctx.channel()));
//        }
//        log.info("channelActive {]", ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        clearChannel(ctx.channel());
//        log.info("ChannelInactive {} Account: {}", ctx.channel(), ChannelUtils.getAccount(ctx.channel()));;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        final Abstractpacket packet = (Abstracct)msg;
//        log.debug("Receive {}, {}, {}", packet.getCommand(), packet.getClass().getSimpleName(), ctx.channel());;
//
//        final GameSession session = ChannelUtils.getChannelSession(ctx.channel());
//        if (session == null) {
//            return;
//        }
//
//        Contex.getPacketHandlerService().handle(session, packet);
    }

    private void clearChannel(Channel channel) {
//        final GameSession session = ChannelUtils.getChannelSession(channel);
//        if (session == null) {
//            return;
//        }
//        Context.getOnlineService().sessionOnClose(session);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        GameSession session = ChannelUtils.getChannelSession(ctx.channel());
//        String msg = String.format("exceptionCauht! IP:%s status:%s",
//                ChannelUtils.getIp(ctx.channel(), session == null ? "" : session.getAccount()),
//                session == null ? "unknow" : session.getStatus());
//        socketLog.error(msg, cause);
//        Channel channel = ctx.channel();
//        if (channel.isOpen() || channel.isActive()) {
//            channel.close();
//        }
    }
}
