package com.example.DreamBig.logging;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class CustomConsoleAppender extends AppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent event) {
        String formattedMessage = new CustomLayout().doLayout(event);
        System.out.print(formattedMessage);
    }
}

