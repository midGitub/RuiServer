/********************************************************************
 * 中文许可
 ******************************************************************** 
 * Copyright (C) 2013 - 2015 by www.duoyu001.com 该源码为 广州多娱网络科技有限公司 所有，授权许可人为
 * 蔡俊鸿(john.cha)。
 * 
 * 你可以： 1、在许可情况下，复制、发行或通过信息网络传播本作品。
 * 
 * 但必须遵守以下条件： 1、您必须按照在传播的时候在明显位置标明该作品的原公司和许可人。
 * 
 * 声明： 1、在再使用或者发行本作品时，您必须向他人明示本作品使用的许可协议条款。 2、任何人不得在未经许可的情况下，将其用于任意用途。
 * 
 ******************************************************************** 
 * English license
 ******************************************************************** 
 * Copyright (C) 2013 - 2015 by www.duoyu001.com This source code are ownered by
 * 广州多娱网络科技有限公司, copyright holder are john.cha(蔡俊鸿)
 * 
 * You can : 1、Copy, distribute and transmit the work after you got permission
 * from the copyright holder.
 * 
 * Under the following conditions: 1、When you use this source code, you should
 * indicate the holder's company and the holder at showy position.
 * 
 * Declaration : 1、While using or publishing this source code, you should show
 * this license to others. 2、Nobody can use this source code for any purpose
 * before you get permission from the copyright holder.
 * 
 ******************************************************************** 
 * 授权许可人联系方式（Contact the copyright holder） 许可人(copyright holder): 蔡俊鸿(john.cha)
 * QQ : 327112182 TEL : 18665023554 EMAIL : john.cha@qq.com 或
 * kakashi9bi@gmail.com
 ******************************************************************** 
 * 
 * com.duoyu001.framework.game.utils.enums.EnumToCSharp.java CREATED ON
 * 下午10:25:56 COPYRIGHT 2013-2015 WWW.DUOYU001.COM. ALL RIGHTS RESERVED.
 */
package com.game.framework.utils.enums;

/**
 * 
 * @author cjunhong
 * @email john.cha@qq.com
 * @date 2014年10月29日 下午10:25:56
 */
public class EnumToCSharp {

	/**
	 * 
	 * @param clazz
	 * @param keyClazz
	 * @param classNamePrefix
	 * @return
	 */
	public static <K> String convert(	Class<? extends IEnumIdAndName<K>> clazz,
										Class<K> keyClazz,
										String classNamePrefix) {
		String className = classNamePrefix + clazz.getSimpleName();
		StringBuilder ss = new StringBuilder();
		IEnumIdAndName<K>[] values = clazz.getEnumConstants();

		ss.append("public class " + className + " {\n");
		ss.append("    private int id;\n");
		ss.append("    private string desc;\n");

		for (int j = 0; j < values.length; j++) {
			IEnumIdAndName<K> t = values[j];
			String insName = ((Enum) t).name();
			insName = constantName(insName);
			ss.append("    ///<summary>\n");
			ss.append("    /// ").append(t.getDesc()).append("\n");
			ss.append("    ///</summary>\n");
			ss.append("    public const int " + insName + "_ID = " + t.getId() + ";\n\n");
		}

		StringBuilder sbb = new StringBuilder();
		for (int j = 0; j < values.length; j++) {
			IEnumIdAndName<K> t = values[j];
			String insName = ((Enum) t).name();
			insName = constantName(insName);
			ss.append("    ///<summary>\n");
			ss.append("    /// ").append(t.getDesc()).append("\n");
			ss.append("    ///</summary>\n");
			ss.append("    public static " + className + " " + insName + " = new " + className + "(" + insName + "_ID, \"" + t.getDesc() + "\");\n\n");
			sbb.append(insName);
			if (j < values.length - 1) {
				sbb.append(", ");
			}
		}

		String types = "    public static " + className + "[] TYPES = {" + sbb.toString() + "};";
		ss.append(types + "\n");
		// StringBuilder sb = new StringBuilder();
		// for (int j = 0; j < values.length; j++) {
		// IEnumIdAndName<K> t = values[j];
		// for (int i = 0; i < types.length(); i++) {
		// sb.append(" ");
		// }
		// sb.append("new " + className + "(" + t.getId() + ", \"" + t.getDesc()
		// + "\")");
		// if (j < values.length - 1) {
		// sb.append(",");
		// }
		// sb.append("\n");
		// }
		// ss.append(sb.toString());
		// ss.append("                                                    };\n");
		ss.append("    private " + className + "(int id, string desc) {\n");
		ss.append("        this.id = id;\n");
		ss.append("        this.desc = desc;\n");
		ss.append("    }\n");
		ss.append("    public int Id{get{return id;}}\n");
		ss.append("    public string Desc{get{return desc;}}\n");
		ss.append("    public static " + className + " getById(int id) {\n");
		ss.append("        foreach(" + className + " t in TYPES) {\n");
		ss.append("            if(t.id==id) return t;\n");
		ss.append("        }\n");
		ss.append("        return null;\n");
		ss.append("    }\n");
		ss.append("    public static " + className + " getByDesc(string desc) {\n");
		ss.append("        foreach(" + className + " t in TYPES) {\n");
		ss.append("            if(t.desc==desc) return t;\n");
		ss.append("        }\n");
		ss.append("        return null;\n");
		ss.append("    }\n");
		ss.append("}\n");
		return ss.toString();
	}

	/**
	 * @param insName
	 * @return
	 */
	private static String constantName(String insName) {
		String s = "";
		int lastIndex = 0;
		for (int i = 0; i < insName.length(); i++) {
			char a = insName.charAt(i);
			if (a >= 'A' && a <= 'Z' && lastIndex != i) {
				String substring = insName.substring(lastIndex, i);
				String upperCase = substring.toUpperCase();
				s += upperCase + "_";
				lastIndex = i;
			}
		}
		if (lastIndex != insName.length() - 1) {
			s += (insName.substring(lastIndex).toUpperCase());
		}
		if (s.lastIndexOf('_') == s.length() - 1) {
			return s.substring(0, s.length() - 1);
		}
		return s;
	}
}
