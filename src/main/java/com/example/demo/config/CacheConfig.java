package com.example.demo.config;

import com.example.demo.utils.LogUtils;
import io.netty.util.concurrent.FastThreadLocal;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfig implements DisposableBean {
    private RedisConnectionFactory redisConnectionFactory;
    private LogUtils logUtils;

    public CacheConfig(RedisConnectionFactory redisConnectionFactory, LogUtils logUtils) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.logUtils = logUtils;
    }

    @Bean("redisCacheManager")
    @Primary
    public RedisCacheManager redisCacheManager() {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        this.logUtils.log("Building redis cache manager");
        return RedisCacheManager.builder(this.redisConnectionFactory)
                .withInitialCacheConfigurations(cacheConfigurations)
                .disableCreateOnMissingCache()
                .transactionAware()
                .build();
    }

    @Override
    public void destroy() throws Exception {
        this.logUtils.log("CacheConfig->destroy called");
        RedisConnection redisConnection = this.redisConnectionFactory.getConnection();
        RedisConnectionUtils.releaseConnection(redisConnection, this.redisConnectionFactory);
        FastThreadLocal.removeAll();
    }
}
