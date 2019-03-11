package minatest.example.com.minatest_android.socket;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import minatest.example.com.minatest_android.utils.ChatLog;

public class ChatHandle extends IoHandlerAdapter {
    private SessionBusiness sessionBusiness = new SessionBusiness();
    //在打开连接时调用
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        ChatLog.showRun("登录中");
        sessionBusiness.signIn(session);
    }
    //收到信息时调用
    @Override
    public void messageReceived(IoSession session, Object message)throws Exception {
        ChatLog.showRun("收到信息：");
        sessionBusiness.getTextMessage(message);

    }
    //在发送iossession .write(Object)编写的消息时调用
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        ChatLog.showRun("发送中：");
    }
    //当连接变为空闲时，使用相关的空闲状态调用。
    @Override
    public void sessionIdle(IoSession session, IdleStatus status)throws Exception {
        ChatLog.showRun("休眠等待中。。。");
        sessionBusiness.sessionSleep(session,status);
    }
    //在连接关闭时调用
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        ChatLog.showRun("已退出--连接关闭");
        sessionBusiness.closeSession();
    }
    //当用户IoHandler实现或MINA抛出异常时调用。
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)throws Exception {
        ChatLog.showException(cause.toString());
    }
    //在创建新连接时从I/O处理器线程调用
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        ChatLog.showRun("创建连接。。。");
    }
}
