package minatest.example.com.mvp_test.modle;

import android.os.CountDownTimer;

import minatest.example.com.mvp_test.contact.LoginContact.LoginLisentener;

/**
 * 登录业务处理、
 * 真正的业务处理、处理后使用回调回调给persenter
 */
public class LoginMode {
    //判断账户密码是否正确
    public void login(String userName, String password,final LoginLisentener loginLisentener){
        if(loginLisentener == null || userName == null || password == null) return;
        if(userName.equals("123") && password.equals("123")){
            //计时器//模拟耗时的网络请求
            new CountDownTimer(5*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }
                @Override
                public void onFinish() {
                    loginLisentener.onSeccess();
                }
            }.start();
        }else{
            loginLisentener.onFails();
        }
    }
}
