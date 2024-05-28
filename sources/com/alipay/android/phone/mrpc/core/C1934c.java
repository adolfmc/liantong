package com.alipay.android.phone.mrpc.core;

import android.os.Looper;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C1934c implements HttpRequestInterceptor {
    @Override // org.apache.http.HttpRequestInterceptor
    public final void process(HttpRequest httpRequest, HttpContext httpContext) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("This thread forbids HTTP requests");
        }
    }
}
