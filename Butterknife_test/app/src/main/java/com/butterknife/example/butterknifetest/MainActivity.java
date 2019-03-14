package com.butterknife.example.butterknifetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 在Activity中绑定ButterKnife
 *
 * Activity中的绑定与fragment中有所不同
 * 具体参见TestFragment类
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frameLayout)FrameLayout frameLayout;
    @BindView(R.id.navigation)BottomNavigationView navigation;

    private TestFargment f1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //声明使用ButterKnife
        ButterKnife.bind(this);
        this.f1 = new TestFargment();
        //绑定底部导航栏监听事件
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //这里由于对fragment还有其他见解，放在下个Demo讲解
        if(!f1.isAdded()){//这里先随便弄弄
            transaction.add(R.id.frameLayout,f1);
        }else{
            transaction.show(f1);
        }
        transaction.commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            initFragment();
            switch (item.getItemId()) {
                case R.id.navigation1:
                    Toast.makeText(MainActivity.this,"one",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation2:
                    Toast.makeText(MainActivity.this,"two",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation3:
                    Toast.makeText(MainActivity.this,"three",Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

}
