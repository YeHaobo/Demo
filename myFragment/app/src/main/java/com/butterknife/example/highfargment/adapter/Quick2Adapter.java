package com.butterknife.example.highfargment.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.butterknife.example.highfargment.R;
import com.butterknife.example.highfargment.entity.User;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class Quick2Adapter extends BaseItemDraggableAdapter<User, BaseViewHolder> {

    public Quick2Adapter(@LayoutRes int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    //如果添加头布局在这也可添加
    @Override
    protected void convert(BaseViewHolder viewHolder, User user) {
        //获取当前条目position
        //int position = viewHolder.getLayoutPosition();
        //可链式调用赋值
        viewHolder.setText(R.id.base_tv, user.getName()+" | "+user.getAge()+" | "+user.getSex())
                .setImageDrawable(R.id.base_img,user.getMipmap())
                .addOnClickListener(R.id.base_img);//给图片注册点击事件;
    }
}
