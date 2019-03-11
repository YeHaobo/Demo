package minatest.example.com.minatest_android.viewModel;

import minatest.example.com.minatest_android.entiti.ChatText;
import minatest.example.com.minatest_android.view.ChatActivity;
import minatest.example.com.minatest_android.viewModel.repository.ChatRepository;

public class ChatviewModel implements ChatRepository {

    private ChatActivity chatActivity;

    public ChatviewModel(ChatActivity chatActivity){
        this.chatActivity = chatActivity;
    }

    @Override
    public void newFriendMessage(ChatText chatText) {

    }
}
