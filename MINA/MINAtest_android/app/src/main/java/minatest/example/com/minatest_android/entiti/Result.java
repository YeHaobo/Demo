package minatest.example.com.minatest_android.entiti;

import android.databinding.BaseObservable;

/**
 * 数据返回外部类
 */
public class Result {
    private int isSuccess;//访问是否成功 1成功，0失败
    private Object context;//返回数据

    public Result(int isSuccess, Object context) {
        this.isSuccess = isSuccess;
        this.context = context;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }
}
