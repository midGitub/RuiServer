package com.game.framework.base;

/**
 * 在Spring初始化后执行
 * @author liguorui
 * @date 2018/1/7 23:51
 */
public interface ServerInit {

    int COMMON = 0;
    int HIGHEST = Integer.MIN_VALUE;
    int LOWEST = Integer.MAX_VALUE;

    /**
     * 数字越小越先执行
     * @return
     */
    int getOrder();

    void init();
}
