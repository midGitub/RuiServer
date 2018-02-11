package com.game.framework.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liguorui
 * @date 2017/7/9 15:58
 */
public class ExceptionUtils {

    private static final Logger log = LoggerFactory.getLogger(ExceptionUtils.class);

    public static void log(Throwable e) {
        log.error("", e);
    }

    public static void log(Throwable e, String msg) {
        log.error(msg, e);
    }

    public static void log(Throwable e, String msg, Object ...args) {
        log.error(String.format(msg,args), e);
    }

    public static void log(String format, Object ...args) {
        log.error(format, args);
    }

    public static void log(String msg) {
        log.error(msg);
    }
}
