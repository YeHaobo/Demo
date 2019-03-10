package minatest.example.com.minatest_android.viewModel.repository;

import minatest.example.com.minatest_android.entiti.User;

/**
 * 登录页面数据返回接口
 */
public interface LoginRepository extends BaseRepository{
    //登录成功返回用户数据
    void loginSuccess(User user);
    //登录失败
    void loginFail();
}
