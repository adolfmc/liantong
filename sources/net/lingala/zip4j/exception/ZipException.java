package net.lingala.zip4j.exception;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ZipException extends IOException {
    private static final long serialVersionUID = 1;
    private Type type;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum Type {
        WRONG_PASSWORD,
        TASK_CANCELLED_EXCEPTION,
        CHECKSUM_MISMATCH,
        UNKNOWN_COMPRESSION_METHOD,
        FILE_NOT_FOUND,
        UNKNOWN
    }

    public ZipException(String str) {
        super(str);
        this.type = Type.UNKNOWN;
    }

    public ZipException(Exception exc) {
        super(exc);
        this.type = Type.UNKNOWN;
    }

    public ZipException(String str, Exception exc) {
        super(str, exc);
        this.type = Type.UNKNOWN;
    }

    public ZipException(String str, Type type) {
        super(str);
        this.type = Type.UNKNOWN;
        this.type = type;
    }

    public ZipException(String str, Throwable th, Type type) {
        super(str, th);
        this.type = Type.UNKNOWN;
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }
}
