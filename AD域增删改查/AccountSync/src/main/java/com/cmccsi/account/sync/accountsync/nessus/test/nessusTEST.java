package com.cmccsi.account.sync.accountsync.nessus.test;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;




import com.cmccsi.account.sync.accountsync.nessus.pojo.Users;
import com.cmccsi.account.sync.accountsync.utils.httpclient.HttpUtils;
import org.apache.http.HttpResponse;

;


import java.util.*;

public class nessusTEST {
    private static LinkedHashMap<String, String> headers = new LinkedHashMap<String, String>();

    static {
        headers.put("X-ApiKeys", "accessKey=fca0d88fea2c9b0a217f38e3ef953707026b714f79a5c78bad8d9dc70040d5e6;secretKey=f28a3fae16374a78eff1366d841729f93cfff6497117990849f56813ba55ef47");
        // headers.put("X-Cookie", "token ={f6a383878cb0ac78acfd4fda935c98150d5378a36ce457db}");
         headers.put("Content-type", "application/json;charset=UTF-8");
    }

 /*  public static void main(String[] args) throws Exception {
        HttpResponse s = HttpUtils.doGet("https://172.17.248.63:8834", "/users", headers, null);
        String responseContent = HttpUtils.getResponseContent(s);
        System.out.println(responseContent);



    }*/



 /*   public static void main(String[] args) throws Exception {

       String json="{\"users\":[{\"pro7_whats_new\":null,\"lastlogin\":1545618859,\"permissions\":32,\"type\":\"local\",\"name\":\"lifupeng\",\"email\":\"lifupeng@163.com\",\"username\":\"lfp\",\"id\":2},{\"pro7_whats_new\":null,\"lastlogin\":1545722141,\"permissions\":128,\"type\":\"local\",\"name\":\"admin\",\"email\":null,\"username\":\"admin\",\"id\":3}]}";
   // String json="{'users':[{'pro7_whats_new':null,'lastlogin':1545618859,'permissions':32,'type':'local','name':'lifupeng','email':'lifupeng@163.com','username':'lfp','id':2},{'pro7_whats_new':null,'lastlogin':1545722141,'permissions':128,'type':'local','name':'admin','email':null,'username':'admin','id':3}]}";

        JSONObject jsonObject = JSON.parseObject(json);

    JSONArray users = jsonObject.getJSONArray("users");
    List<Users> userList = JSON.parseObject(users.toJSONString(), new TypeReference<List<Users>>() {});
        for (Users user: userList) {
        System.out.println("Users属性="+user.getPermissions());

    }
}*/
/*  public static void main(String[] args) throws Exception {
      //封装添加参数

      Users users = new Users();
      users.setUsername("skx");
      users.setPassword("123456");
      users.setEmail("skx@163.com");
      users.setPermissions("32");
      users.setName("sikaixuan");
      users.setType("local");
      Map<String,String> objectMap = new HashMap<>();
      objectMap.put("username",users.getUsername());
      objectMap.put("password",users.getPassword());
      objectMap.put("email",users.getEmail());
      objectMap.put("permissions",users.getPermissions());
      objectMap.put("name",users.getName());
      objectMap.put("type",users.getType());
      net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(objectMap);
      String strJson = json.toString();
      HttpResponse result = HttpUtils.doPost("https://172.17.248.63:8834", "/users", headers, null, strJson);

        String responseContent = HttpUtils.getResponseContent(result);
        System.out.println(responseContent);

    }*/

   /* public static void main(String[] args) throws Exception {
        //封装添加参数

        String userId="5";
        HttpResponse result = HttpUtils.doDelete("https://172.17.248.63:8834", "/users/"+userId, headers, null);
        System.out.println(result);
        String responseContent = HttpUtils.getResponseContent(result);
        System.out.println(responseContent);
        System.out.println(result.getStatusLine().getStatusCode());
    }*/
/*    public static void main(String[] args) throws Exception {
        //封装添加参数

       // String demosub = string.substring(1,string.length()-1);
        String string = "6,7";
        String demoArray[] = string.split(",");
        List<String> demoList = Arrays.asList(demoArray);
        JSONObject object = new JSONObject();
        object.put("ids",demoList);
        HttpResponse result = HttpUtils.doDeletes("https://172.17.248.63:8834", "/users", headers, null,object.toJSONString());
        System.out.println(result);
        String responseContent = HttpUtils.getResponseContent(result);
        System.out.println(responseContent);
        System.out.println(result.getStatusLine().getStatusCode());
    }*/
 /*   public static void main(String[] args) throws Exception {
        //封装添加参数
        Users users = new Users();
        users.setCurrent_password("123456");
        users.setPassword("654321");
        users.setId("2");
        //修改密码
        Map<String,String> objectMap = new HashMap<>();
        objectMap.put("current_password",users.getCurrent_password());
        objectMap.put("password",users.getPassword());
        String id = users.getId();
        net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(objectMap);
        String strJson = json.toString();
        HttpResponse result = HttpUtils.doPut("https://172.17.248.63:8834", "/users/"+id+"/chpasswd", headers, null,strJson);

        int code= result.getStatusLine().getStatusCode();
        System.out.println(code);
        String responseContent = HttpUtils.getResponseContent(result);
    }*/
   /* public static void main(String[] args) throws Exception {
        //封装添加参数
        Users user = new Users();
        user.setPermissions("64");
        user.setId("2");
        Map<String, String> objectMap = new HashMap<>();
        objectMap.put("permissions", user.getPermissions());
        objectMap.put("name", user.getPermissions());
        objectMap.put("email", user.getPermissions());
        objectMap.put("enabled", user.getPermissions());

        String id = user.getId();
        net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(objectMap);
        String strJson = json.toString();
        System.out.println(strJson);

        HttpResponse result = HttpUtils.doPut("https://172.17.248.63:8834", "/users/" + id, headers, null, strJson);

        String responseContent = HttpUtils.getResponseContent(result);
        int code = result.getStatusLine().getStatusCode();
        System.out.println(responseContent);
        System.out.println(code);
    }*/
  /* public static void main(String[] args) throws Exception {
       //添加用户组
       String groupName="testUser";
       Map<String, String> objectMap = new HashMap<>();
       objectMap.put("name", groupName);

       net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(objectMap);
       String strJson = json.toString();
       HttpResponse result = HttpUtils.doPost("https://172.17.248.63:8834", "/groups", headers, null, strJson);

       String responseContent = HttpUtils.getResponseContent(result);
       int code = result.getStatusLine().getStatusCode();
       System.out.println(responseContent);
       System.out.println(code);
   }*/
  /* public static void main(String[] args) throws Exception {
        //用户组列表
        HttpResponse result = HttpUtils.doGet("https://172.17.248.63:8834", "/groups", headers, null);

        String responseContent = HttpUtils.getResponseContent(result);
        int code = result.getStatusLine().getStatusCode();
        System.out.println(responseContent);
        System.out.println(code);
    }*/
  /*  public static void main(String[] args) throws Exception {
        //用户组列表
        HttpResponse result = HttpUtils.doGet("https://172.17.248.63:8834", "/groups", headers, null);

        String responseContent = HttpUtils.getResponseContent(result);
        int code = result.getStatusLine().getStatusCode();
        System.out.println(responseContent);
        System.out.println(code);
    }*/
   public static void main(String[] args) throws Exception {
       //用户组添加用户



       HttpResponse result = HttpUtils.doGet("https://172.17.248.63:8834", "/groups", headers, null);

       String responseContent = HttpUtils.getResponseContent(result);
       int code = result.getStatusLine().getStatusCode();
       System.out.println(responseContent);
       System.out.println(code);
   }

}
