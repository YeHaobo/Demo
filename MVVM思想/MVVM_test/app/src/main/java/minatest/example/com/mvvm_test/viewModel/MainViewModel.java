package minatest.example.com.mvvm_test.viewModel;


import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import minatest.example.com.mvvm_test.modle.UserBean;

public class MainViewModel extends BaseObservable implements BaseViewModel {

    private Activity activity;//上下文
    public ObservableField<String> siginSuccess;//将要显示的提示信息
    public UserBean userBean;//不用使用ObservableField，父类直接继承
    public ObservableField<String> url = new ObservableField<>("http://avatar.csdn.net/4/0/7/1_zhuhai__yizhi.jpg");
    /**
     * 构造函数
     */
    public MainViewModel(Activity activity){
        this.activity = activity;
        this.siginSuccess = new ObservableField<>("");
        this.userBean = new UserBean("yhb",123);
    }

    public void siginOkClick(View view){
        Toast.makeText(activity,"已开启子线程",Toast.LENGTH_SHORT).show();
        new CountDownTimer(5*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                siginSuccess.set("登陆成功！");
            }
        }.start();
    }

    @Override
    public void destroy() {

    }

}
