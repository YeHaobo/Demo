package com.yhb.example.observablescrollview;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application {
    /**
     *安装字体
     */
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/vista_black.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
