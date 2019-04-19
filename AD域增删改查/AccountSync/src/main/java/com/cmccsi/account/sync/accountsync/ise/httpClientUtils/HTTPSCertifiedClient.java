package com.cmccsi.account.sync.accountsync.ise.httpClientUtils;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

public class HTTPSCertifiedClient extends HTTPSClient {
    public HTTPSCertifiedClient() {

    }


    public void prepareCertificate() throws Exception {
        // 获得密匙库
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(
                new File("C:/Users/zhda6001/Downloads/software/xxx.keystore"));
        // FileInputStream instream = new FileInputStream(new File("C:/Users/zhda6001/Downloads/xxx.keystore"));
        try {
            // 密匙库的密码
            trustStore.load(instream, "password".toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore,  (TrustStrategy) new TrustSelfSignedStrategy()).build();
        this.connectionSocketFactory = new SSLConnectionSocketFactory(sslcontext);
    }
}
