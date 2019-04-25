package com.cmccsi.account.sync.accountsync.utils.httpclient;

import sun.misc.BASE64Encoder;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpHeaderUtil {


    public static Map getHeader(String userName,String pwd){
        LinkedHashMap <String, String> headers = new LinkedHashMap<String, String>();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Authorization", "Basic "+base64Encoder.encode((userName+":"+pwd).getBytes()));
        headers.put("Content-type", "application/json;charset=UTF-8");
        return headers;
    }


}
