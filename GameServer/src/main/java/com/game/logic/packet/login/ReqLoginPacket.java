package com.game.logic.packet.login;

import com.game.framework.network.code.websocket.ProtobufHandler;
import com.game.framework.network.packet.annotation.Packet;
import com.game.framework.network.packet.protobuf.RequestPacket;
import com.game.logic.base.GameSession;
import com.game.framework.network.session.SessionService;
import com.game.logic.packet.PacketId;
import io.netty.channel.Channel;

/**
 * 玩家登陆
 * @author liguorui
 * @date 2016年6月27日 上午11:53:23
 */
@Packet(commandId = PacketId.REQ_LOGIN)
public class ReqLoginPacket extends RequestPacket {

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 密码验证
	 */
	private String token;

	private int num;

	private int size;

	private int aa;

	@Override
	public void execute(Channel channel) {
		RespLoginPacket createPacket = null;
		boolean loginSucc = false;

		if (SessionService.getInstance().containAccount(account)) {
			GameSession oldSession = SessionService.getInstance().getByAccount(account);
			if (oldSession != null) {
//				ErrorCodeService.send(	oldSession,0, IErrorCode.YOU_LOGIN_IN_SOMEWHERE);
			}
			SessionService.getInstance().clear(account);
		}

		SessionService.getInstance().bind(channel, account);

//		if (PlayerService.validateToken(token, account)) {
//			player = PlayerService.getInstance().getPlayerOnLogin(account);
//			if (player == null) {
//				createPacket = RespLoginPacket.ERROR_NO_CREATE;
//			} else {
//				createPacket = RespLoginPacket.SUCC;
//				loginSucc = true;
//			}
//		} else {
//			createPacket = RespLoginPacket.ERROR_TOKEN;
//		}

		if (loginSucc) {
//			SessionService.getInstance().register(player.getId(), player.getAccount(), channel);

//			RespPlayerInfoPacket packet = PacketFactory.getInstance().createPacket(RespPlayerInfoPacket.class);
//			packet.init(p);
//			channel.write(packet);

			// 触发登录事件
//			ListenerManager.getInstance().afterPlayerOnLogin(player);
//			GameSession session = SessionService.getInstance().getSession(channel);
//			session.toAfterLogin();
//			player.setSession(session);
//			SceneService.addTickerToScene(player);
		}
		createPacket = RespLoginPacket.SUCC;
		ProtobufHandler.sendProtobufPacket(channel,createPacket);
	}
}
