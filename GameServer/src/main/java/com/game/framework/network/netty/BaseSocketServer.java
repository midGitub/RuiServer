package com.game.framework.network.netty;

import com.game.framework.network.code.GamePacketDecoder;
import com.game.framework.network.code.GamePacketEncoder;
import com.game.framework.network.code.GamePacketEncoderForResponseCacheWriteDataPacket;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础socket服务器
 * @author liguorui
 * @date 2017/6/26 14:25
 */
public class BaseSocketServer {

    private final static Logger logger = LoggerFactory.getLogger(BaseSocketServer.class);

    private EventLoopGroup bossGroup = new NioEventLoopGroup(8);
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    public static final byte[] HEADERS = { '2', '0', '1', '7' };
    public static int	PORT = 8087;

    private static BaseSocketServer baseSocketServer = new BaseSocketServer();

    private BaseSocketServer() {}

    public static BaseSocketServer getInstance() {
        return baseSocketServer;
    }

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        try{
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(createBaseChannelHandler());
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.SO_REUSEADDR, true);
            bootstrap.bind(PORT).sync();
            logger.info("System server bind port {}", PORT);
        } catch (Exception e) {
            e.printStackTrace();
            closeEventLoopGroup();
            logger.error("start server error when initializing the server.", e);
        }
    }

    private ChannelHandler createBaseChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(
                        new IdleStateHandler(0,0,180),
                        new WriteTimeoutHandler(60),
                        new GamePacketDecoder(HEADERS),
                        new GamePacketHandler(),
                        new GamePacketEncoder(HEADERS),
                        new GamePacketEncoderForResponseCacheWriteDataPacket());
            }
        };
    }

    /**
     * 停止服务器
     */
    public void stop() {
        try {
            closeEventLoopGroup();
        } catch (Exception e) {
            logger.error("An exception was occured when stoping the server.", e);
        }
    }

    private void closeEventLoopGroup() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
