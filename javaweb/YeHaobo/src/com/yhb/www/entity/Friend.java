package com.yhb.www.entity;

/**
 * 好友关系实体类
 */
public class Friend {
    private String userId;
    private String friendId;
    private int isAdd;

    public Friend(String userId, String friendId, int isAdd) {
        this.userId = userId;
        this.friendId = friendId;
        this.isAdd = isAdd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public int getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(int isAdd) {
        this.isAdd = isAdd;
    }
}
