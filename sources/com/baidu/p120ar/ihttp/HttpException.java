package com.baidu.p120ar.ihttp;

import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ihttp.HttpException */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HttpException extends Exception {
    public static final int HTTP_ERROR = 4;
    public static final int INVOKE_ERROR = 3;
    public static final int LIB_ERROR = 2;
    public static final int NET_ERROR = 1;
    public static final int UNKNOWN_ERROR = -1;
    private int mCode;

    public HttpException(IOException iOException) {
        super(iOException);
        dealException(iOException);
    }

    public HttpException(int i, String str) {
        super(str);
        this.mCode = i;
    }

    public HttpException(int i, Exception exc) {
        super(exc);
        this.mCode = i;
    }

    public int getCode() {
        return this.mCode;
    }

    private void dealException(IOException iOException) {
        if ((iOException instanceof ConnectException) || (iOException instanceof SocketTimeoutException) || (iOException instanceof NoRouteToHostException) || (iOException instanceof UnknownHostException)) {
            this.mCode = 1;
        } else if (iOException instanceof IOException) {
            this.mCode = -1;
        } else {
            this.mCode = 2;
        }
    }
}
