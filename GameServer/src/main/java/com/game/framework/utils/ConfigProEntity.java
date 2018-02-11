package com.game.framework.utils;


/**
 * @author liguorui
 * @date 2017/9/15 23:18
 */
public class ConfigProEntity implements ProEntity {

    /**
     * 概率
     */
    private int weight;

    @Override
    public int getPro() {
        return weight;
    }
}
