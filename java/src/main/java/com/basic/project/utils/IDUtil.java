package com.basic.project.utils;

import java.util.UUID;

public class IDUtil {
    // 获取UUID
    public static String getUUIDString() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }
}
