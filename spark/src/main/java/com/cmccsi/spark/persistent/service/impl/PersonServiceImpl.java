package com.cmccsi.spark.persistent.service.impl;

import com.cmccsi.spark.persistent.db.dao.PersonMapper;
import com.cmccsi.spark.persistent.db.model.Person;
import com.cmccsi.spark.persistent.service.DaoProxy;
import com.cmccsi.spark.persistent.service.PersonService;
import com.cmccsi.spark.utils.GetSessionFactory;
import org.apache.ibatis.session.SqlSession;


public class PersonServiceImpl  extends DaoProxy implements PersonService{



    public void saveOrUpdate(String name){
        SqlSession sqlSession=GetSessionFactory.sqlSession;
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        Person person = mapper.selectByPrimaryKey(name);
        if(person!=null){
            person.setCount(person.getCount()+1);
            mapper.updateByPrimaryKeySelective(person);
        }else{
            person=new Person();
            person.setCount(1);
            person.setName(name);
            int i = mapper.insert(person);
            System.out.println(i);
        }
    }


}
