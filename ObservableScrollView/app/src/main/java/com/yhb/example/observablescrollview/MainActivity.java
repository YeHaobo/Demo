package com.yhb.example.observablescrollview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.yhb.example.observablescrollview.adapter.QuickAdapter;
import com.yhb.example.observablescrollview.entity.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * 除RecyclerView外
 * 同时支持所有滚动控件
 */
public class MainActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {

    @BindView(R.id.recycler_view)
    ObservableRecyclerView recyclerView;
    @BindView(R.id.cut_img)
    TextView cutImg;
    private QuickAdapter quickAdapter;

    /**
     * 设置字体，
     * 以后可以写个父类继承过来，当Activity多时可以减少大量代码
     *
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User("测测滑动框架、试一试字体，abcABC", i, "123", getResources().getDrawable(R.mipmap.ic_launcher_round));
            userList.add(user);
        }
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        quickAdapter = new QuickAdapter(R.layout.item_recycler, userList);
        recyclerView.setAdapter(quickAdapter);
        //为listview设置滚动回调观察者
        recyclerView.setScrollViewCallbacks(this);
        cutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CutActivity.class));
            }
        });
    }

    /**
     * 通过实现ObservableScrollViewCallbacks接口的以下方法去
     * 达到变色，高度转换，图片转换等UI 的动态效果
     */

    /**
     * 滚动时
     *
     * @param scrollY
     * @param firstScroll
     * @param dragging
     */
    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        Log.i("onScrollChanged", "Y轴的坐标：" + scrollY);
    }

    /**
     * 按下时
     */
    @Override
    public void onDownMotionEvent() {

    }

    /**
     * 拖拽结束或取消时
     *
     * @param scrollState 状态
     */
    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        if (scrollState == ScrollState.DOWN) {
            Log.i("onUpOrCancelMotionEvent", "向下滚动");
        } else if (scrollState == ScrollState.UP) {
            Log.i("onUpOrCancelMotionEvent", "向上滚动");
        } else {
            Log.i("onUpOrCancelMotionEvent", "停止滚动");
        }
    }
}
