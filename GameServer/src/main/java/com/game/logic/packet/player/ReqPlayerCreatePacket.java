package com.game.logic.packet.player;

import com.game.framework.network.packet.annotation.Packet;
import com.game.framework.network.packet.protobuf.RequestPacket;
import com.game.framework.network.session.SessionService;
import com.game.logic.packet.PacketId;
import io.netty.channel.Channel;

/**
 * 玩家创建角色
 * @author liguorui
 * @date 2017年6月27日 下午4:56:15
 */
@Packet(commandId = PacketId.REQ_PLAYER_CREATE)
public class ReqPlayerCreatePacket extends RequestPacket {

	private String nickName;

	@Override
	public void execute(Channel channel) {
		String account = SessionService.getInstance().getAccount(channel);
		try {
//			createPlayer = PlayerService.getInstance().createPlayer(account, nickName);
//			SessionService.getInstance().register(createPlayer.getId(), createPlayer.getAccount(), channel);
//			ListenerManager.getInstance().operateCreatePlayer(createPlayer);
////			RespPlayerInfoPacket packet = PacketFactory.getInstance().createPacket(RespPlayerInfoPacket.class);
////			packet.init(createPlayer);
////			channel.writeAndFlush(packet);
//
//			GameSession session = SessionService.getInstance().getSession(channel);
//			createPlayer.setSession(session);
//			session.toAfterLogin();
//			SceneService.addTickerToScene(createPlayer);
//			ReqPlayerModuleDataPacket.sendModuleDate(session, createPlayer.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}