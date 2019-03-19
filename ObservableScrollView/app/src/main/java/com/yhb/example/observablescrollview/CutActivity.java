package com.yhb.example.observablescrollview;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CutActivity extends AppCompatActivity {
    @BindView(R.id.cut_img)
    TextView cutImg;
    @BindView(R.id.recycler_view)
    ObservableRecyclerView recyclerView;
    //请求状态码
    public static int REQUEST_PERMISSION_CODE = 1;
    //读写权限
    public static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static void getExternal(Activity activity){//得到内存读写权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        cutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCrop();
            }
        });
        getExternal(CutActivity.this);
    }

    private void startCrop() {
        Uri sourceUri = Uri.parse("http://star.xiziwang.net/uploads/allimg/140512/19_140512150412_1.jpg");
        //裁剪后保存到文件中
        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), "SampleCropImage.jpeg"));
        /**
         * 启动裁剪
         *  UCrop.of(sourceUri, destinationUri).withAspectRatio(16, 9).withMaxResultSize(300, 300).start(this);
         *  底下为自定义的裁剪布局
         */


        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
        // options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorAccent));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorAccent));
        //是否能调整裁剪框
        // options.setFreeStyleCropEnabled(true);
        //设置裁剪图片的宽高比，比如16：9
        uCrop.withAspectRatio(1, 1);
        uCrop.withOptions(options);
        uCrop.start(this);
        /**
         * 以下为更多设置
         * //设置Toolbar标题
         * void setToolbarTitle(@Nullable String text)
         * //设置裁剪的图片格式
         * void setCompressionFormat(@NonNull Bitmap.CompressFormat format)
         * //设置裁剪的图片质量，取值0-100
         * void setCompressionQuality(@IntRange(from = 0) int compressQuality)
         * //设置最多缩放的比例尺
         * void setMaxScaleMultiplier(@FloatRange(from = 1.0, fromInclusive = false) float maxScaleMultiplier)
         * //动画时间
         * void setImageToCropBoundsAnimDuration(@IntRange(from = 100) int durationMillis)
         * //设置图片压缩最大值
         * void setMaxBitmapSize(@IntRange(from = 100) int maxBitmapSize)
         * //是否显示椭圆裁剪框阴影
         * void setOvalDimmedLayer(boolean isOval)
         * //设置椭圆裁剪框阴影颜色
         * void setDimmedLayerColor(@ColorInt int color)
         * //是否显示裁剪框
         * void setShowCropFrame(boolean show)
         * //设置裁剪框边的宽度
         * void setCropFrameStrokeWidth(@IntRange(from = 0) int width)
         * //是否显示裁剪框网格
         * void setShowCropGrid(boolean show)
         * //设置裁剪框网格颜色
         * void setCropGridColor(@ColorInt int color)
         * //设置裁剪框网格宽
         * void setCropGridStrokeWidth(@IntRange(from = 0) int width)
         */

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //裁切成功
            if (requestCode == UCrop.REQUEST_CROP) {
                Uri croppedFileUri = UCrop.getOutput(data);
                //获取默认的下载目录
                String downloadsDirectoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                String filename = String.format("%d_%s", Calendar.getInstance().getTimeInMillis(), croppedFileUri.getLastPathSegment());
                File saveFile = new File(downloadsDirectoryPath, filename);
                //保存下载的图片
                FileInputStream inStream = null;
                FileOutputStream outStream = null;
                FileChannel inChannel = null;
                FileChannel outChannel = null;
                try {
                    inStream = new FileInputStream(new File(croppedFileUri.getPath()));
                    outStream = new FileOutputStream(saveFile);
                    inChannel = inStream.getChannel();
                    outChannel = outStream.getChannel();
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                    Toast.makeText(this, "裁切后的图片保存在：" + saveFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(outChannel != null)outChannel.close();
                        if(outStream != null)outStream.close();
                        if(inChannel != null)inChannel.close();
                        if(inStream != null)inStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //裁切失败
        if (resultCode == UCrop.RESULT_ERROR) {
            Toast.makeText(this, "裁切图片失败", Toast.LENGTH_SHORT).show();
        }
    }

}
