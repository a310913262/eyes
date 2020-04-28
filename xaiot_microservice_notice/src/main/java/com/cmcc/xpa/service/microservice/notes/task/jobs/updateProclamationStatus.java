package com.cmcc.xpa.service.microservice.notes.task.jobs;





import com.cmcc.xpa.service.microservice.notes.task.QuartzManager;
import com.cmcc.xpa.service.microservice.notes.task.ScheduleJob;

import java.text.SimpleDateFormat;
import java.util.Date;

public class updateProclamationStatus {








    public static void main(String[] args) {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setJobName("门户管理");
        scheduleJob.setJobGroupName("TEST");
        scheduleJob.setTriggerName("00");
        scheduleJob.setTriggerGroupName("TEST");
        scheduleJob.setBeanClass("com.cmcc.xpa.service.proclamation.task.jobs.updateProclamationStatus");
        scheduleJob.setMethodName("show");
        scheduleJob.setCronExpression("0/5 * * * * ?");// 每秒钟执行一次

        QuartzManager.addJob(scheduleJob);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /*
         * 测试修改任务时间
         */
        scheduleJob.setCronExpression("0/30 * * * * ?");// 每30秒执行一次
        QuartzManager.modifyJobTime(scheduleJob);
    }

    public void show() {
        System.err.println(getNow());// 打印当前时间
    }

    private String getNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(new Date());
    }


}
