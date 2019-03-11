package minatest.example.com.minatest_android.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.databinding.ActivityChatBinding;
import minatest.example.com.minatest_android.viewModel.ChatviewModel;
import minatest.example.com.minatest_android.viewModel.LoginViewModel;

/**
 * 单聊界面
 */
public class ChatActivity extends AppCompatActivity {
    public ActivityChatBinding activityChatBinding;
    private ChatviewModel chatviewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatBinding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        chatviewModel = new ChatviewModel(this);
        activityChatBinding.setChat(chatviewModel);
    }
}
