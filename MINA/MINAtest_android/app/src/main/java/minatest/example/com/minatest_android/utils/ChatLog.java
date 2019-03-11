package minatest.example.com.minatest_android.utils;

import android.util.Log;

/**
 * 会话模块日志工具
 */
public class ChatLog {
    //打印异常
    public static void showException(String message){
        Log.i("yehaobo","异常："+message);
    }
    //打印程序执行情况
    public static void showRun(String message){ Log.i("yehaobo",message);}
}
