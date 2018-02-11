package com.game.framework.network.packet.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 
 * @author liguorui
 * @date 2016-01-06
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Packet {
	/** 包ID */
	short commandId();
}