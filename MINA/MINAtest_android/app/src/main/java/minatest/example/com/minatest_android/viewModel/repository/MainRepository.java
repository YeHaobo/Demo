package minatest.example.com.minatest_android.viewModel.repository;

import java.util.List;

import minatest.example.com.minatest_android.entiti.Friend;

/**
 * 通过改接口将数据从model层返回ViewModel层
 *
 */
public interface MainRepository extends BaseRepository{

    void refreshFriend(List<Friend> friendList);
}
