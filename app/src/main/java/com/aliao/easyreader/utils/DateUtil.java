package com.aliao.easyreader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ÀöË« on 2015/4/29.
 */
public class DateUtil {
    public static String getCurrentDate(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
