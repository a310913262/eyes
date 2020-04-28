package com.cmcc.xpa.service.microservice.notes.service;

import com.cmcc.xpa.service.microservice.notes.entity.XaSysteamProclamation;
import com.xaiot.portal.model.response.BaseAnswer;

public interface SysteamProclamationService {

    BaseAnswer queryPage(String star_time, String end_time, String p_sys_content, Integer page, Integer pageSize);

    BaseAnswer queryfirstPage();

    BaseAnswer addSysProclamation(XaSysteamProclamation xaSysteamProclamation);

    BaseAnswer selectSysProclamationById(Long p_sys_id);

    BaseAnswer deleteSysProclamationById(Long p_sys_id, String userName, String userId);

    BaseAnswer selectModuleSystem(Long p_sys_module);
}
