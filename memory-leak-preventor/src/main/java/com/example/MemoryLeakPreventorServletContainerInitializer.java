package com.example;

import com.example.filter.MemoryLeakPreventorFilter;

import javax.servlet.*;
import java.util.EnumSet;
import java.util.Set;

public class MemoryLeakPreventorServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("MemoryLeakPreventorServletContainerInitializer -> onStartup");
        FilterRegistration.Dynamic filter = servletContext.addFilter("memoryLeakPreventorFilter", MemoryLeakPreventorFilter.class);
        if (filter == null) {
            System.out.println("filter is null");
            return;
        }

        filter.setAsyncSupported(true);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, new String[]{"/*"});
    }
}
