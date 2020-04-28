package com.cmcc.xpa.service.microservice.notes.service.impl;

import com.cmcc.xpa.service.microservice.notes.entity.XaSysteamProclamation;
import com.cmcc.xpa.service.microservice.notes.mappers.XaSysteamProclamationMapper;


import com.cmcc.xpa.service.microservice.notes.model.response.ReponsetList;
import com.cmcc.xpa.service.microservice.notes.service.SysteamProclamationService;

import com.cmcc.xpa.service.microservice.notes.utils.*;
import com.xaiot.portal.bean.XpOperationLog;
import com.xaiot.portal.model.response.BaseAnswer;
import com.xaiot.portal.model.response.StatusEnum;
import com.xaiot.portal.service.BaseService;
import com.xaiot.portal.service.XpaSendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysteamProclamationServiceImpl extends BaseService implements SysteamProclamationService {

    @Autowired
    private XaSysteamProclamationMapper xaiotPortalSysteamProclamationMapper;

/*    @Autowired
    private XpaSendLogService XpaSendLogService;*/
    /**
     * @param
     * @return
     */
    @Override
    public BaseAnswer queryPage(String star_time, String end_time, String p_sys_content, Integer page, Integer pageSize) {
        BaseAnswer<ReponsetList> answer = new BaseAnswer<>();
        try {
            Integer limit = pageSize;
            Integer offset = limit == null ? null : page == null ? 0 : (page - 1) * limit;
            List<XaSysteamProclamation> pagelist = xaiotPortalSysteamProclamationMapper.findPageWithResult(star_time, end_time, p_sys_content, offset, limit);
            ReponsetList data = new ReponsetList();
            data.setList(pagelist);
            data.setTotal(xaiotPortalSysteamProclamationMapper.findPageWithCount(star_time, end_time, p_sys_content));
            answer.setData(data);
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
        } catch (Exception e) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }
    }

    @Override
    public BaseAnswer queryfirstPage() {
        BaseAnswer<List<XaSysteamProclamation>> answer = new BaseAnswer<>();
        try {
            List<XaSysteamProclamation> pagelist = xaiotPortalSysteamProclamationMapper.queryfirstPage();
            answer.setData(pagelist);
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
        } catch (Exception e) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }
    }

    @Override
    public BaseAnswer addSysProclamation(XaSysteamProclamation xaSysteamProclamation) {
        BaseAnswer<?> answer = new BaseAnswer<>();
        //参数校验
        if (!ValidationUtil.blank(xaSysteamProclamation.getP_sys_title()) || !ValidationUtil.blankforlenth(xaSysteamProclamation.getP_sys_title(),0,30)) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_PARAM_ERROR);
        }
        if (!ValidationUtil.blank(xaSysteamProclamation.getP_sys_module()) || !ValidationUtil.blankforlenth(xaSysteamProclamation.getP_sys_module(),0,300)) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_PARAM_ERROR);
        }
        if (!ValidationUtil.blank(xaSysteamProclamation.getP_sys_content())) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_PARAM_ERROR);
        }
        //记录操作日志
        XpOperationLog xpOperationLog = new XpOperationLog();
       try{
           //创建人，时间逻辑处理
           xaSysteamProclamation.setCreate_time(Datautils.addDate());
           int i = xaiotPortalSysteamProclamationMapper.insertDynamic(xaSysteamProclamation);
           if (i > 0) {
               xpOperationLog.setSucc(true);
               return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
           } else {
               xpOperationLog.setSucc(false);
               return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
           }
       }catch (Exception e){
           xpOperationLog.setSucc(false);
           return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
       }finally {
           xpOperationLog.setType(1);
           xpOperationLog.setCreatorId(xaSysteamProclamation.getCreate_byId());
           xpOperationLog.setCreatorName(xaSysteamProclamation.getCreate_by());
           xpOperationLog.setOperatePre("");
           xpOperationLog.setOperateSuf(xaSysteamProclamation.toString());
           xpOperationLog.setServiceId(1);
           xpOperationLog.setServiceName("门户管理系统");
           //发送日志
         //  String send = XpaSendLogService.send(xpOperationLog);
       }
    }

    @Override
    public BaseAnswer selectSysProclamationById(Long p_sys_id) {
        BaseAnswer<XaSysteamProclamation> answer = new BaseAnswer<>();
        XaSysteamProclamation xaSysteamProclamation = xaiotPortalSysteamProclamationMapper.selectByPSysId(p_sys_id);
        if (xaSysteamProclamation != null) {
            answer.setData(xaSysteamProclamation);
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
        } else {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }
    }

    @Override
    public BaseAnswer deleteSysProclamationById(Long p_sys_id,String userName,String userId) {
        BaseAnswer<?> answer = new BaseAnswer<>();
        //记录操作日志
        XpOperationLog xpOperationLog = new XpOperationLog();
        try{
            int i = xaiotPortalSysteamProclamationMapper.delete(p_sys_id);
            if (i > 0) {
                xpOperationLog.setSucc(true);
                return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
            } else {
                xpOperationLog.setSucc(true);
                return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
            }
        }catch (Exception e){
            xpOperationLog.setSucc(true);
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }finally {
            xpOperationLog.setType(1);
            xpOperationLog.setCreatorId(Integer.valueOf(userId));
            xpOperationLog.setCreatorName(userName);
            xpOperationLog.setOperatePre("");
            xpOperationLog.setOperateSuf("");
            xpOperationLog.setServiceId(1);
            xpOperationLog.setServiceName("门户管理系统");
            //发送日志
            //String send = XpaSendLogService.send(xpOperationLog);
        }

    }

    @Override
    public BaseAnswer selectModuleSystem(Long p_sys_module) {
        BaseAnswer<List<XaSysteamProclamation>> answer = new BaseAnswer<>();
        try {
            List<XaSysteamProclamation> pagelist = xaiotPortalSysteamProclamationMapper.selectModuleSystem(p_sys_module.toString());
            answer.setData(pagelist);
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
        } catch (Exception e) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }


    }
}
