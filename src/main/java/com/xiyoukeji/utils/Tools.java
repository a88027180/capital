package com.xiyoukeji.utils;

import java.util.Calendar;

/**
 * Created by dasiy on 17/2/14.
 */
public class Tools {
    /*获取当前月份第一天0点0分0秒的时间戳*/
    public static long getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
//将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
//将分钟至0
        c.set(Calendar.MINUTE, 0);
//将秒至0
        c.set(Calendar.SECOND, 0);
//将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
// 获取本月第一天的时间戳
        return c.getTimeInMillis();

    }
    /*获取当前年份第一天0点0分0秒的时间戳*/
    public static long getCurrentYear() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 0);
        c.set(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
//将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
//将分钟至0
        c.set(Calendar.MINUTE, 0);
//将秒至0
        c.set(Calendar.SECOND, 0);
//将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
// 获取本月第一天的时间戳
        return c.getTimeInMillis();

    }
}
