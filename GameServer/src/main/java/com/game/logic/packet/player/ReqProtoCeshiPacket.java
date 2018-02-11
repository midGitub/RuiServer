package com.game.logic.packet.player;

import com.game.framework.network.code.websocket.ProtobufHandler;
import com.game.framework.network.packet.PacketFactory;
import com.game.framework.network.packet.annotation.Packet;
import com.game.framework.network.packet.protobuf.RequestPacket;
import com.game.logic.packet.PacketId;
import com.game.proto.ceshi.ProtoCeshiBuilder;
import io.netty.channel.Channel;

/**
 * @author liguorui
 * @date 2017/8/10 23:01
 */
@Packet(commandId = PacketId.REQ_PRO_CESHI)
public class ReqProtoCeshiPacket extends RequestPacket {

    @Override
    public void execute(Channel channel) {
        try {
            ProtoCeshiBuilder.ProtoCeshi protoCeshi = ProtoCeshiBuilder.ProtoCeshi.parseFrom(getProtobufBytes());
            String name = protoCeshi.getName();
            int pid = protoCeshi.getPid();
            long lid = protoCeshi.getLid();
            boolean valid = protoCeshi.getValid();
            float score = protoCeshi.getScore();
            System.out.println("ReqProtoCeshiPacket.name="+name+",pid="+pid + ",lid=" + lid+",valid="+valid+",score="+score);

            RespProtoCeshiPacket createPacket = PacketFactory.getInstance().createPacket(RespProtoCeshiPacket.class);
            createPacket.init(3344,"{'aaa':'李国锐','dsadad':1111}");
            ProtobufHandler.sendProtobufPacket(channel,createPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
