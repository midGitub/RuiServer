package com.game.logic.base;

import com.game.framework.asyncdb.AsyncDBService;
import com.game.framework.network.packet.PacketHandlerService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;

/**
 * @author liguorui
 * @date 2018/1/14 00:47
 */
public class Context implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static PacketHandlerService packetHandlerService;

    private static AsyncDBService asyncDBService;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public final static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public final static <T> Collection<T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz).values();
    }

    public final static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    @Autowired
    public void setPacketHandlerService(PacketHandlerService packetHandlerService) {
        Context.packetHandlerService = packetHandlerService;
    }

    public static PacketHandlerService getPacketHandlerService() {
        return Context.packetHandlerService;
    }

    @Autowired
    public void setAsyncDBService(AsyncDBService asyncDBService) {
        Context.asyncDBService = asyncDBService;
    }

    public static AsyncDBService getAsyncDBService() {
        return Context.asyncDBService;
    }
}
