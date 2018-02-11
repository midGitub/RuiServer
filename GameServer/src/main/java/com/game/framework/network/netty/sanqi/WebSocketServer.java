package com.game.framework.network.netty.sanqi;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * websocket启动类
 * @author liguorui
 * @date 2018/1/7 23:38
 */
public class WebSocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

    private static WebSocketServer webSocketServer = new WebSocketServer();

    private static final int MAX_CONTENT_LENGTH = 60 * 1024;

    private static final int PORT = 8087;

    private WebSocketServer() {}

    public static WebSocketServer getInstance() {
        return webSocketServer;
    }

    private EventLoopGroup bossGroup = new NioEventLoopGroup(8);
    private EventLoopGroup workGroup = new NioEventLoopGroup();

    public void start() {
//        final IpCountHandler ipCountHandler = new IPCountHandler();
//        final GameServerHandler authHandler = new GameServerHandler();
//        final ResponseEncoder responseEncoder = new ResponseEncoder();
//        final GameSeverIdleHandler idleHandler = new GameSeverIdleHandler();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(websocketChatServerInitializer());
        b.childOption(ChannelOption.SO_KEEPALIVE, true);
        b.option(ChannelOption.SO_REUSEADDR, true);
        b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
//      b.handler(new LoggingHanler(LogLevel.INFO));
//      b.childOption(ChannelOption.TCP_NODELAY, true);

        try {
            ChannelFuture future = b.bind(PORT).sync();
            LOGGER.info("webSocket server bind port {}", PORT);
            System.out.println("WebsocketServer port: " + PORT + " 启动完了");
            // 等待服务器socket 关闭 。
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private ChannelHandler websocketChatServerInitializer() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("HTTP_CODEC", new HttpServerCodec());
                pipeline.addLast("HTTP_AGGREGATOR", new HttpObjectAggregator(MAX_CONTENT_LENGTH));
                pipeline.addLast("SERVER_SOCKET_PROTO", new WebSocketServerProtocolHandler("/"));
                pipeline.addLast("SERVER_HANDLER", new WebSocketServerHandler());
                pipeline.addLast(new ByteToWebSocketFrameEncoder());
                pipeline.addLast(new BaseFrameDecoder());
                pipeline.addLast(new PacketDecoder());
                pipeline.addLast(new GameServerHandler());
//                pipeline.addLast(new ResponseEncoder());
            }
        };
    }

    public void close() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}
