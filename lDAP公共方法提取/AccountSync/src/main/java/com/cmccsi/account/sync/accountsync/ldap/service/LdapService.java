package com.cmccsi.account.sync.accountsync.ldap.service;


import com.cmccsi.account.sync.accountsync.ldap.domain.Style;
import com.cmccsi.account.sync.accountsync.utils.jsonbean.ResponseResult;

import java.util.List;

public interface LdapService {

    Style searchStyle(String cn);
    void addStyle();
    void editStyle();
}
