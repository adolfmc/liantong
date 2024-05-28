package com.networkbench.agent.impl.p241b;

import com.networkbench.agent.impl.crash.C6330i;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6648q;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.n */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RunnableC6244n implements Runnable {

    /* renamed from: b */
    private static InterfaceC6393e f15474b = C6394f.m10150a();

    /* renamed from: a */
    public int f15475a;

    /* renamed from: c */
    private C6239i f15476c;

    public RunnableC6244n(C6239i c6239i) {
        this.f15476c = c6239i;
    }

    @Override // java.lang.Runnable
    public void run() {
        f15474b.mo10122a("TraceFileRunnable : 1111run.....");
        C6243m c6243m = new C6243m();
        try {
            C6396h.m10138d("TraceFileRunnable .... longCode: " + this.f15475a);
            boolean m10889a = c6243m.m10889a(C6638h.m8963w().m9076K());
            this.f15475a = c6243m.f15471c;
            C6396h.m10138d("TraceFileRunnable .... longCode: " + this.f15475a);
            if (!m10889a || this.f15475a == c6243m.f15471c) {
                return;
            }
            this.f15476c.f15444c = c6243m.m10886b();
            this.f15476c.f15445d = c6243m.m10890a();
            this.f15476c.m10915a(C6638h.m8963w().m9001h());
            this.f15476c.m10907g();
            this.f15476c.f15442a = C6330i.m10379a();
            this.f15476c.m10918a();
            this.f15476c.m10916a(c6243m.m10884c());
            C6648q.m8781a(this.f15476c);
        } catch (C6632b e) {
            f15474b.mo10122a(e.getMessage());
        }
    }
}
