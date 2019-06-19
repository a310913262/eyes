package com.kafka;

import net.sf.ehcache.util.PropertyUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class SpringbootkafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootkafkaApplication.class, args);
        InputStream resourceAsStream = PropertyUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties props=new Properties();
        try {
            props.load(resourceAsStream);
            String url = props.getProperty("url");
            System.out.println(url);
            String sss=new String(url.getBytes("ISO8859-1"),"GBK");
            System.out.println("=============================================================================="+sss+"==============================================================================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
