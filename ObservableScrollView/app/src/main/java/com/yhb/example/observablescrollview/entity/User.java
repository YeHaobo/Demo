package com.yhb.example.observablescrollview.entity;


import android.graphics.drawable.Drawable;

public class User {
    private String name;
    private int age;
    private String sex;
    private Drawable mipmap;

    public User(String name, int age, String sex, Drawable mipmap) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.mipmap = mipmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Drawable getMipmap() {
        return mipmap;
    }

    public void setMipmap(Drawable mipmap) {
        this.mipmap = mipmap;
    }
}