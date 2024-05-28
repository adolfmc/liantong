package com.alipay.sdk.util;

import android.app.Activity;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class RunnableC2054o implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Activity f3911a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2054o(Activity activity) {
        this.f3911a = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3911a.finish();
    }
}
