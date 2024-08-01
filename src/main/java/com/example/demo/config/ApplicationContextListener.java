package com.example.demo.config;

import com.example.demo.utils.LogUtils;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener extends ContextLoaderListener /*implements ServletContextListener*/ {
    private final LogUtils logUtils = new LogUtils();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logUtils.log("ApplicationContextListener contextInitialized called");
        System.out.println("ApplicationContextListener contextInitialized called");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setInitParameter("log4j.stop.timeout", "0");
        servletContext.setInitParameter("log4j.stop.timeout.timeunit", "MILLISECONDS");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ApplicationContextListener contextDestroyed called");
        //super.contextDestroyed(sce);
        /*InternalThreadLocalMap.remove();
        InternalThreadLocalMap.destroy();
        FastThreadLocalThread.willCleanupFastThreadLocals(Thread.currentThread());*/
        /*LoggerContext webLoggerContext = WebLoggerContextUtils.getWebLoggerContext(sce.getServletContext());
        webLoggerContext.close();
        webLoggerContext.stop(0L, TimeUnit.MILLISECONDS);*/
    }
}
