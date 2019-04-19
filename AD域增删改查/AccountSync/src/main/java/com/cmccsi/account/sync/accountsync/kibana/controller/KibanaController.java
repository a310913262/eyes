package com.cmccsi.account.sync.accountsync.kibana.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cmccsi.account.sync.accountsync.elasticsearch.domain.EsUser;
import com.cmccsi.account.sync.accountsync.utils.Constant;
import com.cmccsi.account.sync.accountsync.utils.SeachGuardHash;
import com.cmccsi.account.sync.accountsync.utils.httpclient.HttpHeaderUtil;
import com.cmccsi.account.sync.accountsync.utils.httpclient.HttpUtils;
import com.cmccsi.account.sync.accountsync.utils.jsonbean.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@RequestMapping("kibana/")
@Controller
public class KibanaController {

    @Value("${elasticsearch.uri}")
    private String uri;

    private LinkedHashMap <String, String> headers = (LinkedHashMap <String, String>) HttpHeaderUtil.getHeader("admin","admin");



    /**
     * 查询单个
     *
     * @param userName
     * @return
     */
    @RequestMapping("searchUser")
    @ResponseBody
    public ResponseResult searchUser(String userName) throws Exception {
        ResponseResult result = new ResponseResult();
        EsUser user = new EsUser();
        try {
            HttpResponse s = HttpUtils.doGet(uri, "/_searchguard/api/internalusers/" + userName, headers, null);
            String responseContent = HttpUtils.getResponseContent(s);
            JSONObject json = JSON.parseObject(responseContent);
            JSONObject param = (JSONObject) json.get(userName);
            List <String> roles = (List) param.get("roles");
            JSONObject attributes = (JSONObject) param.get("attributes");
            String password = (String) param.get("password");
            user.setName(userName);
            user.setAttr(attributes);
            user.setPwd(password);
            user.setRoles(roles);
            result.setObject(user);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("未找到该用户");
        }
        return result;
    }

    /**
     * 添加单个
     *
     * @param user
     * @return
     */
    @RequestMapping("addUser")
    @ResponseBody
    public ResponseResult addUser(@RequestBody EsUser user) throws Exception {
        ResponseResult result = new ResponseResult();
        List roles=new ArrayList();
        roles.add(Constant.SG_KIBANA_USER);
        try {
            JSONObject jsonob = new JSONObject();
            jsonob.put("hash", SeachGuardHash.hash(user.getPwd().toCharArray()));
            jsonob.put("attributes", user.getAttr());
            jsonob.put("roles", roles);
            HttpResponse s = HttpUtils.doPut(uri, "/_searchguard/api/internalusers/" + user.getName(), headers, null, jsonob.toJSONString());
            String responseContent = HttpUtils.getResponseContent(s);
            JSONObject json = JSON.parseObject(responseContent);
            String status = (String) json.get("status");
            if ("OK".equals(status)) {
                result.setSuccess(true);
                result.setMsg((String) json.get("message"));
            } else if ("CREATED".equals(status)) {
                result.setSuccess(true);
                result.setMsg((String) json.get("message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("用户新增失败");
        }
        return result;
    }

    /**
     * 删除单个
     *
     * @param userName
     * @return
     */
    @RequestMapping("delUser")
    @ResponseBody
    public ResponseResult delUser(String userName) {
        ResponseResult result = new ResponseResult();
        try {
            HttpResponse s = HttpUtils.doDelete(uri, "/_searchguard/api/internalusers/" + userName, headers, null);
            String responseContent = HttpUtils.getResponseContent(s);
            JSONObject json = JSON.parseObject(responseContent);
            result.setSuccess("OK".equals(json.get("status")));
            result.setMsg((String) json.get("message"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("删除用户失败");
        }
        return result;
    }

    /**
     * 修改单个
     *  不支持修改密码
     * @param user
     * @return
     */
    @RequestMapping("editUser")
    @ResponseBody
    public ResponseResult editUser(@RequestBody EsUser user) {
        ResponseResult result = new ResponseResult();

        try {
            JSONArray jsonArray = new JSONArray();

            if (!user.getAttr().isEmpty()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("op", "replace");
                jsonObject.put("path", "/attributes");
                jsonObject.put("value", user.getAttr());
                jsonArray.add(jsonObject);
            }
            if (!user.getRoles().isEmpty()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("op", "replace");
                jsonObject.put("path", "/roles");
                jsonObject.put("value", user.getRoles());
                jsonArray.add(jsonObject);
            }
            if (StringUtils.isNotBlank(user.getPwd())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("op", "replace");
                jsonObject.put("path", "/hash");
                jsonObject.put("value", SeachGuardHash.hash(user.getPwd().toCharArray()));
                jsonArray.add(jsonObject);
            }
            System.out.println(jsonArray.toJSONString());
            HttpResponse httpResponse = HttpUtils.doPatch(uri, "/_searchguard/api/internalusers/" + user.getName(), headers, null, jsonArray.toJSONString());
            String responseContent = HttpUtils.getResponseContent(httpResponse);
            JSONObject json = JSON.parseObject(responseContent);
            result.setSuccess("OK".equals(json.get("status")));
            result.setMsg((String) json.get("message"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("修改发生错误，请重试");
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping("searchUserAll")
    @ResponseBody
    public ResponseResult searchUserAll() {
        ResponseResult result = new ResponseResult();
        try {
            HttpResponse s = HttpUtils.doGet(uri, "/_searchguard/api/internalusers", headers, null);
            String responseContent = HttpUtils.getResponseContent(s);
            System.out.println(responseContent);
            JSONObject jsonArray = (JSONObject) JSON.parse(responseContent);
            Set <Map.Entry <String, Object>> entries = jsonArray.entrySet();

            List <EsUser> list = new ArrayList <>();
            for (Map.Entry <String, Object> iterator :
                    entries) {
                {
                    JSONObject next = (JSONObject) JSON.toJSON(iterator);
                    Set <String> strings = next.keySet();
                    for (String name :
                            strings) {
                        JSONObject param = (JSONObject) next.get(name);
                        List <String> roles = (List) param.get("roles");
                        JSONObject attributes = (JSONObject) param.get("attributes");
                        String password = (String) param.get("password");
                        EsUser user = new EsUser();
                        user.setName(name);
                        user.setAttr(attributes);
                        user.setPwd(password);
                        user.setRoles(roles);
                        list.add(user);
                    }
                }
                if (list.isEmpty()) {
                    result.setSuccess(false);
                    result.setMsg("查询结果为空，请重新操作");
                    return result;
                }
                result.setSuccess(true);
                result.setRows(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("查询结果异常，请重新操作");
        }
        return result;
    }

    /**
     * 批量添加
     *
     * @param users
     * @return
     */
    @RequestMapping("addUsers")
    @ResponseBody
    public ResponseResult addUsers(@RequestBody List <EsUser> users) {
        ResponseResult result = new ResponseResult();
        JSONArray jsonArray = new JSONArray();
        List roles=new ArrayList();
        roles.add(Constant.SG_KIBANA_USER);
        try {
            for (EsUser user :
                    users) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("op", "add");
                jsonObject.put("path", "/" + user.getName());
                JSONObject userobj = new JSONObject();
                userobj.put("hash", SeachGuardHash.hash(user.getPwd().toCharArray()));
                userobj.put("roles",roles);
                userobj.put("attributes", user.getAttr());
                jsonObject.put("value", userobj);
                jsonArray.add(jsonObject);
            }
            HttpResponse httpResponse = HttpUtils.doPatch(uri, "/_searchguard/api/internalusers", headers, null, JSON.toJSONString(jsonArray, SerializerFeature.DisableCircularReferenceDetect));
            String responseContent = HttpUtils.getResponseContent(httpResponse);
            JSONObject json = JSON.parseObject(responseContent);
            result.setSuccess("OK".equals(json.get("status")));
            result.setMsg((String) json.get("message"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("批量新增用户异常，请重新操作");
        }
        return result;
    }

    /**
     * 批量删除
     *
     * @param userNames
     * @return
     */
    @RequestMapping("delUsers")
    @ResponseBody
    public ResponseResult delUsers(@RequestBody List <String> userNames) {
        ResponseResult result = new ResponseResult();
        StringBuffer sb=new StringBuffer();
        StringBuffer sb1=new StringBuffer();
        StringBuffer sb2=new StringBuffer();
        boolean flag=true;
        try {
            for (String userName:
                    userNames) {
                HttpResponse httpResponse = HttpUtils.doDelete(uri, "/_searchguard/api/internalusers/" + userName, headers, null);
                String responseContent = HttpUtils.getResponseContent(httpResponse);
                JSONObject json = JSON.parseObject(responseContent);
                if(!"OK".equals(json.get("status"))){
                    flag=false;
                    sb1.append(json.get("message")+",");
                }else{
                    sb2.append(json.get("message")+",");
                }
            }
            sb.append(sb1.toString()+sb2.toString());
        } catch (
                Exception e) {
            e.printStackTrace();
            flag=false;
            sb.append("批量新增用户异常，请重新操作");
        }
        result.setSuccess(flag);
        result.setMsg(sb.toString());
        return result;
    }


    /**
     * 批量修改
     *
     * @param users
     * @return
     */
    @RequestMapping("editUsers")
    @ResponseBody
    public ResponseResult editUsers(@RequestBody List <EsUser> users) {
        ResponseResult result = new ResponseResult();
        List roles=new ArrayList();
        roles.add(Constant.SG_KIBANA_USER);
        try {
            JSONArray jsonArray = new JSONArray();
            for (EsUser user :
                    users) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("op", "replace");
                jsonObject.put("path", "/" + user.getName());
                JSONObject userobj = new JSONObject();
                userobj.put("hash", SeachGuardHash.hash(user.getPwd().toCharArray()));
                userobj.put("roles", roles);
                userobj.put("attributes", user.getAttr());
                jsonObject.put("value", userobj);
                jsonArray.add(jsonObject);
            }
            HttpResponse httpResponse = HttpUtils.doPatch(uri, "/_searchguard/api/internalusers", headers, null, jsonArray.toJSONString());
            String responseContent = HttpUtils.getResponseContent(httpResponse);
            JSONObject json = JSON.parseObject(responseContent);
            result.setSuccess("OK".equals(json.get("status")));
            result.setMsg((String) json.get("message"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("批量修改用户异常，请重新操作");
        }
        return result;
    }



}
