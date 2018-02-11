package com.game.logic.packet.player;

import com.game.framework.network.packet.annotation.Packet;
import com.game.framework.network.packet.protobuf.ResponseProtobufPacket;
import com.game.logic.packet.PacketId;
import com.game.proto.login.LoginStateBuilder;
import com.google.protobuf.AbstractMessageLite;

/**
 * @author liguorui
 * @date 2017/8/10 23:40
 */
@Packet(commandId = PacketId.RESP_PRO_CESHI)
public class RespProtoCeshiPacket extends ResponseProtobufPacket {

    LoginStateBuilder.LoginState.Builder builder = LoginStateBuilder.LoginState.newBuilder();

    public void init(int result, String data) {
        builder.setResult(result);
        builder.setData(data);
    }

    @Override
    public AbstractMessageLite writeObject() {
        return builder.build();
    }
}
