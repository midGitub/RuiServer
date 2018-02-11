package com.game.logic.commdata.accesser.impl;

import com.game.logic.commdata.accesser.IIntDataAccessor;
import com.game.logic.commdata.accesser.DataWrapper;
import com.game.logic.commdata.entity.PlayerCommData;

/**
 * @author liguorui
 * @date 2018/2/5 20:09
 */
public class IntDataAccessor extends DataWrapper implements IIntDataAccessor {

    private int cache;
    private boolean cached;

    public IntDataAccessor(PlayerCommData data) {
        super(data);
    }

    @Override
    public void reset() {
        cached = false;
    }

    private int getCache() {
        if (cached) {
            return cache;
        }

        cache = data.getInt();
        cached = true;
        return cache;
    }

    @Override
    public int getInt() {
        return getCache();
    }

    @Override
    public Integer getInteger() {
        return getCache();
    }

    @Override
    public void incrInt(int delta) {
//        checkArgument(delta > 0);
        alterInt(delta);
    }

    @Override
    public void decrInt(int delta) {
//        checkArgument(delta > 0);
        alterInt(-delta);
    }

    @Override
    public void alterInt(int delta) {
        if (delta == 0) {
            return;
        }
        setInt(cache + delta);
    }

    @Override
    public void setInt(int intValue) {
        cache = intValue;
        data.setInt(cache);
    }

}
