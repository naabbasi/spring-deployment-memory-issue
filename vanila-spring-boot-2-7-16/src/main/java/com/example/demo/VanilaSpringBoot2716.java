package com.example.demo;

import com.example.demo.config.ApplicationProperties;
import com.example.demo.utils.LogUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.demo"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {LogUtils.class}))
public class VanilaSpringBoot2716 extends SpringBootServletInitializer {
    @Bean
    CommandLineRunner init(ApplicationProperties applicationProperties, LogUtils logUtils) {
        return args -> {
            logUtils.log("Application Name: {}", applicationProperties.getName());
            logUtils.log("Application Version: {}", applicationProperties.getVersion());
            logUtils.log("Application Classification: {}", applicationProperties.getAbsherClassification());
        };
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
        super.setRegisterErrorPageFilter(false);
        return springApplicationBuilder.sources(VanilaSpringBoot2716.class);//.web(WebApplicationType.NONE);
    }

    public static void main(String[] args) {
        SpringApplication.run(VanilaSpringBoot2716.class, args);
    }

}
