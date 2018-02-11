package com.game.framework.network.test.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端连接测试类
 * @author liguorui
 * @date 2016-01-06
 */
public class ClientSocket {

	private final static Logger logger = LoggerFactory.getLogger(ClientSocket.class);
	
	private String ip = "localhost";
	private int port = 8087;
	private int maxContentLength = 1024 * 64;

	public void start() {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group);
			bootstrap.channel(NioSocketChannel.class);
//			bootstrap.option(ChannelOption.TCP_NODELAY, true);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					ChannelPipeline pl = channel.pipeline();
					pl.addLast("HTTP_CLIENT_CODEC", new HttpClientCodec());
					pl.addLast("HTTP_CLIENT_AGGREGATOR", new HttpObjectAggregator(maxContentLength));
					pl.addLast("HTTP_CLIENT_CHUNKED", new ChunkedWriteHandler());
					pl.addLast("CLIENT_HANDLER", new ClientHandler());
				}
			});
			// 发起异步连接操作
	        ChannelFuture f = bootstrap.connect(ip, port).sync();
	        // 等待客户端链路关闭
			System.out.println("客户端启动完毕====");
			logger.info("客户端启动完毕");
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 // 优雅退出，释放NIO线程组
            group.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		new ClientSocket().start();
	}
}
