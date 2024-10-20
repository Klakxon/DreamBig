package com.example.DreamBig.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MDCExample {
    private static final Logger logger = LoggerFactory.getLogger(MDCExample.class);

    public static void main(String[] args) {
        MDC.put("userId", "12345");
        logger.info("This is an informational message");
        MDC.clear();
    }
}

