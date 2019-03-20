package com.yhb.example.myeventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Anim1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFade();
        setContentView(R.layout.activity_anim1);
        ButterKnife.bind(this);

    }
    @OnClick({R.id.btn_anim1,R.id.btn_anim2})
    public void click(View v){
        switch (v.getId()){
            case R.id.btn_anim1 :
                Toast.makeText(this,"有波纹么？",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_anim2 :
                Toast.makeText(this,"波纹超出边界了？",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    /**
     *     该进入动画要放在setContentView以前执行
     */

    private void setupFade() {
//        Explode explode = new Explode();
//        Fade fade = new Fade();
//        Slide slide = new Slide();
//        getWindow().setEnterTransition(slide);
//        getWindow().setExitTransition(slide);
        Transition transition= TransitionInflater.from(this).inflateTransition(R.transition.my_anim);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
        /**
         *          除以上三种动画外还有 Share Element 共享动画
         *         实现主要看下一遍Demo 地址：https://github.com/zb25810045/ActivityTransitionDemo
         */

    }
}
