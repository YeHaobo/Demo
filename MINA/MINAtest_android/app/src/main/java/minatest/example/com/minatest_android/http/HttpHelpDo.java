package minatest.example.com.minatest_android.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpHelpDo {
    //是否有网络
    public static boolean isNet=false;
    //判断手机网络是否开启
    public static void isIntenter(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null)
        {
            NetworkInfo[] info=connectivityManager.getAllNetworkInfo();
            if(info!=null)
            {
                for(int i=0;i<info.length;i++)
                {
                    if(info[i].getState()==NetworkInfo.State.CONNECTED)
                        isNet=true;
                }
            }
        }
        else isNet=false;
    }
}
