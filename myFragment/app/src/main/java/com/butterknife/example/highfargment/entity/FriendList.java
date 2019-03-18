package com.butterknife.example.highfargment.entity;

import com.butterknife.example.highfargment.adapter.Quick3Adapter;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class FriendList extends AbstractExpandableItem<Friend> implements MultiItemEntity {
    @Override
    public int getLevel() {
        return Quick3Adapter.TYPE_LEVEL_0;
    }

    @Override
    public int getItemType() {
        return Quick3Adapter.TYPE_LEVEL_0;
    }

    private String title;

    public FriendList(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
