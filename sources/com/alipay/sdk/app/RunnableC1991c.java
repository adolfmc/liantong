package com.alipay.sdk.app;

import android.app.Activity;
import android.webkit.SslErrorHandler;
import com.alipay.sdk.widget.C2063e;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC1991c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Activity f3572a;

    /* renamed from: b */
    final /* synthetic */ SslErrorHandler f3573b;

    /* renamed from: c */
    final /* synthetic */ C1989b f3574c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC1991c(C1989b c1989b, Activity activity, SslErrorHandler sslErrorHandler) {
        this.f3574c = c1989b;
        this.f3572a = activity;
        this.f3573b = sslErrorHandler;
    }

    @Override // java.lang.Runnable
    public void run() {
        C2063e.m20608a(this.f3572a, "安全警告", "安全连接证书校验无效，将无法保证访问数据的安全性，可能存在风险，请选择是否继续？", "继续", new DialogInterface$OnClickListenerC1992d(this), "退出", new DialogInterface$OnClickListenerC1993e(this));
    }
}
