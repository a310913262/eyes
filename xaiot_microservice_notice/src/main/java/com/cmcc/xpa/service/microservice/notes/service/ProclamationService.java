package com.cmcc.xpa.service.microservice.notes.service;



import com.cmcc.xpa.service.microservice.notes.entity.XaProclamation;
import com.cmcc.xpa.service.microservice.notes.model.request.RequestProList;
import com.xaiot.portal.model.response.BaseAnswer;

public interface ProclamationService {

    BaseAnswer selectProclamationList(RequestProList requestProList);

    BaseAnswer addProclamation(XaProclamation xaProclamation);

    BaseAnswer selectProclamationById(Long p_id);

    BaseAnswer deleteProclamationById(Long p_id);

    BaseAnswer updateProclamationById(XaProclamation xaProclamation);

    BaseAnswer ProclamationBrowseVolume(Long p_id);

    BaseAnswer updateProclamationStatus(String p_id, String p_is_sucess);

    BaseAnswer selectNoticeHiding();

    BaseAnswer queryModule(String userId);
}
