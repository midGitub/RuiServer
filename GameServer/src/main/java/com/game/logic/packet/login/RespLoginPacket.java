package com.game.logic.packet.login;

import com.game.framework.network.packet.annotation.Packet;
import com.game.framework.network.packet.protobuf.ResponseProtobufPacket;
import com.game.logic.packet.PacketId;
import com.game.proto.login.LoginStateBuilder.LoginState;
import com.google.protobuf.AbstractMessageLite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

;

/**
 * 登陆返回
 * @author liguorui
 * @date 2016年6月27日 上午11:58:34
 */
@Packet(commandId = PacketId.RESP_LOGIN)
public class RespLoginPacket extends ResponseProtobufPacket {

	private static final Logger LOGGER = LoggerFactory.getLogger(RespLoginPacket.class);

	/** 成功 */
	public static final byte RESULT_SUCC = 0;
	/** 失败:Token错误 */
	public static final byte RESULT_ERROR_TOKEN		= 1;
	/** 失败:还没创建玩家 */
	public static final byte RESULT_ERROR_NO_CREATE	= 2;

	public final static RespLoginPacket SUCC = createPacket(RESULT_SUCC);
	public final static RespLoginPacket ERROR_TOKEN = createPacket(RESULT_ERROR_TOKEN);
	public final static RespLoginPacket ERROR_NO_CREATE	= createPacket(RESULT_ERROR_NO_CREATE);

	private LoginState state;

	private void init(int state) {
		this.state = LoginState.newBuilder().setResult(state).setData("hello").build();
	}

	public static RespLoginPacket createPacket(byte state) {
		RespLoginPacket createPacket = new RespLoginPacket();
		createPacket.setCommandId(PacketId.RESP_LOGIN);
		createPacket.init(state);
		return createPacket;
	}

	@Override
	public AbstractMessageLite writeObject() {
		LOGGER.info("RespLogin:" + state);
		return state;
	}

}
