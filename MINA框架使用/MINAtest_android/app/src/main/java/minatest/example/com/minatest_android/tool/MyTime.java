package minatest.example.com.minatest_android.tool;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 * 时间获取工具类
 */
public class MyTime {
    //获取精确时间
    public static String getAllTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }
    //获得时间戳
    public static long getTimeMillis(){
        return System.currentTimeMillis();
    }
    //获取mm:ss
    public static String getHour(){
        SimpleDateFormat formatter = new SimpleDateFormat ("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    public static Timestamp getTimeStamp(){
        return new Timestamp(System.currentTimeMillis());
    }
}
