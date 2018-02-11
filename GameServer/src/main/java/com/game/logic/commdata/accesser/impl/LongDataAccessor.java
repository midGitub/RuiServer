package com.game.logic.commdata.accesser.impl;

import com.game.logic.commdata.accesser.ILongDataAccessor;
import com.game.logic.commdata.accesser.DataWrapper;
import com.game.logic.commdata.entity.PlayerCommData;

/**
 * @author liguorui
 * @date 2018/2/5 20:28
 */
public class LongDataAccessor extends DataWrapper implements ILongDataAccessor {

    private long cache;
    private boolean cached;

    public LongDataAccessor(PlayerCommData data) {
        super(data);
    }

    private long getCache() {
        if (cached) {
            return cache;
        }

        cache = data.getLong();
        cached = true;
        return cache;
    }

    @Override
    public long getLong() {
        return getCache();
    }

    @Override
    public Long getLongLong() {
        return getCache();
    }

    @Override
    public void incrLong(long delta) {
//        checkArgument(delta > 0);
        alterLong(delta);
    }

    @Override
    public void decrLong(long delta) {
//        checkArgument(delta > 0);
        alterLong(-delta);
    }

    @Override
    public void alterLong(long delta) {
        if (delta == 0) {
            return;
        }
        setLong(cache + delta);
    }

    @Override
    public void setLong(long longValue) {
        cache = longValue;
        data.setLong(cache);
    }

    @Override
    public void reset() {
        cached = false;
    }
}
