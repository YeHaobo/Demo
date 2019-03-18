package com.butterknife.example.highfargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.butterknife.example.highfargment.adapter.Quick2Adapter;
import com.butterknife.example.highfargment.adapter.Quick3Adapter;
import com.butterknife.example.highfargment.entity.Friend;
import com.butterknife.example.highfargment.entity.FriendList;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 树形列表
 */
public class BaseAdapter3Activity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Quick3Adapter quickAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseadapter);
        ButterKnife.bind(this);
        List<MultiItemEntity> data = new ArrayList<>();
        for(int i = 0; i < 3 ; i ++){
            FriendList friends = new FriendList("分组" + i);
            for(int j = 0; j < 5; j ++){
                friends.addSubItem(new Friend("lqqyhb"));
            }
            data.add(friends);
        }
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        quickAdapter = new Quick3Adapter(data);
        quickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        recyclerView.setAdapter(quickAdapter);
    }

}
