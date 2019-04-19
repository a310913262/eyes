package com.cmccsi.account.sync.accountsync.ise.httpClientUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.apache.log4j.Logger;
public class HTTPSClientUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(HTTPSClientUtil.class);
    public static String doPost(HttpClient httpClient, String url, Map<String, String> paramHeader,
                                Map<String, String> paramBody) throws Exception {
        return doPost(httpClient, url, paramHeader, paramBody, DEFAULT_CHARSET);
    }

    public static String doPost(HttpClient httpClient, String url, Map<String, String> paramHeader,
                                Map<String, String> paramBody, String charset) throws Exception {

        String result = null;
        HttpPost httpPost = new HttpPost(url);
        setHeader(httpPost, paramHeader);
        setBody(httpPost, paramBody, charset);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, charset);
            }
        }

        return result;
    }
    public static String doPost(HttpClient httpClient, String url, Map<String, String> paramHeader,
                                String paramBody, String charset) throws Exception {

        String result = null;
        HttpPost httpPost = new HttpPost(url);
        setHeader(httpPost, paramHeader);
        // 创建请求内容
        StringEntity entity = new StringEntity(paramBody, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, charset);
            }
        }

        return result;
    }
    public static String doGet(HttpClient httpClient, String url, Map<String, String> paramHeader,
                               Map<String, String> paramBody) throws Exception {
        return doGet(httpClient, url, paramHeader, paramBody, DEFAULT_CHARSET);
    }

    public static String doGet(HttpClient httpClient, String url, Map<String, String> paramHeader,
                               Map<String, String> paramBody, String charset) throws Exception {

        String result = null;
        HttpGet httpGet = new HttpGet(url);
        setHeader(httpGet, paramHeader);

        HttpResponse response = httpClient.execute(httpGet);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, charset);
            }
        }

        return result;
    }

    private static void setHeader(HttpRequestBase request, Map<String, String> paramHeader) {
        // 设置Header
        if (paramHeader != null) {
            Set<String> keySet = paramHeader.keySet();
            for (String key : keySet) {
                request.addHeader(key, paramHeader.get(key));
            }
        }
    }

    private static void setBody(HttpPost httpPost, Map<String, String> paramBody, String charset) throws Exception {
        // 设置参数
        if (paramBody != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Set<String> keySet = paramBody.keySet();
            for (String key : keySet) {
                list.add(new BasicNameValuePair(key, paramBody.get(key)));
            }

            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
        }

    }
 /*   private static void setBody(HttpPut httpPut, Map<String, String> paramBody, String charset) throws Exception {
        // 设置参数
        if (paramBody != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Set<String> keySet = paramBody.keySet();
            for (String key : keySet) {
                list.add(new BasicNameValuePair(key, paramBody.get(key)));
            }

            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPut.setEntity(entity);
            }
        }
    }*/
    public static String doput(HttpClient httpClient,String url, Map<String, String> paramHeader,String paramBody, String charset) {

        String result = null;
        if (null == charset){
            charset = DEFAULT_CHARSET;
        }

        HttpPut httpPut = new HttpPut(url);
        setHeader(httpPut, paramHeader);
        // 创建请求内容
        StringEntity entity = new StringEntity(paramBody, ContentType.APPLICATION_JSON);
        httpPut.setEntity(entity);
        try {
            // 提交请求并以指定编码获取返回数据
            HttpResponse response = httpClient.execute(httpPut);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        }
        catch (IOException e) {
            logger.error("网络异常,堆栈信息如下", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    /**
     * 发送delete请求
     * @param url       请求地址
     * @param charset   返回数据编码
     * @return String
     */
    public static String doDelete(HttpClient httpClient,String url, Map<String, String> paramHeader, String charset) {
        String result = null;
        if (null == charset) {
            charset = DEFAULT_CHARSET;
        }
        HttpDelete del = new HttpDelete(url);
        setHeader(del, paramHeader);
        try {

            // 提交请求并以指定编码获取返回数据
            HttpResponse response = httpClient.execute(del);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (IOException e) {
            logger.error("网络异常,堆栈信息如下", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
