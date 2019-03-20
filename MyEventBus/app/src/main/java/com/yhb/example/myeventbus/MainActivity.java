package com.yhb.example.myeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.yhb.example.myeventbus.http.HttpOk;
import com.yhb.example.myeventbus.message.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.date_tv)
    TextView dateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    //获取网络数据
    @OnClick(R.id.getDate_tv)
    public void getNetDate() {
        Toast.makeText(this, "正在获取数据", Toast.LENGTH_SHORT).show();
        String url = "https://www.zhihuimingxing.com/hui/api/xiNaiInterfaceController.do?interface&sign=456&messageFormat=json/xml&locale=zh_CN&appKey=001&appVersion=2.0&method=search";
        HttpOk.startHttp(this, url,
                new FormBody.Builder()
                        .add("searchKeyword", "oppo").build(),
                new HttpOk.HttpBack() {
                    @Override
                    public void onResponse(Call call, Response response, String res) {
                        //发送广播更新数据
                        EventBus.getDefault().post(getStringMsg(res));
                    }

                    @Override
                    public void onFail(Call call, IOException e) {
                        Toast.makeText(MainActivity.this, "网络访问失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     *     广播事件接收后的操作
     *
     *     如果想要区分是那个发送的广播，或者不想处理一些接受到的广播
     *     可以将下面的 MessageEvent.StringMessage message参数替换
     *     比如 Event 方法的参数替换成了String类型，
     *     则只会对发出的String类型的广播做接收处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent.StringMessage message) {
        dateTv.setText(message.getMessage());
    }

    /**
     POSTING (默认)  表示事件处理函数的线程跟发布事件的线程在同一个线程。
     MAIN 表示事件处理函数的线程在主线程(UI)线程，因此在这里不能进行耗时操作。
     BACKGROUND 表示事件处理函数的线程在后台线程，因此不能进行UI操作。如果发布事件的线程是主线程(UI线程)，那么事件处理函数将会开启一个后台线程，如果果发布事件的线程是在后台线程，那么事件处理函数就使用该线程。
     ASYNC 表示无论事件发布的线程是哪一个，事件处理函数始终会新建一个子线程运行，同样不能进行UI操作。

     作者：Android平头哥
     链接：https://www.jianshu.com/p/f9ae5691e1bb
     来源：简书
     简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
     */

    @OnClick(R.id.tosecond_tv)
    public void tonext(){
        startActivity(new Intent(MainActivity.this,BusActivity.class));
    }

}
