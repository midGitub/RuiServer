package com.game.logic.error.anotation;

import java.lang.annotation.*;

/**
 * @author liguorui
 * @date 2014年7月9日 上午11:44:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface ErrorCodeComment {

	String value();
}
