package com.alipay.sdk.widget;

import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.r */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC2077r implements Runnable {

    /* renamed from: a */
    final /* synthetic */ View f3980a;

    /* renamed from: b */
    final /* synthetic */ View$OnClickListenerC2076q f3981b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2077r(View$OnClickListenerC2076q view$OnClickListenerC2076q, View view) {
        this.f3981b = view$OnClickListenerC2076q;
        this.f3980a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3980a.setEnabled(true);
    }
}
