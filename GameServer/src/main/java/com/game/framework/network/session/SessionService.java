package com.game.framework.network.session;

import com.game.framework.network.packet.protobuf.ResponsePacket;
import com.game.logic.base.GameSession;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * session管理
 * @author liguorui
 * @date 2016-01-06
 */
public class SessionService {

	private final static SessionService instance = new SessionService();

	/**
	 * 玩家id-->session
	 */
	private Map<Long, GameSession> id2Sessions = new ConcurrentHashMap<Long, GameSession>();

	/**
	 * channel-->session
	 */
	private Map<Channel, GameSession> channel2Sessions = new ConcurrentHashMap<Channel, GameSession>();

	/**
	 * channel-->玩家账号
	 */
	private Map<Channel, String> channel2Accounts = new ConcurrentHashMap<Channel, String>();

	/**
	 * 账号-->session
	 */
	private Map<String, GameSession> account2Sessions = new ConcurrentHashMap<String, GameSession>();

	private SessionService() {
	}

	public static SessionService getInstance() {
		return instance;
	}
	
	public void register(long id, String account, Channel channel) {
		GameSession session = channel2Sessions.get(channel);
		if (session != null) {
			session.setPlayerId(id);
			session.setAccount(account);
			account2Sessions.put(account, session);
			id2Sessions.put(id, session);
		}
	}

	public boolean register(Channel channel, GameSession gameSession) {
		GameSession oldGameSession = channel2Sessions.get(channel);
		if (oldGameSession != null) {
			return false;
		}
		channel2Sessions.put(channel, gameSession);
		return true;
	}

	public void clearChannel(Channel channel) {
		channel2Sessions.remove(channel);
	}

	public GameSession getSession(int id) {
		return id2Sessions.get(id);
	}

	public GameSession getSession(Channel channel) {
		return channel2Sessions.get(channel);
	}

	public boolean containAccount(String account) {
		return account2Sessions.containsKey(account);
	}

	public GameSession getByAccount(String account) {
		return account2Sessions.get(account);
	}

	public void clear(String account) {
		GameSession remove = account2Sessions.remove(account);
		if (remove != null) {
			id2Sessions.remove(remove.getPlayerId());
			channel2Sessions.remove(remove.getChannel());
			channel2Accounts.remove(remove.getChannel());
			remove.close();
		}
	}

	public void bind(Channel channel, String account) {
		channel2Accounts.put(channel, account);
	}

	public String getAccount(Channel channel) {
		return channel2Accounts.get(channel);
	}

	public boolean HasBinded(Channel channel) {
		return channel2Accounts.containsKey(channel);
	}

	private boolean contains(	ISessionIdentiable[] excludes,
								ISessionIdentiable sessionId) {
		for (ISessionIdentiable exclude : excludes) {
			if (exclude.getSessionId() == sessionId.getSessionId())
				return true;
		}
		return false;
	}

	public Map<Long, GameSession> getId2Sessions() {
		return id2Sessions;
	}

	public void writeToAndFlush(ISessionIdentiable ticker, ResponsePacket packet) {
		GameSession gameSession = id2Sessions.get(ticker.getSessionId());
		if (gameSession != null) {
			gameSession.writeAndFlush(packet);
		}
	}

	public void writeTo(ISessionIdentiable ticker, ResponsePacket packet) {
		GameSession gameSession = id2Sessions.get(ticker.getSessionId());
		if (gameSession != null) {
			gameSession.write(packet);
		}
	}

	public void flush(ISessionIdentiable ticker) {
		GameSession gameSession = id2Sessions.get(ticker.getSessionId());
		if (gameSession != null) {
			gameSession.flush();
		}
	}

	/**
	 * 获取IP地址
	 */
	public String getIp(Channel channel) {
		return ((InetSocketAddress)channel.remoteAddress()).getAddress().toString().substring(1);
	}

}
