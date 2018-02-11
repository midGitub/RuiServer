package com.game.logic.base.actor;

import com.game.framework.message.MessageHandler;
import com.game.logic.base.GameSession;
import com.game.logic.commdata.PlayerCommDataHandler;

/**
 * @author liguorui
 * @date 2018/1/7 18:21
 */
public class Player extends MessageHandler<Player> {

    private GameSession session;

    private long playerId;

    private PlayerCommDataHandler playerCommDataHandler;

    public Player() {
        playerCommDataHandler = PlayerCommDataHandler.create(this);
    }

    public GameSession getSession() {
        return session;
    }

    public void setSession(GameSession session) {
        this.session = session;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getAccount() {
        return "";
    }

    public String getPlayerName() {
        return "";
    }

    public boolean isRobot() {
        return false;
    }

    public PlayerCommDataHandler getPlayerCommDataHandler() {
        return this.playerCommDataHandler;
    }

    public void setPlayerCommDataHandler(PlayerCommDataHandler playerCommDataHandler) {
        this.playerCommDataHandler = playerCommDataHandler;
    }
}
