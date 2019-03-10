package minatest.example.com.minatest_android.model;

import android.app.Activity;
import java.util.List;
import minatest.example.com.minatest_android.http.AppUrl;
import minatest.example.com.minatest_android.http.HttpOk;
import minatest.example.com.minatest_android.json.Bean2Json;
import minatest.example.com.minatest_android.entiti.User;
import minatest.example.com.minatest_android.utils.Verifier;
import minatest.example.com.minatest_android.viewModel.repository.LoginRepository;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * 登录页面所有操作
 */
public class LoginModel {

    private Activity activity;
    private LoginRepository loginRepository;

    public LoginModel(Activity activity,LoginRepository loginRepository){
        this.activity = activity;
        this.loginRepository = loginRepository;
    }

    //点击事件业务处理
    public void clickLoginM(String name,String pwd){
        if(!Verifier.isPhoneNumber(name))return;
        HttpOk.startHttp(activity, AppUrl.SIGNIN ,
                new FormBody.Builder()
                        .add("phone", name)
                        .add("pwd", pwd).build(),
                new HttpOk.HttpBack() {
                    @Override
                    public void onResponse(Call call, Response response, String res) {
                        List<User> userList = Bean2Json.json2result(res);
                        if(userList != null){
                            User user = userList.get(0);
                            loginRepository.loginSuccess(user);
                        }
                        else{
                            loginRepository.loginFail();
                        }
                    }
                });
    }



}
