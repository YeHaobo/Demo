package minatest.example.com.minatest_android.tool;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Map;

/**
 * 界面跳转封装类
 */
public class IntentTo {
    /**
     * 销毁当前Activity后进行跳转
     * messageMap为空则不传值
     */
    public static void finishToActivity(Activity from, Class to, Map <String,String> messageMap){
        Intent intent=new Intent(from,to);
        if(messageMap!=null){
            Bundle bundle=new Bundle();
            for(String key : messageMap.keySet()){
                bundle.putString(key,messageMap.get(key));
            }
            intent.putExtras(bundle);
        }
        from.startActivity(intent);
        from.finish();
    }
    /**
     * 不销毁当前Activity进行跳转
     * messageMap为空则不传值
     */
    public static void toActivity(Activity from, Class to, Map <String,String> messageMap){
        Intent intent=new Intent(from,to);
        if(messageMap!=null){
            Bundle bundle=new Bundle();
            for(String key : messageMap.keySet()){
                bundle.putString(key,messageMap.get(key));
            }
            intent.putExtras(bundle);
        }
        from.startActivity(intent);
    }
    /**
     * 判断服务是否开启
     *
     */
    public static boolean isServiceRunning(Context context, String ServiceName) {
        if (("").equals(ServiceName) || ServiceName == null)
            return false;
        ActivityManager myManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>)
                myManager.getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().equals(ServiceName)) {
                return true;
            }
        }
        return false;
    }
}
