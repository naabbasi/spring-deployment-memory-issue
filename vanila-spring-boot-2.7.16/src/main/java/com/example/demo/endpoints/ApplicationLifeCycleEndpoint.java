package com.example.demo.endpoints;

import com.example.demo.utils.LogUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api")
public class ApplicationLifeCycleEndpoint {
    private final LogUtils logUtils;
    private WebApplicationContext webApplicationContext;

    public ApplicationLifeCycleEndpoint(LogUtils logUtils, WebApplicationContext webApplicationContext) {
        this.logUtils = logUtils;
        this.webApplicationContext = webApplicationContext;
    }

    @GetMapping(path = "/log/{numbers}")
    public String logMessage(@PathVariable("numbers") Integer numbers) throws InterruptedException {

        long start = System.currentTimeMillis();
        final int threads = numbers;
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<String>> futures = new ArrayList<>();
        for(int i = 0 ; i < threads ; i++) {
            int finalI = i;
            final Callable<String> futureTask = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    logUtils.log("Message " + finalI);
                    return "Message " + finalI;
                }
            };
            futures.add(futureTask);
        }

        executorService.invokeAll(futures);
        executorService.shutdown();
        long end = System.currentTimeMillis();
        String message = String.format("Took sec %d, ms %d", TimeUnit.MILLISECONDS.toSeconds((end - start)) , end - start);
        logUtils.log(message);

        return message;
    }

    @GetMapping(path = "/destroy")
    public String destroy() {
        logUtils.log("User requested to destroy the context");
        ContextLoader contextLoader = new ContextLoader(this.webApplicationContext);
        contextLoader.closeWebApplicationContext(Objects.requireNonNull(this.webApplicationContext.getServletContext()));
        return "SUCCESS";
    }
}