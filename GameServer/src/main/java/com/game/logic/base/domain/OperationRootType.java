package com.game.logic.base.domain;

/**
 * @author liguorui
 * @date 2018/2/4 01:19
 */
public enum OperationRootType {

    None(0, "无"),

    Backpack(1, "背包"),

    Equip(2, "装备"),

    Task(3, "任务"),
    ;

    private int id;

    private String name;

    OperationRootType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
