package com.game.logic.commdata.entity;

import com.game.framework.asyncdb.Persistent;
import com.game.framework.asyncdb.orm.BaseDBEntity;
import com.game.logic.commdata.CommDataKey;
import com.game.logic.commdata.CommDataMonitor;
import com.game.logic.commdata.CommDataType;
import com.game.logic.commdata.DataStatus;
import com.game.logic.commdata.accesser.IDataAccessor;
import com.game.logic.commdata.dao.PlayerCommDataDao;

import javax.persistence.*;

/**
 * @author liguorui
 * @date 2018/1/15 00:11
 */
@Entity
@Table(name = "PlayerCommData")
@IdClass(CommDataKey.class)
//@Persistent(syncClass = Playercom.class)
public class PlayerCommData {

    @Id
    private long playerId;

    @Id
    private int type;

    @Column(columnDefinition = "text", nullable = true)
    private String value;
    private String monitorParam;

    @Transient
    private CommDataType typeEnum;
    @Transient
    private CommDataMonitor monitor;
    @Transient
    private DataStatus status;
    @Transient
    private boolean dirty = false;

    public PlayerCommData() {}

    public PlayerCommData(long playerId, CommDataType typeEnum) {
        this(playerId, typeEnum.getType(), typeEnum.getInitValue());
    }

    public PlayerCommData(long playerId, int type, String value) {
        this(playerId, type, value, "");
    }

    public PlayerCommData(long playerId, int type, String value, String monitorParam) {
        this.playerId = playerId;
        this.type = type;
        this.value = value;
        this.monitorParam = monitorParam;
//        markNotInserted();
//        wrapType(type);
    }

    public void reset() {
        setValue(typeEnum.getInitValue());
    }

    public void onLoad() {
        monitorLoad();
    }

    public void onMidnight(IDataAccessor accessor) {
        monitorMidnight(accessor);
    }

    public Integer getInt() {
        return Integer.valueOf(getValue());
    }

    public void setInt(Integer intValue) {
        setValue(intValue.toString());
    }

    public void setInt(int intValue) {
        setValue(String.valueOf(intValue));
    }

    public Long getLong() {
        return Long.valueOf(getValue());
    }

    public void setLong(Long longValue) {
        setValue(longValue.toString());
    }

    public void setLong(long longValue) {
        setValue(String.valueOf(longValue));
    }

    public String getValue() {
        monitorRead();
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        monitorWrite();
        markDirty();
        updateValue();
    }

    /**
     * 即时入库
     */
    private void updateValue() {
//        if (this.isNotInserted()) {
//            this.insert();
//            this.markInserted();
//        } else {
//            this.update();
//        }
        this.markClean();
    }

    public void markClean() {
        dirty = false;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void markNotInserted() {
        status = DataStatus.NOT_INSERTED;
    }

    public void markInserted() {
        status = DataStatus.INSERTED;
    }

    public boolean isNotInserted() {
        return status == DataStatus.NOT_INSERTED;
    }

    private void markDirty() {
        dirty = true;
    }

    private void monitorRead() {
        if (monitor != null) {
            monitor.onRead(this);
        }
    }

    private void monitorWrite() {
        if (monitor != null) {
            monitor.onWrite(this);
        }
    }

    private void monitorMidnight(IDataAccessor accessor) {
        if (monitor != null) {
            monitor.onMidnight(this, accessor);
        }
    }

    private void monitorLoad() {
        if (monitor != null) {
            monitor.onLoad(this);
        }
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        wrapType(type);
    }

    public String getMonitorParam() {
        return monitorParam;
    }

    public void setMonitorParam(String monitorParam) {
        this.monitorParam = monitorParam;
    }

    public CommDataType getTypeEnum() {
        return typeEnum;
    }

    private void wrapType(int type) {
        typeEnum = CommDataType.wrap(type);
//        checkNotNull(typeEnum, "type:%s", type);
//        monitor = typeEnum.getMonitor();
    }

//    @Override
//    public void deserialize() {
//        wrapType(type);
//    }
}
