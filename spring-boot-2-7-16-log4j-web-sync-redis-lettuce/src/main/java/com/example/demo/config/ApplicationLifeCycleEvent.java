package com.example.demo.config;

import com.example.demo.utils.LogUtils;
import org.apache.logging.log4j.web.Log4jWebSupport;
import org.apache.logging.log4j.web.WebLoggerContextUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Objects;

@Component
public class ApplicationLifeCycleEvent implements ApplicationListener<ContextClosedEvent> {
    private final WebApplicationContext webApplicationContext;

    public ApplicationLifeCycleEvent(LogUtils logUtils, WebApplicationContext webApplicationContext, LettuceConnectionFactory lettuceConnectionFactory) {
        this.webApplicationContext = webApplicationContext;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        ServletContext servletContext = this.webApplicationContext.getServletContext();
        Log4jWebSupport log4jWebSupport = WebLoggerContextUtils.getWebLifeCycle(servletContext);
        if(log4jWebSupport != null) {
            log4jWebSupport.clearLoggerContext();
        }
    }
}
