package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.StringJoiner;

@Component
@ConfigurationProperties(prefix = "application")
@Validated
public class ApplicationProperties {
    private Short absherClassification;
    private String name;
    private String version;

    public Short getAbsherClassification() {
        return absherClassification;
    }

    public void setAbsherClassification(Short absherClassification) {
        this.absherClassification = absherClassification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApplicationProperties.class.getSimpleName() + "[", "]")
                .add("absherClassification=" + absherClassification)
                .add("name='" + name + "'")
                .add("version='" + version + "'")
                .toString();
    }
}
