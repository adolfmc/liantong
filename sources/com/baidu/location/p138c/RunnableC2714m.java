package com.baidu.location.p138c;

import com.baidu.location.p137b.C2666p;
import com.baidu.location.p137b.C2676u;
import com.baidu.location.p137b.C2686z;
import com.baidu.location.p138c.C2711l;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.c.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC2714m implements Runnable {

    /* renamed from: a */
    final /* synthetic */ boolean f5655a;

    /* renamed from: b */
    final /* synthetic */ C2711l.C2713a f5656b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2714m(C2711l.C2713a c2713a, boolean z) {
        this.f5656b = c2713a;
        this.f5655a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        z = C2711l.this.f5649k;
        if (!z) {
            C2711l.this.f5649k = this.f5655a;
        }
        C2711l.this.m19109q();
        C2666p.m19375c().m19357i();
        if (System.currentTimeMillis() - C2676u.m19316b() <= 5000) {
            C2686z.m19278a().m19273c();
        }
    }
}
