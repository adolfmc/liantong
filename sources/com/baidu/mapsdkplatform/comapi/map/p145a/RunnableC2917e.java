package com.baidu.mapsdkplatform.comapi.map.p145a;

import com.baidu.mapapi.map.track.TraceOverlay;
import com.baidu.mapsdkplatform.comapi.map.C2925d;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.a.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2917e implements Runnable {

    /* renamed from: a */
    final /* synthetic */ TraceOverlay f7226a;

    /* renamed from: b */
    final /* synthetic */ C2914c f7227b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2917e(C2914c c2914c, TraceOverlay traceOverlay) {
        this.f7227b = c2914c;
        this.f7226a = traceOverlay;
    }

    @Override // java.lang.Runnable
    public void run() {
        C2912a c2912a;
        C2925d c2925d;
        C2912a c2912a2;
        c2912a = this.f7227b.f7215a;
        if (c2912a != null) {
            c2925d = this.f7227b.f7216b;
            if (c2925d == null) {
                return;
            }
            this.f7227b.m18406c(this.f7226a);
            c2912a2 = this.f7227b.f7215a;
            c2912a2.m18420a();
        }
    }
}
