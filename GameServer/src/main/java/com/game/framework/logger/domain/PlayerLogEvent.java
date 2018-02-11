package com.game.framework.logger.domain;

import com.game.logic.base.actor.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liguorui
 * @date 2017/8/20 17:25
 */
public abstract class PlayerLogEvent extends LogEvent {

    protected String account;

    protected long playerId;

    protected String playerName;

    protected long time;

    protected String from_channel;

    public PlayerLogEvent(String account, long playerId, String playerName) {
        this(account, playerId, playerName, "");
    }

    public PlayerLogEvent(String account, long playerId, String playerName, String from_channel) {
        super();
        this.account = account;
        this.playerId = playerId;
        this.playerName = playerName;
        this.time = System.currentTimeMillis();
        this.from_channel = from_channel;
    }

    public PlayerLogEvent(Player player) {
        this(player.getAccount(), player.getPlayerId(), player.getPlayerName());
    }

    public long getPlayerId() {
        return playerId;
    }

    public long getTime() {
        return time;
    }

    public LogAppender messagePlayerActor() {
        return LogAppender.create(256)
                .appendKeyValue("time", time)
                .appendKeyValue("pl", "") //平台
                .appendKeyValue("sid", "") //区服ID
                .appendKeyValue("ext", "")
                .appendKeyValue("account", account)
                .appendKeyValue("playerId", playerId)
                .appendKeyValue("playerName", playerName)
                ;
    }

    public StringBuilder messagePlayer() {
        StringBuilder message = new StringBuilder(256);
        message.append("account:").append(account);
        message.append(",playerId:").append(playerId);
        message.append(",playerName:").append(playerName);
        message.append(",time:").append(time);
        message.append(",date:").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(time)));
        return message;
    }
}
