package com.yhb.www.chat.server;

import com.yhb.www.codeFactory.MyCodeFactory;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server implements Runnable {
    private final Logger logger = Logger.getLogger(Server.class);
    public Thread start = new Thread(this);

    public Server() {
    }

    public void startService() {
        this.logger.info("MINA启动");
        this.start.start();
    }

    public void stopService() {
        this.logger.info("MINA停止");
        this.start.interrupt();
    }

    public void run() {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("codec", MyCodeFactory.getCodeFactory());
        acceptor.setHandler(new ServerHandler());
        acceptor.getSessionConfig().setReadBufferSize(1024);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        try {
            acceptor.bind(new InetSocketAddress(7080));
        } catch (IOException var3) {
            this.logger.error("通讯服务启动失败！");
            return;
        }
        this.logger.info("通讯服务已启动。。。");
    }
}
