package com.game.framework.threads;

import com.game.framework.listener.annotation.Listener;

/**
 *JVM关闭触发事件
 * @author liguorui
 * @date 2015年2月4日 下午11:23:19
 */
@Listener
public interface IServerClose {

	public void onClose();
}
