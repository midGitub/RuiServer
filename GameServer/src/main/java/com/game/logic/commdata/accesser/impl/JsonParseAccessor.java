package com.game.logic.commdata.accesser.impl;

import com.alibaba.fastjson.JSON;
import com.game.logic.commdata.accesser.IObjectDataAccessor;
import com.game.logic.commdata.accesser.DataWrapper;
import com.game.logic.commdata.entity.PlayerCommData;

/**
 * @author liguorui
 * @date 2018/2/5 20:23
 */
public class JsonParseAccessor extends DataWrapper implements IObjectDataAccessor {

    private Object obj;

    public JsonParseAccessor(PlayerCommData data) {
        super(data);
    }

    @Override
    public <T> T getObject(Class<T> clz) {
        if (obj == null) {
            obj = JSON.parseObject(data.getValue(), clz);
        }
        return (T)obj;
    }

    @Override
    public void saveObject() {
        if (obj != null) {
            data.setValue(JSON.toJSONString(obj));
        }
    }

    @Override
    public void reset() {
        obj = null;
    }
}
