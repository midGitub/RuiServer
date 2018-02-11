package com.game.framework.network.netty.sanqi;//package com.game.framework.net.sanqi;
//
//import com.game.logic.base.domain.GameSession;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandler;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.MessageToByteEncoder;
//import org.apache.commons.lang3.ArrayUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author liguorui
// * @date 2018/1/14 15:32
// */
//@ChannelHandler.Sharable
//public class ResponseEncoder extends MessageToByteEncoder<ByteBufResposne> {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseEncoder.class);
//
//    static final int[] excludeIds = new int[] {
//            PacketId.Fight.RESP_FIGHT_INFO,
//            packetId.Fight.RESP_BOSS_HP
//    };
//
//    static final int[] includeIds = new int[] {
//
//    };
//
//    private boolean EXCLUDE = true;
//
//    @Override
//    protected void encode(ChannelHandlerContext ctx, ByteBufResposne resposne, ByteBuf out) throws Exception {
//        ByteBuf byteBuf = resposne.getBytebuf();
//        short command = resposne.getPacketId();
//        /**
//         * 排除某些不想看到的packet
//         */
//        if(LOGGER.isDebugEnabled()) {
//            if (!EXCLUDE || !ArrayUtils.isIn(command, excludeIds)) {
//                GameSession session = ChannelUtils.getChannelSession(ctx.channel());
//                Class<?> packetClass =PacketFactory.getPacketClassByCOmmondId(command);
//                String commandStr = String.valueOf((packetClass != null ? packetClass.getSimpleName() : command));
//                LOGGER.debug("send {}, {}, size: {}, {}, {} ", command, session.getUid(),
//                        byteBuf.readableBytes(), commandStr, ctx.channel());
//            }
//
//            out.writeInt(byteBuf.readableBytes() + 4);
//            out.writeInt(Integer.MAX_VALUE);
//            out.writeBytes(byteBuf, 0 ,byteBuf.readableBytes());
//        }
//    }
//}
//
//
//
