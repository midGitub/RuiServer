package com.game.framework.network.packet;

import com.game.framework.network.packet.annotation.Packet;
import com.game.framework.network.packet.annotation.PacketIndex;
import com.game.framework.utils.type.ScanConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

	/**
	 * 写入属性值 字节
	 *
	 * @param obj
	 */
	public static ByteBuf writeObject(Object obj) {
		ByteBuf buf = null;
		// 增加buffer的容量
		List<Field> list = InitRequestPacket.getList(obj.getClass());
		if (list == null || list.size() <= 0) {
			return buf;
		}
		buf = Unpooled.buffer(16);
		for (Field f : list) {
			Class<?> clsf = f.getType();
			Object value = null;
			try {
				value = f.get(obj);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (clsf == String.class) {
				Packets.writeString(buf, value.toString());
			} else if (clsf.getName().equals("int")) {
				buf.writeInt((Integer) value);
			} else if (clsf.getName().equals("byte")) {
				buf.writeByte((Byte) value);
			} else if (clsf.getName().equals("long")) {
				buf.writeLong((Long) value);
			} else if (clsf.getName().equals("short")) {
				buf.writeShort((Short) value);

			} else if (clsf.getName().equals("double")) {
				buf.writeDouble((Double) value);
			} else if (clsf.getName().equals("float")) {
				buf.writeFloat((Float) value);
			} else if (clsf == int[].class) {
				int[] temp = (int[]) value;
				buf.writeByte(temp.length);
				for (int i = 0; i < temp.length; i++) {
					buf.writeInt(temp[i]);
				}
			} else if (clsf == double[].class) {
				double[] temp = (double[]) value;
				buf.writeByte(temp.length);
				for (int i = 0; i < temp.length; i++) {
					buf.writeDouble(temp[i]);
				}
			} else if (clsf == float[].class) {
				float[] temp = (float[]) value;
				buf.writeByte(temp.length);
				for (int i = 0; i < temp.length; i++) {
					buf.writeFloat(temp[i]);
				}
			} else if (clsf == long[].class) {
				long[] temp = (long[]) value;
				buf.writeByte(temp.length);
				for (int i = 0; i < temp.length; i++) {
					buf.writeLong(temp[i]);
				}
			} else if (clsf == String[].class) {

				String[] temp = (String[]) value;
				buf.writeByte(temp.length);
				for (int i = 0; i < temp.length; i++) {
					Packets.writeString(buf, temp[i]);
				}
			} else if (clsf.getName().equals(List.class.getName())) {
				List<?> temp = (List<?>) value;
				int len = temp.size();
				buf.writeByte(len);
				Type gType = f.getGenericType();
				ParameterizedType pType = (ParameterizedType) gType;
				Type[] types = pType.getActualTypeArguments();

				if (types.length > 0) {
					Class<?> t1 = (Class<?>) types[0];
					for (int i = 0; i < len; i++) {
						Object v = temp.get(i);
						writeType(buf, t1, v);

						/*
						 * if(clsf == String.class ){ Packets.writeString(buf,
						 * (String)temp.get(i)); }else if(t1 == Integer.class){
						 * buf.writeInt( (int)temp.get(i)); }else if(t1 ==
						 * Float.class){ buf.writeFloat( (int)temp.get(i));
						 * }else if(t1 == Double.class){
						 * buf.writeDouble((double)temp.get(i)); }else if(t1 ==
						 * Long.class){ buf.writeLong((long)temp.get(i)); }else
						 * if(t1 == Byte.class){
						 * buf.writeByte((byte)temp.get(i)); }else if(t1 ==
						 * Short.class){ buf.writeShort((short)temp.get(i));
						 * }else{ }
						 */
					}
				}
			} else if (clsf.getName().equals(Set.class.getName())) {
				Set<?> temp = (Set<?>) value;
				int len = temp.size();

				buf.writeByte(len);
				Type gType = f.getGenericType();
				ParameterizedType pType = (ParameterizedType) gType;
				Type[] types = pType.getActualTypeArguments();

				if (types.length > 0) {
					Class<?> t1 = (Class<?>) types[0];
					Iterator<?> it = temp.iterator();
					while (it.hasNext()) {
						Object objTemp = it.next();
						writeType(buf, t1, objTemp);
						/*
						 * if(clsf == String.class ){ Packets.writeString(buf,
						 * (String)objTemp); }else if(t1 == Integer.class){
						 * buf.writeInt( (int)objTemp); }else if(t1 ==
						 * Float.class){ buf.writeFloat( (int)objTemp); }else
						 * if(t1 == Double.class){
						 * buf.writeDouble((double)objTemp); }else if(t1 ==
						 * long.class ){ buf.writeLong((long)objTemp); }else
						 * if(t1 == Byte.class){ buf.writeByte((byte)objTemp);
						 * }else if(t1 == Short.class ){
						 * buf.writeShort((short)objTemp); }else{ }
						 */
					}
				}
			} else if (clsf.getName().equals(Map.class.getName())) {
				Map<?, ?> temp = (Map<?, ?>) value;
				int len = temp.size();

				buf.writeByte(len);
				Type gType = f.getGenericType();
				ParameterizedType pType = (ParameterizedType) gType;
				Type[] types = pType.getActualTypeArguments();

				if (types.length > 1) {
					Class<?> t1 = (Class<?>) types[0];
					Class<?> t2 = (Class<?>) types[1];
					Iterator<?> it = temp.keySet().iterator();
					while (it.hasNext()) {
						Object key = it.next();
						Object v = temp.get(key);
						writeType(buf, t1, key);
						writeType(buf, t2, v);
					}
				}
			} else {
				// throws new RuntimeErrorException("");
			}
		}
		return buf;
	}

	public static Object readType(ByteBuf buf, Class<?> clsf) {
		Object obj = null;
		if (clsf == String.class) {
			obj = Packets.readString(buf);
		} else if (clsf == Integer.class) {
			obj = buf.readInt();
		} else if (clsf == Float.class) {
			obj = buf.readFloat();
		} else if (clsf == Double.class) {
			obj = buf.readDouble();
		} else if (clsf == Long.class) {
			obj = buf.readLong();
		} else if (clsf == Byte.class) {
			obj = buf.readByte();
		} else if (clsf == Short.class) {
			obj = buf.readShort();
		} else {
		}
		return obj;
	}

	public static void writeType(ByteBuf buf, Class<?> t1, Object objTemp) {
		if (t1 == String.class) {
			Packets.writeString(buf, (String) objTemp);
		} else if (t1 == Integer.class) {
			buf.writeInt((Integer) objTemp);
		} else if (t1 == Float.class) {
			buf.writeFloat((Float) objTemp);
		} else if (t1 == Double.class) {
			buf.writeDouble((Double) objTemp);
		} else if (t1 == long.class) {
			buf.writeLong((Long) objTemp);
		} else if (t1 == Byte.class) {
			buf.writeByte((Byte) objTemp);
		} else if (t1 == Short.class) {
			buf.writeShort((Short) objTemp);
		} else {
		}
	}

	/**
	 * 读取设置属性值
	 *
	 * @param obj
	 * @param buf
	 */
	public static void readObject(Object obj, ByteBuf buf) {
		List<Field> list = InitRequestPacket.getList(obj.getClass());
		if (list == null) {
			return;
		}
		for (Field f : list) {
			Class<?> clsf = f.getType();
			Object value = null;
			if (clsf == String.class) {
				value = Packets.readString(buf);
			} else if (clsf.getName().equals("int")) {
				value = buf.readInt();
			} else if (clsf.getName().equals("byte")) {
				value = buf.readByte();
			} else if (clsf.getName().equals("long")) {
				value = buf.readLong();
			} else if (clsf.getName().equals("short")) {
				value = buf.readShort();
			} else if (clsf.getName().equals("double")) {
				value = buf.readDouble();
			} else if (clsf.getName().equals("float")) {
				value = buf.readFloat();
			} else if (clsf == int[].class) {
				int len = buf.readByte();
				int[] tmp = new int[len];
				for (int i = 0; i < len; i++) {
					tmp[i] = buf.readInt();
				}
				value = tmp;
			} else if (clsf == double[].class) {
				int len = buf.readByte();
				double[] tmp = new double[len];
				for (int i = 0; i < len; i++) {
					tmp[i] = buf.readDouble();
				}
				value = tmp;
			} else if (clsf == float[].class) {
				int len = buf.readByte();
				float[] tmp = new float[len];
				for (int i = 0; i < len; i++) {
					tmp[i] = buf.readFloat();
				}
				value = tmp;
			} else if (clsf == long[].class) {
				int len = buf.readByte();
				long[] tmp = new long[len];
				for (int i = 0; i < len; i++) {
					tmp[i] = buf.readLong();
				}
				value = tmp;
			} else if (clsf == String[].class) {
				int len = buf.readByte();
				String[] tmp = new String[len];
				for (int i = 0; i < len; i++) {
					tmp[i] = Packets.readString(buf);
				}
				value = tmp;
			}
			// List类型
			else if (clsf.getName().equals(List.class.getName())) {
				int len = buf.readByte();

				Type gType = f.getGenericType();
				ParameterizedType pType = (ParameterizedType) gType;
				Type[] types = pType.getActualTypeArguments();

				if (types.length > 0) {
					Class<?> t1 = (Class<?>) types[0];
					value = new ArrayList<Integer>();
					List temp = new ArrayList();
					for (int i = 0; i < len; i++) {
						temp.add(readType(buf, t1));
					}
					value = temp;
				}
			}
			// set类型
			else if (clsf.getName().equals(Set.class.getName())) {

				int len = buf.readByte();

				Type gType = f.getGenericType();
				ParameterizedType pType = (ParameterizedType) gType;
				Type[] types = pType.getActualTypeArguments();

				if (types.length > 0) {
					Class<?> t1 = (Class<?>) types[0];
					value = new ArrayList<Integer>();
					Set temp = new HashSet();
					for (int i = 0; i < len; i++) {
						temp.add(readType(buf, t1));
						/*
						 * if(clsf == String.class ){
						 * temp.add(Packets.readString(buf)); }else if(t1 ==
						 * Integer.class){ temp.add(buf.readInt()); }else if(t1
						 * == Float.class){ temp.add(buf.readFloat()); }else
						 * if(t1 == Double.class ){ temp.add(buf.readDouble());
						 * }else if(t1 == Long.class ){
						 * temp.add(buf.readLong()); }else if(t1 == Byte.class
						 * ){ temp.add(buf.readByte()); }else if(t1 ==
						 * Short.class ){ temp.add(buf.readShort()); }else{ }
						 */
					}
					value = temp;
				}
			} else if (clsf.getName().equals(Map.class.getName())) {

				int len = buf.readByte();

				Type gType = f.getGenericType();
				ParameterizedType pType = (ParameterizedType) gType;
				Type[] types = pType.getActualTypeArguments();

				if (types.length > 0) {
					Class t1 = (Class) types[0];
					Class t2 = (Class) types[1];
					value = new ArrayList<Integer>();
					Map temp = new HashMap();
					for (int i = 0; i < len; i++) {
						Object k = readType(buf, t1);
						Object v = readType(buf, t2);
						temp.put(k, v);
					}
					value = temp;
				}
			} else {
			}
			try {
				f.set(obj, value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
	}
}
