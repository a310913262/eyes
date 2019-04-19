package com.cmccsi.account.sync.accountsync.ldap.service.impl;

import com.cmccsi.account.sync.accountsync.ldap.domain.*;
import com.cmccsi.account.sync.accountsync.ldap.service.LdapService;
import com.cmccsi.account.sync.accountsync.ldap.tools.LdapConvert;
import com.cmccsi.account.sync.accountsync.utils.jsonbean.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import java.util.List;


@Service
public class LdapServiceImpl implements LdapService {

    @Autowired
    private LdapTemplate ldapTemplate;

    /**
     * 添加用戶到AD
     *
     * @param user
     */
    @Override
    public boolean addUser(AdUser user) {
        // 开通AD域
        try {
            Attributes attrs = LdapConvert.buildAttributes(user);
            String pwd = user.getUserPassword();
            attrs.put("userpassword", pwd);
//			根据目录地址创建
            ldapTemplate.bind("CN=" + user.getCommonName() + ",CN=Users", null, attrs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * AD中刪除用戶
     *
     * @param user
     */
    @Override
    public boolean delUser(AdUser user) {
        try {
            ldapTemplate.unbind("CN=" + user.getCommonName() + ",CN=Users");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改用戶信息
     *
     * @param user
     */
    @Override
    public void editUser(AdUser user) {
        // 开通AD域
        try {
            Attributes attrs = LdapConvert.buildAttributes(user);

            String pwd = user.getUserPassword();
            attrs.put("userpassword", pwd);
//			根据目录地址创建
            ldapTemplate.rebind("CN=" + user.getCommonName() + ",CN=Users", null, attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Style searchStyle(String cn) {

        try {

            AndFilter filter = new AndFilter();
            filter.and(new EqualsFilter("objectClass", "cmcc-style"));
            List <Style> search = ldapTemplate.search("CN=" + cn + ",ou=style,dc=organization", filter.encode(), new StyleAttributeMapper());
            if (search.isEmpty()) {
                return null;
            }
            return search.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询用户
     *
     * @param userName
     */
    @Override
    public AdUser searchUser(String userName) {
        try {

            AndFilter filter = new AndFilter();
            filter.and(new EqualsFilter("objectClass", "person"));
            List <AdUser> search = ldapTemplate.search("CN=" + userName + ",CN=Users", filter.encode(), new AdUserAttributeMapper());
            if (search.isEmpty()) {
                return null;
            }
            return search.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加用户组
     *
     * @param group
     */
    @Override
    public boolean addGroup(Group group) {
        // 开通AD域
        try {
            Attributes attrs = LdapConvert.buildAttributes(group);
            ldapTemplate.bind("CN=" + group.getCommonName() + ",CN=Users", null, attrs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除用户组
     *
     * @param group
     */
    @Override
    public boolean delGroup(Group group) {
        try {
            ldapTemplate.unbind("CN=" + group.getCommonName() + ",CN=Users");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改用户组
     *
     * @param group
     */
    @Override
    public void editGroup(Group group) {

    }

    /**
     * 查询用户组
     *
     * @param groupName
     * @return
     */
    @Override
    public Group searchGroup(String groupName) {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectClass", "group"));
        List <Group> search = ldapTemplate.search("CN=" + groupName + ",CN=Users", filter.encode(), new GroupAttributeMapper());
        if (search.isEmpty()) {
            return null;
        }
        return search.get(0);
    }

    /**
     * 查询所有用户组
     *
     * @return
     */
    @Override
    public List <Group> searchGroupAll() {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectClass", "group"));
        List <Group> search = ldapTemplate.search("CN=Users", filter.encode(), new GroupAttributeMapper());
        if (search.isEmpty()) {
            return null;
        }
        return search;
    }

    /**
     * 单密码同步
     *
     * @param adUser
     */
    @Override
    public boolean updateOnePwd(AdUser adUser) {
        try {
            ModificationItem[] mods = new ModificationItem[9];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mail", adUser.getEmail()));
            mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("displayName", adUser.getDisplayName()));
            mods[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mobile", adUser.getMobile()));
            mods[3] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("sn", adUser.getSurName()));
            mods[4] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("givenName", adUser.getGivenName()));
            mods[5] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("title", adUser.getTitle()));
            mods[6] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("description", adUser.getDescription()));
            mods[7] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("st", adUser.getSt()));
            mods[8] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userpassword", adUser.getUserPassword()));
            ldapTemplate.modifyAttributes("CN=" + adUser.getCommonName() + ",CN=Users", mods);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加用户进组
     *
     * @param groupName
     * @param userNames
     */
    public ResponseResult addMemberToGroup(String groupName, List <String> userNames) {
        ResponseResult result = new ResponseResult();
        boolean flag = true;
        StringBuffer msg1 = null;
        try {
            Group search = searchGroup(groupName);
            if (search == null) {
                result.setSuccess(false);
                result.setMsg("用户组不存在");
                return result;
            }
            List <String> members = search.getMembers();
            for (String userName :
                    userNames) {
                AdUser adUser = searchUser(userName);
                if (adUser != null) {
                    members.add(adUser.getDn());
                    flag = false;
                } else {
                    msg1.append("用户： " + userName + "不存在/n");
                    flag = false;
                }
            }
            search.setMembers(members);
            rebindGroup(search);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setMsg(msg1.toString());
        result.setSuccess(flag);
        return result;
    }

    /**
     * 组内移除用户
     *
     * @param groupName
     * @param userNames
     */
    public ResponseResult removeMemberToGroup(String groupName, List <String> userNames) {
        ResponseResult result = new ResponseResult();
        boolean flag = true;
        StringBuffer msg1 = null;
        StringBuffer msg2 = null;
        try {
            Group search = searchGroup(groupName);
            if (search == null) {
                result.setSuccess(false);
                result.setMsg("用户组不存在");
                return result;
            }
            List <String> members = search.getMembers();
            for (String userName :
                    userNames) {
                AdUser adUser = searchUser(userName);
                if (adUser != null) {
                    boolean contains = members.contains(adUser.getDn());
                    if (contains) {
                        members.remove(adUser.getDn());
                        msg2.append("用户组成员不包含用户： " + userName + "/n");
                        flag = false;
                    }
                } else {
                    msg1.append("用户： " + userName + "不存在/n");
                    flag = false;
                }
            }
            search.setMembers(members);
            rebindGroup(search);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setMsg(msg1.toString() + msg2.toString());
        result.setSuccess(flag);
        return result;
    }

    @Override
    public List searchAll() {
        try {
            AndFilter filter = new AndFilter();
            filter.and(new EqualsFilter("objectClass", "person"));
            List <AdUser> search = ldapTemplate.search("CN=Users", filter.encode(), new AdUserAttributeMapper());
            return search;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * group公共提取
     *
     * @param group
     */
    public void rebindGroup(Group group) {
        // 开通AD域
        try {
            Attributes attrs = LdapConvert.buildAttributes(group);
            ldapTemplate.rebind("CN=" + group.getCommonName() + ",CN=Users", null, attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
