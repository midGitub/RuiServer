package com.game.logic.base;

import com.game.framework.base.ILoadable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 游戏主键ID生成器,合服ID处理规则
 * @author liguorui
 * @date 2017/8/14 20:19
 */
@Component
public class IdGeneratorService implements ILoadable {

//    @Autowired
//    private IPlayerEntityService playerEntityService;
//
//    @Autowired
//    private IGuildService guildService;

//    @Autowired
    private ConfigGameBean configGameBean;

    private IdGeneratorService(){
        instance = this;
    }

    private static IdGeneratorService instance;

    public static IdGeneratorService getInstance() {
        return instance;
    }

    /**
     * 每个服务器对应的当前玩家最大id
     */
    private Map<String,AtomicLong> server2MaxId;

    /**
     * 公会唯一id
     */
    private AtomicLong guildId;

    /**
     * 游戏全局唯一uuid
     */
    private AtomicLong uuid;

    private static final long PLATFORM_OFFSET = 1000000000000L;

    private static final long SERVER_OFFSET = 10000000L;

    @Override
    public void load() {
        initPlayerIds();
        initGuildId();
        initUUId();
    }

    /**
     * 生成该服务器的唯一玩家id
     * @param server
     * @return
     */
    public long generateUniquePlayeId(String server) {
        AtomicLong id = server2MaxId.get(server);
        if (id == null) {
            throw new RuntimeException();
        }
        if (isIDOverFlow(server, id.get())) {
            throw new RuntimeException();
        }

        return id.incrementAndGet();
    }

    public long generateUniquePlayeId() {
        String server = configGameBean.getServerIdFlag();
        return generateUniquePlayeId(server);
    }

    /**
     * 生成唯一公会id
     * @return
     */
    public final long generateUniqueGuildId() {
        if (isIDOverFlow(guildId.get())) {
            throw new RuntimeException();
        }
        return guildId.incrementAndGet();
    }

    private boolean isIDOverFlow(String server, long id) {
        return id % SERVER_OFFSET >= SERVER_OFFSET - 1;
    }

    private boolean isIDOverFlow(long id) {
        return isIDOverFlow("S1", id);
    }

    /**
     * 初始化玩家唯一id（考虑合服情况）
     */
    private void initPlayerIds() {
//        List<Long> serverPidList = playerEntityService.getAllServerIds();
//        Map<Long, Long> serverMax = new HashMap<>(serverPidList.size());
//        for (Long serPid : serverPidList) {
//            /**
//             * 获取每个服务器下最大的玩家id
//             */
//            Long maxId = playerEntityService.getMaxIdBySerId(serPid);
//            serverMax.put(serPid, maxId);
//        }
//
//        Map<String, Long> holdingServersMap = getHoldingServersMap();
//        Map<String, AtomicLong> server2MaxId = new HashMap<>(holdingServersMap.size());
//        for (Map.Entry<String, Long> entry : holdingServersMap.entrySet()) {
//            Long maxId = serverMax.get(entry.getValue());
//            if (maxId == null || maxId == 0) {
//                maxId =getStartPlayerIdByServerId(entry.getValue());
//            }
//            server2MaxId.put(entry.getKey(), new AtomicLong(maxId));
//        }
//        System.out.println("initPlayerIds.server2MaxId="+server2MaxId);
//        this.server2MaxId = server2MaxId;
    }

    private Map<String, Long> getHoldingServersMap() {
        Map<String, Long> holdingServersMap = new HashMap<>();
        String []serverArray = configGameBean.getHoldingServers().split(",");
        for (String server : serverArray) {
           String []serverIdArray = server.split(":");
           long pid = Long.parseLong(serverIdArray[1]) + configGameBean.getPlatformId() * PLATFORM_OFFSET /SERVER_OFFSET;
           holdingServersMap.put(serverIdArray[0], pid);
        }
        return holdingServersMap;
    }

    /**
     * 根据服务器id获得玩家的初始id
     * @param serverId
     * @return
     */
    private long getStartPlayerIdByServerId(long serverId) {
        return serverId * SERVER_OFFSET;
    }

    /**
     * 初始化工会唯一id
     */
    private void initGuildId() {
//        long maxId = 0;
//        try {
//            Long id = guildService.getMaxId();
//            maxId = id == null ? 0 : id;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (maxId <= 0) {
//            maxId = configGameBean.getPlatformId() * PLATFORM_OFFSET + configGameBean.getServerId() * SERVER_OFFSET;
//        }
//        System.out.println("initGuildId.maxId="+maxId);
//        guildId = new AtomicLong(maxId);
    }

    /**
     * 根据平台，服务器，时间戳生成唯一ID
     * @return
     */
    private String getUUID() {
        StringBuilder uuidData = new StringBuilder(32);
        uuidData.append(configGameBean.getPlatformId()).append("-");
        uuidData.append(configGameBean.getServerIdFlag()).append("-");
        uuidData.append(uuid.incrementAndGet());
        return uuidData.toString();
    }

    /**
     * 初始化uuid
     */
    private void initUUId() {
        String t = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        long id = Long.parseLong(t);
        uuid = new AtomicLong(id);
    }

    /**
     * 获取id所属的服务器
     * @param id
     * @return
     */
    public long getBaseServerId(long id) {
        return id % PLATFORM_OFFSET / SERVER_OFFSET;
    }

}
