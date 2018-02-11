package com.game.framework.asyncdb.orm;

import com.game.framework.asyncdb.AsynDBEntity;
import com.game.framework.logger.ExceptionUtils;
import com.game.logic.base.Context;

/**
 * @author liguorui
 * @date 2018/1/15 00:42
 */
public class BaseDBEntity extends AsynDBEntity {

    public void insert() {
        if (isRobot()) {
            return;
        }
        Context.getAsyncDBService().insert(this);
    }

    public void update() {
        if (isRobot()) {
            return;
        }
        Context.getAsyncDBService().update(this);
    }

    public void delete() {
        if (isRobot()) {
            return;
        }
        try {
            Context.getAsyncDBService().delete(this);
        } catch (Exception e) {
            ExceptionUtils.log(e);
        }
    }

    public void serialize() {

    }

    public void deserialize() {

    }

//    public ISaver saver() {
//        return new BaseDBEntitySaver(this);
//    }

    public boolean isRobot() {
        return false;
    }
}
