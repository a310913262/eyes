package com.cmccsi.account.sync.accountsync.ldap.tools;

import com.cmccsi.account.sync.accountsync.ldap.domain.AdUser;
import com.cmccsi.account.sync.accountsync.ldap.domain.Group;
import com.cmccsi.account.sync.accountsync.ldap.domain.Style;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import java.util.List;

public class LdapConvert {

//  用户转换
    public static Attributes buildAttributes(AdUser adUser) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocAttr = new BasicAttribute("objectclass");
        ocAttr.add("top");
        ocAttr.add("person");
        ocAttr.add("organizationalPerson");
        ocAttr.add("user");
        attrs.put(ocAttr);
        attrs.put("cn", adUser.getCommonName());
        attrs.put("sn", adUser.getSurName());
        attrs.put("sAMAccountName", adUser.getAccountName());
        attrs.put("displayName", adUser.getDisplayName());
        attrs.put("mail", adUser.getEmail());
        attrs.put("mobile", adUser.getMobile());
        attrs.put("title", adUser.getTitle());
        return attrs;
    }
//    组转换
    public static Attributes buildAttributes(Group group) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocAttr = new BasicAttribute("objectclass");
        ocAttr.add("top");
        ocAttr.add("group");
        attrs.put(ocAttr);
        attrs.put("cn", group.getCommonName());
        attrs.put("name", group.getName());
        attrs.put("groupType", group.getGroupType());
        List<String> members = group.getMembers();
        if(members!=null) {
            BasicAttribute memberAttr = new BasicAttribute("member");
            for (String string : members) {
                memberAttr.add(string);
            }
            attrs.put(memberAttr);
        }
        return attrs;
    }


    //    组转换
    public static Attributes buildAttributes(Style style) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocAttr = new BasicAttribute("objectclass");
        ocAttr.add("top");
        ocAttr.add("cmcc-baseBusinessObj");
        ocAttr.add("cmcc-style");
        attrs.put(ocAttr);
        attrs.put("cn", style.getCn());
        attrs.put("name", style.getName());
        attrs.put("displayStyleName",style.getDisplayStyleName());
        attrs.put("logoPath", style.getLogoPath());
        attrs.put("stylePath", style.getStylePath());
        attrs.put("imagePath", style.getImagePath());
        attrs.put("color", style.getColor());
        attrs.put("status", style.getStatus());
        attrs.put("isDefault", style.getIsDefault());

        return attrs;
    }
}
