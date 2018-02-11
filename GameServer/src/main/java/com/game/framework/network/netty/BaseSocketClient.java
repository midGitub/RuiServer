package com.game.framework.network.netty;

import com.game.framework.network.packet.AbstractPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty客户端
 * @author liguorui
 * @date 2017/7/16 23:16
 */
public class BaseSocketClient {

	public static EventLoopGroup workerGroup = new NioEventLoopGroup();
	public static Bootstrap clientBootstrap = new Bootstrap();
	private static Channel channel;
	private static ChannelFuture channelFuture;

	public static final byte[] HEADERS = { '2', '0', '1', '7' };

	public BaseSocketClient() {
		try{
			clientBootstrap.group(workerGroup);
			clientBootstrap.channel(NioSocketChannel.class);
			clientBootstrap.option(ChannelOption.SO_KEEPALIVE,true);
			clientBootstrap.option(ChannelOption.TCP_NODELAY,true);
			clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
//					ch.pipeline().addLast(new ClientHandlet());
				}
			});
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ChannelFuture connect(String host,int port) {
		return clientBootstrap.connect(host,port);
	}


	public boolean isValid() {
		return channel != null && channel.isActive();
	}

	public void stop() {
		channel.close();
	}

	public void writeMessage(AbstractPacket packet) {
		channel.write(packet);
	}

	public void writeMessageAndFlush(AbstractPacket packet) {
		channel.writeAndFlush(packet);
	}

	public static void main(String[]args) throws Exception {
		BaseSocketClient client = new BaseSocketClient();
		channelFuture = client.connect("127.0.0.1",8087);
		channelFuture.channel().closeFuture().sync();
		channel = channelFuture.channel();

//
//		ByteBuf data = PooledByteBufAllocator.DEFAULT.buffer(100);
//		data.writeBytes(HEADERS);
//		data.writeShort(2);
//		data.writeShort(1200);
//		data.writeByte(1);
//		data.writeInt(9991);
//		AbstractPacket createPacket = PacketFactory.getInstance().createPacket(data);
//		client.writeMessageAndFlush(createPacket);
//		for (int i=0;i<10;i++) {
//			ChannelFuture f = client.connect("127.0.0.1",8029);
//		}
	}

	@Override
	public String toString() {
		return "BaseSocketClient [channel=" + channel + "]";
	}
}
