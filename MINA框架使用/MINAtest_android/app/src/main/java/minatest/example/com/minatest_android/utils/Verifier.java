package minatest.example.com.minatest_android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.tool.MyToast;

/**
 * 验证工具类
 */
public class Verifier {
    //手机号验证
    public static boolean isPhoneNumber(String telephone){
        //判断手机号是否正确
        Pattern p = Pattern.compile("^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$");
        Matcher m = p.matcher(telephone);
        if(telephone==null||telephone.equals("")){
            MyToast.showImgToast("请输入手机号",R.mipmap.ic_launcher_round);
            return false;
        }
        else if(!m.matches()){
            MyToast.showImgToast("请输入正确手机号",R.mipmap.ic_launcher_round);
            return false;
        }
        else return true;
    }
}
