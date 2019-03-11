package minatest.example.com.minatest_android.http;

public interface AppUrl {
    //请求头
    String HREAD = "http://192.168.1.102:8080/My_Mina";
    String SIGNIN = HREAD + "/userDao/signIn";
    String FRIENDS = HREAD + "/friendDao/selectFriendOrAgree";
}
