package com.game.framework.network.packet;

import com.game.framework.network.packet.protobuf.RequestPacket;
import com.game.framework.network.session.SessionService;
import com.game.logic.base.GameSession;
import com.game.logic.error.ErrorCodeService;
import com.game.logic.error.domain.IErrorCode;
import io.netty.channel.Channel;

/**
 * 登陆后的请求包
 * @author liguorui
 * @date 2017/8/6 00:12
 */
public abstract class RequestPacketAfterLogin extends RequestPacket {

	@Override
	public void execute(Channel channel) {
		GameSession session = SessionService.getInstance().getSession(channel);
		if (session == null) {
			ErrorCodeService.send(channel, 0, IErrorCode.PLAYER_YOU_HAVE_NOT_LOGIN);
			return;
		}
		execute(session);
	}

	/**
	 * @param session
	 */
	public abstract void execute(GameSession session);

}
