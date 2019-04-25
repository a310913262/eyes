package com.cmccsi.account.sync.accountsync.ldap.controller;

import com.cmccsi.account.sync.accountsync.ldap.domain.Style;
import com.cmccsi.account.sync.accountsync.ldap.service.LdapService;
import com.cmccsi.account.sync.accountsync.utils.jsonbean.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("LdapUserController/")
public class LdapUserController {

    @Autowired
    LdapService ldapService;



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

        ldapService.addStyle();
        ldapService.editStyle();


        return result;
    }

}
