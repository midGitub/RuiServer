package com.game.logic.base.actor;

import com.game.framework.message.IMessage;
import com.game.framework.message.MessageHandler;
import com.game.logic.base.GameSession;

/**
 * @author liguorui
 * @date 2018/1/7 23:07
 */
public class GateKeeper extends MessageHandler<GateKeeper> {

    private static int NUM = 10;

    private static GateKeeper[] gateKeepers = new GateKeeper[NUM];

    static {
        for (int i = 0; i < gateKeepers.length; i++) {
            gateKeepers[i] = new GateKeeper();
        }
    }

    public static void executeMessage(final GameSession session, final IMessage<GateKeeper> message) {
        gateKeepers[hashIndex(session)].addMessage(message);
    }

    private static int hashIndex(GameSession session) {
        int index = 0;
        if (session.getAccount() == null) {
            index = (int)(session.getIpHashCode() % NUM);
        } else {
            index = session.getAccount().hashCode() % NUM;
        }
        return Math.abs(index);
    }
}
