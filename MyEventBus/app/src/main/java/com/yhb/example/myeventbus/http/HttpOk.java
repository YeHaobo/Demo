package com.yhb.example.myeventbus.http;

import android.app.Activity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
        void onFail(Call call,IOException e);
    }
    private HttpOk(Activity activity, String url, FormBody body, HttpBack httpBack){
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
    private void startCall() {
        HttpHelpDo.isIntenter(activity);
        if (!HttpHelpDo.isNet)return ;//首先判断是否有网络
        request = new Request.Builder().url(url).post(body).build();
        mOkHttpClient.newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(Call call,IOException e) {
                mHttpBack.onFail(call,e);
            }
            @Override
            public void onResponse( Call call, Response response) throws IOException{
                ResponseBody responseBody = response.body();
                if(responseBody == null) {
                    mHttpBack.onFail(call,null);
                }
                else {
                    String res = responseBody.string();
                    mHttpBack.onResponse(call,response,res);
                }
            }
        });
    }

    public static void startHttp(Activity activity, String url, FormBody body, HttpBack httpBack){
        HttpOk httpOk=new HttpOk(activity,url,body,httpBack);
        httpOk.startCall();
    }
}

