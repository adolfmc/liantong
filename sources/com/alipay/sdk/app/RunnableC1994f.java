package com.alipay.sdk.app;

import android.app.Activity;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class RunnableC1994f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Activity f3577a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC1994f(Activity activity) {
        this.f3577a = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3577a.finish();
    }
}
