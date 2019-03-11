package minatest.example.com.minatest_android.viewModel.repository;

import minatest.example.com.minatest_android.entiti.ChatText;

public interface ChatRepository {
    //接收好友发送的信息
    void newFriendMessage(ChatText chatText);
}
