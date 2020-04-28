package com.cmcc.xpa.service.microservice.notes.controller;




import com.cmcc.xpa.service.microservice.notes.entity.XaProclamation;
import com.cmcc.xpa.service.microservice.notes.model.request.RequestProList;
import com.cmcc.xpa.service.microservice.notes.service.ProclamationService;
import com.xaiot.portal.model.response.BaseAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProclamationController {

    private static Logger logger = LoggerFactory.getLogger(ProclamationController.class);

    @Autowired
    private ProclamationService systeamProclamationService;

    /**
     * 列表查询
     * @param requestProList
     * @return
     */
    @GetMapping(value = "/selectProclamationList")
    public BaseAnswer  selectProclamationList(RequestProList requestProList) {
        BaseAnswer page = systeamProclamationService.selectProclamationList(requestProList);
        return page;
    }

    /**
     * 平台公告添加
     * @param XaProclamation
     * @return
     *
     */
    @PostMapping(value = "/addProclamation")
    public BaseAnswer  addProclamation(XaProclamation XaProclamation, @RequestHeader String userName) {
        //String userName="lisu";
        XaProclamation.setCreate_by(userName);
        BaseAnswer page = systeamProclamationService.addProclamation(XaProclamation);
        return page;
    }

    /**
     * 平台公告查询
     * @param p_id
     * @return
     */
    @GetMapping(value = "/selectProclamationById/{p_id}")
    public BaseAnswer  selectProclamationById(@PathVariable(value="p_id") Long p_id) {
        BaseAnswer page = systeamProclamationService.selectProclamationById(p_id);
        return page;
    }

    /**
     * 平台公告删除
     * @param p_id
     * @return
     */
    @GetMapping(value = "/deleteProclamationById/{p_id}")
    public BaseAnswer  deleteProclamationById(@PathVariable(value="p_id") Long p_id) {
        BaseAnswer page = systeamProclamationService.deleteProclamationById(p_id);
        return page;
    }
    /**
     * 平台公告更新
     * @param XaProclamation
     * @return
     */
    @PostMapping(value = "/updateProclamationById")
    public BaseAnswer  updateProclamationById(XaProclamation XaProclamation,@RequestHeader String userName) {
        XaProclamation.setUpdate_by(userName);
        BaseAnswer page = systeamProclamationService.updateProclamationById(XaProclamation);
        return page;
    }

    /**
     * 平台公告浏览量
     * @param p_id
     * @return
     */
    @GetMapping(value = "/ProclamationBrowseVolume/{p_id}")
    public BaseAnswer  ProclamationBrowseVolume(@PathVariable(value="p_id") Long p_id) {
        BaseAnswer page = systeamProclamationService.ProclamationBrowseVolume(p_id);
        return page;
    }

    /**
     * 平台公告浏览量
     * @param p_id
     * @return
     */
    @GetMapping(value = "/noticeHiding/{p_id}")
    public BaseAnswer  noticeHiding(@PathVariable(value="p_id") Long p_id) {
        BaseAnswer page = systeamProclamationService.updateProclamationStatus(p_id.toString(),"3");
        return page;
    }

    /**
     * 平台公告轮播8组最新数据
     * @param
     * @return
     */
    @GetMapping(value = "/selectNoticeHiding")
    public BaseAnswer  selectNoticeHiding() {
        BaseAnswer page = systeamProclamationService.selectNoticeHiding();
        return page;
    }
    /**
     * 公告查找模块
     *
     * @param
     */
    @GetMapping(value = "queryModule")
    public BaseAnswer queryModule(@RequestHeader String userId) {

        BaseAnswer page = systeamProclamationService.queryModule(userId);
        return page;
    }
}
