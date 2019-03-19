package com.yhb.example.observablescrollview.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yhb.example.observablescrollview.R;
import com.yhb.example.observablescrollview.entity.User;

import java.util.List;

public class QuickAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public QuickAdapter(@LayoutRes int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    //如果添加头布局在这也可添加
    @Override
    protected void convert(BaseViewHolder viewHolder, User user) {
        //获取当前条目position
        //int position = viewHolder.getLayoutPosition();
        //可链式调用赋值
        viewHolder.setText(R.id.base_tv, user.getName()+" | "+user.getAge()+" | "+user.getSex())
                .setImageDrawable(R.id.base_img,user.getMipmap());
    }
}
