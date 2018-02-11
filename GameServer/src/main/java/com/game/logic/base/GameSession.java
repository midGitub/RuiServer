package com.game.logic.base;

import com.game.framework.network.packet.AbstractPacket;
import com.game.framework.network.packet.protobuf.ICacheablePacket;
import com.game.framework.network.packet.protobuf.ResponsePacket;
import com.game.framework.network.session.IMessageWritable;
import com.game.framework.network.session.ISessionIdentiable;
import com.game.framework.utils.IpAddressUtils;
import com.game.logic.base.actor.Player;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 游戏session类
 * @author liguorui
 * @date 2016-01-06
 */
public class GameSession implements IMessageWritable, ISessionIdentiable {

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 平台
	 */
	private String platform;

	private Channel channel;

	/**
	 * 玩家id
	 */
	private long playerId;

	private Player player;

	/**
	 * 服务器
	 */
	private String server;

	private String ip;

	private long ipHashCode;

	public final static byte	STATUS_INIT			= 0; //默认没登陆
	public final static byte	STATUS_AFTER_LOGIN	= 1; //登陆后
	public final static byte	STATUS_LOGINGOUT	= 2;//下线后

	private AtomicInteger status = new AtomicInteger(STATUS_INIT);

	private AtomicBoolean refreshChannelSchedule = new AtomicBoolean();

	public GameSession(String account, String platform, Channel channel) {
		super();
		this.account = account;
		this.platform = platform;
		this.channel = channel;
		this.ip = IpAddressUtils.getIp(channel);
		this.ipHashCode = IpAddressUtils.hashCode(ip);
	}

	public GameSession(Channel channel) {
		this.channel = channel;
		this.ip = IpAddressUtils.getIp(channel);
		this.ipHashCode = IpAddressUtils.hashCode(ip);
	}

	private GameSession(String ip, int port) {
		this.channel = null;
		this.ip = ip;
		this.ipHashCode = IpAddressUtils.hashCode(ip);
	}

	public String getAccount() {
		return account;
	}

	public String getPlatform() {
		return platform;
	}

	public void sendPacket(AbstractPacket packet) {
		if (packet == null) {
			return;
		}

		if (player != null) {
//			write(packet);
		}
	}

	public void write(ResponsePacket packet) {
		if (packet != null) {
			channel.write(packet);
		}
	}

	public void writeAndFlush(ResponsePacket packet) {
		if (packet != null) {
			channel.writeAndFlush(packet);
		}
	}

	public void writeAndFlush(ICacheablePacket packet) {
		if (packet != null) {
			channel.writeAndFlush(packet);
		}
	}

	/**
	 * 延迟写
	 */
	public void writeSchedule(ResponsePacket packet) {
		ChannelFuture writeFuture = channel.write(packet);
		if (refreshChannelSchedule.compareAndSet(false,true)) {
			scheduleFlush(writeFuture);
		}
	}

	public void writeAndClose(ResponsePacket packet) {
		writeAndFlush(packet);
		close();
	}

	/**
	 * 把数据立即写，写完后立即关闭channel，如果写超时就关闭（1秒钟超时）
	 * @param o
	 */
	public void writeAndClose(Object o) {
		final ChannelFuture future = channel.writeAndFlush(o);
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				close();
			}
		});
		channel.eventLoop().schedule(new Runnable() {
			@Override
			public void run() {
				if (!future.isDone()) {
					close();
				}
			}
		}, 1, TimeUnit.SECONDS);
	}

	private void scheduleFlush(final ChannelFuture writeFuture) {
		channel.eventLoop().schedule(new Runnable() {
			@Override
			public void run() {
				if (refreshChannelSchedule.compareAndSet(true,false)) {
					channel.flush();
				}
			}
		}, 5, TimeUnit.MILLISECONDS);
	}

	public Channel getChannel() {
		return channel;
	}

	@Override
	public String toString() {
		return "GameSession [account=" + account + ", platform=" + platform + ", playerId=" + playerId + "]";
	}

	public void flush() {
		channel.flush();
	}

	public void close() {
		try {
			if (channel.isOpen() || channel.isActive()) {
				channel.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public long getSessionId() {
		return playerId;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public boolean isAfterLogin() {
		return status.get() == STATUS_AFTER_LOGIN;
	}

	public void toAfterLogin() {
		status.compareAndSet(STATUS_INIT, STATUS_AFTER_LOGIN);
	}

	public void toLogingOut() {
		status.set(STATUS_LOGINGOUT);
	}

	public boolean isLogintOut() {
		return status.get() == STATUS_LOGINGOUT;
	}

	public String getIp() {
		return ip;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getIpHashCode() {
		return ipHashCode;
	}

	public void setIpHashCode(long ipHashCode) {
		this.ipHashCode = ipHashCode;
	}

	public AtomicInteger getStatus() {
		return status;
	}

	public void setStatus(AtomicInteger status) {
		this.status = status;
	}
}