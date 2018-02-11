package com.game.framework.network.packet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liguorui
 * @date 2016-01-06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface PacketIndex {

	/** 索引名，同一资源的索引名必须唯一 */
	int index() default 0;
}