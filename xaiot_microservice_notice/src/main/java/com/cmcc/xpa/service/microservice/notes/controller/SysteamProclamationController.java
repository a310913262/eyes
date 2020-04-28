package com.cmcc.xpa.service.microservice.notes.controller;


import com.cmcc.xpa.service.microservice.notes.entity.XaSysteamProclamation;
import com.cmcc.xpa.service.microservice.notes.service.SysteamProclamationService;
import com.xaiot.portal.model.response.BaseAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author lifupeng
 * @Date 2020/4/15 16:13
 */
@RestController
public class SysteamProclamationController {

    private static Logger logger = LoggerFactory.getLogger(SysteamProclamationController.class);

    @Autowired
    private SysteamProclamationService systeamProclamationService;

    /**
     * 查看更多列表
     *
     * @param
     * @return
     */

    @GetMapping(value = "/selectSysProclamationList")
    public BaseAnswer selectSysProclamation( String star_time,String end_time,String p_sys_content,Integer page, Integer pageSize) {
        BaseAnswer pages = systeamProclamationService.queryPage(star_time,end_time,p_sys_content,page,pageSize);
        return pages;
    }

    /**
     * 首页系统公告
     *
     * @param
     */
    @GetMapping(value = "/firstPageProclamation")
    public BaseAnswer firstPageProclamation() {
        BaseAnswer page = systeamProclamationService.queryfirstPage();
        return page;
    }

    /**
     * 首页系统公告添加
     *
     * @param
     */
    @PostMapping(value = "/addSysProclamation")
    public BaseAnswer addSysProclamation(XaSysteamProclamation xaSysteamProclamation, @RequestHeader String userName, @RequestHeader String userId) {
        xaSysteamProclamation.setCreate_by(userName);
        xaSysteamProclamation.setCreate_byId(Integer.valueOf(userId));
        BaseAnswer page = systeamProclamationService.addSysProclamation(xaSysteamProclamation);
        return page;
    }
    /**
     * 首页系统详情
     *
     * @param
     */
    @GetMapping(value = "/selectSysProclamationById/{p_sys_id}")
    public BaseAnswer selectSysProclamationById(@PathVariable(value="p_sys_id") Long p_sys_id) {
        BaseAnswer page = systeamProclamationService.selectSysProclamationById(p_sys_id);
        return page;
    }
    /**
     * 删除公告
     *
     * @param
     */
    @GetMapping(value = "deleteSysProclamationById/{p_sys_id}")
    public BaseAnswer deleteSysProclamationById(@PathVariable(value="p_sys_id") Long p_sys_id,@RequestHeader String userName,@RequestHeader String userId) {
        BaseAnswer page = systeamProclamationService.deleteSysProclamationById(p_sys_id,userName,userId);
        return page;
    }

    /**
     * 根据公告模块id 查找公告
     *
     * @param
     */
    @GetMapping(value = "selectModuleSystem/{p_sys_module}")
    public BaseAnswer selectModuleSystem(@PathVariable(value="p_sys_module") Long p_sys_module) {
        BaseAnswer page = systeamProclamationService.selectModuleSystem(p_sys_module);
        return page;
    }
}
