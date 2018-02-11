package com.game.logic.commdata.accesser;

import com.game.logic.commdata.entity.PlayerCommData;

/**
 * @author liguorui
 * @date 2018/2/5 19:07
 */
public class DataWrapper {

    protected final PlayerCommData data;

    public DataWrapper(PlayerCommData data) {
        this.data = data;
    }

    public PlayerCommData unwrap() {
        return data;
    }
}
