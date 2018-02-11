package com.game.framework.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 接口方法实现类
 * @author liguorui
 * @date 2016-01-06
 */
public class InterfaceInvoker {

	private static Logger LOGGER	= LoggerFactory.getLogger(InterfaceInvoker.class);

	public static void invoke(ApplicationContext applicationContext, Class<?> clazz, String methodName) {
		Map<String, ?> loadables = applicationContext.getBeansOfType(clazz);
		Method method = null;
		try {
			method = clazz.getMethod(methodName);
		} catch (NoSuchMethodException e) {
			LOGGER.error("", e);
		} catch (SecurityException e) {
			LOGGER.error("", e);
		}
		if (loadables != null) {
			LOGGER.debug("#############################################################################################");
			LOGGER.debug("# Start Invoke " + clazz);
			LOGGER.debug("#############################################################################################");
			for (Entry<String, ?> entry : loadables.entrySet()) {
				try {
					LOGGER.debug("#   Start loading " + entry.getValue().getClass() + "#load");
					method.invoke(entry.getValue());
					LOGGER.debug("#   Finish loaded " + entry.getValue().getClass() + "#load");
				} catch (Exception e) {
					LOGGER.error("", e);
				}
			}
			LOGGER.debug("#############################################################################################");
			LOGGER.debug("# Finish loaded");
			LOGGER.debug("#############################################################################################");
		}
		LOGGER.debug("\n");
	}
	
	/**
	 * 使用例子
	 * @param applicationContext
	 */
//	public static void invoke(ApplicationContext applicationContext) {
//		Map<String, ILoadable> loadables = applicationContext.getBeansOfType(ILoadable.class);
//		for (Entry<String, ILoadable> entry : loadables.entrySet()) {
//			entry.getValue().load();
//		}
//	}
}
