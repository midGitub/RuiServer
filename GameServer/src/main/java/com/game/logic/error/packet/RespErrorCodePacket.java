package com.game.logic.error.packet;

import com.game.framework.network.packet.annotation.Packet;
import com.game.framework.network.packet.protobuf.ResponseProtobufPacket;
import com.game.logic.packet.PacketId;
import com.google.protobuf.AbstractMessageLite;
import  com.game.proto.error.ErrorCodeBuilder.ErrorCode.Builder;
import com.game.proto.error.ErrorCodeBuilder.ErrorCode;

/**
 * 返回错误提示
 * @author liguorui
 * @date 2014年7月7日 上午11:43:04
 */
@Packet(commandId = PacketId.RESP_ERROR_CODE)
public class RespErrorCodePacket extends ResponseProtobufPacket {

	private static final String	NULL	= "null";
	Builder						builder	= ErrorCode.newBuilder();

	public void init(int commandId, int errorCode) {
		init(commandId, errorCode, null);
	}

	public void init(int commandId, int errorCode, Object[] args) {
		builder.setCommandId(commandId);
		builder.setErrorCode(errorCode);
		if (args != null) {
			for (Object arg : args) {
				if (arg == null) {
					builder.addErrorArgs(NULL);
				} else {
					builder.addErrorArgs(arg.toString());
				}
			}
		}
	}

	@Override
	public AbstractMessageLite writeObject() {
		return builder.build();
	}

}
