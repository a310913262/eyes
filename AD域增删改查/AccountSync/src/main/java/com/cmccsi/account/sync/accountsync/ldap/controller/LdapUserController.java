package com.cmccsi.account.sync.accountsync.ldap.controller;

import com.cmccsi.account.sync.accountsync.ldap.domain.AdUser;
import com.cmccsi.account.sync.accountsync.ldap.domain.Group;
import com.cmccsi.account.sync.accountsync.ldap.domain.Style;
import com.cmccsi.account.sync.accountsync.ldap.service.LdapService;
import com.cmccsi.account.sync.accountsync.utils.jsonbean.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("LdapUserController/")
public class LdapUserController {

    @Autowired
    LdapService ldapService;

    /**
     * 单密码同步
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("updateOnePwd")
    public ResponseResult updateOnePwd(AdUser adUser) {
        ResponseResult result = new ResponseResult();
        String msg = "";
        boolean flag = true;
        AdUser search = ldapService.searchUser(adUser.getCommonName());
        if (search == null || StringUtils.isBlank(search.getDn())) {
            flag = false;
            msg = "用户： " + adUser.getCommonName() + "   不存在";
        } else {
            flag = ldapService.updateOnePwd(adUser);
        }
        result.setMsg(msg);
        result.setSuccess(flag);
        return result;
    }

    /**
     * 批量密码同步
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("updateMorePwd")
    public ResponseResult updateMorePwd(List <AdUser> adUsers) {
        ResponseResult result = new ResponseResult();
        StringBuffer msg1 = null;
        StringBuffer msg2 = null;
        boolean flag = true;
        for (AdUser adUser : adUsers) {
            AdUser search = ldapService.searchUser(adUser.getCommonName());
            if (search == null || StringUtils.isBlank(search.getDn())) {
                flag = false;
                msg1.append("用户： " + adUser.getCommonName() + "   不存在/n");
            } else {
                boolean b = ldapService.updateOnePwd(adUser);
                msg2.append(b ? "" : adUser.getCommonName() + "密码同步失败/n");
                if (!b) {
                    flag = false;
                }
            }
        }
        result.setMsg(msg1.toString() + msg2.toString());
        result.setSuccess(flag);
        return result;
    }


    /**
     * 创建从账号
     *
     * @param adUser
     * @return
     */
    @ResponseBody
    @RequestMapping("addUser")
    public ResponseResult addUser(AdUser adUser) {
        ResponseResult result = new ResponseResult();
        String msg = "";
        boolean flag = true;
        AdUser search = ldapService.searchUser(adUser.getCommonName());
        if (search == null || StringUtils.isBlank(search.getDn())) {
            ldapService.addUser(adUser);
            flag = true;
        } else {
            msg = "用户： " + adUser.getCommonName() + "   已存在";
            flag = false;
        }
        result.setMsg(msg);
        result.setSuccess(flag);
        return result;

    }

    /**
     * 从账号删除
     *
     * @param adUser
     * @return
     */
    @ResponseBody
    @RequestMapping("delUser")
    public ResponseResult delUser(AdUser adUser) {
        ResponseResult result = new ResponseResult();
        String msg = "";
        boolean flag = true;
        AdUser search = ldapService.searchUser(adUser.getCommonName());
        if (search == null || StringUtils.isBlank(search.getDn())) {
            flag = false;
            msg = "用户： " + adUser.getCommonName() + "   不存在";
        } else {
            flag = ldapService.delUser(adUser);
        }
        result.setMsg(msg);
        result.setSuccess(flag);
        return result;
    }

    /**
     * 用户组添加从账号
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("groupAddUser")
    public ResponseResult groupAddUser(String groupName, List <String> userNames) {
        ResponseResult result = ldapService.addMemberToGroup(groupName, userNames);
        return result;
    }

    /**
     * 用户组删除从账号
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("groupDelUser")
    public ResponseResult groupDelUser(String groupName, List <String> userNames) {
        ResponseResult result = ldapService.removeMemberToGroup(groupName, userNames);
        return result;
    }

    /**
     * 查询用户组下成员
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("searchUserByGroup")
    public ResponseResult searchUserByGroup(String name) {
        ResponseResult result = new ResponseResult();
        List list = new ArrayList();
        Group group = ldapService.searchGroup(name);
        if (group == null) {
            result.setSuccess(false);
            result.setMsg("用户组不存在");
            return result;
        }
        List <String> members = group.getMembers();
        if (members.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("用户组成员为空");
        } else {
            for (String userDn :
                    members) {
                String userName = userDn.split(",")[0].split("=")[1].trim();
                list.add(ldapService.searchUser(userName));
            }
            result.setSuccess(true);
            result.setRows(list);
        }
        return result;
    }

    /**
     * 查询所有用户组
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("searchGroupAll")
    public ResponseResult searchGroupAll() {
        ResponseResult result = new ResponseResult();
        List list = new ArrayList();
        List <Group> groups = ldapService.searchGroupAll();
        if (groups.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("用户组为空");
            return result;
        }

        for (Group group : groups) {
            String groupDn = group.getDn();
            String groupName = groupDn.split(",")[0].split("=")[1].trim();
            list.add(groupName);
        }
        result.setSuccess(true);
        result.setRows(list);

        return result;
    }

    /**
     * 查询所有成员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("searchAll")
    public ResponseResult searchAll() {
        ResponseResult result = new ResponseResult();
        List list = ldapService.searchAll();
        result.setSuccess(true);
        result.setRows(list);
        return result;
    }

    /**
     * 根据名字查询用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("searchUser")
    public ResponseResult searchUser(String name) {
        ResponseResult result = new ResponseResult();
        AdUser adUser = ldapService.searchUser(name);

        if (adUser == null) {
            result.setMsg("用户： " + name + "不存在");
            result.setSuccess(false);
        } else {
            result.setSuccess(true);
            result.setObject(adUser);
        }
        return result;
    }


    /**
     * 根据名字查询用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("searchStyle")
    public ResponseResult searchStyle(String cn) {
        ResponseResult result = new ResponseResult();
        Style style = ldapService.searchStyle(cn);

        System.out.println(style);

        return result;
    }

}
