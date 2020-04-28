package com.cmcc.xpa.service.microservice.notes.utils;

import java.util.UUID;

public class UUIDUtil {

    /**
     * @method 获取UUID（32位字符串）
     * @return UUID值
     */
    public static String getUUID() {

        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }
    
    /**
     * @method 获取UUID（20位 Number型）
     * @return UUID值
     */
    public static long getUUNum() {

        long result = UUID.randomUUID().getMostSignificantBits();
        return Math.abs(result);
    }

}
