package com.cmccsi.user.server.controller;

import com.cmccsi.user.server.entity.Hr;
import com.cmccsi.user.server.service.HrService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Hr)表控制层
 *
 * @author makejava
 * @since 2020-04-27 19:59:05
 */
@RestController
@RequestMapping("hr")
public class HrController {
    /**
     * 服务对象
     */
    @Resource
    private HrService hrService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Hr selectOne(Integer id) {
        return this.hrService.queryById(id);
    }

}