package com.game.framework.utils.type.annotation;

import java.lang.annotation.*;

/**
 * 类型标记
 * @author liguorui
 * @date 2013-11-9 上午11:21:19
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Inherited
public @interface TypeManagered {

	int index() default 0;		// 顺序，数字越大，排到越前
}