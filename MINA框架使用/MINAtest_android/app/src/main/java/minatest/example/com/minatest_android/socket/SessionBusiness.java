package minatest.example.com.minatest_android.socket;


import android.os.Looper;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.db.UserDateDo;
import minatest.example.com.minatest_android.entiti.ChatText;
import minatest.example.com.minatest_android.tool.MyTime;
import minatest.example.com.minatest_android.tool.MyToast;

/**
 * 聊天业务处理类
 */
public class SessionBusiness {
    public SessionBusiness(){}
    //登录
    public void signIn(IoSession session) {
        //上线与服务器建立连接之后，告诉服务器上线了
        ChatText chatText = new ChatText(0,0,0,UserDateDo.getUserToken(),
                UserDateDo.getUserToken(),0,MyTime.getTimeStamp(),"上线");
        session.write(ChatCodeFactory.myEncode(chatText));
    }
    //退出
    public void signOut(IoSession session) {
        ChatText chatText = new ChatText(2,0,0,UserDateDo.getUserToken(),
                UserDateDo.getUserToken(),0,MyTime.getTimeStamp(),"下线");
        session.write(ChatCodeFactory.myEncode(chatText));
    }
    //发送文本消息(P2P)
    public void sendTextMessage(IoSession session,String toUserToekn,String text){
        ChatText chatText = new ChatText(1,0,0,UserDateDo.getUserToken(),
                toUserToekn,0,MyTime.getTimeStamp(),text);
        session.write(ChatCodeFactory.myEncode(chatText));
    }
    //接收消息
    public void getTextMessage(Object message){
        ChatText chatText = ChatCodeFactory.myDecode(message);
        if(chatText == null) return;
        switch (chatText.getLogic()){
            //请求连接
            case 0 :getSignInMessage(chatText); break;
            //聊天消息
            case 1 :getMessage(chatText);break;
            //请求断开连接
            case 2 :getSignOutMessage(chatText);break;
            default:break;
        }
    }

    //收到登录性通知
    public void getSignInMessage(ChatText chatText){
        if(chatText.getIsRead() == 1){
            Looper.prepare();
            MyToast.showImgToast(chatText.getChatContext(),R.mipmap.ic_launcher_round);
            Looper.loop();
        }
    }

    //收到退出性通知
    public void getSignOutMessage(ChatText chatText){
        if(chatText.getIsRead() == 1){
            Looper.prepare();
            ChatService.connector.dispose();//关闭与服务器的连接
            MyToast.showImgToast(chatText.getChatContext(),R.mipmap.ic_launcher_round);
            Looper.loop();
        }
    }

    //收到普通消息
    public void getMessage(ChatText chatText){
        Looper.prepare();
        MyToast.showImgToast("新消息："+chatText.getChatContext(),R.mipmap.ic_launcher_round);
        Looper.loop();
    }

    //休眠等待
    public void sessionSleep(IoSession session,IdleStatus status){
        if(status==IdleStatus.READER_IDLE){
            session.close(true);
        }
    }

    //断开连接
    public void closeSession(){
        ChatService.connector.dispose();//关闭与服务器的连接
    }
}
