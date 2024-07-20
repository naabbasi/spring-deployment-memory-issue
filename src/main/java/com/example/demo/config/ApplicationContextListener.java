package com.example.demo.config;

import com.example.demo.utils.LogUtils;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.web.WebLoggerContextUtils;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.TimeUnit;

@WebListener
public class ApplicationContextListener extends ContextLoaderListener /*implements ServletContextListener*/ {
    private final LogUtils logUtils = new LogUtils();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logUtils.log("ApplicationContextListener contextInitialized called");
        System.out.println("ApplicationContextListener contextInitialized called");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setInitParameter("log4j.stop.timeout", "0L");
        servletContext.setInitParameter("log4j.stop.timeout.timeunit", "MILLISECONDS");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logUtils.log("ApplicationContextListener contextDestroyed called");
        System.out.println("ApplicationContextListener contextDestroyed called");
        /*InternalThreadLocalMap.remove();
        InternalThreadLocalMap.destroy();
        FastThreadLocalThread.willCleanupFastThreadLocals(Thread.currentThread());*/
        LoggerContext webLoggerContext = WebLoggerContextUtils.getWebLoggerContext(sce.getServletContext());
        webLoggerContext.close();
        webLoggerContext.stop(0L, TimeUnit.MILLISECONDS);

        super.contextDestroyed(sce);

        //this.cleanThreadLocals();
    }
}
