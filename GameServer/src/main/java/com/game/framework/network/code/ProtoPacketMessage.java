package com.game.framework.network.code;

import com.google.protobuf.ByteString;

/**
 * protobuf编码的消息包 （协议号+包内容）
 * @author liguorui
 * @date 2017/8/9 11:40
 */
public class ProtoPacketMessage {

    private short packetId;

    private ByteString content;

    public short getPacketId() {
        return packetId;
    }

    public ProtoPacketMessage(short packetId, ByteString content) {
        this.packetId = packetId;
        this.content = content;
    }

    public static ProtoPacketMessage create(short packetId, ByteString content) {
        return new ProtoPacketMessage(packetId, content);
    }

    public void setPacketId(short packetId) {
        this.packetId = packetId;
    }

    public ByteString getContent() {
        return content;
    }

    public void setContent(ByteString content) {
        this.content = content;
    }
}
