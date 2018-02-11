package com.game.logic.commdata;

import com.game.logic.base.actor.Player;
import com.game.logic.commdata.accesser.IDataAccessor;
import com.game.logic.commdata.accesser.impl.DataAccessor;
import com.game.logic.commdata.entity.PlayerCommData;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author liguorui
 * @date 2018/2/5 21:30
 */
public class PlayerCommDataHandler {

    private final Player player;
    private final ConcurrentMap<CommDataType, IDataAccessor> accessors;

    private boolean initialized;

    public static PlayerCommDataHandler create(Player player) {
        return new PlayerCommDataHandler(player);
    }

    private PlayerCommDataHandler(Player player) {
        this.player = player;
        this.accessors = new ConcurrentHashMap<>();
        this.initialized = false;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized() {
        initialized = true;
    }

    public boolean contains(CommDataType type) {
        return accessors.containsKey(type);
    }

    public void cache(PlayerCommData data) {
        CommDataType type = data.getTypeEnum();
        accessors.put(type, new DataAccessor(data));
    }

    public IDataAccessor access(CommDataType type) {
        return access(type);
    }

    public IDataAccessor access(CommDataType type, Class<? extends IDataAccessor> clz) {
        IDataAccessor accessor = accessors.get(type);
        if (accessor != null) {
            return accessor;
        }
        synchronized (this) {
            //上线已经缓存已经有的，没有的表里也没数据则直接new新的
            accessor = accessors.get(type);
            if (accessor == null) {
                final long playerId = player.getPlayerId();
                PlayerCommData data = new PlayerCommData(playerId, type);
                if (clz == null) {
                    accessor = new DataAccessor(data);
                } else {
                    try {
                        Constructor<? extends IDataAccessor> c = clz.getConstructor(PlayerCommData.class);
                        accessor = c.newInstance(data);
                    } catch (Exception e) {
                        throw new IllegalStateException("create failed:" + clz, e);
                    }
                }
                accessors.put(type, accessor);
            }
        }
        return accessor;
    }

    public void onMidnight() {
        for (IDataAccessor accessor : accessors.values()) {
            accessor.unwrap().onMidnight(accessor);
        }
    }
}
