package com.game.logic.init;

import com.game.logic.Person;
import com.game.logic.PersonRepository;
import com.game.logic.article.service.ArticleService;
import com.game.logic.commdata.dao.PlayerCommDataDao;
import com.game.logic.commdata.entity.PlayerCommData;
import com.game.logic.player.dao.PlayerEntityDao;
import com.game.logic.player.entity.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 服务器启动操作数据库
 * @author liguorui
 */
@Component
public class InitSQLData implements CommandLineRunner {
    @Autowired
    ArticleService articleService;

    @Autowired
    private PlayerEntityDao playerEntityDao;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PlayerCommDataDao playerCommDataDao;

//    @Autowired
//    private PlayerCommDataManager playerCommDataManager;

    @Override
    public void run(String... args) throws Exception {
//        insertSqlData();
//        Thread.sleep(30000);
//        updateSqlData();
//        Thread.sleep(30000);
//        DeleteSqlData();
        long cur = System.currentTimeMillis();
//        testDb();
        long now = System.currentTimeMillis();
        System.err.println("now-cur="+(now - cur));
//        testCommdata();
//        findData();
    }

    public void findData() {
//        for (PlayerCommData playerCommData : playerCommDataDao.getByPlayerId(1)) {
//            System.out.println("===="+playerCommData.getPlayerId() +","+playerCommData.getType() +"," +playerCommData.getValue());
//        }
        PlayerCommData playerCommData = playerCommDataDao.getByPlayerIdAndType(2,9);
        System.out.println("===="+playerCommData.getPlayerId() +","+playerCommData.getType() +"," +playerCommData.getValue());
        playerCommDataDao.deleteByPlayerIdAndType(2, 9);
        playerCommDataDao.deleteByPlayerId(1);
    }

    public void testCommdata() {
        for (int i = 1; i< 10; i++) {
            for (int j = 1; j < 10; j++) {
                PlayerCommData playerCommData = new PlayerCommData();
                playerCommData.setPlayerId(i);
                playerCommData.setType(j);
                playerCommData.setValue("playerId:" + i + ",type:" + j);
                playerCommData.setMonitorParam("monitor");
                playerCommDataDao.insertData(playerCommData);
            }
        }
    }

    public void testDb() {
        for (int i = 1; i< 10000; i++) {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setId(i);
            playerEntity.setName("update:"+i*2);
            playerEntityDao.insertData(playerEntity);
            System.err.println("playerEntity="+playerEntity);
        }
    }

    private void insertSqlData() {
        for (int i = 1; i< 10000; i++) {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setId(i);
            playerEntity.setName("name:"+i);
            playerEntity.setCreateTime(i*10);
            playerEntityDao.insertData(playerEntity);

//            playerEntity.setName("playerEntity"+i);
//            playerEntity.setCreateTime(i);
//            playerEntityDao.updateData(playerEntity);
//
//            playerEntity.setName("agmin"+i);
//            playerEntityDao.updateData(playerEntity);
//
//            Person person = new Person();
//            person.setId((long)i);
//            person.setAddress("adress"+i);
//            person.setName("name"+i);
//            person.setAge(i*10);
//            personRepository.save(person);
//
//            person.setAge(1);
//            person.setName("person.name"+i);
//            person.setAddress("person.address"+i);
//            personRepository.updateData(person);
//
//            person.setAge(1000);
//            person.setName("again.person.name"+i);
//            personRepository.updateData(person);
        }
    }

    public void updateSqlData() {
        for (int i = 1; i< 10000; i++) {
            PlayerEntity playerEntity = playerEntityDao.findOne((long)i);
            playerEntity.setName("name"+i*5);
            playerEntity.setCreateTime(i*50);
            playerEntityDao.updateData(playerEntity);

            Person person = personRepository.findOne((long)i);
            person.setAddress("adress" + i*2);
            person.setName("name" + i*2);
            person.setAge(i * 20);
            personRepository.updateData(person);
        }
    }

    public void DeleteSqlData() {
        for (int i = 1; i< 10000; i++) {
            if (i % 2 == 0) continue;
            PlayerEntity playerEntity = playerEntityDao.findOne((long)i);
            playerEntityDao.deleteData(playerEntity);

            Person person = personRepository.findOne((long)i);
            personRepository.deleteData(person);
        }
    }
}
