package com.game.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @author cjunhong
 * @email john.cha@qq.com
 * @date 2014年6月27日 下午11:06:06
 */
public class SimpleProperties {

	private Properties			properties;
	private String				fileName;
	private static final Logger logger	= LoggerFactory.getLogger(SimpleProperties.class);

	public SimpleProperties(String path) throws IOException {
		this.fileName = path;

		Properties p = new Properties();
		p.load(SimpleProperties.class.getClassLoader()
										.getResourceAsStream(path));
		this.properties = p;
	}

	// public SimpleProperties(InputStream is) throws IOException {
	// Properties p = new Properties();
	// p.load(is);
	// this.properties = p;
	// }
	//
	// public SimpleProperties(Properties properties){
	// if(properties==null)
	// throw new NullPointerException("properties should not be null.");
	// this.properties = properties;
	// }

	/**
	 * 从配置文件中获取整数
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		return Integer.valueOf(properties.get(key).toString());
	}

	/**
	 * 从配置文件中获取Short
	 * 
	 * @param key
	 * @return
	 */
	public short getShort(String key) {
		return Short.valueOf(properties.get(key).toString());
	}

	/**
	 * @param key
	 */
	private void checkKey(String key) {
		if (!properties.containsKey(key)) {
			throw new NullPointerException("Can not found this key `" + key + "` in `" + fileName + "`");
		}
	}

	/**
	 * 从配置文件中获取布尔值
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(String key) {
		return Boolean.valueOf(properties.get(key).toString());
	}

	/**
	 * 从配置文件中获取字符串
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		return properties.get(key).toString();
	}

	/**
	 * 从配置文件中获取一个对象</br> 该对象必须符合JavaBean标准。对属性提供公有的getter&setter。
	 * 目前支持的对象属性类型包括：String、int、Integer、int[]、Integer[]、Boolean、boolean、枚举、枚举数组
	 * </br>
	 * 
	 * @param clazz
	 *            配置中的实例名，根据这个class的名字生成。比如，有一下配置： </br>
	 *            serverInfo.host=127.0.0.1</br> serverInfo.port=8080</br>
	 *            那么说明你有一个叫ServerInfo的类，而且他有host和port两个属性。</br>
	 *            <b>NOTE:</b>暂时不支持嵌套对象。
	 * @return
	 */
	public <T> T getObject(Class<T> clazz) {
		String simpleName = clazz.getSimpleName();
		simpleName = antiCapitalize(simpleName);

		try {
			T newInstance = clazz.newInstance();

			String[] methodsName = getSetterMethodsName(clazz);
			for (String methodName : methodsName) {
				Method method = getMethod(clazz, methodName);
				String key = simpleName + "." + antiCapitalize(methodName.replaceFirst(	"set",
																						""));
//				if (method.isAnnotationPresent(Optional.class) && !has(key)) {
//					continue;
//				}
				checkKey(key);
				Object val = null;
				Class<?> paramType = method.getParameterTypes()[0];
				if (isInt(paramType)) {
					val = getInt(key);
				} else if (isShort(paramType)) {
					val = getShort(key);
				} else if (isBoolean(paramType)) {
					val = getBoolean(key);
				} else if (isIntArray(paramType)) {
					val = getIntArray(key);
				} else if (isEnum(paramType)) {
					val = getEnum(method.getParameterTypes()[0], key);
				} else if (isEnumArray(paramType)) {
					val = getEnumArray(method.getParameterTypes()[0], key);
				} else {
					val = getString(key);
				}
				method.invoke(newInstance, val);
			}
			return newInstance;
		} catch (InstantiationException e) {
			logger.error("", e);
		} catch (IllegalAccessException e) {
			logger.error("", e);
		} catch (IllegalArgumentException e) {
			logger.error("", e);
		} catch (InvocationTargetException e) {
			logger.error("", e);
		}

		return null;
	}

	/**
	 * @param key
	 * @return
	 */
	private boolean has(String key) {
		return properties.containsKey(key);
	}

	/**
	 * @param paramType
	 * @return
	 */
	private boolean isMap(Class<?> paramType) {
		if (paramType == Map.class)
			return true;
		Class<?>[] interfaces = paramType.getInterfaces();
		for (Class<?> c : interfaces) {
			if (c == Map.class)
				return true;
		}
		return false;
	}

	/**
	 * @param enumType
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] getEnumArray(Class<T> enumType, String key) {
		if (!isEnumArray(enumType))
			throw new RuntimeException("Argument enumType '" + enumType.getName() + "' is not a enum.");
		String[] stringArray = getStringArray(key);
		if (stringArray == null || stringArray.length <= 0)
			return enumType.getEnumConstants();

		Class<?> componentType = enumType.getComponentType();
		T[] data = (T[]) Array.newInstance(componentType, stringArray.length);

		for (int i = 0; i < stringArray.length; i++) {
			data[i] = (T) getEnum(componentType, stringArray[i]);
		}
		return (T[]) data;
	}

	/**
	 * @param enumName
	 * @return
	 */
	public <T> T getEnum(Class<T> enumType, String enumName) {
		try {
			Field field = enumType.getField(enumName.trim());
			Object object = field.get(enumType);
			if (object == null)
				throw new RuntimeException("No such element '" + enumName + "' in enum type '" + enumType.getName() + "'");
			@SuppressWarnings("unchecked")
			T data = (T) object;
			return data;
		} catch (SecurityException e) {
			logger.error("", e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(	"Error occure when get '" + enumName + "' from Enum:'" + enumType.getName() + "'",
										e);
		} catch (IllegalArgumentException e) {
			logger.error("", e);
		} catch (IllegalAccessException e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 从配置文件中获取整数数组
	 * 
	 * @param key
	 * @return
	 */
	public int[] getIntArray(String key) {
		Object object = properties.get(key);
		String replace = object.toString().replace("[", "").replace("]", "");
		if (replace.trim().equals(""))
			return new int[0];

		String[] split = replace.split(",");
		int[] ints = new int[split.length];
		for (int i = 0; i < split.length; i++) {
			ints[i] = Integer.valueOf(split[i].trim());
		}
		return ints;
	}

	/**
	 * 获取字符串数组
	 * 
	 * @param key
	 */
	public String[] getStringArray(String key) {
		Object object = properties.get(key);
		String replace = object.toString().replace("[", "").replace("]", "");
		if (replace.trim().equals(""))
			return new String[0];
		String[] splits = replace.split(",");
		for (int i = 0; i < splits.length; i++) {
			splits[i] = splits[i].trim();
		}
		return splits;
	}

	public Set<String> getkeys() {
		return properties.stringPropertyNames();
	}

	private <K, V> Map<K, V> convert(	Map<String, String> src,
										Class<K> k,
										Class<V> v) {
		Map<K, V> map = new HashMap<K, V>();
		for (Entry<String, String> entry : src.entrySet()) {
			K key = convert(entry.getKey(), k);
			V value = convert(entry.getValue(), v);
			map.put(key, value);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private <T> T convert(Object obj, Class<T> toType) {
		try {
			String str = obj.toString();
			if (isInt(toType)) {
				return (T) Integer.valueOf(str);
			} else if (isBoolean(toType)) {
				return (T) Boolean.valueOf(str);
			} else if (isLong(toType)) {
				return (T) Long.valueOf(str);
			} else if (isEnum(toType)) {
				return (T) getEnum(toType, str);
			} else if (isString(toType)) {
				return (T) str;
			} else {
				throw new Exception("No such type support : " + toType);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * @param types
	 * @return
	 */
	private boolean isEnumArray(Class<?> types) {
		if (types.isArray()) {
			Class<?> componentType = types.getComponentType();
			if (componentType.isEnum())
				return true;
		}
		return false;
	}

	/**
	 * 是否枚举
	 * 
	 * @param paramType
	 * @return
	 */
	private boolean isEnum(Class<?> paramType) {
		if (paramType.isEnum())
			return true;
		return false;
	}

	/**
	 * @param paramType
	 * @return
	 */
	private boolean isInt(Class<?> paramType) {
		if (paramType == Integer.TYPE || paramType == Integer.class) {
			return true;
		}
		return false;
	}

	private boolean isShort(Class<?> paramType) {
		if (paramType == Short.TYPE || paramType == Short.class) {
			return true;
		}
		return false;
	}

	private boolean isLong(Class<?> paramType) {
		if (paramType == Long.TYPE || paramType == Long.class) {
			return true;
		}
		return false;
	}

	private boolean isString(Class<?> paramType) {
		if (paramType == String.class) {
			return true;
		}
		return false;
	}

	/**
	 * @param paramType
	 * @return
	 */
	private boolean isBoolean(Class<?> paramType) {
		if (paramType == Boolean.TYPE || paramType == Boolean.class) {
			return true;
		}
		return false;
	}

	private boolean isIntArray(Class<?> paramType) {
		if (paramType.isArray()) {
			Class<?> componentType = paramType.getComponentType();
			if (isInt(componentType))
				return true;
		}
		return false;
	}

	/**
	 * @param methodName
	 */
	private Method getMethod(Class<?> clazz, String methodName) {
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.getName().equals(methodName))
				return m;
		}
		return null;
	}

	private String[] getSetterMethodsName(Class<?> clazz) {
		Method[] methods = clazz.getMethods();
		List<String> methodNames = new ArrayList<String>();
		for (Method m : methods) {
			if (m.getName().startsWith("set"))
				methodNames.add(m.getName());
		}
		return methodNames.toArray(new String[0]);
	}

	/**
	 * 首字母大写
	 *
	 * @param str
	 * @return
	 */
	private String capitalize(String str) {
//		return Strings.capitalize(str);
		return "";
	}

	/**
	 * 首字母小写
	 *
	 * @param str
	 * @return
	 */
	private String antiCapitalize(String str) {
//		return Strings.antiCapitalize(str);
		return "";
	}
}
