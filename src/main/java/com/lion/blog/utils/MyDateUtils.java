package com.lion.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtils {
    public static String getTimeString(Date time) {
        Date date = new Date();
        if(time == null) {
            return "未知";
        }
        Long longTime = (date.getTime() - time.getTime()) / 1000;
        if(longTime <= 0) {
            return "刚刚";
        }
        if (longTime < 60) {
            return longTime + "秒前";
        }
        if(longTime < 3600 ) {
            return longTime / 60 + "分钟前";
        }
        if(longTime < 86400) {
            return longTime / 3600 + "小时前";
        }
        if(longTime < 31536000) {
            return longTime / 86400 + "天前";
        }
       return longTime / 31536000 + "年前";
    }

    public static String getFormatString(Date time) {
        if(time == null) {
            return "未知";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }
}
