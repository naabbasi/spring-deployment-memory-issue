package com.example.demo.config;

/*@Configuration
@EnableCaching
public class CacheConfig implements DisposableBean {
    private final LogUtils logUtils;
    private final ApplicationProperties applicationProperties;

    public CacheConfig(LogUtils logUtils, ApplicationProperties applicationProperties) {
        this.logUtils = logUtils;
        this.applicationProperties = applicationProperties;
    }

    *//*@Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }*//*

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        this.logUtils.log("Building redis cache manager");

        //Same cache name needs to define in @Cacheable(cacheNames = "ApplicationPropertiesCache", key = "'ApplicationPropertiesCache'") while creating a cache
        cacheConfigurations.put("ApplicationPropertiesCache", this.applicationPropertiesCacheConfiguration());

        return RedisCacheManager.builder(redisConnectionFactory)
                .withInitialCacheConfigurations(cacheConfigurations)
                .disableCreateOnMissingCache()
                .transactionAware()
                .build();
    }

    private RedisCacheConfiguration applicationPropertiesCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofDays(7))
                .prefixCacheNameWith(this.applicationProperties.getName() + "_" + this.applicationProperties.getVersion() + "_");
    }

    @Override
    public void destroy() throws Exception {
        this.logUtils.log("CacheConfig->destroy called");
        *//*RedisConnection redisConnection = this.redisConnectionFactory.getConnection();
        RedisConnectionUtils.releaseConnection(redisConnection, this.redisConnectionFactory);
        FastThreadLocal.removeAll();*//*
    }
}*/
