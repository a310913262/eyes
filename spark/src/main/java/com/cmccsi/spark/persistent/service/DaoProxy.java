package com.cmccsi.spark.persistent.service;

import com.cmccsi.spark.utils.GetSessionFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.Method;

public class DaoProxy implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        SqlSessionFactory sqlSessionFactory= GetSessionFactory.getSqlSessionFactory();
        GetSessionFactory.sqlSession = sqlSessionFactory.openSession();
        methodProxy.invokeSuper(o, objects);
        GetSessionFactory.sqlSession.commit();
        GetSessionFactory.sqlSession.close();
        return null;
    }

    public static Object getInvoke(Class clazz){
        DaoProxy daoProxy = new DaoProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(daoProxy);
        Object o = enhancer.create();
        return o;
    }
}
