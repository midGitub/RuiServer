package com.game.framework.asyncdb.orm;

import com.game.framework.asyncdb.Synchronizer;

import java.io.Serializable;
import java.util.List;

/**
 * @author liguorui
 * @date 2018/1/15 00:37
 */
public interface BaseDao<E> extends Synchronizer<E> {

    E get(Serializable id);

    List<E> getAll();
}
