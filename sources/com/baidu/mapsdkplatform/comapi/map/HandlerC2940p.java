package com.baidu.mapsdkplatform.comapi.map;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.p */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HandlerC2940p extends Handler {

    /* renamed from: a */
    final /* synthetic */ C2939o f7336a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC2940p(C2939o c2939o) {
        this.f7336a = c2939o;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        C2939o c2939o;
        C2944t c2944t;
        super.handleMessage(message);
        c2939o = C2939o.f7332c;
        if (c2939o != null) {
            c2944t = this.f7336a.f7334d;
            c2944t.m18201a(message);
        }
    }
}
