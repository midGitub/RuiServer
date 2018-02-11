package com.game.framework.network.packet.original;

/**
 * RESPONSE工厂
 * @author liguorui
 *
 */
public class ResponseFactory {

	private static ResponseFactory instance;
	
	private ResponseFactory() {}

	public static ResponseFactory getInstance() {
		if (instance == null) {
			instance = new ResponseFactory();
		}
		return instance;
	}
	
	public Response createResponse(int packetId) {
		return new ByteBufResponse(packetId);
	}
	
}
