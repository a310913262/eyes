package com.cmccsi.account.sync.accountsync.ise.controller;

import com.alibaba.fastjson.JSON;
import com.cmccsi.account.sync.accountsync.ise.httpClientUtils.HTTPSCertifiedClient;
import com.cmccsi.account.sync.accountsync.ise.httpClientUtils.HTTPSClientUtil;
import com.cmccsi.account.sync.accountsync.ise.httpClientUtils.HTTPSTrustClient;
import com.cmccsi.account.sync.accountsync.ise.pojo.*;
import com.cmccsi.account.sync.accountsync.ise.utils.JaxbXmlUtil;
import com.cmccsi.account.sync.accountsync.nessus.pojo.Users;
import com.cmccsi.account.sync.accountsync.utils.httpclient.HttpUtils;
import com.cmccsi.account.sync.accountsync.utils.jsonbean.ResponseResult;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("IseUser")
public class IseUserController {

    HttpClient httpClient = null;

    public static String host = "https:://<ISE-ADMIN-NODE>:9060/";

    String url = "ers/config/internaluser/";

    @ResponseBody
    @RequestMapping("/getUserList")
    public ResponseResult getUserList() throws Exception {
        ResponseResult responseResult = new ResponseResult();

        String url = "ers/config/internaluser/";
        Map<String, String> paramHeader = new HashMap<>();
        paramHeader.put("Accept", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//get请求需要的头部
        paramHeader.put("Content-Type", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//post请求需要的头部
        paramHeader.put("Authorization", "Basic YWRtaW46MTIzNDU2QWQ=");//接口必传
        try {
            Map<String, String> paramBody = new HashMap<>();
            //调ise 后返回的xml 数据
            HttpResponse result = HttpUtils.doGet(host, url, paramHeader, null);
            String responseContent = HttpUtils.getResponseContent(result);
            //解析xml
            Document document = null;
            document = DocumentHelper.parseText(responseContent);
            //获取根节点元素对象  
            Element root = document.getRootElement();
            //获取子节点
            System.out.println("当前节点的名称：" + root.getName());
            //获取子节点
            Element data = root.element("resources");
            System.out.println(data.attributeValue("description"));
            //获取resource 的集合
            List nodes = data.elements("resource");
            if (nodes.size() > 0) {
                //创建返回的集合
                List<UserBean> userBeans = new ArrayList<>();

                for (Iterator it = nodes.iterator(); it.hasNext(); ) {
                    Element elm = (Element) it.next();
                    System.out.println(elm.attributeValue("description") + "***" + elm.attributeValue("id") + "****" + elm.attributeValue("name"));
                    UserBean userbean = new UserBean();
                    userbean.setDescription(elm.attributeValue("description"));
                    userbean.setId(elm.attributeValue("id"));
                    userbean.setName(elm.attributeValue("name"));
                    userBeans.add(userbean);
                }
                responseResult.setSuccess(true);
                responseResult.setRows(userBeans);
            } else {
                responseResult.setSuccess(false);
                responseResult.setMsg("查询结果异常，请重新操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("查询结果异常，请重新操作");
        }
        return responseResult;
    }

    /**
     * ise 用户添加
     *
     * @param params
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/addUser")
    public ResponseResult addUser(@RequestBody UserBean params) throws Exception {

        ResponseResult responseResult = new ResponseResult();
        String url = "ers/config/internaluser/";

        Map<String, String> paramHeader = new HashMap<>();
        paramHeader.put("Accept", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//get请求需要的头部
        paramHeader.put("Content-Type", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//post请求需要的头部
        paramHeader.put("Authorization", "Basic YWRtaW46MTIzNDU2QWQ=");//接口必传

        Map<String, String> paramBody = new HashMap<>();
        //接受参数，设置xml
        String xml = null;
        //处理数据，往xml 实体中插入数据
        ReqBody addReqBody = new ReqBody();
        ReqEntity addReqEntity = new ReqEntity();
        try {
            if (!StringUtils.isEmpty(params.getId())) {
                addReqEntity.setId(params.getId());// 用户id
            }
            if (!StringUtils.isEmpty(params.getName())) {
                addReqEntity.setName(params.getName());
            }
            if (!StringUtils.isEmpty(params.getDescription())) {
                addReqEntity.setDescription(params.getDescription());
            }
            if (!StringUtils.isEmpty(params.getEmail())) {
                addReqEntity.setEmail(params.getEmail());
            }
            if (!StringUtils.isEmpty(params.getEnabled())) {
                addReqEntity.setEnabled(params.getEnabled());
            }
            if (!StringUtils.isEmpty(params.getFirstname())) {
                addReqEntity.setFirstName(params.getFirstname());
            }
            if (!StringUtils.isEmpty(params.getLastname())) {
                addReqEntity.setLastName(params.getFirstname());
            }
            if (!StringUtils.isEmpty(params.getIdentityGroups())) {
                addReqEntity.setIdentityGroups(params.getIdentityGroups());
            }
            if (!StringUtils.isEmpty(params.getPassword())) {
                addReqEntity.setPassword(params.getPassword());
            }
            if (!StringUtils.isEmpty(params.getChangePassword())) {
                addReqEntity.setChangePassword(params.getChangePassword());
            }
            List<ReqHeader> arrayList = new ArrayList<>();
            if (!StringUtils.isEmpty(params.getEntry())) {
                List<Entry> entrys = params.getEntry();
                for (Entry entry : entrys) {
                    ReqHeader addHeader = new ReqHeader();
                    addHeader.setKey(entry.getKey());
                    addHeader.setValue(entry.getValue());
                    arrayList.add(addHeader);
                }
            }
            //数据封装
            addReqEntity.setCustomAttributes(arrayList);
            String xmlData = JaxbXmlUtil.convertToXml(addReqEntity);
            //调ise 后返回的xml 数据
            //  String result = HTTPSClientUtil.doPost(httpClient, url, paramHeader, xmlData,null);
            HttpResponse result = HttpUtils.doPost(host, url, paramHeader, null, xmlData);

            String responseContent = HttpUtils.getResponseContent(result);
            int code = result.getStatusLine().getStatusCode();
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setMsg("添加成功");
            } else {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("添加异常，请重新操作");
        }
        return responseResult;
    }

    /**
     * ise 用户添加(批量添加)
     *
     * @param params
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/addUsers")
    public ResponseResult addUsers(@RequestBody List<UserBean> params) throws Exception {

        ResponseResult responseResult = new ResponseResult();
        StringBuffer res = new StringBuffer();
        StringBuffer sucess = new StringBuffer();
        StringBuffer fail = new StringBuffer();
        boolean flag = true;

        String url = "ers/config/internaluser/";
        Map<String, String> paramHeader = new HashMap<>();
        paramHeader.put("Accept", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//get请求需要的头部
        paramHeader.put("Content-Type", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//post请求需要的头部
        paramHeader.put("Authorization", "Basic YWRtaW46MTIzNDU2QWQ=");//接口必传

        Map<String, String> paramBody = new HashMap<>();

        //接受参数，设置xml
        String xml = null;
        //处理数据，往xml 实体中插入数据
        ReqBody addReqBody = new ReqBody();
        ReqEntity addReqEntity = new ReqEntity();
        try {
            for (UserBean param : params) {
                if (!StringUtils.isEmpty(param.getId())) {
                    addReqEntity.setId(param.getId());// 用户id
                }
                if (!StringUtils.isEmpty(param.getName())) {
                    addReqEntity.setName(param.getName());
                }
                if (!StringUtils.isEmpty(param.getDescription())) {
                    addReqEntity.setDescription(param.getDescription());
                }
                if (!StringUtils.isEmpty(param.getEmail())) {
                    addReqEntity.setEmail(param.getEmail());
                }
                if (!StringUtils.isEmpty(param.getEnabled())) {
                    addReqEntity.setEnabled(param.getEnabled());
                }
                if (!StringUtils.isEmpty(param.getFirstname())) {
                    addReqEntity.setFirstName(param.getFirstname());
                }
                if (!StringUtils.isEmpty(param.getLastname())) {
                    addReqEntity.setLastName(param.getFirstname());
                }
                if (!StringUtils.isEmpty(param.getIdentityGroups())) {
                    addReqEntity.setIdentityGroups(param.getIdentityGroups());
                }
                if (!StringUtils.isEmpty(param.getPassword())) {
                    addReqEntity.setPassword(param.getPassword());
                }
                if (!StringUtils.isEmpty(param.getChangePassword())) {
                    addReqEntity.setChangePassword(param.getChangePassword());
                }
                List<ReqHeader> arrayList = new ArrayList<>();
                if (!StringUtils.isEmpty(param.getEntry())) {
                    List<Entry> entrys = param.getEntry();
                    for (Entry entry : entrys) {
                        ReqHeader addHeader = new ReqHeader();
                        addHeader.setKey(entry.getKey());
                        addHeader.setValue(entry.getValue());
                        arrayList.add(addHeader);
                    }
                }
                //数据封装
                addReqEntity.setCustomAttributes(arrayList);
                String xmlData = JaxbXmlUtil.convertToXml(addReqEntity);
                //调ise 后返回的xml 数据
                //  String result = HTTPSClientUtil.doPost(httpClient, url, paramHeader, xmlData,null);
                HttpResponse result = HttpUtils.doPost(host, url, paramHeader, null, xmlData);
                String responseContent = HttpUtils.getResponseContent(result);
                System.out.println(responseContent);
                com.alibaba.fastjson.JSONObject json = JSON.parseObject(responseContent);
                System.out.println(json);
                int code = result.getStatusLine().getStatusCode();
                //根据返回状态码判断成功失败
                if (code == 200) {
                    sucess.append(param.getName() + ",");
                } else  {
                    flag=false;
                    fail.append(param.getName() + ":" + responseContent + ",");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            fail.append("添加异常，请重新操作");
        }
        responseResult.setSuccess(flag);
        responseResult.setMsg(res.append(sucess.toString() + fail.toString()).toString());
        return responseResult;
    }

    /**
     * ise 从账号授权接口（/单个密码修改）
     *
     * @param params
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public ResponseResult updateUser(@RequestBody UserBean params) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        String url = "ers/config/internaluser/";
        Map<String, String> paramHeader = new HashMap<>();
        // paramHeader.put("Accept", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//get请求需要的头部
        paramHeader.put("Content-Type", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//post请求需要的头部
        paramHeader.put("Authorization", "Basic YWRtaW46MTIzNDU2QWQ=");//接口必传

        Map<String, String> paramBody = new HashMap<>();
        //接受参数，设置xml
        String xml = null;
        //处理数据，往xml 实体中插入数据

        ReqBody addReqBody = new ReqBody();
        ReqEntity addReqEntity = new ReqEntity();

        try {
            if (!StringUtils.isEmpty(params.getId())) {
                addReqEntity.setId(params.getId());// 用户id
            }
            if (!StringUtils.isEmpty(params.getName())) {
                addReqEntity.setName(params.getName());
            }
            if (!StringUtils.isEmpty(params.getDescription())) {
                addReqEntity.setDescription(params.getDescription());
            }
            if (!StringUtils.isEmpty(params.getEmail())) {
                addReqEntity.setEmail(params.getEmail());
            }
            if (!StringUtils.isEmpty(params.getEnabled())) {
                addReqEntity.setEnabled(params.getEnabled());
            }
            if (!StringUtils.isEmpty(params.getFirstname())) {
                addReqEntity.setFirstName(params.getFirstname());
            }
            if (!StringUtils.isEmpty(params.getLastname())) {
                addReqEntity.setLastName(params.getFirstname());
            }
            if (!StringUtils.isEmpty(params.getIdentityGroups())) {
                addReqEntity.setIdentityGroups(params.getIdentityGroups());
            }
            if (!StringUtils.isEmpty(params.getPassword())) {
                addReqEntity.setPassword(params.getPassword());
            }
            if (!StringUtils.isEmpty(params.getChangePassword())) {
                addReqEntity.setChangePassword(params.getChangePassword());
            }
            List<ReqHeader> arrayList = new ArrayList<>();
            if (!StringUtils.isEmpty(params.getEntry())) {
                List<Entry> entrys = params.getEntry();
                for (Entry entry : entrys) {
                    ReqHeader addHeader = new ReqHeader();
                    addHeader.setKey(entry.getKey());
                    addHeader.setValue(entry.getValue());
                    arrayList.add(addHeader);
                }
            }
            //数据封装
            addReqEntity.setCustomAttributes(arrayList);
            //转成xml 格式的字符串数据
            String xmlData = JaxbXmlUtil.convertToXml(addReqEntity);

            //调ise 后返回的xml 数据
            //  String result = HTTPSClientUtil.doput(httpClient, url + params.getId(), paramHeader, xmlData, null);
            HttpResponse result = HttpUtils.doPut(host, url + params.getId(), paramHeader, null, xmlData);
            String responseContent = HttpUtils.getResponseContent(result);
            //处理返回的报文

            SAXReader saxReader = new SAXReader();
            Document document = null;
            String rootName = null;

            document = DocumentHelper.parseText(responseContent);
            //获取根节点元素对象  
            Element root = document.getRootElement();
            //获取子节点
            System.out.println("当前节点的名称：" + root.getName());
            //
            Element data = root.element("updatedFields");//获取子节点
            //从集合中取出 旧值和新值
            int a = 0;
            for (Iterator it = root.elementIterator(); it.hasNext(); ) {
                Element elm = (Element) it.next();
                Element newValue = elm.element("newValue");//取标签
                Element oldValue = elm.element("oldValue");
                String field = elm.attributeValue("field");//取节点内的属性
                System.out.println(newValue.getText());//取标签内的节点
                System.out.println(oldValue.getText());
                System.out.println(field);
                // System.out.println(elm.attributeValue("field")+"***"+elm.element("newValue")+"**********"+elm.element("oldValue"));
                if (newValue.getText().equals(oldValue.getText())) {
                    a++;
                }
            }
            if (a == 0) {
                responseResult.setSuccess(true);
                responseResult.setMsg("修改密码成功");
            } else {
                responseResult.setSuccess(false);
                responseResult.setMsg("修改密码成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("修改密码异常，请重新操作");
        }
        return responseResult;
    }

    /**
     * ise 用户删除
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public ResponseResult deleteUser(@RequestBody String userId) throws Exception {

        ResponseResult responseResult = new ResponseResult();
        httpClient = new HTTPSTrustClient().init();
        // httpClient = new HTTPSCertifiedClient().init();
        Map<String, String> paramHeader = new HashMap<>();
        // paramHeader.put("Accept", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//get请求需要的头部
        paramHeader.put("Content-Type", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//post请求需要的头部

        paramHeader.put("Authorization", "Basic YWRtaW46MTIzNDU2QWQ=");//接口必传
        //执行请求
        try {
            //  String result = HTTPSClientUtil.doDelete(httpClient, url + params.getId(), paramHeader, null);
            HttpResponse result = HttpUtils.doDelete(host, url + userId, paramHeader, null);
            JSONObject jsonobject = JSONObject.fromObject(result);
            //处理返回结果
            String responseContent = HttpUtils.getResponseContent(result);
            int code = result.getStatusLine().getStatusCode();
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setMsg("删除成功");
            } else {
                responseResult.setSuccess(false);
                responseResult.setMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("删除异常，请重新操作");
        }
        return responseResult;
    }

    /**
     * ise 用户删除（批量）
     *
     * @param userIds
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/deleteUsers")
    public ResponseResult deleteUsers(@RequestBody String userIds) throws Exception {

        ResponseResult responseResult = new ResponseResult();
        StringBuffer res = new StringBuffer();
        StringBuffer sucess = new StringBuffer();
        StringBuffer fail = new StringBuffer();
        boolean flag = true;
        String demoArray[] = userIds.split(",");
        List<String> list = Arrays.asList(demoArray);

        Map<String, String> paramHeader = new HashMap<>();
        // paramHeader.put("Accept", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//get请求需要的头部
        paramHeader.put("Content-Type", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//post请求需要的头部

        paramHeader.put("Authorization", "Basic YWRtaW46MTIzNDU2QWQ=");//接口必传
        //执行请求
        try {
            for (int i = 0; list.size() > i; i++) {
                //  String result = HTTPSClientUtil.doDelete(httpClient, url + params.getId(), paramHeader, null);
                HttpResponse result = HttpUtils.doDelete(host, url + list.get(i), paramHeader, null);
                JSONObject jsonobject = JSONObject.fromObject(result);
                //处理返回结果
                String responseContent = HttpUtils.getResponseContent(result);
                int code = result.getStatusLine().getStatusCode();
                if (code == 200) {
                    sucess.append(list.get(i) + ",");
                } else {
                    flag = false;
                    fail.append(list.get(i) + ":" + responseContent + ",");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            fail.append("批量修改用户密码户异常，请重新操作");
        }
        responseResult.setSuccess(flag);
        responseResult.setMsg(res.append(sucess.toString() + fail.toString()).toString());
        return responseResult;
    }

    /**
     * ise (批量密码同步)
     *
     * @param params
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/updateUserPassword")
    public ResponseResult updateUserPassword(@RequestBody List<UserBean> params) throws Exception {

        ResponseResult responseResult = new ResponseResult();
        StringBuffer res = new StringBuffer();
        StringBuffer sucess = new StringBuffer();
        StringBuffer fail = new StringBuffer();
        boolean flag = true;

        int a = 0;
        //循环取数据
        try {
            for (UserBean param : params) {

                httpClient = new HTTPSTrustClient().init();
                //httpClient = new HTTPSCertifiedClient().init();
                Map<String, String> paramHeader = new HashMap<>();
                // paramHeader.put("Accept", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//get请求需要的头部
                paramHeader.put("Content-Type", "application/vnd.com.cisco.ise.identity.internaluser.1.0+xml");//post请求需要的头部
                paramHeader.put("Authorization", "Basic YWRtaW46MTIzNDU2QWQ=");//接口必传

                Map<String, String> paramBody = new HashMap<>();
                //接受参数，设置xml
                String xml = null;
                //处理数据，往xml 实体中插入数据
                ReqBody addReqBody = new ReqBody();
                ReqEntity addReqEntity = new ReqEntity();

                if (!StringUtils.isEmpty(param.getId())) {
                    addReqEntity.setId(param.getId());// 用户id
                }
                if (!StringUtils.isEmpty(param.getName())) {
                    addReqEntity.setName(param.getName());
                }
                if (!StringUtils.isEmpty(param.getDescription())) {
                    addReqEntity.setDescription(param.getDescription());
                }
                if (!StringUtils.isEmpty(param.getEmail())) {
                    addReqEntity.setEmail(param.getEmail());
                }
                if (!StringUtils.isEmpty(param.getEnabled())) {
                    addReqEntity.setEnabled(param.getEnabled());
                }
                if (!StringUtils.isEmpty(param.getFirstname())) {
                    addReqEntity.setFirstName(param.getFirstname());
                }
                if (!StringUtils.isEmpty(param.getLastname())) {
                    addReqEntity.setLastName(param.getFirstname());
                }
                if (!StringUtils.isEmpty(param.getIdentityGroups())) {
                    addReqEntity.setIdentityGroups(param.getIdentityGroups());
                }
                if (!StringUtils.isEmpty(param.getPassword())) {
                    addReqEntity.setPassword(param.getPassword());
                }
                if (!StringUtils.isEmpty(param.getChangePassword())) {
                    addReqEntity.setChangePassword(param.getChangePassword());
                }
                List<ReqHeader> arrayList = new ArrayList<>();
                if (!StringUtils.isEmpty(param.getEntry())) {
                    List<Entry> entrys = param.getEntry();
                    for (Entry entry : entrys) {
                        ReqHeader addHeader = new ReqHeader();
                        addHeader.setKey(entry.getKey());
                        addHeader.setValue(entry.getValue());
                        arrayList.add(addHeader);
                    }
                }
                //数据封装
                addReqEntity.setCustomAttributes(arrayList);
                //转成xml 格式的字符串数据
                String xmlData = JaxbXmlUtil.convertToXml(addReqEntity);
                //调ise 后返回的xml 数据
                //  String result = HTTPSClientUtil.doput(httpClient, url + param.getId(), paramHeader, xmlData, null);
                HttpResponse result = HttpUtils.doPut(host, url + param.getId(), paramHeader, null, xmlData);
                String responseContent = HttpUtils.getResponseContent(result);

                int code = result.getStatusLine().getStatusCode();
                if (code == 200) {
                    sucess.append(param.getName() + ",");
                } else {
                    flag = false;
                    fail.append(param.getName() + ":" + responseContent + ",");
                }
          /*  //处理返回的报文
            SAXReader saxReader = new SAXReader();
            Document document = null;
            String rootName = null;

            document = DocumentHelper.parseText(responseContent);
            //获取根节点元素对象  
            Element root = document.getRootElement();
            //获取子节点
            System.out.println("当前节点的名称：" + root.getName());
            //
            Element data = root.element("updatedFields");//获取子节点
            //从集合中取出 旧值和新值

            for (Iterator it = root.elementIterator(); it.hasNext(); ) {
                Element elm = (Element) it.next();
                Element newValue = elm.element("newValue");//取标签
                Element oldValue = elm.element("oldValue");
                String field = elm.attributeValue("field");//取节点内的属性
                System.out.println(newValue.getText());//取标签内的节点
                System.out.println(oldValue.getText());
                System.out.println(field);
                // System.out.println(elm.attributeValue("field")+"***"+elm.element("newValue")+"**********"+elm.element("oldValue"));
                if (newValue.getText().equals(oldValue.getText())) {
                    a++;
                }
            }*/
            }

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            fail.append("删除异常，请重新操作");
        }
        responseResult.setSuccess(flag);
        responseResult.setMsg(res.append(sucess.toString() + fail.toString()).toString());
        return responseResult;
    }

}
