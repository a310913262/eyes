package com.cmccsi.account.sync.accountsync.ldap.service;


import com.cmccsi.account.sync.accountsync.ldap.domain.AdUser;
import com.cmccsi.account.sync.accountsync.ldap.domain.Group;
import com.cmccsi.account.sync.accountsync.ldap.domain.Style;
import com.cmccsi.account.sync.accountsync.utils.jsonbean.ResponseResult;

import java.util.List;

public interface LdapService {
    /**
     * 添加用户
     * @param user
     */
    public boolean addUser(AdUser user);

    /**
     * 删除用户
     * @param user
     */
    public boolean delUser(AdUser user);

    /**
     * 修改用户
     * @param user
     */
    public void editUser(AdUser user);

    /**
     * 查询用户
     * @param userName
     * @return
     */
    public AdUser searchUser(String userName);

    /**
     * 添加用户组
     * @param group
     */
    public boolean addGroup(Group group);

    /**
     * 删除用户组
     * @param group
     */
    public boolean delGroup(Group group);

    /**
     * 修改用户组
     * @param group
     */
    public void editGroup(Group group);

    /**
     * 查询用户组
     * @param groupName
     * @return
     */
    public Group searchGroup(String groupName);


    /**
     * 查询所有用户组
     * @return
     */
    public List<Group> searchGroupAll();


    /**
     * 更改密码
     * @param adUser
     * @return
     */
    boolean updateOnePwd(AdUser adUser);

    /**
     * 添加组成员
     * @param groupName
     * @param userNames
     * @return
     */
    public ResponseResult addMemberToGroup(String groupName, List <String> userNames);

    /**
     * 移除组成员
     * @param groupName
     * @param userNames
     * @return
     */
    public ResponseResult removeMemberToGroup(String groupName, List <String> userNames);

    /**
     * 查询所有用户
     */
    public List searchAll();

    Style searchStyle(String cn);
}
