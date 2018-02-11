package com.game.logic.commdata.dao;

import com.game.framework.base.repository.BaseRepository;
import com.game.logic.commdata.entity.PlayerCommData;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liguorui
 * @date 2018/1/29 00:00
 */
@Transactional
public interface PlayerCommDataDao extends BaseRepository<PlayerCommData, Long> {

    List<PlayerCommData> getByPlayerId(long playerId);

    PlayerCommData getByPlayerIdAndType(long playerId, int type);

    void deleteByPlayerIdAndType(long playerId, int type);

    void deleteByPlayerId(long playerId);

}
