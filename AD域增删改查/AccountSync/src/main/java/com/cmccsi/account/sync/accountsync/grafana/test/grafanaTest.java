package com.cmccsi.account.sync.accountsync.grafana.test;

import com.cmccsi.account.sync.accountsync.grafana.pojo.UserBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class grafanaTest {



  /*  public static void main(String[] args) {
        Map<String,String> map = new HashMap();
        //创建参数
        UserBean params = new UserBean();
        params.setName("lfp");
        params.setEmail("lifupeng@163.com");
        params.setLogin("lifupeng");
        params.setPassword("123456");
        //封装参数发送参数


        if (!StringUtils.isEmpty(params.getName())) {
            map.put("name",params.getName());// 用户名
        }
        if (!StringUtils.isEmpty(params.getEmail())) {
            map.put("email",params.getEmail());// 用户邮箱
        }
        if (!StringUtils.isEmpty(params.getLogin())) {
            map.put("login",params.getLogin());// 用户登录名
        }
        if (!StringUtils.isEmpty(params.getPassword())) {
            map.put("password",params.getPassword());// 用户登密码
        }
        //将数据转成json 数据
        //1、使用JSONObject
        JSONObject json = JSONObject.fromObject(map);
        String strJson=json.toString();
        System.out.println(strJson);
        String jsons="{\"id\":5,\"message\":\"User created\"}";
        JSONObject jsonobject = JSONObject.fromObject(jsons);
        Object id = jsonobject.get("id");
        System.out.println(id);
    }*/
    public static void main(String[] args) {
     List<UserBean> list =new ArrayList<UserBean>();
        //创建参数
        UserBean params = new UserBean();
        params.setName("lfp");
        params.setEmail("lifupeng@163.com");
        params.setLogin("lifupeng");
        params.setPassword("123456");
        //两个对象
        UserBean params1 = new UserBean();
        params1.setName("lfp");
        params1.setEmail("lifupeng@163.com");
        params1.setLogin("lifupeng");
        params1.setPassword("123456");
        //封装参数发送参数
        list.add(params);
        list.add(params1);


        //将数据转成json 数据
        //1、使用JSONObject
     //   JSONObject json = JSONObject.fromObject(list);
        //2、使用JSONArray
        JSONArray listArray=JSONArray.fromObject(list);
        String strJson=listArray.toString();
        List<UserBean> list2=(List<UserBean>)JSONArray.toList(JSONArray.fromObject(strJson), UserBean.class);
        System.out.println("list类型json 数据"+strJson);
        for (UserBean stu : list2) {
            System.out.println(stu);
        }
        String jsons="{\"id\":5,\"message\":\"User created\"}";
        JSONObject jsonobject = JSONObject.fromObject(jsons);
        Object id = jsonobject.get("id");
        System.out.println(id);
    }
}
