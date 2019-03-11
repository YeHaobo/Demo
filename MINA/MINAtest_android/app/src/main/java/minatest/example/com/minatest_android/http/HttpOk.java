package minatest.example.com.minatest_android.http;

import android.app.Activity;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.tool.MyToast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络访问封装
 */
public class HttpOk {

    private OkHttpClient mOkHttpClient;
    private Request request;
    private Activity activity;
    private String url;
    private FormBody body;
    private HttpBack mHttpBack; //网络请求接口封装
    public interface HttpBack{//可直接进行UI操作
        void onResponse(Call call, Response response, String res);
    }
    private HttpOk( Activity activity,String url, FormBody body, HttpBack httpBack){
        this.activity = activity;
        this.mHttpBack=httpBack;
        this.url=url;
        this.body = body;
        this.mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
    }
    public static void startHttp( Activity activity,String url, FormBody body,HttpBack httpBack){
        HttpOk httpOk=new HttpOk(activity,url,body,httpBack);
        httpOk.startCall();
    }

    private void startCall() {
        HttpHelpDo.isIntenter(activity);
        if (!HttpHelpDo.isNet)return ;//首先判断是否有网络
        request = new Request.Builder().url(url).post(body).build();
        mOkHttpClient.newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(Call call, final IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(e instanceof SocketTimeoutException){//判断超时异常
                            MyToast.showImgToast("连接超时",R.mipmap.ic_launcher_round);
                        }
                        else if(e instanceof ConnectException){//判断连接异常
                            MyToast.showImgToast("网络异常",R.mipmap.ic_launcher_round);
                        }
                        else{
                            MyToast.showImgToast("网络错误",R.mipmap.ic_launcher_round);
                        }
                    }
                });
            }
            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                final String res = response.body().string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mHttpBack.onResponse(call, response, res);
                    }
                });
            }
        });
    }
}

