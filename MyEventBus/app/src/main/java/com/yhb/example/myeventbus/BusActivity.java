package com.yhb.example.myeventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @OnClick(R.id.finish)
    public void tonext() {
        EventBus.getDefault().post(getStringMsg("返回的数据"));
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent.StringMessage message) {
        showTv.setText(message.getMessage());
    }
}
