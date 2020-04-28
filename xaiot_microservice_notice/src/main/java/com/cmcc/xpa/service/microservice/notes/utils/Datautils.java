package com.cmcc.xpa.service.microservice.notes.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Datautils {

    public static Date dealDateFormat(String oldDate) {
        Date date1 = null;
        DateFormat df2 = null;
        Date  date2 =null;
        try {
            oldDate= oldDate.replace("Z", " UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String dates=df2.format(date1);
             date2 = df1.parse(dates);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return date2;
    }
    public static Date  addDate() {
        long hour=Long.valueOf(8);
        long time = new Date().getTime(); // 得到指定日期的毫秒数
        hour = hour * 60 * 60 * 1000; // 要加上的小时数转换成毫秒数
        time += hour; // 相加得到新的毫秒数
        return  new Date(time); // 将毫秒数转换成日期
    }

}
