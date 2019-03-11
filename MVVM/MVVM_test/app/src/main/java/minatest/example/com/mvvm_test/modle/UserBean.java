package minatest.example.com.mvvm_test.modle;

import android.databinding.BaseObservable;

/**
 * 若该类想实现双向绑定必须继承BaseObservable
 */
public class UserBean extends BaseObservable {
    private String name;
    private int age;

    public UserBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
