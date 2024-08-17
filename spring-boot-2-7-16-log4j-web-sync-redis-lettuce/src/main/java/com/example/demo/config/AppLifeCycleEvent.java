package com.example.demo.config;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PreDestroy;
import java.util.Objects;

@Component
public class AppLifeCycleEvent {
    private final WebApplicationContext webApplicationContext;
    private final LettuceConnectionFactory lettuceConnectionFactory;

    public AppLifeCycleEvent(WebApplicationContext webApplicationContext, LettuceConnectionFactory lettuceConnectionFactory) {
        this.webApplicationContext = webApplicationContext;
        this.lettuceConnectionFactory = lettuceConnectionFactory;
    }

    @PreDestroy
    public void destroy() {
        ContextLoader contextLoaderListener = new ContextLoader(Objects.requireNonNull(this.webApplicationContext));
        contextLoaderListener.closeWebApplicationContext(Objects.requireNonNull(this.webApplicationContext.getServletContext()));

        RedisConnection redisConnection = this.lettuceConnectionFactory.getConnection();
        RedisConnectionUtils.releaseConnection(redisConnection, this.lettuceConnectionFactory);
        //redisConnection.close();
    }
}
