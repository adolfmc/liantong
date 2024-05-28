package org.apache.commons.logging;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.commons.logging.Log */
/* loaded from: E:\452516_dexfile_execute.dex */
public interface InterfaceC13042Log {
    void debug(Object obj);

    void debug(Object obj, Throwable th);

    void error(Object obj);

    void error(Object obj, Throwable th);

    void fatal(Object obj);

    void fatal(Object obj, Throwable th);

    void info(Object obj);

    void info(Object obj, Throwable th);

    boolean isDebugEnabled();

    boolean isErrorEnabled();

    boolean isFatalEnabled();

    boolean isInfoEnabled();

    boolean isTraceEnabled();

    boolean isWarnEnabled();

    void trace(Object obj);

    void trace(Object obj, Throwable th);

    void warn(Object obj);

    void warn(Object obj, Throwable th);
}
