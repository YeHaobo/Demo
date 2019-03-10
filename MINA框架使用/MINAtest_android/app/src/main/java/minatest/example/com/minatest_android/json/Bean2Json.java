package minatest.example.com.minatest_android.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.entiti.Friend;
import minatest.example.com.minatest_android.entiti.User;
import minatest.example.com.minatest_android.tool.MyToast;

/**
 * 对象与json串之间的转换
 */
public class Bean2Json {
     //剥壳------获取用户对象
     public static List<User> json2result(String date){
         JSONObject jsonObject = JSON.parseObject(date);
         if(jsonObject.getInteger("isSuccess") != 1){
             MyToast.showImgToast(jsonObject.getString("context"), R.mipmap.ic_launcher_round);
             return null;
         }
         List<User> userList = new ArrayList<>();
         JSONArray jsonArray = jsonObject.getJSONArray("context");
         for(int i = 0; i < jsonArray.size();i++){
             JSONObject json = jsonArray.getJSONObject(i);
             User user=new User(json.getString("uId"),json.getTimestamp("time"),json.getString("token"),
                     json.getString("phone"),json.getString("name"),json.getString("pwd"),
                     json.getString("hread"));
             userList.add(user);
         }
         return userList;
     }

     //获取好友信息
    public static List<Friend> json2Friends(String date){
        JSONObject jsonObject = JSON.parseObject(date);
        if(jsonObject.getInteger("isSuccess") != 1){
            MyToast.showImgToast(jsonObject.getString("context"), R.mipmap.ic_launcher_round);
            return null;
        }
        List<Friend> friendList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("context");
        for(int i = 0; i < jsonArray.size();i++){
            JSONObject json = jsonArray.getJSONObject(i);
            Friend friend = new Friend(json.getString("uId"),json.getString("token"),json.getString("name"),
                    json.getString("phone"),json.getString("hread"));
            friendList.add(friend);
        }
        return friendList;
    }
}
