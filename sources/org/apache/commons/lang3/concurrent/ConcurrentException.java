package org.apache.commons.lang3.concurrent;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ConcurrentException extends Exception {
    private static final long serialVersionUID = 6622707671812226130L;

    protected ConcurrentException() {
    }

    public ConcurrentException(Throwable th) {
        super(ConcurrentUtils.checkedException(th));
    }

    public ConcurrentException(String str, Throwable th) {
        super(str, ConcurrentUtils.checkedException(th));
    }
}
