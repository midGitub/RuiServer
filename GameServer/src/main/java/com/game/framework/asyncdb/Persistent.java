package com.game.framework.asyncdb;

import java.lang.annotation.*;

/**
 * @author liguorui
 * @date 2018/1/21 22:55
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Persistent {

    boolean asyn() default true;

    Class<? extends Synchronizer> syncClass();
}
