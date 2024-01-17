package com.example.demo.config;

import com.example.demo.utils.LogUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContext implements ServletContextListener {
    private final LogUtils logUtils = new LogUtils();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logUtils.log("ApplicationContext contextInitialized called");
        ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logUtils.log("ApplicationContext contextDestroyed called");
        ServletContextListener.super.contextDestroyed(sce);
        /*WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        FastThreadLocal.removeAll();
        FastThreadLocal.destroy();*/
    }
}
