package com.example.demo.config;

import com.example.demo.utils.LogUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
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
        ContextLoader contextLoaderListener = new ContextLoader(Objects.requireNonNull(this.webApplicationContext));
        contextLoaderListener.closeWebApplicationContext(Objects.requireNonNull(this.webApplicationContext.getServletContext()));
    }
}
