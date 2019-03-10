package minatest.example.com.minatest_android.entiti;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * 好友信息实体类
 */
public class Friend extends BaseObservable{
    private String uId;
    private String token;
    private String name;
    private String phone;
    private String hread;

    public Friend(){}

    public Friend(String uId, String token, String name, String phone, String hread) {
        this.uId = uId;
        this.token = token;
        this.name = name;
        this.phone = phone;
        this.hread = hread;
    }
    @Bindable
    public String getuId() {
        return uId;
    }
    @Bindable
    public String getToken() {
        return token;
    }
    @Bindable
    public String getName() {
        return name;
    }
    @Bindable
    public String getPhone() {
        return phone;
    }
    @Bindable
    public String getHread() {
        return hread;
    }

    public void setuId(String uId) {
        this.uId = uId;
        notifyPropertyChanged(BR.uId);
    }

    public void setToken(String token) {
        this.token = token;
        notifyPropertyChanged(BR.token);
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    public void setHread(String hread) {
        this.hread = hread;
        notifyPropertyChanged(BR.hread);
    }
}
