package com.example.demo.config;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.Objects;

@Component
public class AppLifeCycleEvent {
    private final WebApplicationContext webApplicationContext;
    private final LettuceConnectionFactory lettuceConnectionFactory;

    public AppLifeCycleEvent(WebApplicationContext webApplicationContext, LettuceConnectionFactory lettuceConnectionFactory) {
        this.webApplicationContext = webApplicationContext;
        this.lettuceConnectionFactory = lettuceConnectionFactory;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE + 10)
    @EventListener({ContextStoppedEvent.class, ContextClosedEvent.class})
    public void destroy() {
        ContextLoader contextLoaderListener = new ContextLoader(Objects.requireNonNull(this.webApplicationContext));
        contextLoaderListener.closeWebApplicationContext(Objects.requireNonNull(this.webApplicationContext.getServletContext()));

        this.lettuceConnectionFactory.getRequiredNativeClient().shutdown(Duration.ZERO, Duration.ZERO);
        /*RedisConnection redisConnection = this.lettuceConnectionFactory.getConnection();
        RedisConnectionUtils.unbindConnection(this.lettuceConnectionFactory);*/
        System.out.println("RedisConnectionUtils.unbindConnection called");
        //RedisConnectionUtils.releaseConnection(redisConnection, this.lettuceConnectionFactory);
        //redisConnection.close();
    }
}
