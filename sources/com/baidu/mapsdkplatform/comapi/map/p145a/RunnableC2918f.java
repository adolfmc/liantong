package com.baidu.mapsdkplatform.comapi.map.p145a;

import com.baidu.mapapi.map.track.TraceOverlay;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.a.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2918f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ TraceOverlay f7228a;

    /* renamed from: b */
    final /* synthetic */ C2914c f7229b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2918f(C2914c c2914c, TraceOverlay traceOverlay) {
        this.f7229b = c2914c;
        this.f7228a = traceOverlay;
    }

    @Override // java.lang.Runnable
    public void run() {
        C2912a c2912a;
        this.f7229b.m18406c(this.f7228a);
        c2912a = this.f7229b.f7215a;
        c2912a.m18420a();
    }
}
