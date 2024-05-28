package com.bytedance.pangle.p171a;

import com.bytedance.pangle.p176d.C3794e;
import java.util.concurrent.CountDownLatch;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3769a {

    /* renamed from: a */
    final CountDownLatch f9017a;

    /* renamed from: b */
    Throwable f9018b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.a.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC3771a {
        /* renamed from: a */
        void mo16758a();
    }

    private C3769a(InterfaceC3771a[] interfaceC3771aArr) {
        this.f9017a = new CountDownLatch(interfaceC3771aArr.length);
        for (final InterfaceC3771a interfaceC3771a : interfaceC3771aArr) {
            C3794e.m16918a(new Runnable() { // from class: com.bytedance.pangle.a.a.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        interfaceC3771a.mo16758a();
                    } catch (Throwable th) {
                        C3769a.this.f9018b = th;
                    }
                    C3769a.this.f9017a.countDown();
                }
            });
        }
    }

    /* renamed from: a */
    public static void m16982a(InterfaceC3771a... interfaceC3771aArr) {
        C3769a c3769a = new C3769a(interfaceC3771aArr);
        try {
            c3769a.f9017a.await();
            Throwable th = c3769a.f9018b;
            if (th != null) {
                throw th;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
