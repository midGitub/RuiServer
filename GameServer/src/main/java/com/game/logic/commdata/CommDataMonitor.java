package com.game.logic.commdata;

import com.game.logic.commdata.accesser.IDataAccessor;
import com.game.logic.commdata.entity.PlayerCommData;
import org.apache.commons.lang3.StringUtils;

/**
 * @author liguorui
 * @date 2018/2/5 18:22
 */
public enum CommDataMonitor {

    DAILY() {

        @Override
        public void onLoad(PlayerCommData data) {
            if (StringUtils.isBlank(data.getMonitorParam())) {
                return;
            }
            int lastWriteTimestamp = Integer.valueOf(data.getMonitorParam());
//            int lastWriteMorningTime = TimeUtils.getMorningTime(lastWriteTimestamp);
//            if (lastWriteMorningTime != Timeutils.getMorningTime()) {
//                data.reset();
//            }
        }

        @Override
        public void onWrite(PlayerCommData data) {
//            data.setMonitorParam(String.valueOf(TimeUtils.timestamp()));
        }

        @Override
        public void onMidnight(PlayerCommData data, IDataAccessor accessor) {
            data.reset();
            accessor.reset();
        }
    }
    ;

    public void onRead(PlayerCommData data) {}
    public void onWrite(PlayerCommData data) {}
    public void onMidnight(PlayerCommData data, IDataAccessor accessor) {}
    public void onLoad(PlayerCommData data) {}
}
