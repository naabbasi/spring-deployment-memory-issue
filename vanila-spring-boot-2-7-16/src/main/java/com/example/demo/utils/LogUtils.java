package com.example.demo.utils;

import org.springframework.stereotype.Component;

@Component
public class LogUtils {
    public void log(String message, Object... params) {
        if(params != null && params.length > 0) {
            System.out.printf(message + "\n", params);
            return;
        }

        System.out.println(message);
    }

    /*public void log(String message, Object... params) {
        if(params != null && params.length > 0) {
            System.out.printf("%s %s\n", message, Arrays.toString(params));
            return;
        }

        System.out.printf("%s \n", message);
    }*/
}