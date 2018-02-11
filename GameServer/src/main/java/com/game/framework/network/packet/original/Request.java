package com.game.framework.network.packet.original;

import io.netty.buffer.ByteBuf;

/**
 * 
 * @author liguorui
 *
 */
public interface Request {

	short getPacketId();
	
	/**
	 * 请求参数字节大小（不包括包ID）
	 * @return
	 */
	int getByteSize();
	
	byte readByte();
	
	short readUnsignedByte();
	
	/**
	 * 16 bit
	 * @return
	 */
	short readShort();
	
	/**
	 * 16 bit
	 * @return
	 */
	int readUnsignedShort();
	
	/**
	 * 32 bit
	 * @return
	 */
	int readInt();
	
	/**
	 * 32bit
	 * @return
	 */
	long readUnsignedInt();
	
	/**
	 * 64bit
	 * @return
	 */
	long readLong();
	
	String readString();
	
	float readFloat();
	
	ByteBuf getByteBuf();
	
	void readBytes(byte[] bytes);
}
