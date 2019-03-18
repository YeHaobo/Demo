package com.butterknife.example.highfargment.adapter;

import android.view.View;
import com.butterknife.example.highfargment.R;
import com.butterknife.example.highfargment.entity.Friend;
import com.butterknife.example.highfargment.entity.FriendList;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.util.List;


/**
 * 二级树形列表适配器
 */
public class Quick3Adapter  extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_PERSON = 1;

    public Quick3Adapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.nav_header_main);
        addItemType(TYPE_PERSON, R.layout.item_base);
    }

    @Override
    protected void convert(final BaseViewHolder holder,MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0://分组项
                final FriendList mItem = (FriendList) item;//获得分组的实例
                holder.setText(R.id.clickMe,mItem.getTitle());//设置分组的显示的内容
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();//获取pos位置
                        if (mItem.isExpanded()) {//判断是否打开
                            collapse(pos);//关闭
                        } else {
                            expand(pos);//打开
                        }
                    }
                });
                break;
            case TYPE_PERSON://实体项
                final Friend mFriend = (Friend) item;
                holder.setText(R.id.base_tv,mFriend.getName());
                break;
        }
    }
}
