package com.networkbench.agent.impl.harvest.p260a;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.util.C6638h;
import java.text.MessageFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6436c extends C6443h {
    public C6436c(String str, boolean z) {
        super(str, z);
        m9968b("AnrDataUrl");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.C6443h, com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        if (TextUtils.isEmpty(C6638h.m8963w().m9075L())) {
            return MessageFormat.format("{0}?version={1}", m9971a("/reportAnr"), NBSAgent.getHttpDataVersion());
        }
        return MessageFormat.format("{0}?version={1}&token={2}", m9971a("/reportAnr"), NBSAgent.getHttpDataVersion(), C6638h.m8963w().m9075L());
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.C6443h, com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        return super.mo9947a(str, harvestResponse);
    }
}
