package com.example.mydnstudyproject.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    /**
     * 获取当前时间
     * @return  返回当前时间，格式2020-05-04 10:54:21
     */
    public static String getNowDateTimeStr(){
        String dateString = null;
        Calendar calendar = Calendar.getInstance();
        Date dateNow = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        dateString = sdf.format(dateNow);
        return dateString;
    }

    /**
     *
     * @param dateTime  2020-05-04 10:54:21
     * @return  2020-05-04
     */
    public static String getDateStrByDateTimeStr(String dateTime){
        if(TextUtils.isEmpty(dateTime)){
            return dateTime;
        }

        String [] array = dateTime.split("\\s+");
        if(array.length>0){
            return array[0];
        }

        return dateTime;
    }

}
