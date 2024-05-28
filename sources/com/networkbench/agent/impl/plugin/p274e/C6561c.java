package com.networkbench.agent.impl.plugin.p274e;

import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p243c.C6281e;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.EnumC6558e;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.e.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6561c extends AbstractC6566h {

    /* renamed from: a */
    private C6281e f16786a;

    /* renamed from: g */
    private ActionData f16787g;

    public C6561c(ActionData actionData, C6281e c6281e) {
        super(EnumC6558e.after_error);
        this.f16787g = actionData;
        this.f16786a = c6281e;
        m9375a("requestHeader", actionData.requestHeaderParam);
        m9375a("responseHeader", actionData.responseHeaderParam);
        m9375a("hostName", C6653u.m8687n(actionData.getUrl()));
        m9375a("port", Integer.valueOf(C6653u.m8686o(actionData.getUrl())));
    }

    @Override // com.networkbench.agent.impl.plugin.p274e.AbstractC6566h
    /* renamed from: a */
    protected void mo9378a() {
        C6396h.m10137e("NetworkScene completeSceneData :--------");
        for (AbstractC6590h abstractC6590h : this.f16799b) {
            if (abstractC6590h.f16891j && abstractC6590h.f16889h.m10736a()) {
                JsonObject m9385a = C6563e.m9385a(abstractC6590h);
                ActionData actionData = this.f16787g;
                if (actionData != null && actionData.getUnknown() != null) {
                    this.f16787g.getUnknown().put(abstractC6590h.f16889h.f15617e, m9385a);
                }
                C6281e c6281e = this.f16786a;
                if (c6281e != null && c6281e.m10653e() != null) {
                    this.f16786a.m10653e().put(abstractC6590h.f16889h.f15617e, m9385a);
                }
            }
        }
        Harvest.addHttpErrorForScene(this.f16786a);
        Harvest.addHttpTransaction(this.f16787g);
    }
}
