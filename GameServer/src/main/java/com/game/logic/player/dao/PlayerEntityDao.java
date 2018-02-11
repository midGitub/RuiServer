package com.game.logic.player.dao;

import com.game.framework.base.repository.BaseRepository;
import com.game.logic.player.entity.PlayerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author liguorui
 * @date 2018/1/28 11:12
 */
public interface PlayerEntityDao extends BaseRepository<PlayerEntity, Long> {

    PlayerEntity findPlayerEntityById(Long id);

    // 使用方法名查询，接受一个name参数，返回值为列表
    List<PlayerEntity> findByName(String name);

    // 使用方法名查询，接受name和createTime，返回值为单个对象
    PlayerEntity findByNameAndCreateTime(String name, int createTime);

    // 使用@Query查询，参数按照名称绑定
    @Query("select p from PlayerEntity p where p.name=:name and p.createTime=:createTime")
    PlayerEntity getNameAndCreateTimeQuery(@Param("name") String name, @Param("createTime") int createTime);

    //使用@NamedQuery查询，请注意在实体类中做的@NamedQuery的定义
    PlayerEntity getNameAndCreateTimeNamedQuery(String name, int createTime);
}
