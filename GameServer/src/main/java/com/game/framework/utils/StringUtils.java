package com.game.framework.utils;

/**
 * @author liguorui
 * @date 2017/9/10 21:15
 */
public class StringUtils {

    public static int[] toIntArray(String str) {
        if (str == null || "".equalsIgnoreCase(str)) return null;
        String[] arry = str.split(",");
        int[]result = new int[arry.length];
        for (int i = 0; i < arry.length; i++) {
            result[i] = Integer.parseInt(arry[i]);
        }
        return result;
    }
}
