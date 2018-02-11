package com.game.framework.network.code.websocket;

import com.game.framework.network.code.ProtoPacketMessage;
import com.game.framework.network.packet.AbstractPacket;
import com.game.logic.base.GameSession;
import com.game.proto.packet.PacketMessageBuilder;
import com.google.protobuf.ByteString;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

/**
 * protobuf收发包handler
 * @author liguorui
 * @date 2017/8/9 12:15
 */
public class ProtobufHandler {

    /**
     * BinaryWebSocketFrame 转成共用协议包
     * @param frame
     * @return
     */
    public static ProtoPacketMessage frameToProtoPacketMessage(BinaryWebSocketFrame frame) {
        try {
            ByteBuf byteBuf = frame.content();
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            PacketMessageBuilder.PacketMessage packetMessage = PacketMessageBuilder.PacketMessage.newBuilder().mergeFrom(bytes).build();
            if (packetMessage != null) {
                return ProtoPacketMessage.create((short)packetMessage.getPacketId(),packetMessage.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 编码发包
     * @param channel
     * @param packet
     */
    public static void sendProtobufPacket(Channel channel, AbstractPacket packet) {
        ByteBuf binaryData = packet.createByteBuf();
        int commandId =packet.getCommandId();
        ByteString content = packet.writeBytes();
        System.out.println("ProtobufHandler.sendProtobufPacket.commandId="+commandId+",content="+content);

        PacketMessageBuilder.PacketMessage packetMessage = PacketMessageBuilder.PacketMessage.newBuilder().setPacketId(commandId).setContent(content).build();
        binaryData.writeBytes(packetMessage.toByteArray());

        BinaryWebSocketFrame respWebSocketFrame = new BinaryWebSocketFrame(binaryData);
        channel.writeAndFlush(respWebSocketFrame);
        System.out.println("ProtobufHandler.sendProtobufPacket.respWebSocketFrame="+respWebSocketFrame);
    }

    public static void sendProtobufPacket(GameSession gameSession, AbstractPacket packet) {
        sendProtobufPacket(gameSession.getChannel(), packet);
    }

    /**
     * 发送protobuf包，协议号加内容
     * @param channel
     * @param packet
     */
//	public void sendProtobufPacket(Channel channel,ResponsePacket packet) {
//		ByteBuf binaryData = packet.createByteBuf();
//		binaryData.writeShort(packet.getCommandId());
//		binaryData.writeBytes(packet.writeBytes());
//		BinaryWebSocketFrame respWebSocketFrame = new BinaryWebSocketFrame(binaryData);
//		channel.writeAndFlush(respWebSocketFrame);
//	}
}
