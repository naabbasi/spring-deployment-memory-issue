package com.example.demo.config;

import com.example.demo.util.LogUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

@Configuration
public class ApplicationHook implements ApplicationListener<ContextClosedEvent> {
    private final WebApplicationContext webApplicationContext;
    private final LogUtils logUtils;

    public ApplicationHook(WebApplicationContext webApplicationContext, LogUtils logUtils) {
        this.webApplicationContext = webApplicationContext;
        this.logUtils = logUtils;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        this.logUtils.log("ContextClosedEvent called");
        try {
            ContextLoader contextLoader = new ContextLoader();
            contextLoader.closeWebApplicationContext(Objects.requireNonNull(this.webApplicationContext.getServletContext()));
        } catch (Exception e) {
            this.logUtils.log("Exception occurred: {}", e.getMessage());
        }
    }
}
