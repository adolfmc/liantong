package org.apache.commons.lang3.exception;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class CloneFailedException extends RuntimeException {
    private static final long serialVersionUID = 20091223;

    public CloneFailedException(String str) {
        super(str);
    }

    public CloneFailedException(Throwable th) {
        super(th);
    }

    public CloneFailedException(String str, Throwable th) {
        super(str, th);
    }
}
