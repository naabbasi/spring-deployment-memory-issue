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
        this.cleanThreadLocals();
    }

    private void cleanThreadLocals() {
        try {
            // Get a reference to the thread locals table of the current thread
            Thread thread = Thread.currentThread();
            Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");
            threadLocalsField.setAccessible(true);
            Object threadLocalTable = threadLocalsField.get(thread);

            System.out.println("============================= \nThread Locals -> " + threadLocalTable + "\n=============================");

            // Get a reference to the array holding the thread local variables inside the
            // ThreadLocalMap of the current thread
            Class threadLocalMapClass = Class.forName("java.lang.ThreadLocal$ThreadLocalMap");
            Field tableField = threadLocalMapClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object table = tableField.get(threadLocalTable);

            System.out.println("============================= \n ThreadLocalMapClass -> " + threadLocalMapClass + "\n=============================");

            // The key to the ThreadLocalMap is a WeakReference object. The referent field of this object
            // is a reference to the actual ThreadLocal variable
            Field referentField = Reference.class.getDeclaredField("referent");
            referentField.setAccessible(true);

            for (int i = 0; i < Array.getLength(table); i++) {
                // Each entry in the table array of ThreadLocalMap is an Entry object
                // representing the thread local reference and its value
                Object entry = Array.get(table, i);
                if (entry != null) {
                    // Get a reference to the thread local object and remove it from the table
                    ThreadLocal threadLocal = (ThreadLocal)referentField.get(entry);
                    if(threadLocal.get().getClass().getClassLoader() == Thread.currentThread().getContextClassLoader()){
                        System.out.println("Web Application ThreadLocal");
                    } else {
                        System.out.println("Tomcat ThreadLocal");
                    }
                    System.out.println("============================= \n ThreadLocal -> " + threadLocal + "\n=============================");
                    threadLocal.remove();

                }
            }
        } catch(Exception e) {
            // We will tolerate an exception here and just log it
            throw new IllegalStateException(e);
        }
    }
}
