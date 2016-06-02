package com.helper.jiaop.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiaop on 2016/6/2.
 * 时间帮助类
 */
public class TimeUtils {

    /**
     * 获取当前时间
     *
     * @param format 时间格式
     * @return
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 获取时间差
     *
     * @param start
     * @param end
     * @return
     */
    public static String getTimeDiff(Date start, Date end) {
        long diff = start.getTime() - end.getTime();//微秒
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        return days + "天" + hours + "小时" + minutes + "分";
    }

}
