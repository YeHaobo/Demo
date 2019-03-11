package minatest.example.com.mvvm_test.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import minatest.example.com.mvvm_test.R;
import minatest.example.com.mvvm_test.databinding.ActivityLoginBinding;
import minatest.example.com.mvvm_test.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    //当给布局指定格式之后会产生相应的Bing类，例如activity_login.xml会产生ActivityLoginBinding类
    public ActivityLoginBinding binding;
    public LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        binding.setLoginModel(loginViewModel);
    }

}
