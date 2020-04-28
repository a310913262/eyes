package com.cmcc.xpa.service.microservice.notes.utility;

import java.util.UUID;

public final class RandomUtil {


    /**
     * 返回一个UUID
     *
     * @return 小写的UUID
     */
    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        return s.substring(0, 8) + s.substring(9, 13) +
                s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }
}