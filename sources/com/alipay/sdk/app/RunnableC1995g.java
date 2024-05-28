package com.alipay.sdk.app;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC1995g implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f3578a;

    /* renamed from: b */
    final /* synthetic */ boolean f3579b;

    /* renamed from: c */
    final /* synthetic */ H5PayCallback f3580c;

    /* renamed from: d */
    final /* synthetic */ PayTask f3581d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC1995g(PayTask payTask, String str, boolean z, H5PayCallback h5PayCallback) {
        this.f3581d = payTask;
        this.f3578a = str;
        this.f3579b = z;
        this.f3580c = h5PayCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3580c.onPayResult(this.f3581d.h5Pay(this.f3578a, this.f3579b));
    }
}
