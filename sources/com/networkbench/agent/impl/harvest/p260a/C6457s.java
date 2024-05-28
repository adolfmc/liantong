package com.networkbench.agent.impl.harvest.p260a;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.util.C6638h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.s */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6457s extends AbstractC6444i {
    public C6457s(String str, boolean z) {
        super(str, z);
        m9968b("UserProfileUrl");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        return m9971a("/setUserProfile?version=" + NBSAgent.getHttpDataVersion() + "&token=" + C6638h.m8963w().m9075L());
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        harvestResponse.setResultMessage(str);
        try {
            harvestResponse.parseResult(str);
        } catch (Exception unused) {
        }
        return super.mo9947a(str, harvestResponse);
    }
}
