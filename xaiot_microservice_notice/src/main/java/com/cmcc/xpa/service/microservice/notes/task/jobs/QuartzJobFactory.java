package com.cmcc.xpa.service.microservice.notes.task.jobs;



import com.cmcc.xpa.service.microservice.notes.service.ProclamationService;
import com.cmcc.xpa.service.microservice.notes.task.ScheduleJob;
import com.cmcc.xpa.service.microservice.notes.task.SpringUtils;
import com.xaiot.portal.model.response.BaseAnswer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * Job实现类：
 * 计划任务执行处（反射执行）
 */
public class QuartzJobFactory implements Job {


    ProclamationService quartService= (ProclamationService) SpringUtils.getBean("proclamationService");

    /**
     * 这个方法里写需要定时执行的任务
     * 我这里已经将需要执行的类名和方法名封装进ScheduleJob中，因此此方法利用反射获取ScheduleJob中的信息去执行
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 获取创建Job时传递的数据
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        //执行任务
        String springId = scheduleJob.getSpringId();
        String isZdy = scheduleJob.getIsZdy();
        BaseAnswer baseAnswer = quartService.updateProclamationStatus(springId,isZdy);
        System.out.println("更新状态："+baseAnswer.getMsg());
        // 利用反射去执行
        //ScheduleJobUtils.invokMethod(scheduleJob);
    }
}
