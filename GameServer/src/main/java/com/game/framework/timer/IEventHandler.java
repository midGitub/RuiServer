package com.game.framework.timer;

//import org.apache.zookeeper.Watcher.Event.EventType;

/**
 * @author liguorui
 * @date 2015年10月9日 上午11:25:15
 */
public interface IEventHandler {
	public String getHandlerName();

	public void handle(int playerId);

//	public EventType getType();

}
