package com.example.demo.endpoints;

import com.example.demo.cache.ApplicationPropertiesCache;
import com.example.demo.cache.bo.ApplicationPropertiesBo;
import com.example.demo.utils.LogUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api")
public class ApplicationLifeCycleEndpoint {
    private final LogUtils logUtils;
    private ApplicationPropertiesCache applicationPropertiesCache;

    public ApplicationLifeCycleEndpoint(LogUtils logUtils, ApplicationPropertiesCache applicationPropertiesCache) {
        this.logUtils = logUtils;
        this.applicationPropertiesCache = applicationPropertiesCache;
    }

    @GetMapping(path = "/log/sequential/{numbers}")
    public String logSequentialMessages(@PathVariable("numbers") Integer numbers) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < numbers; i++) {
            logUtils.log("Message " + i);
        }

        long end = System.currentTimeMillis();
        String message = String.format("Took sec %d, ms %d", TimeUnit.MILLISECONDS.toSeconds((end - start)), end - start);
        logUtils.log(message);
        return message;
    }

    @GetMapping(path = "/log/parallel/{numbers}/{logEvent}")
    public String logParallelMessages(@PathVariable("numbers") Integer numbers, @PathVariable("logEvent") String logEvent) throws InterruptedException {

        long start = System.currentTimeMillis();
        final int threads = numbers;
        List<Callable<String>> futures = new ArrayList<>();
        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threads; i++) {
            int finalI = i;
            final Callable<String> futureTask = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    if ("Y".equalsIgnoreCase(logEvent)) {
                        logUtils.log("Message " + finalI);
                    }
                    return "Message " + finalI;
                }
            };
            futures.add(futureTask);
        }

        executorService.invokeAll(futures);
        executorService.shutdown();

        long end = System.currentTimeMillis();
        String message = String.format("Took sec %d, ms %d", TimeUnit.MILLISECONDS.toSeconds((end - start)), end - start);
        logUtils.log(message);

        return message;
    }

    @GetMapping(path = "/cache")
    public Map<String, ApplicationPropertiesBo> cache() {
        logUtils.log("Get application properties from redis cache");
        Map<String, ApplicationPropertiesBo> applicationPropertiesBoCacheMap = this.applicationPropertiesCache.applicationPropertiesCache();

        for(Map.Entry<String, ApplicationPropertiesBo> applicationPropertiesBoMapEntry : applicationPropertiesBoCacheMap.entrySet()) {
            this.logUtils.log("Key: {}", applicationPropertiesBoMapEntry.getKey());
            this.logUtils.log("Value: {}", applicationPropertiesBoMapEntry.getValue());
        }

        return applicationPropertiesBoCacheMap;
    }
}