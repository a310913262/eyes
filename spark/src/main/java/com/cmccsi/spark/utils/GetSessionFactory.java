package com.cmccsi.spark.utils;

import com.cmccsi.spark.persistent.service.DaoProxy;
import com.cmccsi.spark.persistent.service.impl.PersonServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class GetSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;
    public static SqlSession sqlSession=null;

    private GetSessionFactory(){

    }

    synchronized public static SqlSessionFactory getSqlSessionFactory(){
        if(sqlSessionFactory==null){
            String resources="mybatis-config.xml";
            InputStream inputStream=null;
            try {
                inputStream= Resources.getResourceAsStream(resources);
            }catch (Exception e){
                e.printStackTrace();
            }
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        }
        return sqlSessionFactory;

    }

    public static void main(String[] args) {
        PersonServiceImpl ps = (PersonServiceImpl) DaoProxy.getInvoke(PersonServiceImpl.class);
        ps.saveOrUpdate("lisi");
    }
}
