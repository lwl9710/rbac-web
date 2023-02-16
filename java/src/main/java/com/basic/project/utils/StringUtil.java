package com.basic.project.utils;

import java.security.SecureRandom;

public class StringUtil {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    // 判断字符串是否为空
    public static boolean isEmpty(Object content) {
        return content == null || "".equals(content);
    }

    // 获取随机数字字符
    public static String getIntString(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(SECURE_RANDOM.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
