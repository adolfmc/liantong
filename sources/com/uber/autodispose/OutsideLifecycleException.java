package com.uber.autodispose;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class OutsideLifecycleException extends RuntimeException {
    public OutsideLifecycleException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        if (AutoDisposePlugins.getFillInOutsideLifecycleExceptionStacktraces()) {
            return super.fillInStackTrace();
        }
        return this;
    }
}
