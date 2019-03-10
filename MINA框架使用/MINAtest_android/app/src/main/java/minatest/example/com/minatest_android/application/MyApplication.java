package minatest.example.com.minatest_android.application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    /**系统上下文*/
    public static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
    }

}
