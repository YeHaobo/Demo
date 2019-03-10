package minatest.example.com.mvp_test.presenter;

import android.util.Log;

import minatest.example.com.mvp_test.contact.LoginContact;
import minatest.example.com.mvp_test.contact.LoginContact.LoginLisentener;
import minatest.example.com.mvp_test.modle.LoginMode;
import minatest.example.com.mvp_test.contact.LoginContact.LoginView;

/**
 * 界面逻辑处理，相当与契约，负责传递将要执行的业务，以及执行过后的回调
 * 参与业务处理的顺序逻辑，并不参与真正的业务处理
 * 同时拥有 mode 和 View 的实例
 * mode和View不可直接联系
 */
public class LoginPresenter implements LoginContact.BasePresenter {

    private LoginView loginView;
    private LoginMode loginMode;

    /**
     * 复写该方法防止内存泄漏
     */
    public void distory() {
        loginView = null;
        System.gc();//提示jvm回收
        Log.e("yhb","内存已回收");
    }

    public LoginPresenter (LoginView loginView){
        this.loginView = loginView;
        this.loginMode = new LoginMode();
    }

    public void login(){
        String name = loginView.getUserName();
        String password = loginView.getPassWord();
        loginMode.login(name, password, new LoginLisentener() {
            @Override
            public void onSeccess() {
                /**
                 * 防止出现空指针现象
                 * 注意：在网络加载时或是在操作耗时工作时必须做非空判断
                 */
                if(loginView != null)
                loginView.onLoginSucess();
            }

            @Override
            public void onFails() {
                if(loginView != null)
                loginView.onLoginFail();
            }
        });
    }


}
