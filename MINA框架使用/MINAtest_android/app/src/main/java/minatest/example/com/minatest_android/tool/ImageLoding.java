package minatest.example.com.minatest_android.tool;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

import minatest.example.com.minatest_android.R;

/**
 * 图片加载
 */
public class ImageLoding {

    public static String typeNormal = "1";//正常加载
    public static String typeBig = "2";//大圆角
    public static String typeSmoll = "3";//小圆角
    //动画
    public static DrawableCrossFadeFactory drawableCrossFadeFactory =
            new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();

    //正常加载
    @BindingAdapter({"friendAvatarURL","imageType"})
    public static void setAvatarImage(ImageView imageView, String url,String imageType){
        Glide.with(imageView.getContext())
                .load(url)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                .apply(new RequestOptions().fitCenter().error(R.mipmap.ic_launcher_round))
                .into(imageView);
    }
}
