package com.cmccsi.account.sync.accountsync.ise.test;

import java.util.HashMap;
import java.util.Map;

import com.cmccsi.account.sync.accountsync.ise.httpClientUtils.HTTPSCertifiedClient;
import com.cmccsi.account.sync.accountsync.ise.httpClientUtils.HTTPSClientUtil;
import org.apache.http.client.HttpClient;
public class HTTPSClientTest {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = null;

        //httpClient = new HTTPSTrustClient().init();
        httpClient = new HTTPSCertifiedClient().init();

        String url = "https://1.2.6.2:8011/xxx/api/getToken";
        //String url = "https://1.2.6.2:8011/xxx/api/getHealth";

        Map<String, String> paramHeader = new HashMap<>();
        paramHeader.put("Accept", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//get请求需要的头部
        paramHeader.put("Content-Type", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//post请求需要的头部

        paramHeader.put("Authorization","Basic YWRtaW46MTIzNDU2QWQ=");//接口必传

        Map<String, String> paramBody = new HashMap<>();
        paramBody.put("client_id", "ankur.tandon.ap@xxx.com");
        paramBody.put("client_secret", "P@ssword_1");
        String result = HTTPSClientUtil.doPost(httpClient, url, paramHeader, paramBody);

        //String result = HTTPSClientUtil.doGet(httpsClient, url, null, null);

        System.out.println(result);
    }

}
