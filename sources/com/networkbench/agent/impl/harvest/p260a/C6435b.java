package com.networkbench.agent.impl.harvest.p260a;

import com.networkbench.agent.impl.harvest.HarvestResponse;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6435b extends AbstractC6444i {
    public C6435b(String str, boolean z) {
        super(str, z);
        m9968b("ActionSelectUrl");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        return m9971a("/getMobileAPIHost");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        harvestResponse.setResultMessage(str);
        try {
            harvestResponse.parseResult(str);
        } catch (Exception e) {
            this.f16287a.mo10121a("ActionSelectUrl get response error", e);
        }
        return super.mo9947a(str, harvestResponse);
    }
}
