package com.networkbench.agent.impl.p255g.p256a;

import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.p243c.C6281e;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.EnumC6421f;
import com.networkbench.agent.impl.p255g.InterfaceC6403a;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.plugin.p274e.C6561c;
import com.networkbench.agent.impl.plugin.p274e.C6565g;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.g.a.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6408e extends C6407d {

    /* renamed from: c */
    private static final InterfaceC6393e f16188c = C6394f.m10150a();

    /* renamed from: a */
    private C6404a f16189a;

    /* renamed from: b */
    private C6405b f16190b;

    public C6408e(C6404a c6404a, C6405b c6405b) {
        super(EnumC6421f.MixNetwork);
        this.f16189a = c6404a;
        this.f16190b = c6405b;
    }

    @Override // com.networkbench.agent.impl.p255g.p256a.C6407d, com.networkbench.agent.impl.p255g.p256a.InterfaceC6406c
    /* renamed from: a */
    public void mo10071a(InterfaceC6403a interfaceC6403a) {
        super.mo10071a(interfaceC6403a);
        if (!(interfaceC6403a instanceof C6412c) || interfaceC6403a == null) {
            return;
        }
        C6412c c6412c = (C6412c) interfaceC6403a;
        if (C6565g.f16791c && c6412c.m10033e() != null) {
            f16188c.mo10122a("enter TransactionStateMeasurementConsumer after_error scene");
            ActionData m10073a = this.f16189a.m10073a(c6412c.m10034d());
            C6281e c6281e = new C6281e(c6412c.m10033e());
            if (c6281e.m10651g().startsWith("http")) {
                C6561c c6561c = new C6561c(m10073a, c6281e);
                C6565g.m9380a(c6561c);
                c6561c.m9372d();
                return;
            }
            m10070a(c6412c);
            return;
        }
        m10070a(c6412c);
    }

    /* renamed from: a */
    public void m10070a(C6412c c6412c) {
        f16188c.mo10122a("not NetworkErrorScene");
        this.f16189a.mo10071a((InterfaceC6403a) c6412c.m10034d());
        if (c6412c.m10033e() != null) {
            this.f16190b.mo10071a(c6412c.m10033e());
        }
    }
}
