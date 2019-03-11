package minatest.example.com.minatest_android.model;


import android.app.Activity;
import java.util.List;
import minatest.example.com.minatest_android.http.AppUrl;
import minatest.example.com.minatest_android.http.HttpOk;
import minatest.example.com.minatest_android.json.Bean2Json;
import minatest.example.com.minatest_android.entiti.Friend;
import minatest.example.com.minatest_android.viewModel.repository.MainRepository;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

public class MainModel{
    private MainRepository mainRepository;
    private Activity activity;
    public MainModel(Activity activity,MainRepository mainRepository){
         this.mainRepository = mainRepository;
         this.activity = activity;
     }

     //获取用户好友信息0未添加1已添加
    public void getFriendList(String userId,String isAdd){
         HttpOk.startHttp(activity, AppUrl.FRIENDS,
                new FormBody.Builder()
                .add("userId", userId)
                .add("isAdd", isAdd).build(),
                new HttpOk.HttpBack() {
            @Override
            public void onResponse(Call call, Response response, String res) {
                List<Friend> friendList = Bean2Json.json2Friends(res);
                mainRepository.refreshFriend(friendList);
            }
        });
    }

}
