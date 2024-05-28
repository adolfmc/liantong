package com.baidu.mapapi.utils;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: MapOpenUtil.java */
/* renamed from: com.baidu.mapapi.utils.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class RunnableC2858f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f7023a;

    /* renamed from: b */
    final /* synthetic */ int f7024b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2858f(Context context, int i) {
        this.f7023a = context;
        this.f7024b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        Thread thread;
        long currentTimeMillis = System.currentTimeMillis();
        do {
            if (System.currentTimeMillis() - currentTimeMillis > 3000) {
                MapOpenUtil.m18612a(this.f7023a);
                MapOpenUtil.m18613a(this.f7024b, this.f7023a);
            }
            thread = MapOpenUtil.f7018x;
        } while (!thread.isInterrupted());
    }
}
