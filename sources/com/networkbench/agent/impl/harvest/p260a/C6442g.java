package com.networkbench.agent.impl.harvest.p260a;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.util.C6638h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6442g extends AbstractC6444i {
    public C6442g(String str, boolean z) {
        super(str, z);
        m9968b("ControllerDataUrl");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        return m9971a("/ctl/optimus?version=" + NBSAgent.getVersion() + "&token=" + C6638h.m8963w().m9075L());
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        harvestResponse.setResponseBody(str);
        try {
            harvestResponse.parseResult(str);
        } catch (Exception e) {
            this.f16287a.mo10121a("controller parseResult error", e);
        }
        return super.mo9947a(str, harvestResponse);
    }
}
