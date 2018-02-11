package com.game.framework.network.packet.original;

import com.game.framework.network.packet.Packets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCounted;

/**
 * response实现类
 * @author liguorui
 * @date 2016-01-06
 *
 */
public class ByteBufResponse implements Response, ReferenceCounted {

	private ByteBuf byteBuf;
	
	public ByteBufResponse(int packetId) {
		super();
		this.byteBuf = Unpooled.buffer(64);
//		this.byteBuf = PooledByteBufAllocator.DEFAULT.heapBuffer(64); 好像会泄露
		this.byteBuf.writeShort(packetId);
	}
	
	@Override
	public ByteBuf getByteBuf() {
		return byteBuf;
	}
	
	public short getPacketId() {
		return byteBuf.getShort(0);
	}
	
	@Override
	public Response setPacketId(int packetId) {
		byteBuf.setShort(0, packetId);
		return this;
	}

	@Override
	public Response writeByte(int value) {
		byteBuf.writeByte(value);
		return this;
	}

	@Override
	public Response writeShort(int value) {
		byteBuf.writeShort(value);
		return this;
	}

	@Override
	public Response writeInt(int value) {
		byteBuf.writeInt(value);
		return this;
	}

	@Override
	public Response writeLong(long value) {
		byteBuf.writeLong(value);
		return this;
	}

	@Override
	public Response writeBytes(byte[] src) {
		byteBuf.writeBytes(src);
		return this;
	}

	@Override
	public Response writeBytes(byte[] src, int srcIndex, int length) {
		byteBuf.writeBytes(src, srcIndex, length);
		return this;
	}

	@Override
	public Response writeFloat(float value) {
		byteBuf.writeFloat(value);
		return this;
	}

	@Override
	public Response writeDouble(double value) {
		byteBuf.writeDouble(value);
		return this;
	}

	@Override
	public Response writeString(String value) {
		Packets.writeString(byteBuf, value);
		return this;
	}

	@Override
	public Response writeShortString(String value) {
		Packets.writeShortString(byteBuf, value);
		return this;
	}

	@Override
	public int getIndex() {
		return byteBuf.writerIndex();
	}

	@Override
	public Response setByte(int index, int value) {
		byteBuf.setByte(index, value);
		return this;
	}

	@Override
	public Response setShort(int index, int value) {
		byteBuf.setShort(index, value);
		return this;
	}

	@Override
	public Response setInt(int index, int value) {
		byteBuf.setInt(index, value);
		return this;
	}

	@Override
	public Response setLong(int index, long value) {
		byteBuf.setLong(index, value);
		return this;
	}

	@Override
	public Response writeStringArray(String[] strs) {
		Packets.writeStringArray(this, strs);
		return this;
	}

	@Override
	public Response writeValueByType(long value) {
		return this;
	}

	@Override
	public Response writePositiveInt(int value) {
		return this;
	}

	@Override
	public Response writePositiveInt(long value) {
		return this;
	}

	@Override
	public Response writePositiveLong(long value) {
		return this;
	}

	@Override
	public Response write24Bit(int value) {
		Packets.write24Bit(byteBuf, value);
		return this;
	}

	@Override
	public Response write48Bit(long value) {
		Packets.write48Bit(byteBuf, value);
		return this;
	}

	@Override
	public int refCnt() {
		return byteBuf.refCnt();
	}

	@Override
	public ReferenceCounted retain() {
		return byteBuf.retain();
	}

	@Override
	public ReferenceCounted retain(int increment) {
		return byteBuf.retain(increment);
	}

	@Override
	public ReferenceCounted touch() {
		return byteBuf.touch();
	}

	@Override
	public ReferenceCounted touch(Object o) {
		return byteBuf.touch(o);
	}

	@Override
	public boolean release() {
		return byteBuf.release();
	}

	@Override
	public boolean release(int decrement) {
		return byteBuf.release(decrement);
	}
}
