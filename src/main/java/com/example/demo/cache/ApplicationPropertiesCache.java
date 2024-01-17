package com.example.demo.cache;

import com.example.demo.cache.bo.ApplicationPropertiesBo;
import com.example.demo.utils.LogUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationPropertiesCache {
    private final LogUtils logUtils;

    public ApplicationPropertiesCache(LogUtils logUtils) {
        this.logUtils = logUtils;
    }

    @Cacheable(cacheNames = "ApplicationPropertiesCache", key = "'ApplicationPropertiesCache'")
    public Map<String, ApplicationPropertiesBo> applicationPropertiesCache() {
        Map<String, ApplicationPropertiesBo> applicationPropertiesBoMap = new HashMap<>();
        ApplicationPropertiesBo applicationPropertiesBo = new ApplicationPropertiesBo();
        applicationPropertiesBo.setKey("Key-1");
        applicationPropertiesBo.setValue("Value-1");

        applicationPropertiesBoMap.put("key-1", applicationPropertiesBo);

        this.logUtils.log("Application Business Properties cache created");
        return applicationPropertiesBoMap;
    }
}
