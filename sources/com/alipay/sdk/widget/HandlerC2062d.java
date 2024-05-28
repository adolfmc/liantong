package com.alipay.sdk.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HandlerC2062d extends Handler {

    /* renamed from: a */
    final /* synthetic */ C2058a f3938a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2062d(C2058a c2058a, Looper looper) {
        super(looper);
        this.f3938a = c2058a;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        this.f3938a.m20617c();
    }
}
