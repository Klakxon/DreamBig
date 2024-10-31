package com.example.DreamBig.logging;

import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class CustomLayout extends LayoutBase<ILoggingEvent> {
    @Override
    public String doLayout(ILoggingEvent event) {
        return "[" + event.getLevel() + "] " + event.getLoggerName() + ": " + event.getFormattedMessage() + "\n";
    }
}

