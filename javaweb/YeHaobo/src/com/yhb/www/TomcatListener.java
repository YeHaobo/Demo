package com.yhb.www;


import com.yhb.www.chat.server.Server;
import org.apache.log4j.BasicConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听Tomcat启动与关闭
 */

public class TomcatListener implements ServletContextListener {

	private Server minaService;

	public void contextDestroyed(ServletContextEvent arg0) {
		minaService.stopService();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		BasicConfigurator.configure(); //自动快速地使用缺省Log4j环境。打印日志文件
		minaService = new Server();
		minaService.startService();
	}

}
