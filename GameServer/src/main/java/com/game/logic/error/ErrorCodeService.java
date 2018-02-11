package com.game.logic.error;

import com.game.framework.network.packet.PacketFactory;
import com.game.logic.base.GameSession;
import com.game.logic.error.packet.RespErrorCodePacket;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 错误提示service
 * @author liguorui
 * @date 2014年7月2日 下午8:48:12
 */
public class ErrorCodeService {

	private final static Logger LOGGER	= LoggerFactory.getLogger(ErrorCodeService.class);

	/**
	 * 
	 * @param session
	 * @param commandId
	 *            这个错误码是来自哪个请求包的
	 * @param errorCode
	 *            错误码
	 */
	public static void send(GameSession session,
							int commandId,
							int errorCode,
							Object... args) {
		RespErrorCodePacket packet = PacketFactory.getInstance()
													.createPacket(RespErrorCodePacket.class);
		packet.init(commandId, errorCode, args);
		session.writeAndFlush(packet);
		LOGGER.error(	"Send error code [{}] to command [{}], args : [{}]",
						errorCode,
						commandId,
						args);
	}

	/**
	 * 
	 * @param channel
	 * @param commandId
	 * @param errorCode
	 */
	public static void send(Channel channel,
							int commandId,
							int errorCode,
							Object... args) {
		RespErrorCodePacket packet = PacketFactory.getInstance()
													.createPacket(RespErrorCodePacket.class);
		packet.init(commandId, errorCode, args);
		channel.writeAndFlush(packet);
		LOGGER.error(	"Send error code [{}] to command [{}]",
						errorCode,
						commandId);
	}
}