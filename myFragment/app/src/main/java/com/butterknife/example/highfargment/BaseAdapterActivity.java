package com.butterknife.example.highfargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.butterknife.example.highfargment.adapter.QuickAdapter;
import com.butterknife.example.highfargment.entity.User;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * BaseRecyclerViewAdapterHelper万能适配器包括以下功能
 * Adapter的最基本使用方法
 * 点击事件
 * 添加列表加载动画
 * 添加头部、尾部
 * 上拉加载
 * 下拉加载（符合聊天软件下拉历史数据需求）
 * 分组布局
 * 多布局
 * 设置空布局
 * 添加拖拽、滑动删除
 * 树形列表
 * 自定义ViewHolder
 */
public class BaseAdapterActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private QuickAdapter quickAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseadapter);
        ButterKnife.bind(this);
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 20;i ++){
            User user = new User("yhb",i,"mo",getResources().getDrawable(R.mipmap.ic_launcher_round));
            userList.add(user);
        }
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager( new GridLayoutManager(this,1));
        quickAdapter = new QuickAdapter(R.layout.item_base,userList);
        recyclerView.setAdapter(quickAdapter);
        //条目点击事件
        setClickListener();
        //使用缩放动画
        quickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
    }
    //设置条目点击监听
    private void setClickListener(){
        //条目点击事件
        quickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //获取某项的相应控件
                TextView tv_title = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.base_tv);
                Toast.makeText(BaseAdapterActivity.this, tv_title.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //条目长按事件
        quickAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

                Toast.makeText(BaseAdapterActivity.this, "长按了第" + position + "条条目", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //条目子控件长按事件
        quickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.base_img){
                    Toast.makeText(BaseAdapterActivity.this, "长按了第" + (position + 1) + "条条目的图片", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
