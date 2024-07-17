package com.example.demo.config;

import com.example.demo.utils.LogUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class ApplicationHook implements ApplicationListener<ContextClosedEvent> {
    private final GenericApplicationContext genericApplicationContext;
    private final LettuceConnectionFactory lettuceConnectionFactory;
    private final LogUtils logUtils;

    public ApplicationHook(WebApplicationContext webApplicationContext, GenericApplicationContext genericApplicationContext, AbstractApplicationContext abstractApplicationContext,
                           LettuceConnectionFactory lettuceConnectionFactory, LogUtils logUtils) {
        this.genericApplicationContext = genericApplicationContext;
        this.lettuceConnectionFactory = lettuceConnectionFactory;
        this.logUtils = logUtils;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        this.logUtils.log("ContextClosedEvent called");

        String beanDefinitionName = "";
        try {

            /*DefaultClientResources clientResources = (DefaultClientResources) this.lettuceConnectionFactory.getClientResources();
            clientResources.shutdown(0L, 0L, TimeUnit.MILLISECONDS).getNow();
            this.logUtils.log("ClientResources shutdown called");
            clientResources.eventLoopGroupProvider().shutdown(0L, 0L, TimeUnit.MILLISECONDS).getNow();
            this.logUtils.log("eventLoopGroupProvider shutdown called");
            this.lettuceConnectionFactory.destroy();*/
            this.genericApplicationContext.close();
            //clientResources.eventExecutorGroup().shutdownGracefully();
            //this.logUtils.log("eventExecutorGroup shutdown called");

            /*DefaultListableBeanFactory defaultListableBeanFactory = this.genericApplicationContext.getDefaultListableBeanFactory();
            for(String getBeanDefinitionName : this.genericApplicationContext.getBeanDefinitionNames()){
                beanDefinitionName = getBeanDefinitionName;
                if(defaultListableBeanFactory.isSingleton(beanDefinitionName) && !defaultListableBeanFactory.isSingletonCurrentlyInCreation(beanDefinitionName)) {
                    defaultListableBeanFactory.destroySingleton(beanDefinitionName);
                } else if(defaultListableBeanFactory.isPrototype(beanDefinitionName)) {
                    defaultListableBeanFactory.destroyBean(beanDefinitionName);
                } else {
                    defaultListableBeanFactory.destroyScopedBean(beanDefinitionName);
                }

                defaultListableBeanFactory.clearMetadataCache();
                //this.logUtils.log("Bean count: {}", this.genericApplicationContext.getBeanDefinitionCount());
            }*/

            this.logUtils.log("defaultListableBeanFactory destroySingletons and clearMetadataCache called");
        } catch (Exception e) {
            this.logUtils.log("BeanDefinitionName: {} ,Exception occurred: {}", beanDefinitionName, e.getMessage());
        }
    }
}
