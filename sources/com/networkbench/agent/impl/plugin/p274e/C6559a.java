package com.networkbench.agent.impl.plugin.p274e;

import com.networkbench.agent.impl.p241b.C6239i;
import com.networkbench.agent.impl.p241b.C6241k;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.EnumC6558e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.e.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6559a extends AbstractC6566h {

    /* renamed from: a */
    boolean f16781a;

    /* renamed from: g */
    private C6239i f16782g;

    public C6559a(C6239i c6239i, boolean z) {
        super(EnumC6558e.after_anr);
        this.f16781a = z;
        this.f16782g = c6239i;
    }

    @Override // com.networkbench.agent.impl.plugin.p274e.AbstractC6566h
    /* renamed from: a */
    protected void mo9378a() {
        for (AbstractC6590h abstractC6590h : this.f16799b) {
            if (abstractC6590h.f16891j && abstractC6590h.f16889h.m10736a()) {
                this.f16782g.m10914b().put(abstractC6590h.f16889h.f15617e, C6563e.m9385a(abstractC6590h));
            }
        }
        C6241k.m10902a().m10894b(this.f16782g, this.f16781a);
    }
}
