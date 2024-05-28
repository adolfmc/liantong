package org.apache.commons.lang3.concurrent;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ConcurrentRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -6582182735562919670L;

    protected ConcurrentRuntimeException() {
    }

    public ConcurrentRuntimeException(Throwable th) {
        super(ConcurrentUtils.checkedException(th));
    }

    public ConcurrentRuntimeException(String str, Throwable th) {
        super(str, ConcurrentUtils.checkedException(th));
    }
}
