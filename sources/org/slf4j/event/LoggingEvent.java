package org.slf4j.event;

import org.slf4j.Marker;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface LoggingEvent {
    Object[] getArgumentArray();

    Level getLevel();

    String getLoggerName();

    Marker getMarker();

    String getMessage();

    String getThreadName();

    Throwable getThrowable();

    long getTimeStamp();
}
