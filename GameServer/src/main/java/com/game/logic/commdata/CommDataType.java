package com.game.logic.commdata;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author liguorui
 * @date 2018/1/15 00:11
 */
public enum CommDataType {

    PLAYER_ENTER_ARRAY(1, "[]", "数组类型"),
    RECHARGES(2, "0", "充值总额"),
    TIMES(3, "{}", "次数类型"),
    DAILY(4, "0", true, "每日重置"),
    ;

    private final int type;
    private final String initValue;
    private final CommDataMonitor monitor;
    private final String desc;

    private CommDataType(int type, String initValue, String desc) {
        this(type, initValue, null, desc);
    }

    private CommDataType(int type, String initValue, boolean daily, String desc) {
        this(type, initValue, daily ? CommDataMonitor.DAILY : null, desc);
    }

    private CommDataType(int type, String initValue, CommDataMonitor monitor, String desc) {
        this.type = type;
        this.initValue = initValue;
        this.monitor = monitor;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getInitValue() {
        return initValue;
    }

    public CommDataMonitor getMonitor() {
        return monitor;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, CommDataType> typeCatalog = Maps.newHashMap();

    static {
        for (CommDataType typeEnum : values()) {
            CommDataType oldEnum = typeCatalog.put(typeEnum.type, typeEnum);
            if (oldEnum != null) {
                throw new IllegalStateException(String.format("%s and %s have same type.", oldEnum, typeEnum));
            }
        }
    }

    public static CommDataType wrap(int type) {
        return typeCatalog.get(type);
    }
}
