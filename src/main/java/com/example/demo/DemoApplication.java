package com.example.demo;

import com.example.demo.cache.ApplicationPropertiesCache;
import com.example.demo.config.ApplicationProperties;
import com.example.demo.services.CustomerService;
import com.example.demo.utils.LogUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {
	@Bean
	CommandLineRunner init(ApplicationProperties applicationProperties, LogUtils logUtils,
						   CustomerService customerService, ApplicationPropertiesCache applicationPropertiesCache) {
		return args -> {
			logUtils.log("Application Name: {}", applicationProperties.getName());
			logUtils.log("Application Version: {}", applicationProperties.getVersion());
			logUtils.log("Application Classification: {}", applicationProperties.getAbsherClassification());
			customerService.all();
			customerService.getCustomerById(1L);
			//IndexedQueueSizeUtil
			//When using the cache along with/without redis configuration, while stopping/undeploying the application, threads are being unloaded
			/*Map<String, ApplicationPropertiesBo> applicationPropertiesCacheMap = applicationPropertiesCache.applicationPropertiesCache();
			for(Map.Entry<String, ApplicationPropertiesBo> applicationPropertyEntry : applicationPropertiesCacheMap.entrySet()){
				logUtils.log("Key: {}, Value: {}", applicationPropertyEntry.getKey(), applicationPropertyEntry.getValue());
			}*/
		};
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		super.setRegisterErrorPageFilter(false);
		return springApplicationBuilder.sources(DemoApplication.class);//.web(WebApplicationType.NONE);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
