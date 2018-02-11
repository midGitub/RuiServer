package com.game.framework.time;

/**
 * @author liguorui
 * @date 2018/1/14 18:20
 */
public interface TriggerFuture {

    public static final TriggerFuture FINISH_FUTURE = new TriggerFuture() {

        @Override
        public boolean isFinish() {
            return true;
        }

        @Override
        public void cancel() {

        }
    };

    boolean isFinish();

    void cancel();
}
