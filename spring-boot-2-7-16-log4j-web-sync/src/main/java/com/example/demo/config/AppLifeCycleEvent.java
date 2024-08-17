package com.example.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PreDestroy;
import java.util.Objects;

@Component
public class AppLifeCycleEvent {
    private final WebApplicationContext webApplicationContext;

    public AppLifeCycleEvent(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @PreDestroy
    public void destroy() {
        ContextLoader contextLoader = new ContextLoader(this.webApplicationContext);
        contextLoader.closeWebApplicationContext(Objects.requireNonNull(this.webApplicationContext.getServletContext()));
    }
}
