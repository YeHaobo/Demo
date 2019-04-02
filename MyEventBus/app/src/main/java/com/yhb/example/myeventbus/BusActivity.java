package com.yhb.example.myeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Fade;
import android.view.View;
import android.widget.TextView;

import com.yhb.example.myeventbus.message.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusActivity extends BaseActivity {

    @BindView(R.id.show_tv)
    TextView showTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.finish,R.id.to_anim})
    public void tonext(View v) {
        switch (v.getId()){
            case R.id.finish:
                EventBus.getDefault().post(getStringMsg("返回的数据"));
                finish();
                break;
            case R.id.to_anim:
                /**
                 * 如果想使下一个Activity进入退出都有动画
                 * 通过如下设置
                 */
                Intent intent = new Intent(BusActivity.this,Anim1Activity.class);
                Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(BusActivity.this).toBundle();
                startActivity(intent, options);
                break;
            default:break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent.StringMessage message) {
        showTv.setText(message.getMessage());
    }

}
