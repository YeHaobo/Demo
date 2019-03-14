package com.yhb.www.entity;


import java.sql.Timestamp;

/**
 * 用户实体类
 */
public class User {
    private String uId;
    private Timestamp time;
    private String token;
    private String phone;//手机号
    private String name;//用户名
    private String pwd;//密码
    private String hread;//头像

    public String user2string(){
        return "uId"+uId+"time"+time+"token"+token+"phone"+phone+"name"+name+"pwd"+pwd+"hread"+hread;
    }

    public User(){}
    public User(String uId, Timestamp time, String token, String phone, String name, String pwd, String hread) {
        this.uId = uId;
        this.time = time;
        this.token = token;
        this.phone = phone;
        this.name = name;
        this.pwd = pwd;
        this.hread = hread;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHread() {
        return hread;
    }

    public void setHread(String hread) {
        this.hread = hread;
    }
}