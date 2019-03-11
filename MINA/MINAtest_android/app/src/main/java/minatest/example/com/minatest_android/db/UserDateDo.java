package minatest.example.com.minatest_android.db;

import android.content.Context;
import android.content.SharedPreferences;

import minatest.example.com.minatest_android.application.MyApplication;
import minatest.example.com.minatest_android.entiti.User;

/**
 * 当前用户信息
 */
public class UserDateDo {
    /**
     * 当前登录用户的信息存储
     */
    public static void saveUserDate(User user){
        //步骤2-1：创建一个SharedPreferences.Editor接口对象，lock表示要写入的XML文件名，MODE_WORLD_WRITEABLE写操作
        SharedPreferences.Editor editor = MyApplication.mAppContext.getSharedPreferences("userDate", Context.MODE_WORLD_WRITEABLE).edit();
        //步骤2-2：将获取过来的值放入文件
        editor.putString("uId",user.getuId());
        editor.putLong("time",user.getTime().getTime());
        editor.putString("token",user.getToken());
        editor.putString("phone", user.getPhone());
        editor.putString("name",user.getName());
        editor.putString("pwd",user.getPwd());
        editor.putString("hread",user.getHread());
        editor.commit();//步骤3：提交
    }
    /**
     * 获取登录用户的uId
     */
    public static String getUserUid(){
        //步骤1：创建一个SharedPreferences接口对象
        SharedPreferences read = MyApplication.mAppContext.getSharedPreferences("userDate", Context.MODE_WORLD_READABLE);
        //步骤2：获取文件中的值
        return read.getString("uId", "");
    }

    /**
     * 获取当前用户的创建time
     */
    public static long getUserTime(){
        SharedPreferences read = MyApplication.mAppContext.getSharedPreferences("userDate", Context.MODE_WORLD_READABLE);
        return read.getLong("time", 0);
    }
    /**
     * 获取当前用户的token
     */
    public static String getUserToken(){
        SharedPreferences read = MyApplication.mAppContext.getSharedPreferences("userDate", Context.MODE_WORLD_READABLE);
        return read.getString("token", "");
    }
    /**
     * 取当前登录用户phone
     */
    public static String getUserPhone(){
        SharedPreferences read = MyApplication.mAppContext.getSharedPreferences("userDate", Context.MODE_WORLD_READABLE);
        return read.getString("phone", "");
    }
    /**
     * 获取当前用户的用户名name
     */
    public static String getUserName(){
        SharedPreferences read = MyApplication.mAppContext.getSharedPreferences("userDate", Context.MODE_WORLD_READABLE);
        return read.getString("name", "");
    }
    /**
     * 获取当前用户的密码
     */
    public static String getUserPwd(){
        SharedPreferences read = MyApplication.mAppContext.getSharedPreferences("userDate", Context.MODE_WORLD_READABLE);
        return read.getString("pwd", "");
    }
    /**
     * 获取当前用户的头像地址
     */
    public static String getUserHread(){
        SharedPreferences read = MyApplication.mAppContext.getSharedPreferences("userDate", Context.MODE_WORLD_READABLE);
        return read.getString("hread", "");
    }
}
