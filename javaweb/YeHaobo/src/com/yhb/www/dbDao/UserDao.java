package com.yhb.www.dbDao;

import com.yhb.www.entity.Result;

/**
 * 用户操作所有接口类
 */
public interface UserDao {
    public Result addUser(String phone, String name, String pwd, String hread);
    public Result findUsers();
    public Result findUsersById(String userId);
    public Result findUsersByToken(String token);
    public Result findUserLikePhoneAndName(String phone,String name);
    public Result delectUserByUid(String userId);
    public Result updateUserByUid(String userId , String name, String pwd, String hread);
    public boolean isCheckUserByPhone(String phone);
    public boolean isCheckUserById(String userId);
}