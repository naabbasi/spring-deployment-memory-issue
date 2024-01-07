package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class LogUtils {
    public void log(String message, Object... params) {
        if(params != null && params.length > 0) {
            System.out.printf(message, params);
            return;
        }

        System.out.println(message);
    }
}
