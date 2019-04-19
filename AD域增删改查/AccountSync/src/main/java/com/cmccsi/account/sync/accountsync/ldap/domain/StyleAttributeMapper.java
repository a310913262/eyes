package com.cmccsi.account.sync.accountsync.ldap.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StyleAttributeMapper  implements AttributesMapper {


    @Override
    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        Style bean = null;
        if (Style.class.isAnnotationPresent(Entry.class)) {// 是否加@Entry
            try {
                bean = Style.class.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("反射创建PIJO[<T extends BaseDTO>]实例对象失败", e);
            }
            // 拿到bean对应的属性
            Field[] fields = Style.class.getDeclaredFields();
            // 遍历POJO 所有属性
            for (Field field : fields) {
                // 类属性上是否加@Attribute(name ="smart-type")
                boolean fieldHasAnno = field.isAnnotationPresent(Attribute.class);
                if (fieldHasAnno) {
                    Attribute fieldAnno = field.getAnnotation(Attribute.class);
                    // 注解的name值,如smart-type
                    String name = fieldAnno.name();
                    for (NamingEnumeration attrEnumeration = attributes.getAll(); attrEnumeration.hasMore();) {
                        javax.naming.directory.Attribute attr = (javax.naming.directory.Attribute) attrEnumeration
                                .next();
                        String ldapAttr = attr.getID();
                        if (ldapAttr.equals(name)) {
                            String ldapValue = attr.get().toString();
                            setProperty(bean, field.getName(), ldapValue);// 反射：给bean设置属性值
                        } else {
                            continue;
                        }
                    }
                }
            }
        } else {
            throw new RuntimeException("PIJO[<T extends BaseDTO>]需使用@Entry注解");
        }
        return bean;
    }


    /**
     * 反射：给bean设置属性值
     *
     * @param bean
     * @param name
     * @param value
     */
    private void setProperty(Object bean, String name, Object value) {
        String setterName = "set" + StringUtils.capitalize(name);
        Method setter;
        try {
            setter = bean.getClass().getMethod(setterName, value.getClass());
            setter.invoke(bean, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}