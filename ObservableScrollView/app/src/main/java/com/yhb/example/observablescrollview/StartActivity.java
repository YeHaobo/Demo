package com.yhb.example.observablescrollview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.yhb.example.observablescrollview.fragment.FristFragment;
import com.yhb.example.observablescrollview.fragment.SecondsFragment;
import com.yhb.example.observablescrollview.fragment.ThridsFragment;

/**************************************
 *
 * 打开这个Activity之前应该判断是否是第一次打开应用
 *
 ****************************************/
public class StartActivity extends AppIntro {
    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        addSlide(new FristFragment());
        addSlide(new SecondsFragment());
        addSlide(new ThridsFragment());
        setSeparatorColor(getResources().getColor(R.color.colorAccent));//设置下划线颜色
        setVibrateIntensity(30);//设置每张时长
        setSkipText("跳过");//设置按钮颜色
        setDoneText("完成");
        //以下为封装的动画
        /**
         *         setZoomAnimation();
         *         setFlowAnimation();
         *         setSlideOverAnimation();
         *         setDepthAnimation();
         */
            setFadeAnimation();
    }

    /**跳过动画
     * @param currentFragment
     */
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Toast.makeText(this, "跳过引导页", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(StartActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void onNextPressed() {
        //当执行下一步动作时触发
    }

    @Override
    public void onSlideChanged() {
        //当执行变化动作时触发
    }

    /**
     * 动画完成
     */@Override
    public void onDonePressed() {
        super.onDonePressed();
        Toast.makeText(this, "动画完成", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(StartActivity.this,MainActivity.class));
        finish();
    }

}


