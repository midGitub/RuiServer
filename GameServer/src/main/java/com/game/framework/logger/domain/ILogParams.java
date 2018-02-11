package com.game.framework.logger.domain;

/**
 * @author liguorui
 * @date 2018/2/4 00:42
 */
public interface ILogParams {

    public void append(LogAppender sb);

    public static ILogParams DEFAULT = new ILogParams() {
        @Override
        public void append(LogAppender sb) {
            sb.appendKeyValue("key", "").appendKeyValue("times", 1);
        }
    };
}
