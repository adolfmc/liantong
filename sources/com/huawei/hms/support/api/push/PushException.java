package com.huawei.hms.support.api.push;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PushException extends RuntimeException {
    public static final String EXCEPTION_SEND_FAILED = "send message failed";

    public PushException() {
    }

    public PushException(String str, Throwable th) {
        super(str, th);
    }

    public PushException(String str) {
        super(str);
    }

    public PushException(Throwable th) {
        super(th);
    }
}
