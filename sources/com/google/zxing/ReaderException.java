package com.google.zxing;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class ReaderException extends Exception {
    protected static final StackTraceElement[] NO_TRACE;
    protected static final boolean isStackTrace;

    static {
        isStackTrace = System.getProperty("surefire.test.class.path") != null;
        NO_TRACE = new StackTraceElement[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReaderException() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReaderException(Throwable th) {
        super(th);
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        return null;
    }
}
