package com.cmccsi.account.sync.accountsync.grafana.controller;


import com.cmccsi.account.sync.accountsync.grafana.pojo.UserBean;
import com.cmccsi.account.sync.accountsync.utils.httpclient.HttpUtils;
import com.cmccsi.account.sync.accountsync.utils.jsonbean.ResponseResult;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("grafanaUser")
public class grafanaController {
    //服务器ip
    @Value("${grafana.uri}")
    private String host;

    private static LinkedHashMap<String, String> paramHeader = new LinkedHashMap<String, String>();

    static {
        paramHeader.put("Accept", "application/json");//get请求需要的头部
        paramHeader.put("Content-Type", "application/json");//post请求需要的头部
        paramHeader.put("Authorization", "Basic YWRtaW46YWRtaW4=");//接口必传
    }

    /**
     * 查询所有用户
     * @param params
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getUserList")
    public ResponseResult getUserList(@RequestBody UserBean params) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        //调用httpclient  接口GET /api/users?perpage=10&page=1
        try {
            String url = "/api/users/search";
//            String url = "/api/users/search?perpage=" + params.getPerpage() + "&page=" + params.getPage() + "&query=" + params.getQuery();
            //请求内容
            Map<String, String> paramBody = new HashMap<>();
            //调grafana 后返回的json 数据
            HttpResponse result = HttpUtils.doGet(host, url, paramHeader, null);
            String responseContent = HttpUtils.getResponseContent(result);
            int code = result.getStatusLine().getStatusCode();
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setObject(responseContent);
            } else {
                responseResult.setSuccess(false);
                responseResult.setObject(responseContent);
                responseResult.setMsg("查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("未找到该用户");
        }
        return responseResult;
    }

    /**
     * 添加用户
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUser")
    public ResponseResult addUser(@RequestBody UserBean params) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        //封装参数发送参数
        Map map = new HashMap();
        try {
            if (!StringUtils.isEmpty(params.getName())) {
                map.put("name", params.getName());// 用户名
            }
            if (!StringUtils.isEmpty(params.getEmail())) {
                map.put("email", params.getEmail());// 用户邮箱
            }
            if (!StringUtils.isEmpty(params.getLogin())) {
                map.put("login", params.getLogin());// 用户登录名
            }
            if (!StringUtils.isEmpty(params.getPassword())) {
                map.put("password", params.getPassword());// 用户登密码
            }
            //将数据转成json 数据
            //1、使用JSONObject
            JSONObject json = JSONObject.fromObject(map);
            String strJson = json.toString();
            //调用htttp client 接口发送请求
            String url = "/api/admin/users";

            //调grafana 后返回的json 数据
            HttpResponse result = HttpUtils.doPost(host, url, paramHeader, null, strJson);
            String responseContent = HttpUtils.getResponseContent(result);
            int code = result.getStatusLine().getStatusCode();
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setObject(responseContent);
                responseResult.setObject("添加成功");
            } else {
                responseResult.setSuccess(false);
                responseResult.setObject(responseContent);
                responseResult.setMsg("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("用户新增失败");
        }
        return responseResult;

    }

    /**
     * 添加用户（批量添加）
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUsers")
    public ResponseResult addUsers(@RequestBody List<UserBean> params) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        StringBuffer res = new StringBuffer();
        StringBuffer sucess = new StringBuffer();
        StringBuffer fail = new StringBuffer();
        boolean flag = true;

        //封装参数发送参数
        Map map = new HashMap();
        try {
            for (UserBean param : params) {
                if (!StringUtils.isEmpty(param.getName())) {
                    map.put("name", param.getName());// 用户名
                }
                if (!StringUtils.isEmpty(param.getEmail())) {
                    map.put("email", param.getEmail());// 用户邮箱
                }
                if (!StringUtils.isEmpty(param.getLogin())) {
                    map.put("login", param.getLogin());// 用户登录名
                }
                if (!StringUtils.isEmpty(param.getPassword())) {
                    map.put("password", param.getPassword());// 用户登密码
                }
                //将数据转成json 数据
                //1、使用JSONObject
                JSONObject json = JSONObject.fromObject(map);
                String strJson = json.toString();
                //调用htttp client 接口发送请求
                String url = "/api/admin/users";

                //调grafana 后返回的json 数据
                HttpResponse result = HttpUtils.doPost(host, url, paramHeader, null, strJson);
                String responseContent = HttpUtils.getResponseContent(result);
                int code = result.getStatusLine().getStatusCode();
                if (code == 200) {
                    sucess.append(param.getName() + ",");
                } else {
                    flag = false;
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
     * 修改用户密码
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/EditUserPassword")
    public ResponseResult EditUserPsaaword(@RequestBody UserBean params) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        //封装参数发送参数
        Map map = new HashMap();
        String id = null;
        try {
            if (!StringUtils.isEmpty(params.getId())) {
                id = params.getId();// 用户id
            }
            if (!StringUtils.isEmpty(params.getPassword())) {
                map.put("password", params.getPassword());// 用户登密码
            }
            //将数据转成json 数据//1、使用JSONObject
            JSONObject json = JSONObject.fromObject(map);
            String strJson = json.toString();
            //调用htttp client 接口发送请求PUT /api/admin/users/:id/password
            String url = "/api/admin/users/" + id + "/password";

            //调grafana 后返回的json 数据
            HttpResponse result = HttpUtils.doPut(host, url, paramHeader, null, strJson);
            String responseContent = HttpUtils.getResponseContent(result);
            int code = result.getStatusLine().getStatusCode();
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setObject(responseContent);
                responseResult.setObject("密码修改成功");
            } else {
                responseResult.setSuccess(false);
                responseResult.setObject(responseContent);
                responseResult.setMsg("密码修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("密码修改失败");
        }
        return responseResult;

    }

    /**
     * 批量修改用户密码
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/EditUsersPassword")
    public ResponseResult EditUsersPsaaword(@RequestBody List<UserBean> params) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        StringBuffer res = new StringBuffer();
        StringBuffer sucess = new StringBuffer();
        StringBuffer fail = new StringBuffer();
        boolean flag = true;
        try {
            for (UserBean param : params) {
                //封装参数发送参数
                Map map = new HashMap();
                String id = null;
                if (!StringUtils.isEmpty(param.getId())) {
                    id = param.getId();// 用户id
                }
                if (!StringUtils.isEmpty(param.getPassword())) {
                    map.put("password", param.getPassword());// 用户登密码
                }
                //将数据转成json 数据//1、使用JSONObject
                JSONObject json = JSONObject.fromObject(map);
                String strJson = json.toString();
                //调用htttp client 接口发送请求PUT /api/admin/users/:id/password
                String url = "/api/admin/users/" + id + "/password";

                //调grafana 后返回的json 数据
                HttpResponse result = HttpUtils.doPut(host, url, paramHeader, null, strJson);
                JSONObject jsonobject = JSONObject.fromObject(result);
                int code = result.getStatusLine().getStatusCode();
                String responseContent = HttpUtils.getResponseContent(result);
                if (code == 200) {
                    sucess.append(id + "," + responseContent);
                } else {
                    flag = false;
                    fail.append(id + ":" + responseContent + ",");
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
            flag = false;
            fail.append("批量修改用户密码户异常，请重新操作");
        }
        responseResult.setSuccess(flag);
        responseResult.setMsg(res.append(sucess.toString() + fail.toString()).toString());
        return responseResult;
    }

    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/DeleteUser")
    public ResponseResult DeleteUser(@RequestBody Map uid) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        int userId = (int) uid.get("userid");
        try {
            if ((userId+"").isEmpty()) {
                responseResult.setSuccess(false);
                responseResult.setMsg("参数为空");
            } else {
                //调用htttp client 接口发送请求
                String url = "/api/admin/users/" + userId;

                //调grafana 后返回的json 数据
                HttpResponse result = HttpUtils.doDelete(host, url, paramHeader, null);
                JSONObject jsonobject = JSONObject.fromObject(result);
                int code = result.getStatusLine().getStatusCode();
                String responseContent = HttpUtils.getResponseContent(result);
                if (code == 200) {
                    responseResult.setSuccess(true);
                    responseResult.setMsg("删除成功");
                } else {
                    responseResult.setSuccess(false);
                    responseResult.setMsg(responseContent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("密码修改失败");
        }
        return responseResult;
    }

    /**
     * 删除用户（循环删除）
     *
     * @param userIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/DeleteUsers")
    public ResponseResult DeleteUsers(@RequestBody List<Integer> userIds) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        StringBuffer res = new StringBuffer();
        StringBuffer sucess = new StringBuffer();
        StringBuffer fail = new StringBuffer();
        boolean flag = true;

        try {
            for (Object id:userIds) {
                //封装参数发送参数

                //调用htttp client 接口发送请求
                String url = "/api/admin/users/" + id;
                //调grafana 后返回的json 数据
                HttpResponse result = HttpUtils.doDelete(host, url, paramHeader, null);
                JSONObject jsonobject = JSONObject.fromObject(result);
                int code = result.getStatusLine().getStatusCode();
                String responseContent = HttpUtils.getResponseContent(result);
                if (code == 200) {
                    sucess.append(id.toString() + ",");
                } else {
                    flag = false;
                    fail.append(id.toString() + ":" + responseContent + ",");
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
     * 用户批量授权
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/authorization")
    public ResponseResult authorization(@RequestBody List<UserBean> params) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        StringBuffer res = new StringBuffer();
        StringBuffer sucess = new StringBuffer();
        StringBuffer fail = new StringBuffer();
        boolean flag = true;
        String orgid = null;
        try {
            for (UserBean param : params) {
                //封装参数发送参数
                if (!StringUtils.isEmpty(param.getOrgId())) {
                    orgid = param.getOrgId();// 用户id
                }
                Map map = new HashMap();
                if (!StringUtils.isEmpty(param.getLogin())) {
                    map.put("loginOrEmail", param.getLogin());
                }else if (!StringUtils.isEmpty(param.getEmail())){
                    map.put("loginOrEmail", param.getEmail());
                }else{
                    map.put("loginOrEmail", param.getLoginOrEmail());
                }
                if (!StringUtils.isEmpty(param.getRole())) {
                    map.put("role", param.getRole());// 权限
                }
                //将数据转成json 数据
                //1、使用JSONObject
                JSONObject json = JSONObject.fromObject(map);
                String strJson = json.toString();
                //调用htttp client
                String url = "/api/orgs/" + orgid + "/users";

                //调grafana 后返回的json 数据
                //  String result = HTTPSClientUtil.doPost(httpClient, url, paramHeader, null);
                HttpResponse result = HttpUtils.doPost(host, url, paramHeader, null, strJson);
                int code = result.getStatusLine().getStatusCode();
                String responseContent = HttpUtils.getResponseContent(result);
                if (code == 200) {
                    sucess.append(map.get("loginOrEmail") + "," + responseContent);
                } else {
                    flag = false;
                    fail.append(map.get("loginOrEmail") + ":" + responseContent + ",");
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
            flag = false;
            fail.append("用户批量授权，请重新操作");
        }
        responseResult.setSuccess(flag);
        responseResult.setMsg(res.append(sucess.toString() + fail.toString()).toString());
        return responseResult;
    }


    /**
     * 查询所有组织
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getOrgList")
    public ResponseResult getOrgList() throws Exception {
        ResponseResult responseResult = new ResponseResult();
        //调用httpclient  接口GET /api/users?perpage=10&page=1
        try {
            String url = "/api/orgs";
//            String url = "/api/users/search?perpage=" + params.getPerpage() + "&page=" + params.getPage() + "&query=" + params.getQuery();
            //请求内容
            Map<String, String> paramBody = new HashMap<>();
            //调grafana 后返回的json 数据
            HttpResponse result = HttpUtils.doGet(host, url, paramHeader, null);
            String responseContent = HttpUtils.getResponseContent(result);
            int code = result.getStatusLine().getStatusCode();
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setObject(responseContent);
            } else {
                responseResult.setSuccess(false);
                responseResult.setObject(responseContent);
                responseResult.setMsg("查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("未找到该用户");
        }
        return responseResult;
    }


    /**
     * 添加用户
     *
     * @param org
     * @return
     */
    @ResponseBody
    @RequestMapping("/addOrg")
    public ResponseResult addUser(@RequestBody Map org) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        //封装参数发送参数
        try {
            //将数据转成json 数据
            //1、使用JSONObject
            JSONObject json = JSONObject.fromObject(org);
            String strJson = json.toString();
            //调用htttp client 接口发送请求
            String url = "/api/orgs";

            //调grafana 后返回的json 数据
            HttpResponse result = HttpUtils.doPost(host, url, paramHeader, null, strJson);
            String responseContent = HttpUtils.getResponseContent(result);
            int code = result.getStatusLine().getStatusCode();
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setObject(responseContent);
                responseResult.setObject("添加成功");
            } else {
                responseResult.setSuccess(false);
                responseResult.setObject(responseContent);
                responseResult.setMsg("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("用户新增失败");
        }
        return responseResult;

    }
}
