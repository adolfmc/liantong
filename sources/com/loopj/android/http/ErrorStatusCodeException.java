package com.loopj.android.http;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ErrorStatusCodeException extends Exception {
    public int statusCode;

    public ErrorStatusCodeException(String str, int i) {
        super(str);
        this.statusCode = i;
    }
}
