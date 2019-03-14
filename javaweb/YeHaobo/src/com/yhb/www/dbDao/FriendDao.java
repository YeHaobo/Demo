package com.yhb.www.dbDao;

import com.yhb.www.entity.Result;

/**
 * 用户好友关系表
 */
public interface FriendDao {
    public Result addFriend(String userId,String friendId);
    public Result delectFriendByUid(String userId,String friendId);
    public Result updateFriendByUid(String userId,String friendId);
    public Result selectFriendLikePhoneAndName(String userId,String phone,String name);
    public Result selectFriendOrAgree(String userId,int isAdd);
    public boolean isCheckFriend(String userId,String friendId,int isAdd);
}
