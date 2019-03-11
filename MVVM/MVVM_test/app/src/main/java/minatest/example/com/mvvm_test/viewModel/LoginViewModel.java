package minatest.example.com.mvvm_test.viewModel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import minatest.example.com.mvvm_test.view.MainActivity;

public class LoginViewModel extends BaseObservable implements BaseViewModel{

    private Activity activity;//上下文
    /**
     * 需要绑定UI上的数据最好直接用泛型封装
     */
    public ObservableField<String> loginMessage;//将要显示的提示信息
    public ObservableInt messageVisibility;//是否显示
    private String username = "";//用户名
    private String password = "";//用户密码

    /**
     * 构造函数
     */
    public LoginViewModel(Activity activity){
        this.activity = activity;
        this.loginMessage = new ObservableField<>("");
        this.messageVisibility = new ObservableInt(View.INVISIBLE);
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
     * 密码验证操作
     */
    public void login(View view){
        if(username == null || username.equals("") || password == null || password.equals("")){
            loginMessage.set("Username or Password can't be empty!");
            messageVisibility.set(View.VISIBLE);
        }
        if(username.equals("123") && password.equals("123")){
            activity.startActivity(new Intent(activity,MainActivity.class));
        }
    }

    @Override
    public void destroy() {

    }

}
