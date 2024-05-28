package com.baidu.cloud.videocache.preload;

import com.baidu.cloud.videocache.preload.nxb;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class uwb implements Runnable {

    /* renamed from: a */
    final /* synthetic */ nxb f4941a;

    /* renamed from: b */
    final /* synthetic */ ass f4942b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public uwb(ass assVar, nxb nxbVar) {
        this.f4942b = assVar;
        this.f4941a = nxbVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        AtomicInteger atomicInteger;
        boolean m19754c;
        rwd rwdVar;
        AtomicInteger atomicInteger2;
        atomicInteger = this.f4942b.f4917f;
        atomicInteger.incrementAndGet();
        nxb nxbVar = this.f4941a;
        m19754c = this.f4942b.m19754c(nxbVar);
        if (!m19754c) {
            nxbVar = this.f4942b.m19753d(nxbVar);
        }
        nxb.oia m19748d = nxbVar.m19748d();
        if (m19748d == nxb.oia.COMPLETED || m19748d == nxb.oia.ERROR) {
            rwdVar = this.f4942b.f4916e;
            rwdVar.m19734b(nxbVar.f4922c);
        }
        atomicInteger2 = this.f4942b.f4917f;
        atomicInteger2.decrementAndGet();
        ass assVar = this.f4942b;
        assVar.m19741a(assVar.m19744a(101));
    }
}
