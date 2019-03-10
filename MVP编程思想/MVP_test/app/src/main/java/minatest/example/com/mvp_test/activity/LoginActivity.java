package minatest.example.com.mvp_test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import minatest.example.com.mvp_test.R;
import minatest.example.com.mvp_test.presenter.LoginPresenter;
import minatest.example.com.mvp_test.contact.LoginContact.LoginView;

/**
 * 该View视图只负责进行监听绑定、控件获取、控件数据获取、和界面数据更新
 * 重点只负责界面视图操作，其他业务全权交给persenter处理
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter loginPresenter;
    private EditText userName,password;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 复写该方法防止内存泄漏
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(loginPresenter != null){
            loginPresenter.distory();
            loginPresenter = null;
            System.gc();//提示jvm回收内存
            Log.e("yhb","内存已回收");
        }
    }

    private void initView() {
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        button = findViewById(R.id.buttom);
        loginPresenter = new LoginPresenter(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login();
            }
        });
    }

    @Override
    public String getUserName() {
        return userName.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return password.getText().toString().trim();
    }

    @Override
    public void onLoginSucess() {
        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFail() {
        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
    }

}
