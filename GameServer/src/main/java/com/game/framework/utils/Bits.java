/********************************************************************
 * 中文许可
 ********************************************************************
 * Copyright (C) 2013 - 2015 by www.duoyu001.com 该源码为 广州多娱网络科技有限公司 所有，授权许可人为
 * 蔡俊鸿(john.cha)。
 *
 * 你可以： 1、在许可情况下，复制、发行或通过信息网络传播本作品。
 *
 * 但必须遵守以下条件： 1、您必须按照在传播的时候在明显位置标明该作品的原公司和许可人。
 *
 * 声明： 1、在再使用或者发行本作品时，您必须向他人明示本作品使用的许可协议条款。 2、任何人不得在未经许可的情况下，将其用于任意用途。
 *
 ********************************************************************
 * English license
 ********************************************************************
 * Copyright (C) 2013 - 2015 by www.duoyu001.com This source code are ownered by
 * 广州多娱网络科技有限公司, copyright holder are john.cha(蔡俊鸿)
 *
 * You can : 1、Copy, distribute and transmit the work after you got permission
 * from the copyright holder.
 *
 * Under the following conditions: 1、When you use this source code, you should
 * indicate the holder's company and the holder at showy position.
 *
 * Declaration : 1、While using or publishing this source code, you should show
 * this license to others. 2、Nobody can use this source code for any purpose
 * before you get permission from the copyright holder.
 *
 ********************************************************************
 * 授权许可人联系方式（Contact the copyright holder） 许可人(copyright holder): 蔡俊鸿(john.cha)
 * QQ : 327112182 TEL : 18665023554 EMAIL : john.cha@qq.com 或
 * kakashi9bi@gmail.com
 ********************************************************************
 *
 * com.duoyu001.framework.game.utils.Bits.java CREATED ON 下午4:23:46 COPYRIGHT
 * 2013-2015 WWW.DUOYU001.COM. ALL RIGHTS RESERVED.
 */
package com.game.framework.utils;

/**
 * 针对32位整数，进行位操作。
 * 
 * @author cjunhong
 * @email john.cha@qq.com
 * @date 2015年2月6日 下午4:23:46
 */
public class Bits {

	private static final int	MAX_BIT_LENGTH	= 32;

	/**
	 * <pre>
	 * 清除指定位数的数据。
	 * 比如：指定值为7（二进制：111）
	 * 指定清除位为0，则返回值为6（二进制：110）
	 * 指定清除位为1，则返回值为5（二进制：101）
	 * </pre>
	 * 
	 * @param value
	 * @param bitIndex
	 *            [0, 32] 最右边为第0位。
	 * @return
	 */
	public static int clearInBit(int value, int bitIndex) {
		long bitMask = 1L << bitIndex;
		return (int) ((value | bitMask) ^ bitMask);
	}

	/**
	 * <pre>
	 * 生成指定位数的mask。
	 * 比如：指定3位，那么返回值为7（二进制：111）
	 * </pre>
	 * 
	 * @param bitLength
	 *            [0, 32]
	 * @return
	 */
	public static int generateMask(int bitLength) {
		return (int) ((0x1L << bitLength) - 1);
	}

	/**
	 * 判断一个值是否能用指定长度的二进制位表示
	 * 
	 * @param value
	 * @param bitLength
	 *            [0,32]
	 * @return
	 */
	public static boolean vaildValueInBitLength(long value, int bitLength) {
		long maxValue = 1L << bitLength;
		return value < maxValue;
	}
}
