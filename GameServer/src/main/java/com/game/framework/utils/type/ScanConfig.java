package com.game.framework.utils.type;

import java.lang.reflect.Modifier;
import java.util.List;

public class ScanConfig {
	private ClassFilter	filter;

	public ScanConfig() {
		setUp();
	}

	protected void setUp() {
		filter = new ClassFilter() {
			@Override
			public boolean accept(Class<?> clazz) {
				return !Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers()) && Modifier.isPublic(clazz.getModifiers()) && !Modifier.isStatic(clazz.getModifiers());
			}
		};
	}

	/**
	 * 扫描包 返回List
	 */
	public List<Class<?>> getClassList(String packageName) throws Exception {
		return ClassUtils.scanPackage(packageName, filter);

	}

}