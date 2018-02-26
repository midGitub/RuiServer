package com.game;

import com.game.framework.base.repository.BaseRepositoryFactoryBean;
import com.game.framework.listener.ListenerManager;
import com.game.framework.network.netty.websocket.WebSocketServer;
import com.game.framework.threads.IServerClose;
import com.game.framework.base.ServerInit;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.*;

/**
* 游戏启动入口
*/
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.game.logic", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EntityScan(basePackages = "com.game.logic")
public class GameApplication {

    protected final static Logger LOGGER = LoggerFactory.getLogger(GameApplication.class);

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(GameApplication.class, args);
//        initGameServer();
//		registerShutdownHook();
    }

    /**
     * 初始化游戏服务器
     */
    private static void initGameServer() {
        Preconditions.checkNotNull(context);
//		SessionService.getInstance().initCachePacketConverter();
        // 初始化游戏包处理器
//		GamePacketProcesser.init();
//
//		LOGGER.info("Initialized AsyncInsertOrUpdateService finish !");
//		// 初始化指令
//		InitRequestPacket.init("com.game.logic.packet");
//		PacketFactory.getInstance().init(new PacketScaner().filter(context));
//		initListener(context);
//		InterfaceInvoker.invoke(context, ILoadable.class, "load");
//		LOGGER.info("Initialized ILoadable finish !");
//		LOGGER.info("Initialized IAssemable finish !");
////		TimerSchedulerHandler.getInstance().taskStart(context);
//		LOGGER.info("Initialized TimerScheduler finish !");
//		serverStart();
//		BaseSocketServer.getInstance().start();
        WebSocketServer.getInstance().start();
        LOGGER.info("Start Netty Server Finish at " + new Date());
        System.err.println(new Date() + "server started ! curTime:" + System.currentTimeMillis());
    }

    private static void serverStart() {
        List<ServerInit> serverInits = new ArrayList<>(context.getBeansOfType(ServerInit.class).values());
        Collections.sort(serverInits, new Comparator<ServerInit>() {
            @Override
            public int compare(ServerInit o1, ServerInit o2) {
                return o1.getOrder() == o2.getOrder() ? 0 : o1.getOrder() > o2.getOrder() ? 1 : -1;
            }
        });
        for (ServerInit serverInit : serverInits) {
            try {
                serverInit.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注册关闭服务器钩子，关闭服务器前触发
     */
    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                onStop();
            }
        });
    }

    private static void onStop() {
        onCloseServer();
        WebSocketServer.getInstance().close();
        context.close();
        LOGGER.info("server is stop !");
        System.err.println("server stop");
    }

    /**
     * 关闭服务器前触发
     */
    private static void onCloseServer() {
        List<IServerClose> types = ListenerManager.getInstance().getListeners(IServerClose.class);
        for (IServerClose type : types) {
            try {
                type.onClose();
            } catch (Exception e) {
                LOGGER.error(	"Error occured when close server , exception={}",e);
            }
        }
    }

    private static void initListener(ApplicationContext applicationContext) {
        ListenerManager.getInstance().init(applicationContext);
    }

}
