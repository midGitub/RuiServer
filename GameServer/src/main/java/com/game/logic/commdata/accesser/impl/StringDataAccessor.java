package com.game.logic.commdata.accesser.impl;

import com.game.logic.commdata.accesser.IStringDataAccessor;
import com.game.logic.commdata.accesser.DataWrapper;
import com.game.logic.commdata.entity.PlayerCommData;

/**
 * @author liguorui
 * @date 2018/2/5 20:35
 */
public class StringDataAccessor extends DataWrapper implements IStringDataAccessor {

    public StringDataAccessor(PlayerCommData data) {
        super(data);
    }

    @Override
    public String getString() {
        return data.getValue();
    }

    @Override
    public void setString(String value) {
        data.setValue(value);
    }

    @Override
    public void reset() {

    }
}
