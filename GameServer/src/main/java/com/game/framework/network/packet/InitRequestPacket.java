package com.game.framework.network.packet;

import com.game.framework.network.packet.annotation.Packet;
import com.game.framework.network.packet.annotation.PacketIndex;
import com.game.framework.utils.type.ScanConfig;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 读写对象
 * @author liguorui
 * @date 2016-01-06
 */
public class InitRequestPacket {
	static Map<Class<?>, List<Field>>	map				= new HashMap<Class<?>, List<Field>>();
	static Set<Short>					mapCommandId	= new HashSet<Short>();

	public static List<Field> getList(Class<?> cls) {
		return map.get(cls);
	}

	public static void init(String pakage) {
		ScanConfig scanConfig = new ScanConfig();
		List<Class<?>> listClass = null;
		try {
			listClass = scanConfig.getClassList(pakage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Class<?> cls : listClass) {
			Field[] fis = cls.getDeclaredFields();
			List<Field> list = new ArrayList<Field>();
			for (Field f : fis) {
				PacketIndex packetIndex = f.getAnnotation(PacketIndex.class);
				if (packetIndex != null) {

					f.setAccessible(true);
					list.add(f);
				}
			}
			Packet packet = (Packet) cls.getAnnotation(Packet.class);
			if (packet != null) {
				if (mapCommandId.contains(packet.commandId())) {
					System.err.println("----------------指令重复注册：" + packet.commandId() + " " + cls);
					continue;
				}
				mapCommandId.add(packet.commandId());

				System.out.println("----------------注册指令：" + packet.commandId() + " " + cls);
				map.put(cls, list);
			}
		}

	}

}
