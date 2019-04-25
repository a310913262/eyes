package com.cmccsi.account.sync.accountsync.ldap.service.impl;

import com.cmccsi.account.sync.accountsync.ldap.domain.*;
import com.cmccsi.account.sync.accountsync.ldap.service.LdapService;
import com.cmccsi.account.sync.accountsync.ldap.tools.LdapConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.stereotype.Service;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import java.lang.reflect.Field;
import java.util.List;


@Service
public class LdapServiceImpl implements LdapService {

    @Autowired
    private LdapTemplate ldapTemplate;



    @Override
    public void addStyle() {
        Style style=new Style();
        style.setCn("66");
        style.setColor("aa");
        style.setDisplayStyleName("aaaaa");
        style.setLogoPath("bbb");
        style.setName("aaa");
        style.setStylePath("vvv/ss");
// 开通AD域
        try {
            Attributes attrs = LdapConvert.buildAttributes(style);
//			根据目录地址创建
            ldapTemplate.bind("CN=" + style.getCn() + ",ou=style,dc=organization", null, attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void editStyle() {

        Style style=new Style();
        style.setCn("66");
        style.setColor("ccc");
        style.setDisplayStyleName("aaccccaaa");
        style.setLogoPath("bcccbb");
        style.setName("uuuuu");
        style.setStylePath("aa/ss");


        Field[] fields = Style.class.getDeclaredFields();
        ModificationItem[] mods = new ModificationItem[fields.length];
        int i=0;
        for (Field field: fields) {
            Attribute attr = field.getAnnotation(Attribute.class);
            String name = attr.name();
            mods[i++] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(name, LdapConvert.getFieldValueByName(name,style)==null?"aaaa":LdapConvert.getFieldValueByName(name,style)));
        }

//            mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("displayName", adUser.getDisplayName()));
//            mods[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mobile", adUser.getMobile()));
//            mods[3] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("sn", adUser.getSurName()));
//            mods[4] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("givenName", adUser.getGivenName()));
//            mods[5] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("title", adUser.getTitle()));
//            mods[6] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("description", adUser.getDescription()));
//            mods[7] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("st", adUser.getSt()));
//            mods[8] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userpassword", adUser.getUserPassword()));
            ldapTemplate.modifyAttributes("CN=" + style.getCn() + ",ou=style,dc=organization", mods);

    }



    @Override
    public Style searchStyle(String cn) {

        try {

            AndFilter filter = new AndFilter();
            filter.and(new EqualsFilter("objectClass", "cmcc-style"));
            List <Style> search = ldapTemplate.search("CN=" + cn + ",ou=style,dc=organization", filter.encode(), new TAttributeMapper(Style.class));
            if (search.isEmpty()) {
                return null;
            }
            return search.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
