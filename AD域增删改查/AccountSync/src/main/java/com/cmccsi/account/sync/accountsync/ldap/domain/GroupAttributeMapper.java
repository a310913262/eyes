package com.cmccsi.account.sync.accountsync.ldap.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;


public class GroupAttributeMapper implements AttributesMapper  {

	public Group mapFromAttributes(Attributes attributes) throws NamingException {
		Group bean = null;
		if (Group.class.isAnnotationPresent(Entry.class)) {// 是否加@Entry
			try {
				bean = Group.class.newInstance();
			} catch (Exception e) {
				throw new RuntimeException("反射创建PIJO[<T extends BaseDTO>]实例对象失败", e);
			}

			Field[] fields = Group.class.getDeclaredFields();// 拿到bean对应的属性
			for (Field field : fields) {// 遍历POJO 所有属性
				boolean fieldHasAnno = field.isAnnotationPresent(Attribute.class);// 类属性上是否加@Attribute(name =
				// "smart-type")
				if (fieldHasAnno) {
					Attribute fieldAnno = field.getAnnotation(Attribute.class);
					String name = fieldAnno.name();// 注解的name值,如smart-type
					for (NamingEnumeration attrEnumeration = attributes.getAll(); attrEnumeration.hasMore();) {

						javax.naming.directory.Attribute attr = (javax.naming.directory.Attribute) attrEnumeration
								.next();
						String ldapAttr = attr.getID();
						if (ldapAttr.equals(name)) {
							if("member".equals(name)) {
								StringBuffer sb=new StringBuffer();
								int size = attr.size();
								for (int i = 0; i < attr.size(); i++) {
									Object object = attr.get(i);
									sb.append(StringUtils.isBlank(sb.toString())?object.toString():"&&"+object.toString());
								}
								String ldapValue = attr.get().toString();
								setProperty(bean, field.getName(), sb.toString());// 反射：给bean设置属性值
							}else {

								String ldapValue = attr.get().toString();
								setProperty(bean, field.getName(), ldapValue);// 反射：给bean设置属性值
							}

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
			if("members".equals(name)) {
				String[] split = value.toString().split("&&");
				List <String> list=new ArrayList <String>();
				for (String string : split) {
					list.add(string);
				}
				setter = bean.getClass().getMethod(setterName, List.class);
				setter.invoke(bean, list);
			}else {
				setter = bean.getClass().getMethod(setterName, value.getClass());
				setter.invoke(bean, value);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
