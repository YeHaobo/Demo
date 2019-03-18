package com.butterknife.example.highfargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.butterknife.example.highfargment.adapter.Quick2Adapter;
import com.butterknife.example.highfargment.entity.User;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加拖拽、滑动删除
 */
public class BaseAdapter2Activity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Quick2Adapter quickAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseadapter);
        ButterKnife.bind(this);
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User("yhb", i, "mo", getResources().getDrawable(R.mipmap.ic_launcher_round));
            userList.add(user);
        }
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        quickAdapter = new Quick2Adapter(R.layout.item_base, userList);
        recyclerView.setAdapter(quickAdapter);
        addSwipe();
    }

    //开启添加拖拽、滑动删除
    private void addSwipe(){
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(quickAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        // 开启拖拽
        quickAdapter.enableDragItem(itemTouchHelper, R.id.base_img, true);
        //监听拖拽
        //quickAdapter.setOnItemDragListener(onItemDragListener);

        // 开启滑动删除
        quickAdapter.enableSwipeItem();
        //监听删除
        //quickAdapter.setOnItemSwipeListener(onItemSwipeListener);
        /**
         * 拖拽和滑动删除的回调函数
         * OnItemDragListener onItemDragListener = new OnItemDragListener() {
         *     @Override
         *     public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos){}
         *     @Override
         *     public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {}
         *     @Override
         *     public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {}
         * }
         *
         * OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
         *     @Override
         *     public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {}
         *     @Override
         *     public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {}
         *     @Override
         *     public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {}
         * };
         */
    }

}
