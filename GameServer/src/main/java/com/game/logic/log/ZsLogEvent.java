package com.game.logic.log;

import com.game.framework.logger.domain.PlayerLogEvent;
import com.game.logic.base.actor.Player;

/**
 * 转生日志
 * @author liguorui
 * @date 2018/2/4 00:52
 */
public class ZsLogEvent extends PlayerLogEvent {

    private int level;

    private long oldExp;

    private long newExp;

    public ZsLogEvent(Player player, int level, long oldExp, long newExp) {
        super(player);
        this.level = level;
        this.oldExp = oldExp;
        this.newExp = newExp;
    }

    @Override
    public String message() {
        return messagePlayerActor()
                .appendKeyValue("level", level)
                .appendKeyValue("oldExp", oldExp)
                .appendKeyValue("newExp", newExp)
                .toString();
    }
}
