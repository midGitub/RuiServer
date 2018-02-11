package com.game.framework.network.packet;

import com.game.framework.network.packet.original.Response;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * 数据包工具类
 * 
 * @author liguorui
 * @date 2016-01-06
 */
public class Packets {
	
	public static final long MAX_48BIT = (1L << 47);
	
	public static final long MAX_48BIT_NEGATIVE = -MAX_48BIT;

	/**
	 * 写长字符串，32767个字节
	 * @param buffer
	 * @param str
	 */
	public static void writeString(ByteBuf buffer, String str) {
		if (str != null) {
			byte[] content = str.getBytes(Charset.forName("UTF-8"));
			buffer.writeShort(content.length);
			buffer.writeBytes(content);
		} else {
			buffer.writeShort((byte) 0);
		}
	}
	
	/**
	 * 写短字符串 127个字节
	 * @param buffer
	 * @param str
	 */
	public static void writeShortString(ByteBuf buffer, String str) {
		if (str != null) {
			byte[] content = str.getBytes(Charset.forName("UTF-8"));
			buffer.writeByte(content.length);
			buffer.writeBytes(content);
		} else {
			buffer.writeByte((byte) 0);
		}
	}

	public static String readString(ByteBuf buffer) {
		short length = buffer.readShort();
		byte[] content = new byte[length];
		buffer.readBytes(content);
		return new String(content, Charset.forName("UTF-8"));
	}
	
	/**
	 * 写长字符串数组
	 * @param response
	 * @param strs
	 */
	public static void writeStringArray(Response response, String[]strs) {
		if (strs == null || strs.length ==0) {
			response.writeShort(0);
		} else {
			response.writeShort(strs.length);
			for (String str : strs) {
				response.writeString(str);
			}
		}
	}
	
	/**
	 * 写24位
	 * @param byteBuf
	 * @param value
	 */
	public static void write24Bit(ByteBuf byteBuf, int value) {
		byteBuf.writeByte((value & 0xFFFF0000) >> 16);
		byteBuf.writeShort(value & 0xFFFF);
	}
	
	public static int read24Bit(ByteBuf byteBuf, int value) {
		int high = byteBuf.readByte();
		int low = byteBuf.readUnsignedShort();
		return ((high << 16) | low);
	}
	
	/**
	 * 写48位
	 * @param byteBuf
	 * @param value
	 */
	public static void write48Bit(ByteBuf byteBuf, long value) {
		if (value < MAX_48BIT && value > MAX_48BIT_NEGATIVE) {
			byteBuf.writeShort((int)(value >>> 32));
			byteBuf.writeInt((int)value);
		}
	}
	
	public static long read48Bit(ByteBuf byteBuf) {
		long high = byteBuf.readShort();
		long low = byteBuf.readUnsignedInt();
		return ((high << 32) | low);
	}
	
}