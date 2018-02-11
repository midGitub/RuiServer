package com.game.logic.logger;

import com.game.framework.logger.domain.PlayerLogEvent;
import com.game.logic.base.actor.Player;

/**
 * @author liguorui
 * @date 2017/8/20 17:38
 */
public class GuildLogEvent extends PlayerLogEvent {

    private long guildId;

    private String name;

    public GuildLogEvent(Player player, long guildId, String name) {
        super(player);
        this.guildId = guildId;
        this.name = name;
    }

    @Override
    public String message() {
        StringBuilder message = messagePlayer();
        message.append(",guildId:").append(guildId);
        message.append(",name:").append(name);
        return message.toString();
    }
}
