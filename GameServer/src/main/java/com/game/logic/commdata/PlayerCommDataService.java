//package com.game.logic.commdata;
//
//import com.game.logic.base.actor.Player;
//import com.game.logic.commdata.entity.PlayerCommData;
//import com.game.logic.commdata.manager.PlayerCommDataManager;
//import com.game.logic.listener.login.AfterLoginListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @author liguorui
// * @date 2018/2/5 21:46
// */
//@Component
//public class PlayerCommDataService implements AfterLoginListener {
//
//    @Autowired
//    private PlayerCommDataManager playerCommDataManager;
//
//    private void loadCommData(Player player) {
//        PlayerCommDataHandler playerCommDataHandler = player.getPlayerCommDataHandler();
//
//        if (playerCommDataHandler.isInitialized()) {
//            return;
//        }
//
//        playerCommDataHandler.setInitialized();
//        long playerId = player.getPlayerId();
//        List<PlayerCommData> all = playerCommDataManager.selectAll(playerId);
//        for (PlayerCommData data : all) {
//            data.markInserted();
//            data.onLoad();
//            playerCommDataHandler.cache(data);
//        }
//    }
//
//    private void openCommData(Player player) {
//        player.getPlayerCommDataHandler().setInitialized();
//    }
//
//    @Override
//    public void onLogin(Player player) {
//        loadCommData(player);
//    }
//}
