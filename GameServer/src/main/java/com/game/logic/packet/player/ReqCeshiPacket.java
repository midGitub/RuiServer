package com.game.logic.packet.player;

import com.game.framework.network.code.websocket.ProtobufHandler;
import com.game.framework.network.packet.PacketFactory;
import com.game.framework.network.packet.annotation.Packet;
import com.game.framework.network.packet.protobuf.RequestPacket;
import com.game.logic.base.IdGeneratorService;
import com.game.logic.packet.PacketId;
import com.game.proto.login.LoginReqBuilder;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author liguorui
 * @date 2017/8/9 15:01
 */
@Packet(commandId = PacketId.REQ_CESHI)
@Component
public class ReqCeshiPacket extends RequestPacket {

    @Override
    public void execute(Channel channel) {
        try {
//            ceshi();
//            Thread.sleep(15000);
//            ceshi2();
//            Thread.sleep(15000);
//            ceshi1();
//            Thread.sleep(15000);
//            ceshi3();
//            Thread.sleep(15000);
//            ceshi4();
//            Thread.sleep(15000);
//            ceshi5();
//            Thread.sleep(15000);
//            ceshi6();
//            Thread.sleep(15000);
//            ceshi7();
//            ceshi10();
//            Thread.sleep(15000);
//            ceshi8();
//            Thread.sleep(15000);
//            ceshi9();
                ceshi11();
            LoginReqBuilder.LoginReq loginReq = LoginReqBuilder.LoginReq.parseFrom(getProtobufBytes());
            String account = loginReq.getAccount();
            String token = loginReq.getToken();
            int type = loginReq.getType();
            System.out.println("ReqLoginPacket.account="+account+",token="+token + ",type=" + type);

            RespCeshiPacket createPacket = PacketFactory.getInstance().createPacket(RespCeshiPacket.class);
            createPacket.init(1055,"helloworld你好啊!!");
            ProtobufHandler.sendProtobufPacket(channel,createPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String ceshi() {
        for (long i=2;i<1000;i++) {
//            TestDb testDb = new TestDb(i,"ceshixiugai"+i*10,"helloxigai"+i*10);
//            AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.INSERT,testDb);
        }
        return "ceshi success";
    }

    public String ceshi1() {
        for (long i=2;i<1000;i++) {
//            PlayerLogin playerLogin = new PlayerLogin(i, "playerLogin"+i*10,(int)i*100);
//            AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.INSERT,playerLogin);
//            if (i % 2 == 0) {
//                playerLogin.setAccount("playerLogin.ceshi1"+ i * 2);
//                AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.UPDATE, playerLogin);
//            } else {
//                AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.DELETE, playerLogin);
//            }
        }
        return "ceshi1 success";
    }

    public String ceshi2() {
        for (long i=2;i<1000;i++) {
//            TestDb testDb = new TestDb(i,"ceshiname2"+i*2,"ceshiaccount2"+i*2);
//            if (i % 2 == 0) {
//                AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.UPDATE, testDb);
//            } else {
//                AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.DELETE, testDb);
//            }
        }
        return "ceshi2 success";
    }

    public String ceshi3() {
        for (long i=2;i<1000;i++) {
//            PlayerLogin playerLogin = new PlayerLogin(i, "ceshi3account"+i*5,(int)i*5);
//            if (i % 2 == 0) {
//                AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.UPDATE, playerLogin);
//            } else {
//                AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.DELETE, playerLogin);
//            }
        }
        return "ceshi3 success";
    }

    public String ceshi4() {
        for (long i=2;i<1000;i++) {
//            TestDb testDb = new TestDb(i,"ceshiname2"+i*2,"ceshiaccount2"+i*2);
//            AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.DELETE,testDb);
        }
        return "ceshi4 success";
    }

    public String ceshi5() {
        for (long i=2;i<1000;i++) {
//            PlayerLogin playerLogin = new PlayerLogin(i, "ceshi3account"+i*5,(int)i*5);
//            AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.DELETE,playerLogin);
        }
        return "ceshi5 success";
    }

    public String ceshi6() {
        for (long i=1;i<1000;i++) {
            System.out.println("idGeneratorService="+ IdGeneratorService.getInstance());
//            Guild guild = new Guild(IdGeneratorService.getInstance().generateUniqueGuildId(),"guildName"+i*10,(int)i*1000);
//            AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.INSERT,guild);
        }
        return "ceshi6 success";
    }

    public String ceshi7() {
        for (long i=1;i<15;i++) {
//            PlayerEntity playerEntity = new PlayerEntity(IdGeneratorService.getInstance().generateUniquePlayeId("S1"),"playerEntity"+i*10,i+"李国锐",""+i,""+i,(int)i*10,(int)i*100);
//            GuildLogEvent guildLogEvent = new GuildLogEvent(new Player(playerEntity), i, "guildLogEvent"+i);
//            System.out.println("ceshi7.guildLogEvent="+guildLogEvent);
//            LogEventHandler.getInstance().post(guildLogEvent);
//            AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.INSERT,playerEntity);
        }
        return "ceshi7 success";
    }

    public String ceshi8() {
        for (long i=1;i<1000;i++) {
//            PlayerEntity playerEntity = new PlayerEntity(IdGeneratorService.getInstance().generateUniquePlayeId("S2000"),"playerEntity"+i*10,i+"李国锐",""+i,""+i,(int)i*10,(int)i*100);
//            AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.INSERT,playerEntity);
        }
        return "ceshi8 success";
    }

    public String ceshi9() {
        for (long i=1;i<1000;i++) {
//            PlayerEntity playerEntity = new PlayerEntity(IdGeneratorService.getInstance().generateUniquePlayeId("S1000"),"playerEntity"+i*10,i+"李国锐",""+i,""+i,(int)i*10,(int)i*100);
//            AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.INSERT,playerEntity);
        }
        return "ceshi9 success";
    }

    public String ceshi10() {
        for (long i=1;i<1000;i++) {
//            PlayerActivity playerActivity = new PlayerActivity(IdGeneratorService.getInstance().generateUniquePlayeId("S1"),"playerActivity"+i*2);
//            AsyncDBService.getInstance().addAsyncObject(AsyncOperationType.INSERT, playerActivity);
        }
        return "ceshi10 success";
    }

    public String ceshi11() {
//        PlayerEntity playerEntity = PlayerEntityHandler.getPlayerEntityHandler().getEntityOrCreate(20000010000044l);
//        System.out.println("playerEntity.id="+playerEntity.getId()+",playerEntity.name="+playerEntity.getName());
//        PlayerEntity playerEntity2 = PlayerEntityHandler.getPlayerEntityHandler().getEntityOrCreate(20000010000045l);
//        System.out.println("playerEntity2.id="+playerEntity.getId()+",playerEntity.name="+playerEntity.getName());
//        PlayerEntity playerEntity1 = PlayerEntityHandler.getPlayerEntityHandler().getEntityByCache(20000010000044l);
//        System.out.println("playerEntity1.id="+playerEntity.getId()+",playerEntity1.name="+playerEntity.getName());
        return "ceshi11 success";
    }
}
