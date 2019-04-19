package com.cmccsi.account.sync.accountsync.ldap.tools;

import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Component;

@Component
public class LdapTemplateUtil {
	@Bean
	public LdapTemplate getLdapTemplate(){
        LdapTemplate template = null;
        try {
            LdapContextSource contextSource = new LdapContextSource();
            contextSource.setUrl(LdapConstans.LDAP_URL);
            contextSource.setBase(LdapConstans.BASE_DC);
            contextSource.setUserDn(LdapConstans.USER_NAME);
            contextSource.setPassword(LdapConstans.PASS_WORD);
            contextSource.setPooled(false);
            contextSource.afterPropertiesSet(); // important
            template = new LdapTemplate(contextSource);
        }catch (Exception e){
            e.printStackTrace();
        }
        return template;
    }
}
