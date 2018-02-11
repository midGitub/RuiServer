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
 * com.duoyu001.framework.game.utils.Converters.java CREATED ON 上午11:15:38
 * COPYRIGHT 2013-2015 WWW.DUOYU001.COM. ALL RIGHTS RESERVED.
 */
package com.game.framework.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Converters {

	public static <S, D> Collection<D> convert(	Iterable<S> src,
												IObjectConverter<S, D> converter) {
		if (src == null) {
			return new ArrayList<D>(0);
		}
		if (converter == null) {
			throw new NullPointerException("Converter can not be null");
		}
		List<D> list = new LinkedList<D>();
		for (S s : src) {
			D convert = converter.convert(s);
			if (convert != null) {
				list.add(convert);
			}
		}
		return list;
	}
}
