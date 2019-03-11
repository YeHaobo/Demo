package minatest.example.com.minatest_android.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.databinding.ActivityMainBinding;
import minatest.example.com.minatest_android.viewModel.MainViewModel;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        binding.setMain(mainViewModel);
        initRecyclerView();//初始化好友列表
        mainViewModel.getFriendList();//获取好友数据
    }
    //初始化好友列表
    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.mainRecyclerView.setLayoutManager(linearLayoutManager);
    }


}
