package minatest.example.com.minatest_android.tool;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import minatest.example.com.minatest_android.application.MyApplication;
import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.utils.Density;

/**
 * Toast吐司类封装
 */
public class MyToast{
    private static Toast toast;//实现不管我们触发多少次Toast调用，都只会持续一次Toast显示的时长

    private static TextView mTextView;
    private static ImageView mImageView;
    /**
     * 带图片的Toast吐司
     */
    public static void showImgToast(String message,int resId) {
        //加载Toast布局
        View toastRoot = LayoutInflater.from(MyApplication.mAppContext).inflate(R.layout.view_toast, null);
        //初始化布局控件
        mImageView= toastRoot.findViewById(R.id.toast_img);
        mTextView = toastRoot.findViewById(R.id.toast_tv);
        //为控件设置属性
        mTextView.setText(message);
        mImageView.setImageResource(resId);
        //Toast的初始化
        Toast toastStart = new Toast(MyApplication.mAppContext);
        //获取屏幕高度
        WindowManager wm = (WindowManager) MyApplication.mAppContext.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.TOP, 0, height / 3);
        toastStart.setDuration(Toast.LENGTH_SHORT);
        toastStart.setView(toastRoot);
        toastStart.show();
    }


    /**
     * 短时间显示Toast【居下】
     * @param msg 显示的内容-字符串*/
    public static void showShortToast(String msg) {
        if(MyApplication.mAppContext != null){
            if (toast == null) {
                toast = Toast.makeText(MyApplication.mAppContext, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            //1、setGravity方法必须放到这里，否则会出现toast始终按照第一次显示的位置进行显示（比如第一次是在底部显示，那么即使设置setGravity在中间，也不管用）
            //2、虽然默认是在底部显示，但是，因为这个工具类实现了中间显示，所以需要还原，还原方式如下：
            toast.setGravity(Gravity.BOTTOM, 0, Density.dip2px(MyApplication.mAppContext,64));
            toast.show();
        }
    }
    /**
     * 短时间显示Toast【居中】
     * @param msg 显示的内容-字符串*/
    public static void showShortToastCenter(String msg){
        if(MyApplication.mAppContext != null) {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.mAppContext, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     * 短时间显示Toast【居上】
     * @param msg 显示的内容-字符串*/
    public static void showShortToastTop(String msg){
        if(MyApplication.mAppContext != null) {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.mAppContext, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
    }

    /**
     * 长时间显示Toast【居下】
     * @param msg 显示的内容-字符串*/
    public static void showLongToast(String msg) {
        if(MyApplication.mAppContext != null) {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.mAppContext, msg, Toast.LENGTH_LONG);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.BOTTOM, 0, Density.dip2px(MyApplication.mAppContext,64));
            toast.show();
        }
    }
    /**
     * 长时间显示Toast【居中】
     * @param msg 显示的内容-字符串*/
    public static void showLongToastCenter(String msg){
        if(MyApplication.mAppContext != null) {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.mAppContext, msg, Toast.LENGTH_LONG);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
    /**
     * 长时间显示Toast【居上】
     * @param msg 显示的内容-字符串*/
    public static void showLongToastTop(String msg){
        if(MyApplication.mAppContext != null) {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.mAppContext, msg, Toast.LENGTH_LONG);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
    }
}
