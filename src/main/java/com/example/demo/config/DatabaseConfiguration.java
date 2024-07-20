package com.example.demo.config;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseConfiguration {
    @Bean(destroyMethod = "")
    @Primary
    DataSource sysDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/memory_leak");
        driverManagerDataSource.setUsername("nabbasi");
        driverManagerDataSource.setPassword("Password1");
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        return driverManagerDataSource;
    }

    @Bean(name = "sysEntityManagerFactory", destroyMethod = "")
    @Primary
    LocalContainerEntityManagerFactoryBean sysLocalContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        Map<String, Object> propertiesMap = new HashMap<>();
        return entityManagerFactoryBuilder
                .dataSource(sysDataSource())
                .packages("com.example.demo.entities")
                .persistenceUnit("sysPersistentUnit")
                .properties(propertiesMap)
                .build();
    }

    @Bean(name = "sysTransactionManager", destroyMethod = "")
    @Primary
    PlatformTransactionManager platformTransactionManager(LocalContainerEntityManagerFactoryBean sysLocalContainerEntityManagerFactoryBean) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(sysLocalContainerEntityManagerFactoryBean.getObject());
        return jpaTransactionManager;
    }

    @Bean(destroyMethod = "")
    PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
