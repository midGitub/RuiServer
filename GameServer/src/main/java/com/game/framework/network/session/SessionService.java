package com.game.framework.network.session;

import com.game.framework.network.netty.BaseSocketServer;
import com.game.framework.network.packet.ResponsePacketCacheWriteData;
import com.game.framework.network.packet.protobuf.ICacheablePacket;
import com.game.framework.network.packet.protobuf.ResponsePacket;
import com.game.framework.utils.IObjectConverter;
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

	private IObjectConverter<ResponsePacket, ICacheablePacket> converter;

	private SessionService() {
	}

	public void setCacheWraper(IObjectConverter<ResponsePacket, ICacheablePacket> converter) {
		this.converter = converter;
	}

	public static SessionService getInstance() {
		return instance;
	}
	
	public void initCachePacketConverter() {
		setCacheWraper(new IObjectConverter<ResponsePacket, ICacheablePacket>() {
			@Override
			public ICacheablePacket convert(ResponsePacket s) {
				return new ResponsePacketCacheWriteData(s, BaseSocketServer.HEADERS);
			}
		});
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

	public void writeAndFlushToAll(ResponsePacket packet) {
		writeAndFlushToMultiWithIdentiable(packet, id2Sessions.values());
	}

	/**
	 * 公会聊天,广发给公会所有在线会员
	 */
	public void writeAndFlushToSociety(ResponsePacket packet, List<Integer> list) {
		ConcurrentHashMap<Integer, GameSession> societyid2Sessions = new ConcurrentHashMap<Integer, GameSession>();
		for (Integer id : list) {
			GameSession gameSession = id2Sessions.get(id);
			if (gameSession != null) {
				societyid2Sessions.put(id, gameSession);
			}
		}
		writeAndFlushToMultiWithIdentiable(packet, societyid2Sessions.values());
	}

	public void writeAndFlushToMultiWithIdentiable(	ResponsePacket packet,
													Iterable<? extends ISessionIdentiable> sessions,
													ISessionIdentiable... excludes) {
		ICacheablePacket responseCacheWriteDataPacket = converter.convert(packet);
		for (ISessionIdentiable sessionId : sessions) {
			if (excludes != null && contains(excludes, sessionId)) {
				continue;
			}
			long sessionId2 = sessionId.getSessionId();
			GameSession gameSession = id2Sessions.get(sessionId2);
			if (gameSession != null) {
				gameSession.writeAndFlush(responseCacheWriteDataPacket);
			}
		}
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

	public void writeAndFlushToAll(ResponsePacket packet, ISessionIdentiable... excludes) {
		writeAndFlushToMultiWithIdentiable(packet, id2Sessions.values(), excludes);
	}

	/**
	 * 获取IP地址
	 */
	public String getIp(Channel channel) {
		return ((InetSocketAddress)channel.remoteAddress()).getAddress().toString().substring(1);
	}

}
