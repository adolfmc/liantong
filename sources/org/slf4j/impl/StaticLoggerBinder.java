package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StaticLoggerBinder implements LoggerFactoryBinder {
    private final ILoggerFactory loggerFactory = new AndroidLoggerFactory();
    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
    public static String REQUESTED_API_VERSION = "1.6.99";
    private static final String loggerFactoryClassStr = AndroidLoggerFactory.class.getName();

    public static StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    private StaticLoggerBinder() {
    }

    @Override // org.slf4j.spi.LoggerFactoryBinder
    public ILoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    @Override // org.slf4j.spi.LoggerFactoryBinder
    public String getLoggerFactoryClassStr() {
        return loggerFactoryClassStr;
    }
}
