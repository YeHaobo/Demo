package minatest.example.com.minatest_android.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.databinding.ActivityLoginBinding;
import minatest.example.com.minatest_android.viewModel.LoginViewModel;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity {
    public ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        binding.setLogin(loginViewModel);
    }
}
