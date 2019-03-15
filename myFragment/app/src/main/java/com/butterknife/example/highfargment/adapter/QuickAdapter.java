package com.butterknife.example.highfargment.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.butterknife.example.highfargment.R;
import com.butterknife.example.highfargment.entity.User;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 继承自万能适配器的使用
 */
public class QuickAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public QuickAdapter(@LayoutRes int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    //如果添加头布局在这也可添加
    @Override
    protected void convert(BaseViewHolder viewHolder, User user) {
        //可链式调用赋值
        viewHolder.setText(R.id.base_tv, user.getName()+" | "+user.getAge()+" | "+user.getSex())
                .setImageDrawable(R.id.base_img,user.getMipmap())
                .addOnLongClickListener(R.id.base_img);//给图片添加长按事件;
        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}
