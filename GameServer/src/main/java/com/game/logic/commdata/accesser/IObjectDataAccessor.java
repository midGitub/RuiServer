package com.game.logic.commdata.accesser;

/**
 * @author liguorui
 * @date 2018/2/5 19:22
 */
public interface IObjectDataAccessor extends IDataReset {

    <T> T getObject(Class<T> clz);

    void saveObject();
}
