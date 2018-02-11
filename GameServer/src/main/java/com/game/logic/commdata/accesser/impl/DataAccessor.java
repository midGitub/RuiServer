package com.game.logic.commdata.accesser.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.game.logic.commdata.accesser.*;
import com.game.logic.commdata.entity.PlayerCommData;

/**
 * @author liguorui
 * @date 2018/2/5 19:29
 */
public class DataAccessor extends IDataAccessor {

    private IIntDataAccessor intDataAccessor;
    private ILongDataAccessor longDataAccessor;
    private IStringDataAccessor stringDataAccessor;
    private IJsonDataAccessor jsonDataAccessor;
    private IJsonArrayDataAccessor jsonArrayDataAccessor;
    private IObjectDataAccessor objectDataAccessor;
    private IBooleanDataAccessor booleanDataAccessor;

    public DataAccessor(PlayerCommData data) {
        super(data);
    }

    public DataAccessor(PlayerCommData data, IObjectDataAccessor objectDataAccessor) {
        super(data);
        this.objectDataAccessor = objectDataAccessor;
    }

    @Override
    public void reset() {
        data.reset();
        if (intDataAccessor != null) {
            intDataAccessor.reset();
        }

        if (longDataAccessor != null) {
            longDataAccessor.reset();
        }
        if (stringDataAccessor != null) {
            stringDataAccessor.reset();
        }
        if (jsonDataAccessor != null) {
            jsonDataAccessor.reset();
        }
        if (jsonArrayDataAccessor != null) {
            jsonArrayDataAccessor.reset();
        }
        if (objectDataAccessor != null) {
            objectDataAccessor.reset();
        }
        if (booleanDataAccessor != null) {
            booleanDataAccessor.reset();
        }
    }

    @Override
    public JSONArray getJsonArray() {
        return getJsonArrayDataAccessor().getJsonArray();
    }

    @Override
    public void saveJsonArray() {
        getJsonArrayDataAccessor().saveJsonArray();
    }

    @Override
    public JSONObject getJson() {
        return getJsonDataAccessor().getJson();
    }

    @Override
    public void saveJson() {
        getJsonDataAccessor().saveJson();
    }

    @Override
    public String getString() {
        return getStringDataAccessor().getString();
    }

    @Override
    public void setString(String value) {
        getStringDataAccessor().setString(value);
    }

    @Override
    public long getLong() {
        return getLongDataAccessor().getLong();
    }

    @Override
    public Long getLongLong() {
        return getLongDataAccessor().getLongLong();
    }

    @Override
    public void incrLong(long delta) {
        getLongDataAccessor().incrLong(delta);
    }

    @Override
    public void decrLong(long delta) {
        getLongDataAccessor().decrLong(delta);
    }

    @Override
    public void alterLong(long delta) {
        getLongDataAccessor().alterLong(delta);
    }

    @Override
    public void setLong(long longValue) {
        getLongDataAccessor().setLong(longValue);
    }

    @Override
    public int getInt() {
        return getIntDataAccessor().getInt();
    }

    @Override
    public Integer getInteger() {
        return getIntDataAccessor().getInteger();
    }

    @Override
    public void incrInt(int delta) {
        getIntDataAccessor().incrInt(delta);
    }

    @Override
    public void decrInt(int delta) {
        getIntDataAccessor().decrInt(delta);
    }

    @Override
    public void alterInt(int delta) {
        getIntDataAccessor().alterInt(delta);
    }

    @Override
    public void setInt(int intValue) {
        getIntDataAccessor().setInt(intValue);
    }

    @Override
    public <T> T getObject(Class<T> clz) {
        return getObjectDataAccessor().getObject(clz);
    }

    @Override
    public void saveObject() {
        getObjectDataAccessor().saveObject();
    }

    public IIntDataAccessor getIntDataAccessor() {
        if (intDataAccessor == null) {
            intDataAccessor = new IntDataAccessor(data);
        }
        return intDataAccessor;
    }

    public ILongDataAccessor getLongDataAccessor() {
        if (longDataAccessor == null) {
            longDataAccessor = new LongDataAccessor(data);
        }
        return longDataAccessor;
    }

    public IStringDataAccessor getStringDataAccessor() {
        if (stringDataAccessor == null) {
            stringDataAccessor = new StringDataAccessor(data);
        }
        return stringDataAccessor;
    }

    public IJsonDataAccessor getJsonDataAccessor() {
        if (jsonDataAccessor == null) {
            jsonDataAccessor = new JsonDataAccessor(data);
        }
        return jsonDataAccessor;
    }

    public IJsonArrayDataAccessor getJsonArrayDataAccessor() {
        if (jsonArrayDataAccessor == null) {
            jsonArrayDataAccessor = new JsonArrayDataAccessor(data);
        }
        return jsonArrayDataAccessor;
    }

    public IObjectDataAccessor getObjectDataAccessor() {
        if (objectDataAccessor == null) {
            objectDataAccessor = new JsonParseAccessor(data);
        }
        return objectDataAccessor;
    }

    public IBooleanDataAccessor getBooleanDataAccessor() {
        if (booleanDataAccessor == null) {
            booleanDataAccessor = new BooleanDataAccessor(data);
        }
        return booleanDataAccessor;
    }

    @Override
    public boolean isTrue() {
        return getBooleanDataAccessor().isTrue();
    }

    @Override
    public void setBool(boolean booleanValue) {
        getBooleanDataAccessor().setBool(booleanValue);
    }
}
