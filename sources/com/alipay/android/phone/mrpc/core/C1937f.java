package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C1937f implements ConnectionKeepAliveStrategy {

    /* renamed from: a */
    final /* synthetic */ C1935d f3409a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1937f(C1935d c1935d) {
        this.f3409a = c1935d;
    }

    @Override // org.apache.http.conn.ConnectionKeepAliveStrategy
    public final long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return 180000L;
    }
}
