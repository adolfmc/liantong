package com.networkbench.agent.impl.plugin.p274e;

import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.EnumC6558e;
import com.networkbench.agent.impl.util.C6653u;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.e.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6562d extends AbstractC6566h {

    /* renamed from: a */
    private ActionData f16788a;

    public C6562d(ActionData actionData) {
        super(EnumC6558e.after_net);
        this.f16788a = actionData;
        m9375a("requestHeader", actionData.requestHeaderParam);
        m9375a("responseHeader", actionData.responseHeaderParam);
        m9375a("hostName", C6653u.m8687n(actionData.getUrl()));
        m9375a("port", Integer.valueOf(C6653u.m8686o(actionData.getUrl())));
    }

    @Override // com.networkbench.agent.impl.plugin.p274e.AbstractC6566h
    /* renamed from: a */
    protected void mo9378a() {
        ActionData actionData;
        C6396h.m10137e("NetworkScene completeSceneData :--------");
        for (AbstractC6590h abstractC6590h : this.f16799b) {
            if (abstractC6590h.f16891j && abstractC6590h.f16889h.m10736a() && (actionData = this.f16788a) != null && actionData.getUnknown() != null) {
                this.f16788a.getUnknown().put(abstractC6590h.f16889h.f15617e, C6563e.m9385a(abstractC6590h));
            }
        }
        Harvest.addHttpTransactionForScene(this.f16788a);
    }
}
