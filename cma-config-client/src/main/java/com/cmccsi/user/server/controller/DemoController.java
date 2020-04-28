package com.cmccsi.user.server.controller;


import com.cmccsi.user.server.entity.DmpNode;
import com.cmccsi.user.server.entity.Hr;
import com.cmccsi.user.server.remote.CusmerRemoteService;
import com.cmccsi.user.server.service.DmpNodeService;
import com.cmccsi.user.server.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname DemoController
 * @Description TODO
 * @Date 2020/4/3 21:45
 * @Created by SunFei
 */
@RestController
//引用配置时必加注解
@RefreshScope
@RequestMapping("cma/client/")
public class DemoController {

//    @Value("${hive}")
//    private String hive;
//    @Value("${mysql}")
//    private String mysql;



    @Autowired
    private CusmerRemoteService cusmerRemoteService;


    @Resource
    private DmpNodeService dmpNodeService;

    @Autowired
    private HrService hrService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("vv")
    public String selectOne() {
        DmpNode dmpNode = this.dmpNodeService.queryById("1");
        Hr hr = hrService.queryById(3);
        System.out.println(this.dmpNodeService.queryById("1"));
        System.out.println(hrService.queryById(3));
        return "aaaaa";
    }


    @GetMapping("id")
    public String id() {
        return cusmerRemoteService.cd();
    }

    @RequestMapping("cd")
    public String cd() {
        return "*******************************来自7000*******************************";
    }


}
