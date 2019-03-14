package data.mysql.com.greendaotest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Time {
    /**
     * 获取时间
     */
    //获取精确时间
    public static String getLocaleTime(){
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }
}
