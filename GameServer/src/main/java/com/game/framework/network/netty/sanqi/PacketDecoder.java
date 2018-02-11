package com.game.framework.network.netty.sanqi;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.omg.CORBA.Request;

import java.util.List;

/**
 * @author liguorui
 * @date 2018/1/14 15:11
 */
public class PacketDecoder extends MessageToMessageDecoder<Request>{

    @Override
    protected void decode(ChannelHandlerContext ctx, Request msg, List<Object> out) throws Exception {
//        final GameSession session = ChannelUtils.getChannelSession(ctx.channel());
//        final AbstractPacket packet = PacketFactory.createPacket(msg, session);
//        out.add(packet);
    }
}
