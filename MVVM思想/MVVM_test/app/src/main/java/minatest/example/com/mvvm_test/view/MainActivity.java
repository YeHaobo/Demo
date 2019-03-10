package minatest.example.com.mvvm_test.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import minatest.example.com.mvvm_test.R;
import minatest.example.com.mvvm_test.databinding.ActivityMainBinding;
import minatest.example.com.mvvm_test.viewModel.LoginViewModel;
import minatest.example.com.mvvm_test.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding activityMainBinding;
    public MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        activityMainBinding.setMainModel(mainViewModel);
    }
}
