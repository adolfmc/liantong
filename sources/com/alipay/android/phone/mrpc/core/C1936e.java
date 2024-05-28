package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C1936e extends DefaultRedirectHandler {

    /* renamed from: a */
    int f3407a;

    /* renamed from: b */
    final /* synthetic */ C1935d f3408b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1936e(C1935d c1935d) {
        this.f3408b = c1935d;
    }

    @Override // org.apache.http.impl.client.DefaultRedirectHandler, org.apache.http.client.RedirectHandler
    public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        int statusCode;
        this.f3407a++;
        boolean isRedirectRequested = super.isRedirectRequested(httpResponse, httpContext);
        if (isRedirectRequested || this.f3407a >= 5 || !((statusCode = httpResponse.getStatusLine().getStatusCode()) == 301 || statusCode == 302)) {
            return isRedirectRequested;
        }
        return true;
    }
}
