package com.example.demo.config;

import com.example.demo.utils.LogUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

@Component
public class ApplicationLifeCycleEvent {
    private LogUtils logUtils;
    private GenericApplicationContext genericApplicationContext;

    public ApplicationLifeCycleEvent(LogUtils logUtils, GenericApplicationContext genericApplicationContext) {
        this.logUtils = logUtils;
        this.genericApplicationContext = genericApplicationContext;
    }

    @PostConstruct
    public void init() {
        this.logUtils.log("Application Life Cycle initiated");
    }

    @PreDestroy
    public void doClose() {
        Configuration configuration = LoggerContext.getContext().getConfiguration();
        for(Map.Entry<String, Appender> appendersEntry : configuration.getRootLogger().getAppenders().entrySet()) {
            appendersEntry.getValue().stop();
            System.out.println("Appender " + appendersEntry.getKey() + " is stopped");
        }

        for(Map.Entry<String, Appender> appendersEntry : configuration.getRootLogger().getAppenders().entrySet()) {
            System.out.println("Appender " + appendersEntry.getKey() + " status " + appendersEntry.getValue());
        }

        LogManager.shutdown();

        this.genericApplicationContext.stop();
        this.genericApplicationContext.close();
    }
}
