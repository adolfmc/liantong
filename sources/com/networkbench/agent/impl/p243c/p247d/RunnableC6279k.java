package com.networkbench.agent.impl.p243c.p247d;

import com.networkbench.agent.impl.p243c.p248e.C6295m;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.d.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RunnableC6279k implements Runnable {

    /* renamed from: a */
    C6272d f15656a;

    /* renamed from: b */
    InterfaceC6271c f15657b;

    public RunnableC6279k(C6272d c6272d, InterfaceC6271c interfaceC6271c) {
        this.f15656a = c6272d;
        this.f15657b = interfaceC6271c;
    }

    @Override // java.lang.Runnable
    public void run() {
        C6272d c6272d = this.f15656a;
        if (c6272d != null) {
            c6272d.m10710a(C6295m.EnumC6299d.timeOut);
            this.f15656a.f15634a.clear();
            C6274f.m10697a(this.f15656a.f15635b);
        }
    }
}
