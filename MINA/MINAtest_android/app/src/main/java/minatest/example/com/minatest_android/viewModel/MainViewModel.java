package minatest.example.com.minatest_android.viewModel;


import android.text.Editable;
import android.text.TextWatcher;

import com.android.databinding.library.baseAdapters.BR;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.adapter.MainAdapter;
import minatest.example.com.minatest_android.entiti.Friend;
import minatest.example.com.minatest_android.model.MainModel;
import minatest.example.com.minatest_android.db.UserDateDo;
import minatest.example.com.minatest_android.tool.MyToast;
import minatest.example.com.minatest_android.view.MainActivity;
import minatest.example.com.minatest_android.viewModel.repository.MainRepository;

public class MainViewModel implements MainRepository{

    private MainModel mainModel;
    private MainActivity activity;

    public MainViewModel(MainActivity activity){
        this.activity = activity;
        this.mainModel = new MainModel(activity,this);
    }

    /**
     * 获取好友数据
     */
    public void getFriendList(){
        mainModel.getFriendList(UserDateDo.getUserUid(),"1");
    }

    /**
     * 获取好友信息回调
     */
    @Override
    public void refreshFriend(List<Friend> friendList) {
        if(friendList == null){
            MyToast.showImgToast("好友信息获取失败",R.mipmap.ic_launcher_round);
        }else{
            Map<Integer,Integer> map = new HashMap<>();
            map.put(R.layout.item_main_chat,BR.friendList);
            activity.binding.mainRecyclerView.setAdapter(new MainAdapter(friendList, map,activity));
        }
    }

    /**
     * 获取好友的账号
     */
    public String friendName = "";
    public TextWatcher getFriendName(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                friendName = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

}
