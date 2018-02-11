package com.game.framework.network.packet;

import com.game.framework.network.packet.annotation.Packet;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author liguorui
 * @date 2016-01-06
 */
public class PacketScaner {
	
	public Map<Short, Class<? extends AbstractPacket>> filter(ApplicationContext ctx) {
		return filter(ctx.getBeansWithAnnotation(Packet.class));
	}

	public Map<Short, Class<? extends AbstractPacket>> filter(
			Map<String, Object> beans) {
		Map<Short, Class<? extends AbstractPacket>> packets = new HashMap<Short, Class<? extends AbstractPacket>>();
		for (Object bean : beans.values()) {
			AbstractPacket packet = (AbstractPacket) bean;
			Packet annotation = bean.getClass().getAnnotation(Packet.class);
			short commandId = annotation.commandId();
			if (packets.get(Short.valueOf(commandId)) != null) {
				throw new RuntimeException("Conflict packet id `" + commandId
						+ "` found : "
						+ packets.get(Short.valueOf(commandId)).getName()
						+ " and " + packet.getClass().getName());
			}
			packets.put(commandId, packet.getClass());
		}
		return packets;
	}
}