package minatest.example.com.mvp_test.contact;

/**
 * 关于login的所有接口类
 */
public class LoginContact {
    /**
     * View层数据获取接口
     */
    public interface LoginView {
        String getUserName();
        String getPassWord();
        void onLoginSucess();
        void onLoginFail();
    }

    /**
     * presenter层所有该层的类都必须实现此接口
     * presenterf父类
     */
    public interface BasePresenter {
        void distory();
    }

    /**
     * modle层
     * 登陆成功失败回调接口
     */
    public interface LoginLisentener {
        void onSeccess();
        void onFails();
    }
}
