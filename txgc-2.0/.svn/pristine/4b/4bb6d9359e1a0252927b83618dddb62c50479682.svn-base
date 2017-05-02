package com.yp.sys.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yp.sys.util.ManageCache;

public class SystemListener implements ServletContextListener {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SystemListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		try {
		//	SystemManager.getInstance();

			WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
			
			ManageCache manageCache =(ManageCache)app.getBean("manageCache");
			manageCache.loadRtuPopertyReferMap();
			//manageCache.loadAllCache();
			

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("System load faild!"+e.getMessage());
			System.out.println("System load faild!"+e.getMessage());
			try {
				throw new Exception("系统初始化失败！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
