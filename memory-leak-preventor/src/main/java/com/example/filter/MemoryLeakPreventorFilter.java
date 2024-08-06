package com.example.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LifeCycle;
import org.apache.logging.log4j.core.LifeCycle2;
import org.apache.logging.log4j.web.Log4jWebSupport;
import org.apache.logging.log4j.web.WebLoggerContextUtils;

import javax.servlet.*;
import java.io.IOException;

public class MemoryLeakPreventorFilter implements Filter {
    private static ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(servletContext == null) {
            servletContext = filterConfig.getServletContext();
        }
        System.out.println("MemoryLeakPreventorFilter-> init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MemoryLeakPreventorFilter-> doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("WebLoggerContextUtils.getServletContext(): " + WebLoggerContextUtils.getServletContext());
        System.out.println("MemoryLeakPreventorFilter.servletContext: " + MemoryLeakPreventorFilter.servletContext);
        if(MemoryLeakPreventorFilter.servletContext != null) {
            Log4jWebSupport log4jWebSupport = WebLoggerContextUtils.getWebLifeCycle(MemoryLeakPreventorFilter.servletContext);
            log4jWebSupport.clearLoggerContext();
            System.out.println("clearLoggerContext() called: " + log4jWebSupport);
        }

        LifeCycle2 lifeCycle = (LifeCycle2) LogManager.getContext(false);
        System.out.println("isStarted: " + lifeCycle.isStarted());
        System.out.println("isStopped: " + lifeCycle.isStopped());
        lifeCycle.stop();
        System.out.println("isStarted: " + lifeCycle.isStarted());
        System.out.println("isStopped: " + lifeCycle.isStopped());
        System.out.println("MemoryLeakPreventorFilter-> destroy");
    }
}