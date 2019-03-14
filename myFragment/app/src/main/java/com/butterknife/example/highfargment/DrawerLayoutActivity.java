package com.butterknife.example.highfargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 侧滑菜单
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    @BindView(R.id.ToNextDemo)TextView ToNextDemo;
    @BindView(R.id.drawer_layout)DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        ButterKnife.bind(this);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(DrawerLayoutActivity.this, "侧滑栏已打开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(DrawerLayoutActivity.this, "侧滑栏已关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

        ToNextDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawerLayoutActivity.this,MaterialDialogsActivity.class));
            }
        });

    }
}
