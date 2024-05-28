package com.huawei.agconnect.exception;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AGCNetworkException extends AGCException {
    public static final int NETWORK_UNAVAILABLE = 0;
    public static final int SERVER_NOT_REACH = 1;

    public AGCNetworkException(String str, int i) {
        super(str, i);
    }
}
