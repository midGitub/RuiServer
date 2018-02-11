package com.game.framework.time.impl;

import com.game.framework.time.TimeTriggerTask;

/**
 * 立即触发类型
 * @author liguorui
 * @date 2018/1/7 19:06
 */
public abstract class ImmediateTriggerTask implements TimeTriggerTask {

    private String taskName;
    private long triggerTime = System.currentTimeMillis();
    private boolean trigger;

    public ImmediateTriggerTask(String taskName) {
        this.taskName = taskName;
    }

    protected abstract void handler(long time);

    @Override
    public boolean canTrigger() {
        return !trigger;
    }

    @Override
    public void trigger(long time) {
        try {
            handler(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.trigger = true;
    }

    @Override
    public long getTriggerTime() {
        return triggerTime;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
