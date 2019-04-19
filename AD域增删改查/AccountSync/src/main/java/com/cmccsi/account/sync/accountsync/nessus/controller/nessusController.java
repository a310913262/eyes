package com.cmccsi.account.sync.accountsync.nessus.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cmccsi.account.sync.accountsync.nessus.pojo.Users;
import com.cmccsi.account.sync.accountsync.utils.httpclient.HttpUtils;
import com.cmccsi.account.sync.accountsync.utils.jsonbean.ResponseResult;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("nessusUser")
public class nessusController {

    @Value("${nessus.uri}")
    private String uri;

    private static LinkedHashMap<String, String> headers = new LinkedHashMap<String, String>();

    static {
        headers.put("X-ApiKeys", "accessKey=fca0d88fea2c9b0a217f38e3ef953707026b714f79a5c78bad8d9dc70040d5e6;secretKey=f28a3fae16374a78eff1366d841729f93cfff6497117990849f56813ba55ef47");
        // headers.put("X-Cookie", "token ={f6a383878cb0ac78acfd4fda935c98150d5378a36ce457db}");
        headers.put("Content-type", "application/json;charset=UTF-8");
    }

    /**
     * 查询用户列表
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getUserList")
    public ResponseResult getUserList() {
        ResponseResult responseResult = new ResponseResult();
        try {
            HttpResponse s = HttpUtils.doGet(uri, "/users", headers, null);
            String responseContent = HttpUtils.getResponseContent(s);
            System.out.println(responseContent);
            JSONObject jsonObject = JSON.parseObject(responseContent);
            //取json 中的key获取集合
            JSONArray users = jsonObject.getJSONArray("users");
            List<Users> userList = JSON.parseObject(users.toJSONString(), new TypeReference<List<Users>>() {
            });
            //创建返回的用户集合
            List<Users> list = new ArrayList<>();
            for (Users user : userList) {
                Users use = new Users();
                System.out.println("Users属性=" + user.getPermissions());
                use.setId(user.getId());
                use.setUsername(user.getUsername());
                use.setEmail(user.getEmail());
                use.setLastlogin(user.getLastlogin());
                use.setName(user.getName());
                use.setPermissions(user.getPermissions());
                use.setPro7_whats_new(user.getPro7_whats_new());
                list.add(use);
            }
            if (list.isEmpty()) {
                responseResult.setSuccess(false);
                responseResult.setMsg("查询结果为空，请重新操作");
            }
            responseResult.setSuccess(true);
            responseResult.setRows(list);

        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("查询结果异常，请重新操作");
        }
        return responseResult;
    }


    /**
     * 用户添加
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/addUser")
    public ResponseResult addUser(@RequestBody Users users) {
        ResponseResult responseResult = new ResponseResult();
        try {
            Map<String, String> objectMap = new HashMap<>();
            objectMap.put("username", users.getUsername());
            objectMap.put("password", users.getPassword());
            objectMap.put("email", users.getEmail());
            objectMap.put("permissions", users.getPermissions());
            objectMap.put("name", users.getName());
            objectMap.put("type", users.getType());
            net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(objectMap);
            String strJson = json.toString();
            HttpResponse result = HttpUtils.doPost(uri, "/users", headers, null, strJson);

            String responseContent = HttpUtils.getResponseContent(result);

            int code = result.getStatusLine().getStatusCode();

            //根据返回状态码判断成功失败
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setMsg("添加成功");
            } else if (code == 400) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 403) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 409) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("查询结果异常，请重新操作");
        }
        return responseResult;
    }

    /**
     * （批量）用户添加
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/addUsers")
    public ResponseResult addUsers(@RequestBody List<Users> users) {
        ResponseResult responseResult = new ResponseResult();
        StringBuffer res = new StringBuffer();
        StringBuffer sucess = new StringBuffer();
        StringBuffer fail = new StringBuffer();
        boolean flag=true;
        for (Users user : users) {
            try {

                Map<String, String> objectMap = new HashMap<>();
                objectMap.put("username", user.getUsername());
                objectMap.put("password", user.getPassword());
                objectMap.put("email", user.getEmail());
                objectMap.put("permissions", user.getPermissions());
                objectMap.put("name", user.getName());
                objectMap.put("type", user.getType());
                net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(objectMap);
                String strJson = json.toString();
                HttpResponse result = HttpUtils.doPost(uri, "/users", headers, null, strJson);

                String responseContent = HttpUtils.getResponseContent(result);

                int code = result.getStatusLine().getStatusCode();

                //根据返回状态码判断成功失败
                if (code == 200) {
                    sucess.append(user.getName() + ",");
                } else if (code == 400) {
                    flag=false;
                    fail.append(user.getName() + ":" + responseContent + ",");
                } else if (code == 403) {
                    flag=false;
                    fail.append(user.getName() + ":" + responseContent + ",");
                } else if (code == 409) {
                    flag=false;
                    fail.append(user.getName() + ":" + responseContent + ",");
                }

            } catch (Exception e) {
                e.printStackTrace();
                flag=false;
                fail.append("查询结果异常，请重新操作");
            }
        }
        responseResult.setSuccess(flag);
        responseResult.setMsg(res.append(sucess.toString() + fail.toString()).toString());
        return responseResult;
    }

    /**
     * 用户删除
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public ResponseResult deleteUser(String userId) {
        ResponseResult responseResult = new ResponseResult();
        try {

            HttpResponse result = HttpUtils.doDelete(uri, "/users" + userId, headers, null);

            int code = result.getStatusLine().getStatusCode();

            String responseContent = HttpUtils.getResponseContent(result);
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setMsg("删除成功");
            } else if (code == 403) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 404) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 409) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 500) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("查询结果异常，请重新操作");
        }
        return responseResult;
    }

    /**
     * 用户删除(批量)
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/deleteUsers")
    public ResponseResult deleteUsers(String userIds) {
        ResponseResult responseResult = new ResponseResult();
        try {

            String demoArray[] = userIds.split(",");
            List<String> demoList = Arrays.asList(demoArray);
            JSONObject object = new JSONObject();
            object.put("ids", demoList);
            HttpResponse result = HttpUtils.doDeletes(uri, "/users", headers, null, object.toJSONString());

            int code = result.getStatusLine().getStatusCode();
            String responseContent = HttpUtils.getResponseContent(result);
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setMsg(responseContent);
            } else if (code == 403) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 404) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 409) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 500) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("查询结果异常，请重新操作");
        }
        return responseResult;
    }

    /**
     * 用户修改密码
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/updatePassword")
    public ResponseResult updatePassword(@RequestBody Users users) {
        ResponseResult responseResult = new ResponseResult();
        try {

            Map<String, String> objectMap = new HashMap<>();
            objectMap.put("current_password", users.getCurrent_password());
            objectMap.put("password", users.getPassword());
            String id = users.getId();
            net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(objectMap);
            String strJson = json.toString();
            HttpResponse result = HttpUtils.doPut(uri, "/users/" + id + "/chpasswd", headers, null, strJson);

            int code = result.getStatusLine().getStatusCode();
            System.out.println(code);
            String responseContent = HttpUtils.getResponseContent(result);
            if (code == 200) {
                responseResult.setSuccess(true);
                responseResult.setMsg(responseContent);
            } else if (code == 400) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 403) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 404) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            } else if (code == 500) {
                responseResult.setSuccess(false);
                responseResult.setMsg(responseContent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMsg("查询结果异常，请重新操作");
        }
        return responseResult;
    }

    /**
     * 批量修改密码用户修改密码
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/updatePasswords")
    public ResponseResult updatePasswords(@RequestBody List<Users> users) {
        ResponseResult responseResult = new ResponseResult();
        StringBuffer res = new StringBuffer();
        StringBuffer sucess = new StringBuffer();
        StringBuffer fail = new StringBuffer();
        boolean flag=true;
        for (Users user : users) {

            try {
                Map<String, String> objectMap = new HashMap<>();
                objectMap.put("current_password", user.getCurrent_password());
                objectMap.put("password", user.getPassword());
                String id = user.getId();
                net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(objectMap);
                String strJson = json.toString();
                HttpResponse result = HttpUtils.doPut(uri, "/users/" + id + "/chpasswd", headers, null, strJson);

                String responseContent = HttpUtils.getResponseContent(result);
                int code = result.getStatusLine().getStatusCode();
                System.out.println(code);
                if (code == 200) {
                    sucess.append(user.getName() + ",");
                } else if (code == 400) {
                    flag=false;
                    fail.append(user.getName() + ":" + responseContent + ",");
                } else if (code == 403) {
                    flag=false;
                    fail.append(user.getName() + ":" + responseContent + ",");
                } else if (code == 409) {
                    flag=false;
                    fail.append(user.getName() + ":" + responseContent + ",");
                }

            } catch (Exception e) {
                e.printStackTrace();
                flag=false;
                fail.append("查询结果异常，请重新操作");
            }
        }
        responseResult.setSuccess(flag);
        responseResult.setMsg(res.append(sucess.toString() + fail.toString()).toString());
        return responseResult;
    }



}


