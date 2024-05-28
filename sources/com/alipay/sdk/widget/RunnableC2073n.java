package com.alipay.sdk.widget;

import android.webkit.SslErrorHandler;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC2073n implements Runnable {

    /* renamed from: a */
    final /* synthetic */ SslErrorHandler f3975a;

    /* renamed from: b */
    final /* synthetic */ C2068j f3976b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2073n(C2068j c2068j, SslErrorHandler sslErrorHandler) {
        this.f3976b = c2068j;
        this.f3975a = sslErrorHandler;
    }

    @Override // java.lang.Runnable
    public void run() {
        C2063e.m20608a(this.f3976b.f3940a, "安全警告", "安全連接證書校驗無效，將無法保證訪問資料的安全性，可能存在風險，請選擇是否繼續？", "繼續", new DialogInterface$OnClickListenerC2074o(this), "退出", new DialogInterface$OnClickListenerC2075p(this));
    }
}
