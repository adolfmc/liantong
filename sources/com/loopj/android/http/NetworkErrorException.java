package com.loopj.android.http;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class NetworkErrorException extends Exception {
    private Throwable originalThrowable;

    public NetworkErrorException(String str, Throwable th) {
        super(str);
        this.originalThrowable = th;
    }
}
