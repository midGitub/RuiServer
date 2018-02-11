package com.game.framework.network.packet.original;

import io.netty.buffer.ByteBuf;

public interface Response {

	Response setPacketId(int packetId) ;
	
	Response writeByte(int value);
	
	Response writeShort(int value);
	
	Response writeInt(int value);
	
	Response writeLong(long value);
	
	Response writeBytes(byte[] src);
	
	Response writeBytes(byte[] src, int srcIndex, int length);
	
	Response writeFloat(float value);
	
	Response writeDouble(double value);
	
	Response writeString(String value);
	
	Response writeShortString(String value);
	
	int getIndex();
	
	Response setByte(int index, int value);

	Response setShort(int index, int value);

	Response setInt(int index, int value);

	Response setLong(int index, long value);

	Response writeStringArray(String[] strs);
	
	Response writeValueByType(long value);
	
	/**
	 * 此方法只能写正数
	 * @param value
	 * @return
	 */
	Response writePositiveInt(int value);
	
	Response writePositiveInt(long value);
	
	Response writePositiveLong(long value);
	
	/**
	 * 写24位数值
	 * @param value
	 * @return
	 */
	Response write24Bit(int value);
	
	/**
	 * 写48位数值
	 * @param value
	 * @return
	 */
	Response write48Bit(long value);
	
	ByteBuf getByteBuf();
}
