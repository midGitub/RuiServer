package com.game.framework.network.netty;

import com.game.framework.network.packet.AbstractPacket;
import com.game.framework.network.packet.protobuf.RequestPacket;
import com.game.framework.network.session.SessionService;
import com.game.logic.base.GameSession;
import io.netty.channel.Channel;

/**
 * 守护者（处理读到的包交给业务逻辑）
 * @author liguorui
 * @date 2017-01-06
 */
public class GamePacketProcesser {

	private static final int threadNum = Runtime.getRuntime().availableProcessors() * 2; //CPU核数的两倍

	private final static short[] validLoginPacket = new short[] {
			//登陆包
	};

	/**
	 * 处理消息请求
	 * @param packet
	 * @param channel
	 */
	public static void process(final AbstractPacket packet, final Channel channel) {
		GameSession session = SessionService.getInstance().getSession(channel);
		//        if (createPacket) { //如果是跨服包直接进入游戏,包id在1000-2000
//          processByGateKeeper(packet,channel,session);
//          return;
//        }
//
//        if (packetid < 1000) { //如果是GM包直接验证执行，否则关闭连接
//          processByGateKeeper(packet,channel,session);
//          return;
//        }

		RequestPacket requestPacket = (RequestPacket)packet;
		processByGateKeeper(requestPacket,channel,session);
		//无需登陆后操作的包，直接请求session执行
//		if (ArrayUtils.contains(validLoginPacket,(short)packet.getCommandId())) {
//			processByGateKeeper(requestPacket,channel,session);
//			return;
//		}
//
//		if (session.isAfterLogin()) {
//			processPlayerMessage(requestPacket,session);
//		} else {
//			processByGateKeeper(requestPacket,channel,session);
//		}
	}

	/**
	 * 玩家登陆后的处理，交给玩家包队列处理
	 * @param packet
	 * @param session
	 */
	public static void processPlayerMessage(RequestPacket packet, GameSession session) {
//		IPlayer player = PlayerManager.getInstance().getPlayerFromCacheById(session.getUid());
//		if (player != null) {
//			player.addMessage(packet);
//		}
	}

	/**
	 * 处理未进入场景前的所有业务逻辑，执行线程为netty io线程
	 * @param packet
	 * @param channel
	 * @param session
	 */
	public static void processByGateKeeper(final RequestPacket packet, final Channel channel, GameSession session) {
	}

}
