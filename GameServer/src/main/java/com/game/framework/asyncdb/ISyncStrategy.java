package com.game.framework.asyncdb;

/**
 * @author liguorui
 * @date 2018/1/21 20:41
 */
public interface ISyncStrategy {

    long getSleepTime(int waitingSize);

    int getNumEachLoop();

    int getTryTime();
}
