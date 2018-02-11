package com.game.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * 类型转化和判断工具
 * @author liguorui
 * @date 2015-11-6 下午4:04:47
 */
public class Types {

	private final static Logger logger	= LoggerFactory.getLogger(Types.class);

	public static <T> T getObject(Object obj, Class<T> toType) {
		try {
			String str = obj.toString();
			if (isInt(toType)) {
				return (T) Integer.valueOf(str);
			} else if (isBoolean(toType)) {
				return (T) Boolean.valueOf(str);
			} else if (isByte(toType)) {
				return (T) Byte.valueOf(str);
			} else if (isShort(toType)) {
				return (T) Short.valueOf(str);
			} else if (isLong(toType)) {
				return (T) Long.valueOf(str);
			} else if (isEnum(toType)) {
				return (T) getEnum(toType, str);
			} else if (isFloat(toType)) {
				return (T) Float.valueOf(str);
			} else if (isString(toType)) {
				return (T) str;
			}  else {
				throw new Exception("No such type support : " + toType);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public static boolean isStringToObject(Class<?> types) {
		return true;
	}

	/**
	 * @param types
	 * @return
	 */
	public static boolean isEnumArray(Class<?> types) {
		if (types.isArray()) {
			Class<?> componentType = types.getComponentType();
			if (componentType.isEnum())
				return true;
		}
		return false;
	}

	/**
	 * @param enumName
	 * @return
	 */
	public static <T> T getEnum(Class<T> enumType, String enumName) {
		try {
			Field field = enumType.getField(enumName.trim());
			Object object = field.get(enumType);
			if (object == null)
				throw new RuntimeException("No such element '" + enumName + "' in enum type '" + enumType.getName() + "'");
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
	 * 是否枚举
	 * 
	 * @param paramType
	 * @return
	 */
	public static boolean isEnum(Class<?> paramType) {
		if (paramType.isEnum())
			return true;
		return false;
	}

	/**
	 * @param paramType
	 * @return
	 */
	public static boolean isInt(Class<?> paramType) {
		if (paramType == Integer.TYPE || paramType == Integer.class) {
			return true;
		}
		return false;
	}

	public static boolean isLong(Class<?> paramType) {
		if (paramType == Long.TYPE || paramType == Long.class) {
			return true;
		}
		return false;
	}

	public static boolean isFloat(Class<?> paramType) {
		if (paramType == Float.TYPE || paramType == Float.class) {
			return true;
		}
		return false;
	}

	public static boolean isShort(Class<?> paramType) {
		if (paramType == Short.TYPE || paramType == Short.class) {
			return true;
		}
		return false;
	}

	public static boolean isByte(Class<?> paramType) {
		if (paramType == Byte.TYPE || paramType == Byte.class) {
			return true;
		}
		return false;
	}

	public static boolean isString(Class<?> paramType) {
		if (paramType == String.class) {
			return true;
		}
		return false;
	}

	/**
	 * @param paramType
	 * @return
	 */
	public static boolean isBoolean(Class<?> paramType) {
		if (paramType == Boolean.TYPE || paramType == Boolean.class) {
			return true;
		}
		return false;
	}

	public static boolean isIntArray(Class<?> paramType) {
		if (paramType.isArray()) {
			Class<?> componentType = paramType.getComponentType();
			if (isInt(componentType))
				return true;
		}
		return false;
	}
}
