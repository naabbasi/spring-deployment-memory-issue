package com.example.demo.cache.bo;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

public class ApplicationPropertiesBo implements Serializable {
    private String key;
    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationPropertiesBo)) return false;
        ApplicationPropertiesBo that = (ApplicationPropertiesBo) o;
        return Objects.equals(getKey(), that.getKey()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApplicationPropertiesBo.class.getSimpleName() + "[", "]")
                .add("key='" + key + "'")
                .add("value=" + value)
                .toString();
    }
}
