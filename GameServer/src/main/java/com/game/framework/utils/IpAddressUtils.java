package com.game.framework.utils;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;

/**
 * @author liguorui
 * @date 2018/1/14 21:43
 */
public class IpAddressUtils {

    public static long hashCode(String ipAddress) {
        String[] splits = ipAddress.split("\\.");
        long ipValue = 0;
        int offset = 24;
        for (String value : splits) {
            long part = Long.valueOf(value);
            ipValue += (part << offset);
            offset -= 8;
        }
        return ipValue;
    }

    public static long hashCode(String ipAddress, int port) {
        return hashCode(ipAddress) + port;
    }

    /**
     * 获取ip
     * @param channel
     * @return
     */
    public static final String getIp(Channel channel) {
        return ((InetSocketAddress)channel.remoteAddress()).getAddress().toString().substring(1);
    }
}
