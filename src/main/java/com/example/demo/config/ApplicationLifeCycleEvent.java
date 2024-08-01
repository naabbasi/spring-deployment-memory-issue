package com.example.demo.config;

import com.example.demo.utils.LogUtils;
import org.apache.logging.log4j.core.LifeCycle;
import org.apache.logging.log4j.web.Log4jWebSupport;
import org.apache.logging.log4j.web.WebLoggerContextUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

@Component
public class ApplicationLifeCycleEvent implements ApplicationListener<ContextClosedEvent> {
    private final WebApplicationContext webApplicationContext;

    public ApplicationLifeCycleEvent(LogUtils logUtils, WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        /*Configuration configuration = LoggerContext.getContext().getConfiguration();
        for(Map.Entry<String, Appender> appendersEntry : configuration.getRootLogger().getAppenders().entrySet()) {
            appendersEntry.getValue().stop();
            System.out.println("Appender " + appendersEntry.getKey() + " is stopped");
        }

        for(Map.Entry<String, Appender> appendersEntry : configuration.getRootLogger().getAppenders().entrySet()) {
            System.out.println("Appender " + appendersEntry.getKey() + " status " + appendersEntry.getValue());
        }*/

        try {
            Log4jWebSupport log4jWebSupport = WebLoggerContextUtils.getWebLifeCycle(Objects.requireNonNull(this.webApplicationContext.getServletContext()));
            log4jWebSupport.clearLoggerContext();
            LifeCycle lifeCycle = (LifeCycle) log4jWebSupport;
            lifeCycle.stop();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
