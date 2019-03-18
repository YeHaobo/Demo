package com.butterknife.example.highfargment.entity;

import com.butterknife.example.highfargment.adapter.Quick3Adapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class Friend implements MultiItemEntity{
    @Override
    public int getItemType() {
        return Quick3Adapter.TYPE_PERSON;
    }

    private String name;

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
