package com.baidu.mapsdkplatform.comapi;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BMapManagerInternal.java */
/* renamed from: com.baidu.mapsdkplatform.comapi.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HandlerC2883b extends Handler {

    /* renamed from: a */
    final /* synthetic */ BMapManagerInternal f7121a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC2883b(BMapManagerInternal bMapManagerInternal) {
        this.f7121a = bMapManagerInternal;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        this.f7121a.m18537a(message);
    }
}
