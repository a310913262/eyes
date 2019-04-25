package com.cmccsi.account.sync.accountsync.ldap.tools;

import com.cmccsi.account.sync.accountsync.ldap.domain.Style;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.util.StringUtils;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class LdapConvert {






    //    组转换
    public static <T> Attributes buildAttributes(T t){
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocAttr = new BasicAttribute("objectclass");
//      获取注解属性
        Class <?> clazz = t.getClass();
        Entry entryf = clazz.getAnnotation(Entry.class);
        String[] strings = entryf.objectClasses();
        for (String objectclass: strings) {
            ocAttr.add(objectclass);
        }
        attrs.put(ocAttr);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            Attribute attr = field.getAnnotation(Attribute.class);
            String name = attr.name();
            Object fieldValueByName = getFieldValueByName(name, t);
            attrs.put(name, fieldValueByName==null?"aaaa":fieldValueByName);
        }
        return attrs;
    }



    /**
     * <p>
     *     针对类
     * 根据属性名获取属性值
     * </p>
     * fieldName 属性名 object 属性所属对象
     * 支持Map扩展属性, 不支持List类型属性，
     * return 属性值
     */
    @SuppressWarnings("unchecked")
    public static Object getFieldValueByName(String fieldName, Object object) {
        try {
            Object fieldValue = null;
            if (StringUtils.hasLength(fieldName) && object != null) {
                String firstLetter = ""; // 首字母
                String getter = ""; // get方法
                Method method = null; // 方法
                String extraKey = null;
                // 处理扩展属性 extraData.key
                if (fieldName.indexOf(".") > 0) {
                    String[] extra = fieldName.split("\\.");
                    fieldName = extra[0];
                    extraKey = extra[1];
                }
                firstLetter = fieldName.substring(0, 1).toUpperCase();
                getter = "get" + firstLetter + fieldName.substring(1);
                method = object.getClass().getMethod(getter, new Class[] {});
                fieldValue = method.invoke(object, new Object[] {});
                if (extraKey != null) {
                    Map <String, Object> map = (Map<String, Object>) fieldValue;
                    fieldValue = map==null ? "":map.get(extraKey);
                }
            }
            return fieldValue;
        } catch (Throwable e) {
//            logger.error("获取属性值出现异常：" + e.getMessage(), e);
            return null;
        }
    }
}
