package com.game.logic.listener.login;

import com.game.framework.listener.annotation.Listener;
import com.game.logic.base.actor.Player;

/**
 * 登陆成功监听器
 * @author liguorui
 * @date 2016年7月2日 下午2:25:23
 */
@Listener
public interface AfterLoginListener {

	void onLogin(Player player);
}
