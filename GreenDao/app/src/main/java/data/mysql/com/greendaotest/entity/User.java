package data.mysql.com.greendaotest.entity;

import org.litepal.crud.DataSupport;

/**
 * 用户表映射实体类
 */
public class User extends DataSupport {
    private String user_id;
    private String user_name;
    private String user_phone;
    private String user_sex;
    private int user_age;
    private String user_birthday;

    public User(){}

    public User(String user_id, String user_name, String user_phone, String user_sex, int user_age, String user_birthday) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_sex = user_sex;
        this.user_age = user_age;
        this.user_birthday = user_birthday;
    }

    public String user2string(){
        return user_id+" | "+user_name+" | "+user_phone+" | "+user_sex+" | "+user_age+" | "+user_birthday;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }
}
