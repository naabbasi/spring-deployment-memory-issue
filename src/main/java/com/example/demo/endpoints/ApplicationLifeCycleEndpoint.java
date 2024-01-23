package com.example.demo.endpoints;

import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;
import com.example.demo.utils.LogUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api")
public class ApplicationLifeCycleEndpoint {
    private final CustomerService customerService;
    private final LogUtils logUtils;
    private WebApplicationContext webApplicationContext;

    public ApplicationLifeCycleEndpoint(CustomerService customerService, LogUtils logUtils, WebApplicationContext webApplicationContext) {
        this.customerService = customerService;
        this.logUtils = logUtils;
        this.webApplicationContext = webApplicationContext;
    }

    @GetMapping(path = "/customer")
    public Iterable<Customer> customers() {
        logUtils.log("Fetching all customers");
        return this.customerService.all();
    }

    @GetMapping(path = "/customer/{customerId}")
    public ResponseEntity<?> customerById(@PathVariable("customerId") Long customerId) {
        logUtils.log("Fetching customer by id: {}", customerId);
        Customer customer = this.customerService.getCustomerById(customerId);
        if(customer != null ){
            return ResponseEntity.ok(customer);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/log")
    public String logMessage() {

        final Thread thread =  new Thread(() -> {
            while(true) {
                logUtils.log("Log statement to busy logger");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });        
        thread.start();
        
        return "KEEP LOGGING";
    }

    @GetMapping(path = "/destroy")
    public String destroy() {
        logUtils.log("User requested to destroy the context");
        ContextLoader contextLoader = new ContextLoader(this.webApplicationContext);
        contextLoader.closeWebApplicationContext(Objects.requireNonNull(this.webApplicationContext.getServletContext()));
        return "SUCCESS";
    }
}