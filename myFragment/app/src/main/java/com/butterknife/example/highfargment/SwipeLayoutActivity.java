package com.butterknife.example.highfargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.butterknife.example.highfargment.adapter.SwiperAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 侧滑拉出删除菜单
 */
public class SwipeLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.swipeRecyclerView) RecyclerView swipeRecyclerView;

    private SwiperAdapter swiperAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_swipelayout);
        ButterKnife.bind(this);
        List<String> stringList = new ArrayList<>();
        for(int i = 0;i < 20 ; i++){
            stringList.add("<<< "+i+" >>>");
        }
        swipeRecyclerView.setNestedScrollingEnabled(true);
        swipeRecyclerView.setLayoutManager( new GridLayoutManager(this,1));
        swiperAdapter = new SwiperAdapter(this,stringList);
        swipeRecyclerView.setAdapter(swiperAdapter);
        swiperAdapter.setDelectItem(new SwiperAdapter.DelectItem() {
            @Override
            public void delete(int position) {
                swiperAdapter.stringList.remove(position);
                swiperAdapter.notifyItemRemoved(position);
                swiperAdapter.notifyItemRangeChanged(position,swiperAdapter.stringList.size() - position);
            }
        });
    }

    @OnClick({R.id.swiper_img, R.id.swiper_tv})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.swiper_img:
                Toast.makeText(SwipeLayoutActivity.this,"点击了删除",Toast.LENGTH_SHORT).show();
                break;
            case R.id.swiper_tv:
                startActivity(new Intent(SwipeLayoutActivity.this,BaseAdapterActivity.class));
                break;
            default:break;
        }
    }

}
