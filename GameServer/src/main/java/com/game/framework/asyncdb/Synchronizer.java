package com.game.framework.asyncdb;

/**
 * @author liguorui
 * @date 2018/1/15 00:38
 */
public interface Synchronizer<T> {

    boolean insertData(T object);

    boolean updateData(T object);

    boolean deleteData(T object);
}
