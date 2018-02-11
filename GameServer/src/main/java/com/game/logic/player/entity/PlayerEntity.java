package com.game.logic.player.entity;

import javax.persistence.*;

/**
 * @author liguorui
 * @date 2018/1/28 11:12
 */
@Entity
@Table(name = "Player")
@NamedQuery(name = "PlayerEntity.getNameAndCreateTimeNamedQuery", query = "select p from PlayerEntity p where p.name=?1 and createTime=?2")
public class PlayerEntity{

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }
}
