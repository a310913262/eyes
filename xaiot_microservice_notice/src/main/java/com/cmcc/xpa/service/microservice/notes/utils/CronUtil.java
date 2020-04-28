package com.cmcc.xpa.service.microservice.notes.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Description: Java日期转换成Cron日期表达式工具类
 * @Author: 宫崎不骏
 * @CreateDate: 2020年1月7日 下午4:57:51
 * @UpdateUser:
 * @UpdateDate: 2020年1月7日 下午4:57:51
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class CronUtil {
    /***
     *
     * @param date
     * @param dateFormat : yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     * convert Date to cron ,eg. "21 25 17 07 01 ? 2020"
     *
     * @param date : 时间点
     * @return
     */
    public static String getCron(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

  /*  @Test
    public void testGetCron() {
        String cron = CronUtil.getCron(new Date());
        System.out.println(cron);
    }*/

}

