package com.game.framework.network.packet;

import com.game.framework.message.IMessage;
import com.game.framework.network.packet.annotation.PacketHandler;
import com.game.framework.network.packet.protobuf.RequestPacket;
import com.game.logic.base.GameSession;
import com.game.logic.base.actor.GateKeeper;
import com.game.logic.base.actor.Player;
import com.game.logic.packet.RequestBeforeLoginPacket;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author liguorui
 * @date 2018/1/7 21:06
 */
@Component
public class PacketHandlerService implements InitializingBean, ApplicationContextAware {

    private ApplicationContext ctx;

    private Map<Class<?>, PacketHandlerWrapper> packetHandlers;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> handlers = ctx.getBeansWithAnnotation(PacketHandler.class);
        Map<Class<?>, PacketHandlerWrapper> packetHandlers = Maps.newHashMap();
        for (Object handler : handlers.values()) {
            Class<?>[] interfaces = handler.getClass().getInterfaces();
            for (Class<?> interf : interfaces) {
                if (!interf.isAnnotationPresent(PacketHandler.class)) {
                    continue;
                }
                Method[] methods = interf.getMethods();
                for (Method method : methods) {
                    if (isPacketMethod(method)) {
                        Class<?>[] paramType = method.getParameterTypes();
                        PacketHandlerWrapper wrapper = new PacketHandlerWrapper(handler, method);
                        packetHandlers.put(paramType[1], wrapper);
                    }
                }
            }
        }
        this.packetHandlers = packetHandlers;
    }

    private boolean isPacketMethod(Method method) {
        Class<?>[] paramType = method.getParameterTypes();
        if (paramType.length != 2) {
            return false;
        }
        if (!AbstractPacket.class.isAssignableFrom(paramType[1])) {
            return false;
        }
        return GameSession.class == paramType[0] || Player.class == paramType[0];
    }

    public void handle(final GameSession session, final AbstractPacket packet) {
        final PacketHandlerWrapper wrapper = packetHandlers.get(packet.getClass());
        Preconditions.checkNotNull(wrapper, "NoPacketHandler for %", packet.getClass().getSimpleName());
        if (packet instanceof RequestBeforeLoginPacket) {
            handleSessionPacket(wrapper, session, packet);
            return;
        }
        if (packet instanceof RequestPacket) {
            handlePlayerPacket(wrapper, session.getPlayer(), packet);
            return;
        }
    }

    private void handlePlayerPacket(final PacketHandlerWrapper wrapper, final Player player, final AbstractPacket packet) {
        player.addMessage(new IMessage<Player>() {
            @Override
            public void execute(Player h) {
                try {
                    wrapper.invoke(player, packet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void handleSessionPacket(final PacketHandlerWrapper wrapper, final GameSession session, final AbstractPacket packet) {
        GateKeeper.executeMessage(session, new IMessage<GateKeeper>() {
           @Override
           public void execute(GateKeeper h) {
               try {
                   wrapper.invoke(session, packet);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        });
    }
}
