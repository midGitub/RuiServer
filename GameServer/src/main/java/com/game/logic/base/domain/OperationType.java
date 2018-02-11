package com.game.logic.base.domain;

/**
 * 调整后的日志类型，日志最终ID，将由两部分组成：主类ID，子类ID
 * @author liguorui
 * @date 2018/2/4 01:18
 */
public enum OperationType {

    NONE(OperationRootType.Backpack, 1001, "无"),
    RECYCKE(OperationRootType.Backpack, 1002, "回收装备"),
    ;


    private OperationRootType operationRootType;

    private int type;

    private String desc;

    OperationType(OperationRootType operationRootType, int type, String desc) {
        this.operationRootType = operationRootType;
        this.type = type;
        this.desc = desc;
    }
}
