package com.yhb.example.myeventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yhb.example.myeventbus.message.MessageEvent;

import org.greenrobot.eventbus.EventBus;


public class BaseActivity extends AppCompatActivity {
    public MessageEvent messageEvent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.messageEvent = new MessageEvent();
        EventBus.getDefault().register(this);//注册EventBus
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);//注销EventBus
        }
    }

    /**
     * 获取数据传输实体
     * @param res
     * @return
     */
    public MessageEvent.StringMessage getStringMsg(String res){
        return messageEvent.new StringMessage(res);
    }
}
