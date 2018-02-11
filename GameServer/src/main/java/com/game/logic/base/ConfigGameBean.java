package com.game.logic.base;

/**
 * 游戏全局配置变量
 * @author liguorui
 * @date 2017/7/28 18:11
 */
public class ConfigGameBean {

    /**
     * 服务器标识 S1
     */
    private String serverIdFlag;

    /**
     * 服务器id 1
     */
    private int serverId;

    /**
     * 当前服务器最大在线人数 1000
     */
    private int maxOnline;

    /**
     * 支持的服务器，包括合服 S1:1,S2:2,S2000:2000
     */
    private String holdingServers;

    /**
     * 平台标识 tengxun
     */
    private String platform;

    /**
     * 平台id 2
     */
    private int platformId;

    public String getServerIdFlag() {
        return serverIdFlag;
    }

    public void setServerIdFlag(String serverIdFlag) {
        this.serverIdFlag = serverIdFlag;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getMaxOnline() {
        return maxOnline;
    }

    public void setMaxOnline(int maxOnline) {
        this.maxOnline = maxOnline;
    }

    public String getHoldingServers() {
        return holdingServers;
    }

    public void setHoldingServers(String holdingServers) {
        this.holdingServers = holdingServers;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }
}
