package com.google.zxing;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class ChecksumException extends ReaderException {
    private static final ChecksumException INSTANCE;

    static {
        ChecksumException checksumException = new ChecksumException();
        INSTANCE = checksumException;
        checksumException.setStackTrace(NO_TRACE);
    }

    private ChecksumException() {
    }

    private ChecksumException(Throwable th) {
        super(th);
    }

    public static ChecksumException getChecksumInstance() {
        return isStackTrace ? new ChecksumException() : INSTANCE;
    }

    public static ChecksumException getChecksumInstance(Throwable th) {
        return isStackTrace ? new ChecksumException(th) : INSTANCE;
    }
}
