package com.mob.apc;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class APCException extends Exception {
    public int errorCode;

    public APCException(String str) {
        super(str);
        this.errorCode = 0;
    }

    public APCException(int i, String str) {
        super(str);
        this.errorCode = 0;
        this.errorCode = i;
    }

    public APCException(Throwable th) {
        super(th);
        this.errorCode = 0;
    }
}
