package com.game.framework.network.netty.websocket;

import com.game.framework.network.code.websocket.WebGamePacketDecoder;
import com.game.framework.network.netty.GamePacketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * websocket netty服务
 * @author liguorui
 * @date 2017/8/6 00:12
 */
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static final int MAX_CONTENT_LENGTH = 60 * 1024;

    public static final byte[] HEADERS = { '2', '0', '1', '7' };

//    private static final String IP = "116.62.115.70";

    private static final int PORT = 8087;

    private static WebSocketServer webSocketServer = new WebSocketServer();

    private WebSocketServer() {}

    public static WebSocketServer getInstance() {
        return webSocketServer;
    }

    final EventLoopGroup bossGroup = new NioEventLoopGroup();
    final EventLoopGroup workerGroup = new NioEventLoopGroup();

    public void start() {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(WebsocketChatServerInitializer());
//      b.option(ChannelOption.TCP_NODELAY, true);
      b.option(ChannelOption.SO_REUSEADDR, true);
        b.option(ChannelOption.SO_BACKLOG, 128);
        b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        b.childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture future = b.bind(PORT).sync();
            logger.info("WebSocketServer server start success bind port {}", PORT);
            System.out.println("WebsocketServer port: " + PORT + " 启动完了");
            // 等待服务器socket 关闭 。
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error(e.toString());
        } finally {
            close();
        }
    }

    private ChannelHandler WebsocketChatServerInitializer() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pl = ch.pipeline();
                pl.addLast("HTTP_CODEC", new HttpServerCodec());
                pl.addLast("HTTP_AGGREGATOR", new HttpObjectAggregator(MAX_CONTENT_LENGTH));
                pl.addLast("SERVER_SOCKET_PROTO", new WebSocketServerProtocolHandler("/"));
                pl.addLast("HTTP_CHUNKED", new ChunkedWriteHandler());
//                pl.addLast("SERVER_HANDLER", new WebSocketServerHandler());
                pl.addLast("WEB_GAME_DECODER",new WebGamePacketDecoder());
                pl.addLast("WEB_GAME_PACKET_HANDLER",new GamePacketHandler());
            }
        };
    }

//    public static void main(String[] args) {
//        new WebSocketServer().start();
//    }

    public void close() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
