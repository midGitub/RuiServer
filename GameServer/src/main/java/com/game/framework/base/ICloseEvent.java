package com.game.framework.base;

/**
 * @author liguorui
 * @date 2018/2/3 23:19
 */
public interface ICloseEvent {

    /**
     * JVM关闭时触发的事件
     */
    void onServerClose();
}
