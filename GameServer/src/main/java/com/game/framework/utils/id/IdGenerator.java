package com.game.framework.utils.id;

import com.game.framework.utils.Bits;
import com.game.framework.utils.id.exception.IDGenerateMaxException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 主键生成器<br>
 * [1保留位][服务器位][ID自增位]
 *
 * @author liguorui
 * @date 2017年1月5日 下午4:41:38
 */
public class IdGenerator {

	/**
	 * <pre>
	 * 服务器表示位长度。
	 * 实际服务器数目为： 2^服务器表示位长度-1
	 * 比如：服务器表示位长度为12.那么支持的服务器数目为：2^12-1=4095
	 * </pre>
	 */
	private static final byte	SERVER_BIT_LENGTH	= 12;

	/**
	 * 1是保留位。
	 */
	private static final byte	ID_BIT_LENGTH		= 32 - 1 - SERVER_BIT_LENGTH;

	/** 服务器ID mask */
	public final int			SERVER_MASK;
	/** ID mask */
	public final int			ID_MASK;
	/** 服务器ID */
	public final int			SERVER;
	/** 当前ID */
	private final AtomicInteger	ID;

	/**
	 * @param server
	 * @param current
	 * 如果想让该ID生成器从最小的时候生成。那么可以传入-1.
	 */
	public IdGenerator(int server, int current) {
		if (!Bits.vaildValueInBitLength(server, SERVER_BIT_LENGTH)) {
			throw new IllegalArgumentException("服务器标识[" + server + "]超过了" + SERVER_BIT_LENGTH + "位二进制数的表示范围");
		}
		this.SERVER = server;
		this.SERVER_MASK = server << ID_BIT_LENGTH;
		ID_MASK = Bits.generateMask(ID_BIT_LENGTH);
		int min = SERVER_MASK;

		if (current != -1) {
			if (current <= min) {
				throw new IllegalArgumentException("当前主键值[" + current + "]应该大于[" + min + "]");
			}
			this.ID = new AtomicInteger(current);
		} else {
			this.ID = new AtomicInteger(min);
		}
	}

	/**
	 * 返回指定服务器ID的最小玩家ID(不包含该ID)
	 * @param server
	 * @return
	 */
	public static int getMin(int server) {
		return server << ID_BIT_LENGTH;
	}

	/**
	 * 返回指定服务器ID的最大玩家ID(包含该ID)
	 * @param server
	 * @return
	 */
	public static int getMax(int server) {
		return getMin(server) + Bits.generateMask(ID_BIT_LENGTH);
	}

	/**
	 * 获取当前的主键值
	 * @return
	 */
	public long getCurrent() {
		return ID.get();
	}

	/**
	 * 获取下一个主键值
	 * @return
	 */
	public int getNext() throws IDGenerateMaxException {
		int result = ID.incrementAndGet();
		if (((result - 1) & ID_MASK) == ID_MASK) {
			ID.decrementAndGet();
			throw new IDGenerateMaxException(ID_MASK);
		}
		return result;
	}
}
