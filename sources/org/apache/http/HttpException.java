package org.apache.http;

import org.apache.http.util.ExceptionUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpException extends Exception {
    private static final long serialVersionUID = -5437299376222011036L;

    public HttpException() {
    }

    public HttpException(String str) {
        super(str);
    }

    public HttpException(String str, Throwable th) {
        super(str);
        ExceptionUtils.initCause(this, th);
    }
}
