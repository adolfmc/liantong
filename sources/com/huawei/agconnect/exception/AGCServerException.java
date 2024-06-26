package com.huawei.agconnect.exception;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AGCServerException extends AGCException {
    public static final int ACCESS_TOKEN_EXPIRED = 205524994;
    public static final int AUTHENTICATION_FAILED = 403;
    public static final int AUTHENTICATION_INVALID = 400;
    public static final int CLIENT_TOKEN_EXPIRED = 205524993;
    public static final int FETCH_THROTTLED = 1;
    public static final int NO_USER_LOGIN = 3;

    /* renamed from: OK */
    public static final int f10816OK = 200;
    public static final int SERVER_NOT_AVAILABLE = 503;
    public static final int SERVER_RSP_INVALID = 2;
    public static final int TOKEN_INVALID = 401;
    public static final int UNKNOW_EXCEPTION = 500;
    private int retCode;

    public AGCServerException(String str, int i) {
        super(str, i);
    }

    public AGCServerException(String str, int i, int i2) {
        this(str, i);
        this.retCode = i2;
    }

    public int getRetCode() {
        return this.retCode;
    }
}
