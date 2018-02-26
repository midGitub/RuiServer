package com.game.framework.network.code.websocket;

import com.game.framework.network.code.ProtoPacketMessage;
import com.game.framework.network.packet.AbstractPacket;
import com.game.framework.network.packet.PacketFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 客户端包转成protobuf数据
 * @author liguorui
 * @date 2017/8/7 14:16
 */
public class WebGamePacketDecoder extends MessageToMessageDecoder<BinaryWebSocketFrame> {

    private static final Logger socketLog = LoggerFactory.getLogger("SocketLogger");

    @Override
    protected void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame msg, List<Object> out) throws Exception {
        ProtoPacketMessage packetMessage = ProtobufHandler.frameToProtoPacketMessage(msg);
        if (packetMessage == null) {
            socketLog.warn("WebGamePacketDecoder.decode.packetMessage is null msg="+msg);
            return;
        }
        AbstractPacket abstractPacket = PacketFactory.getInstance().createPacket(packetMessage);
        if (abstractPacket != null) {
            out.add(abstractPacket);
        }
    }

}
