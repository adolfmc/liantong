package com.networkbench.agent.impl.p241b;

import com.networkbench.agent.impl.p241b.C6235g;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6229b extends C6235g.AbstractC6237a {

    /* renamed from: b */
    private static final InterfaceC6393e f15412b = C6394f.m10150a();

    /* renamed from: e */
    private static C6229b f15413e;

    /* renamed from: d */
    private boolean f15415d = true;

    /* renamed from: c */
    private InterfaceC6234f f15414c = new C6230c();

    /* renamed from: a */
    public static C6229b m10939a() {
        if (f15413e == null) {
            synchronized (C6229b.class) {
                if (f15413e == null) {
                    f15413e = new C6229b();
                }
            }
        }
        return f15413e;
    }

    private C6229b() {
    }

    /* renamed from: b */
    public InterfaceC6234f m10938b() {
        return this.f15414c;
    }

    @Override // com.networkbench.agent.impl.p241b.C6235g.AbstractC6237a
    /* renamed from: c */
    public boolean mo10921c() {
        return this.f15415d;
    }

    @Override // com.networkbench.agent.impl.p241b.C6235g.AbstractC6237a
    /* renamed from: d */
    public void mo10920d() {
        super.mo10920d();
        this.f15414c.mo10931a(System.currentTimeMillis());
    }

    @Override // com.networkbench.agent.impl.p241b.C6235g.AbstractC6237a
    /* renamed from: e */
    public void mo10919e() {
        super.mo10919e();
        this.f15414c.mo10930b();
    }
}
