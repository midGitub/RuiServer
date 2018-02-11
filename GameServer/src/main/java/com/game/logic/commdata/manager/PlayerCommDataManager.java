//package com.game.logic.commdata.manager;
//
//import com.game.framework.asyncdb.Synchronizer;
//import com.game.logic.commdata.dao.PlayerCommDataDao;
//import com.game.logic.commdata.entity.PlayerCommData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @author liguorui
// * @date 2018/1/29 00:22
// */
//@Component
//public class PlayerCommDataManager implements Synchronizer<PlayerCommData> {
//
//    @Autowired
//    private PlayerCommDataDao playerCommDataDao;
//
//    @Override
//    public boolean insertData(PlayerCommData object) {
//        return playerCommDataDao.insertData(object);
//    }
//
//    @Override
//    public boolean updateData(PlayerCommData object) {
//        return playerCommDataDao.updateData(object);
//    }
//
//    @Override
//    public boolean deleteData(PlayerCommData object) {
//        return playerCommDataDao.deleteData(object);
//    }
//}
