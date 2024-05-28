package io.objectbox.exception;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DbException extends RuntimeException {
    private final int errorCode;

    public DbException(String str) {
        super(str);
        this.errorCode = 0;
    }

    public DbException(String str, Throwable th) {
        super(str, th);
        this.errorCode = 0;
    }

    public DbException(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    @Override // java.lang.Throwable
    public String toString() {
        if (this.errorCode == 0) {
            return super.toString();
        }
        return super.toString() + " (error code " + this.errorCode + ")";
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
