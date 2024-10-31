package com.example.DreamBig.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class MarkerExample {
    private static final Logger logger = LoggerFactory.getLogger(MarkerExample.class);

    public static void main(String[] args) {
        Marker securityMarker = MarkerFactory.getMarker("SECURITY");
        logger.info(securityMarker, "This is a security log message");
    }
}

