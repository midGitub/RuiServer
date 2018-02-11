package com.game.framework.network.test.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础socket服务器测试类
 * @author liguorui
 */
public class ServerSocket {

	private final static Logger logger = LoggerFactory.getLogger(ServerSocket.class);
	
	public static final byte[] HEADERS = { '2', '0', '1', '6' };
	public static final int	PORT = 8088;
	
	EventLoopGroup bossGroup = new NioEventLoopGroup();
	EventLoopGroup workerGroup = new NioEventLoopGroup();
	
	public void start() {
		ServerBootstrap bootstrap = new ServerBootstrap();
		try {
			bootstrap.group(bossGroup,workerGroup)
						.channel(NioServerSocketChannel.class)
						.childHandler(new ChannelInitializer<SocketChannel>() {
							@Override
							protected void initChannel(SocketChannel ch) throws Exception {
								ch.pipeline().addLast(new ServerHandler());
							}
						});
			bootstrap.option(ChannelOption.TCP_NODELAY, true);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			// 绑定端口，同步等待成功
			ChannelFuture f = bootstrap.bind(PORT).sync();
			logger.info("服务器启动完毕");
			// 等待服务端监听端口关闭
//            f.channel().closeFuture().sync();
		} catch(Exception e) {
			e.printStackTrace();
			closeEventLoopGroup();
		}
//		finally {
//			closeEventLoopGroup();
//		}
//		logger.info("{} started at {}", SocketServer.class, PORT);
	}
	
	private void closeEventLoopGroup() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
	
	public static void main(String[] args) {
		new ServerSocket().start();
	}
}
