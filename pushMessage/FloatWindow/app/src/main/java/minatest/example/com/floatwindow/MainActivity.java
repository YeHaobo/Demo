package minatest.example.com.floatwindow;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private String channelID = "minatest.example.com";//通知通道唯一标识，一般用包名
    private String channelName = "打开通知、铃声、震动等...";//将要在应用信息页面提示的选项名称
    private NotificationManager manager;
    private NotificationCompat.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=findViewById(R.id.test_tv);
        initNotification();//初始化通知通道
        textView.setOnClickListener(getListener());
    }
    //点击事件
    private View.OnClickListener getListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManagerCompat manager = NotificationManagerCompat.from(MainActivity.this);
                boolean isOpened = manager.areNotificationsEnabled();
                if (isOpened) {
                    showNotification();
                } else {
                    final TipsDialog tipsDialog = new TipsDialog("去通知管理开启通知！", "去开启", "提示", MainActivity.this);
                    tipsDialog.getDialog(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getApplication().getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                            tipsDialog.finishThis();
                        }
                    });
                }
            }
        };
        return onClickListener;
    }
    //初始化通知通道
    private void initNotification(){
        builder = new NotificationCompat.Builder(getApplicationContext(), channelID);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){//8.0以上
            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), Notification.AUDIO_ATTRIBUTES_DEFAULT);
            channel.setShowBadge(true);
            channel.setBypassDnd(false);
            channel.setImportance(NotificationManager.IMPORTANCE_HIGH);
            channel.setLightColor(Color.RED);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            channel.setVibrationPattern(new long[]{100, 200, 300});
            manager.createNotificationChannel(channel);
        }else{//8.0以下4.1以上
            manager = (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);
            //builder.setDefaults(Notification.DEFAULT_ALL);
            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            builder.setVibrate(new long[]{100, 200, 300});
        }
    }
    //发送通知
    private void showNotification(){
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置小图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background));//设置大图标
        builder.setTicker("通知来啦");//手机状态栏的提示
        builder.setWhen(System.currentTimeMillis());//设置创建通知的时间，单位是毫秒。（当前时间）
        builder.setContentTitle("我是通知标题");//设置标题  
        builder.setContentText("看看能不能全部显示看看能不能全部显示看看能不能全部显示看看能不能全部显示看看能不能全部显示");//显示不下会自动加省略号
        builder.setOngoing(false);//设置是否需要把Notification放置在“正在运行”的栏目
        builder.setAutoCancel(true);//设置点击后消失小图标
        Intent  intent = new Intent(this, TextActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);//点击后的意图(跳转) 
        Notification notification = builder.build();
        manager.notify((int)System.currentTimeMillis(),notification);//更新通知
    }
}
