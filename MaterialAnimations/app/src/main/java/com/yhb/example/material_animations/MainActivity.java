package com.yhb.example.material_animations;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.my_img);
        linearLayout = findViewById(R.id.my_linear);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShare();
            }
        });
    }

    /**
     * 共享元素转场动画
     * 1、两个xml文件中共享的相对元素定义以下属性
     * android:transitionName="TextView"
     * 2、使用如下方法中的API将pair1共享元素填充入bundle
     * 3、启动Acivity
     */
    public void startShare() {
        Intent intent = new Intent(this, SecondActivity.class);
        Pair<View, String> pair1 = new Pair<View, String>(imageView, ViewCompat.getTransitionName(imageView));
        Pair<View, String> pair2 = new Pair<View, String>(linearLayout, ViewCompat.getTransitionName(linearLayout));
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, pair1, pair2).toBundle();
        startActivity(intent, bundle);
    }
}
