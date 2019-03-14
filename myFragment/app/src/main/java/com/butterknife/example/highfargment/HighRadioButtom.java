package com.butterknife.example.highfargment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 四个item的地步导航栏
 */
public class HighRadioButtom extends LinearLayout {

    /**
     * 自定义控件初始化操作
     */
    private View view;
    public HighRadioButtom(Context context) { super(context); }
    public HighRadioButtom(Context context,AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater=LayoutInflater.from(context);
        this.view=inflater.inflate(R.layout.activity_main,this);
    }

    /**
     *获取控件信息
     */
    private FrameLayout frameLayout;
    private void initView(){
        
    }

}
