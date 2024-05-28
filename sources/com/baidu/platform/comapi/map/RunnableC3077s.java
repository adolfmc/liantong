package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.s */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3077s implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Bitmap f8013a;

    /* renamed from: b */
    final /* synthetic */ C3074p f8014b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3077s(C3074p c3074p, Bitmap bitmap) {
        this.f8014b = c3074p;
        this.f8013a = bitmap;
    }

    @Override // java.lang.Runnable
    public void run() {
        InterfaceC3039c interfaceC3039c;
        interfaceC3039c = this.f8014b.f7994j;
        interfaceC3039c.mo17867a(this.f8013a);
    }
}
