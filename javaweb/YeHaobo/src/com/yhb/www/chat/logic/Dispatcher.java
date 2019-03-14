package com.yhb.www.chat.logic;


import com.yhb.www.chat.session.UserSessionMap;
import com.yhb.www.codeFactory.MyCodeFactory;
import com.yhb.www.entity.ChatText;
import com.yhb.www.entity.User;
import com.yhb.www.util.Utils;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * 接收信息，事件分发器
 */
public class Dispatcher {

    private final UserSessionMap userSessionMap = UserSessionMap.getInstance();

    //转码并判断状态
    public void patchDo(IoSession session, Object message){
        ChatText chatText = MyCodeFactory.myDecode(message);
        if(chatText == null)return;
        switch (chatText.getLogic()){
            //请求连接
            case 0 :connectSession(session,chatText); break;
            //聊天消息
            case 1 :messagePatch(chatText);break;
            //请求断开连接
            case 2 :shortSession(session,chatText);break;
            default:break;
        }
    }
    //连接操作
    public void connectSession(IoSession session, ChatText chatText){
        String token = chatText.getFromUserToken();
        IoSession oldSession = userSessionMap.get(token);
        if(oldSession != null){
            ChatText chatText1 = new ChatText();
            chatText1.setLogic(2);
            chatText1.setIsRead(1);
            chatText1.setChatContext("账号在异地登录，您被迫下线");
            oldSession.close(true);
            oldSession.write(MyCodeFactory.myEncode(chatText1));
        }
        userSessionMap.put(token,session);
        chatText.setIsRead(1);
        chatText.setChatContext("连接成功");
        session.write(MyCodeFactory.myEncode(chatText));
    }

    //断开连接操作
    public void shortSession(IoSession session, ChatText chatText){
        chatText.setIsRead(1);
        chatText.setChatContext("下线成功！");
        session.close(true);
        userSessionMap.remove(chatText.getFromUserToken());
        session.write(MyCodeFactory.myEncode(chatText));
    }

    //普通消息分发
    public void messagePatch(ChatText chatText){
        switch (chatText.getChatEnvirment()){
            case 0://单聊
                P2PSendMessage(chatText);break;
            case 1://群发
                break;
            default: break;
        }
    }

    //单聊信息发送
    public void P2PSendMessage(ChatText chatText){
        IoSession session = userSessionMap.get(chatText.getToUserToken());
        if(session == null)return;
        session.write(MyCodeFactory.myEncode(chatText));
    }

    //线程休眠操作
    public void freeSession(IoSession session, IdleStatus status) {
        if (status == IdleStatus.READER_IDLE) {
            session.close(true);
        }
    }

    //断线操作
    public void closeSession(IoSession session) {
        Iterator var3 = this.userSessionMap.keySet().iterator();
        while(var3.hasNext()) {
            String key = (String)var3.next();
            if (this.userSessionMap.get(key) == session) {
                this.userSessionMap.remove(key);
                Utils.logger.info("已释放Session资源");
            }
        }
    }
}
