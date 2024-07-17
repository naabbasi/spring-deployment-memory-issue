package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void log(String message, Object... params) {
        if(params != null && params.length > 0) {
            logger.info(message, params);
            return;
        }

        logger.info(message);
    }

    /*public void log(String message, Object... params) {
        if(params != null && params.length > 0) {
            System.out.printf("%s %s\n", message, Arrays.toString(params));
            return;
        }

        System.out.printf("%s \n", message);
    }*/
}