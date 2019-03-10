package minatest.example.com.minatest_android.viewModel;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.entiti.User;
import minatest.example.com.minatest_android.model.LoginModel;
import minatest.example.com.minatest_android.socket.ChatService;
import minatest.example.com.minatest_android.tool.IntentTo;
import minatest.example.com.minatest_android.db.UserDateDo;
import minatest.example.com.minatest_android.tool.MyToast;
import minatest.example.com.minatest_android.view.LoginActivity;
import minatest.example.com.minatest_android.view.MainActivity;
import minatest.example.com.minatest_android.viewModel.repository.LoginRepository;

/**
 * viewModel模块，作为view与ViewModel的中间件
 * 动态数据绑定
 */
public class LoginViewModel implements LoginRepository {

    private LoginActivity activity;
    private LoginModel loginModel;
    private String username = "";//用户名
    private String password = "";//用户密码
    /**
     * 构造函数
     */
    public LoginViewModel(LoginActivity activity){
        this.loginModel = new LoginModel(activity,this);
        this.activity = activity;
    }

    /**
     * 获取用户名
     */
    public TextWatcher getUserName(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    /**
     * 获取密码
     */
    public TextWatcher getPassWord(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    /**
     * 登录监听
     */
    public void loginClick(View view){
        loginModel.clickLoginM(username,password);
    }

    /**
     * 用户登陆成功
     * @param user
     */
    @Override
    public void loginSuccess(User user) {
        UserDateDo.saveUserDate(user);
        IntentTo.finishToActivity(activity,MainActivity.class,null);
        ChatService.intent = new Intent(activity,ChatService.class);
        activity.startService(ChatService.intent);
    }

    /**
     * 用户登录失败
     */
    @Override
    public void loginFail() {
        MyToast.showImgToast("登录失败！", R.mipmap.ic_launcher_round);
    }
}
