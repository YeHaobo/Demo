package com.yhb.www.entity;


import java.sql.Timestamp;

/**
 * 聊天信息实体类
 */
public class ChatText {
    private int logic;//登录信息、普通信息、登出信息
    private int chatEnvirment;//聊天环境
    private int type;//信息类别
    private String fromUserToken;//发送的用户
    private String toUserToken;//接收的用户
    private int isRead;//是否已读
    private Timestamp time;//发送时间
    private String chatContext;//信息文本·

    public ChatText(){}

    public ChatText(int logic, int chatEnvirment, int type, String fromUserToken, String toUserToken, int isRead, Timestamp time, String chatContext) {
        this.logic=logic;
        this.chatEnvirment = chatEnvirment;
        this.type = type;
        this.fromUserToken = fromUserToken;
        this.toUserToken = toUserToken;
        this.isRead = isRead;
        this.time = time;
        this.chatContext = chatContext;
    }

    public int getLogic() {
        return logic;
    }

    public void setLogic(int logic) {
        this.logic = logic;
    }

    public int getChatEnvirment() {
        return chatEnvirment;
    }

    public void setChatEnvirment(int chatEnvirment) {
        this.chatEnvirment = chatEnvirment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFromUserToken() {
        return fromUserToken;
    }

    public void setFromUserToken(String fromUserToken) {
        this.fromUserToken = fromUserToken;
    }

    public String getToUserToken() {
        return toUserToken;
    }

    public void setToUserToken(String toUserToken) {
        this.toUserToken = toUserToken;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getChatContext() {
        return chatContext;
    }

    public void setChatContext(String chatContext) {
        this.chatContext = chatContext;
    }
}

