package com.example.demo.config;

import com.example.demo.utils.LogUtils;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class ApplicationHook implements ApplicationListener<ContextClosedEvent> {
    private final WebApplicationContext webApplicationContext;
    private final GenericApplicationContext genericApplicationContext;
    private final LogUtils logUtils;

    public ApplicationHook(WebApplicationContext webApplicationContext, GenericApplicationContext genericApplicationContext, LogUtils logUtils) {
        this.webApplicationContext = webApplicationContext;
        this.genericApplicationContext = genericApplicationContext;
        this.logUtils = logUtils;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        this.logUtils.log("ContextClosedEvent called");
        try {
            /*ContextLoader contextLoader = new ContextLoader();
            contextLoader.closeWebApplicationContext(Objects.requireNonNull(this.webApplicationContext.getServletContext()));*/

            //Both are working fine
            this.genericApplicationContext.close();

            LoggerContext loggerContext = LoggerContext.getContext();
            org.apache.logging.log4j.core.config.Configuration configuration = loggerContext.getConfiguration();

            for (Map.Entry<String, Appender> mapEntry : configuration.getAppenders().entrySet()){
                System.out.println("Appender name: " + mapEntry.getKey());
                System.out.println("Appender Status: " + mapEntry.getValue().getState().name());
            }
        } catch (Exception e) {
            this.logUtils.log("Exception occurred: {}", e.getMessage());
        }
    }
}
