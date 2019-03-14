package com.yhb.www.chat.server;

import com.yhb.www.chat.logic.Dispatcher;
import com.yhb.www.codeFactory.MyCodeFactory;
import com.yhb.www.util.Utils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter {
    private Dispatcher dispatcher=new Dispatcher();

    public ServerHandler() {}

    public void messageReceived(IoSession session, Object message) throws Exception {
        dispatcher.patchDo(session,message);
    }

    public void messageSent(IoSession session, Object message) throws Exception {
        Utils.logger.info("发送消息：" + Utils.chatText2json(MyCodeFactory.myDecode(message)));
    }

    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        dispatcher.freeSession(session, status);
        Utils.logger.info("休眠中。。。");
    }

    public void sessionClosed(IoSession session) throws Exception {
        dispatcher.closeSession(session);
        Utils.logger.info("连接关闭!");
    }

    public void sessionOpened(IoSession session) throws Exception {
        Utils.logger.info("打开连接。。。");
    }

    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        Utils.logger.info("异常：" + cause.toString());
    }

    public void sessionCreated(IoSession session) throws Exception {
        Utils.logger.info("创建连接。。。");
    }
}