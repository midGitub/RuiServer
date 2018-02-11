package com.game.logic.packet.player;

import com.game.framework.network.packet.annotation.Packet;
import com.game.framework.network.packet.protobuf.ResponseProtobufPacket;
import com.game.logic.packet.PacketId;
import com.game.proto.ceshi.CeshiBuilder;
import com.google.protobuf.AbstractMessageLite;

/**
 * @author liguorui
 * @date 2017/8/9 15:04
 */
@Packet(commandId = PacketId.RESP_CESHI)
public class RespCeshiPacket extends ResponseProtobufPacket {

    CeshiBuilder.Ceshi.Builder buidler = CeshiBuilder.Ceshi.newBuilder();

    public void init(int commandId, String msg) {
        buidler.setId(commandId);
        buidler.setMsg(msg);
    }

    @Override
    public AbstractMessageLite writeObject() {
        return buidler.build();
    }
}
