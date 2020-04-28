package com.cmcc.xpa.service.microservice.notes.service.impl;


import com.cmcc.xpa.service.microservice.notes.entity.XaProclamation;
import com.cmcc.xpa.service.microservice.notes.mapper.XaProclamationMapper;
import com.cmcc.xpa.service.microservice.notes.model.request.RequestProList;
import com.cmcc.xpa.service.microservice.notes.model.response.ReponseProList;
import com.cmcc.xpa.service.microservice.notes.service.ProclamationService;
import com.cmcc.xpa.service.microservice.notes.task.QuartzManager;
import com.cmcc.xpa.service.microservice.notes.task.ScheduleJob;
import com.cmcc.xpa.service.microservice.notes.utils.CronUtil;
import com.cmcc.xpa.service.microservice.notes.utils.Datautils;
import com.xaiot.portal.model.response.BaseAnswer;
import com.xaiot.portal.model.response.StatusEnum;
import com.xaiot.portal.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("proclamationService")
public class ProclamationServiceImpl extends BaseService implements ProclamationService {
    @Autowired
    private XaProclamationMapper XaProclamationMapper;


    @Override
    public BaseAnswer selectProclamationList(RequestProList requestProList) {
        BaseAnswer<ReponseProList> answer = new BaseAnswer<>();
        try {
            Integer limit = requestProList.getPageSize();
            Integer offset = limit == null ? null : requestProList.getPage() == null ? 0 : (requestProList.getPage() - 1) * limit;
            List<XaProclamation> pagelist = XaProclamationMapper.findPageWithResult(requestProList.getP_type(), requestProList.getP_is_sucess(), requestProList.getStar_time(), requestProList.getEnd_time(), offset, limit);
            ReponseProList reponseProList = new ReponseProList();
            reponseProList.setList(pagelist);
            reponseProList.setTotal(XaProclamationMapper.findPageWithCount(requestProList.getP_type(), requestProList.getP_is_sucess(), requestProList.getStar_time(), requestProList.getEnd_time()));
            answer.setData(reponseProList);
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
        } catch (Exception e) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }
    }

    @Override
    public BaseAnswer addProclamation(XaProclamation xaProclamation) {
        BaseAnswer<?> answer = new BaseAnswer<>();
        //创建人，时间逻辑处理
        xaProclamation.setCreate_time(Datautils.addDate());
        int pid = XaProclamationMapper.insertDynamic(xaProclamation);
        //处理业务逻辑是否是定时推送
        String s = xaProclamation.getP_isnot_push();
        String s1 = xaProclamation.getP_is_sucess();
        if("0".equals(s) && !"2".equals(s1)){
            //添加定时任务

            ScheduleJob scheduleJob = new ScheduleJob();
            scheduleJob.setJobName("门户公告定时发送"+xaProclamation.getP_id().toString());
            scheduleJob.setJobGroupName("门户公告模块"+xaProclamation.getP_id().toString());
            scheduleJob.setSpringId(xaProclamation.getP_id().toString());
            scheduleJob.setTriggerName(xaProclamation.getP_id().toString());
            scheduleJob.setTriggerGroupName(xaProclamation.getP_id().toString());
            scheduleJob.setBeanClass("com.cmcc.xpa.service.schedule.task.jobs.QuartzJobFactory");
            scheduleJob.setMethodName("show");
            //使用自定义字段定时修改推送状态 0是已推送
            scheduleJob.setIsZdy("0");
            //时间转换
            String cron = CronUtil.getCron(xaProclamation.getP_push_time());
            scheduleJob.setCronExpression(cron);//执行时间
            QuartzManager.addJob(scheduleJob);
        }
        if (pid != 0) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
        } else {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }
    }

    @Override
    public BaseAnswer selectProclamationById(Long p_id) {
        BaseAnswer<XaProclamation> answer = new BaseAnswer<>();
        XaProclamation xaProclamation = XaProclamationMapper.selectByPId(p_id);
        if (xaProclamation != null) {
            answer.setData(xaProclamation);
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
        } else {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }
    }

    @Override
    public BaseAnswer deleteProclamationById(Long p_id) {
        BaseAnswer<?> answer = new BaseAnswer<>();
        int delete = XaProclamationMapper.delete(p_id);
        if (delete > 0) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
        } else {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }
    }

    @Override
    public BaseAnswer updateProclamationById(XaProclamation xaProclamation) {
        BaseAnswer<?> answer = new BaseAnswer<>();
        xaProclamation.setUpdate_time(Datautils.addDate());

        XaProclamation xaProclamations = XaProclamationMapper.selectByPId(xaProclamation.getP_id());
        //根据推送状态判断是否需要添加和修改定时任务
        String s = xaProclamation.getP_is_sucess();
        String s1 = xaProclamation.getP_isnot_push();
        if("1".equals(s) &&"0".equals(s1)){
            //添加定时任务
            //查找定时任务
            ScheduleJob scheduleJobs = new ScheduleJob();
            scheduleJobs.setTriggerName(xaProclamation.getP_id().toString());
            scheduleJobs.setTriggerGroupName(xaProclamation.getP_id().toString());
            Boolean aBoolean = QuartzManager.SelectJob(scheduleJobs);
            if(aBoolean){
                ScheduleJob updatescheduleJob = new ScheduleJob();
                updatescheduleJob.setJobName("门户公告定时发送"+xaProclamation.getP_id().toString());
                updatescheduleJob.setJobGroupName("门户公告模块"+xaProclamation.getP_id().toString());
                updatescheduleJob.setSpringId(xaProclamation.getP_id().toString());
                updatescheduleJob.setTriggerName(xaProclamation.getP_id().toString());
                updatescheduleJob.setTriggerGroupName(xaProclamation.getP_id().toString());
                updatescheduleJob.setBeanClass("com.cmcc.xpa.service.schedule.task.jobs.QuartzJobFactory");
                updatescheduleJob.setMethodName("show");
                //使用自定义字段定时修改推送状态 0是已推送
                updatescheduleJob.setIsZdy("0");
                //时间转换
                String cron = CronUtil.getCron(xaProclamation.getP_push_time());
                updatescheduleJob.setCronExpression(cron);//执行时间
                QuartzManager.modifyJobTime(updatescheduleJob);
            }else{
                ScheduleJob scheduleJob = new ScheduleJob();
                scheduleJob.setJobName("门户公告定时发送"+xaProclamation.getP_id().toString());
                scheduleJob.setJobGroupName("门户公告模块"+xaProclamation.getP_id().toString());
                scheduleJob.setSpringId(xaProclamation.getP_id().toString());
                scheduleJob.setTriggerName(xaProclamation.getP_id().toString());
                scheduleJob.setTriggerGroupName(xaProclamation.getP_id().toString());
                scheduleJob.setBeanClass("com.cmcc.xpa.service.schedule.task.jobs.QuartzJobFactory");
                scheduleJob.setMethodName("show");
                //使用自定义字段定时修改推送状态 0是已推送
                scheduleJob.setIsZdy("0");
                //时间转换
                String cron = CronUtil.getCron(xaProclamation.getP_push_time());
                scheduleJob.setCronExpression(cron);//执行时间
                QuartzManager.addJob(scheduleJob);
            }

        }
        //增加判断逻辑
        int i = XaProclamationMapper.updateDynamic(xaProclamation);
        if (i > 0) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
        } else {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }
    }

    @Override
    public BaseAnswer ProclamationBrowseVolume(Long p_id) {
        //根据pid 进行浏览量的增加
        BaseAnswer<?> answer = new BaseAnswer<>();
        Long p_pageview = XaProclamationMapper.ProclamationBrowseVolume(p_id);
        if (p_pageview == null) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        } else {
            int i = XaProclamationMapper.updateProclamationBrowseVolume(p_id, p_pageview + 1);
            if (i > 0) {
                return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
            } else {
                return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
            }
        }
    }

    /**
     * 根据pid 修改推送状态
     * @param p_id
     * @return
     */
    public BaseAnswer updateProclamationStatus(String p_id,String p_is_sucess) {
        BaseAnswer<?> answer = new BaseAnswer<>();
        int i =  XaProclamationMapper.updateProclamationStatus(Long.valueOf(p_id),p_is_sucess);
        if (i > 0) {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
        } else {
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
        }
    }

    @Override
    public BaseAnswer selectNoticeHiding() {
        BaseAnswer<List<XaProclamation>> answer = new BaseAnswer<>();
        List<XaProclamation> List= XaProclamationMapper.selectNoticeHiding();
        answer.setData(List);
        return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
    }

    @Override
    public BaseAnswer queryModule(String userId) {
        BaseAnswer<List<Map<String ,String>>> answer = new BaseAnswer<>();
        if(userId!=null){
            List<Map<String ,String>>  modus=  XaProclamationMapper.queryModule(userId);
            if(modus.size()>0){
                answer.setData(modus);
                return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SUCCESS);
            }else{
                return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_SERVER_ERROR);
            }
        }else{
            return setState2Answer(answer, StatusEnum.SYSTEM_COMMON_USER_NOT_INVALID);
        }

    }
}
