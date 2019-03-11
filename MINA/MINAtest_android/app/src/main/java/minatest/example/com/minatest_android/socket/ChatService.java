package minatest.example.com.minatest_android.socket;

import java.net.InetSocketAddress;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import minatest.example.com.minatest_android.socket.ChatCodeFactory;
import minatest.example.com.minatest_android.socket.ChatHandle;

/**
 *消息服务
 */
public class ChatService extends Service {
	public static IoSession session;//用于接收和发送信息
	public static Intent intent;//服务
	public static IoConnector connector;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				connector = new NioSocketConnector();
				connector.getFilterChain().addLast("textProtocol",ChatCodeFactory.getCodeFactory());
				connector.getSessionConfig().setReadBufferSize(1024);
				connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
				connector.setHandler(new ChatHandle());//这里是异步操作 连接后立即返回
                ConnectFuture future = connector.connect(new InetSocketAddress("192.168.0.107", 7080));
                future.awaitUninterruptibly();// 等待连接创建完成
				session = future.getSession();
				session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
                connector.dispose();
			}
		}).start();
		super.onCreate();
	}

}
