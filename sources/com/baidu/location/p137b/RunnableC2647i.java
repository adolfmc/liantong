package com.baidu.location.p137b;

import android.location.Location;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2647i implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Location f5275a;

    /* renamed from: b */
    final /* synthetic */ C2644h f5276b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2647i(C2644h c2644h, Location location) {
        this.f5276b = c2644h;
        this.f5275a = location;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f5276b.m19462b(this.f5275a);
    }
}
