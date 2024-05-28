package com.networkbench.agent.impl.p243c.p248e;

import com.networkbench.agent.impl.harvest.HarvestData;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p247d.C6269a;
import com.networkbench.agent.impl.p243c.p247d.C6275g;
import com.networkbench.agent.impl.p243c.p247d.C6277i;
import com.networkbench.agent.impl.util.C6638h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6282a extends C6294l {

    /* renamed from: a */
    private String f15681a;

    @Override // com.networkbench.agent.impl.p243c.p248e.C6294l
    /* renamed from: a */
    public void mo10540a(String str) {
        super.mo10540a(str);
        this.f15681a = str;
    }

    @Override // com.networkbench.agent.impl.p243c.p248e.C6294l
    /* renamed from: a */
    public C6293k mo10541a() {
        if (C6638h.m8963w().m9038ah()) {
            C6293k mo10541a = super.mo10541a();
            if (mo10541a != null) {
                m10638e(C6255f.f15554c.m10724a());
                C6275g m10639a = m10639a(mo10541a);
                if (!m10639a.m10686j()) {
                    C6255f.f15554c.m10721b(this.f15681a);
                    C6275g.f15646a = C6255f.f15554c;
                    HarvestData.getPageDatas().mo10631a((HarvestableArray) m10639a);
                    C6277i.m10677a(m10639a, C6255f.f15554c.m10724a());
                }
            }
            return mo10541a;
        }
        return null;
    }

    /* renamed from: e */
    private void m10638e(String str) {
        if (C6277i.m10675a(str)) {
            C6255f.f15554c = new C6269a();
            C6255f.f15554c.m10723a("No_Catched_Action");
            C6255f.f15554c.m10717d("");
        }
    }

    /* renamed from: a */
    private C6275g m10639a(C6293k c6293k) {
        return new C6275g(0, this.f15681a, c6293k);
    }
}
