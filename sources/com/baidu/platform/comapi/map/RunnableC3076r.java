package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.r */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3076r implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Bitmap f8011a;

    /* renamed from: b */
    final /* synthetic */ C3074p f8012b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3076r(C3074p c3074p, Bitmap bitmap) {
        this.f8012b = c3074p;
        this.f8011a = bitmap;
    }

    @Override // java.lang.Runnable
    public void run() {
        InterfaceC3039c interfaceC3039c;
        interfaceC3039c = this.f8012b.f7994j;
        interfaceC3039c.mo17867a(this.f8011a);
    }
}
