package com.networkbench.agent.impl.p255g.p256a;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p243c.C6281e;
import com.networkbench.agent.impl.p243c.C6302f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p255g.EnumC6421f;
import com.networkbench.agent.impl.p255g.InterfaceC6403a;
import com.networkbench.agent.impl.p255g.p257b.C6411b;
import com.networkbench.agent.impl.util.C6642k;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.g.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6405b extends C6407d {
    public C6405b() {
        super(EnumC6421f.HttpError);
    }

    @Override // com.networkbench.agent.impl.p255g.p256a.C6407d, com.networkbench.agent.impl.p255g.p256a.InterfaceC6406c
    /* renamed from: a */
    public void mo10071a(InterfaceC6403a interfaceC6403a) {
        Harvest.addHttpErrorForScene(new C6281e((C6411b) interfaceC6403a));
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestAdapter, com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestFilter() {
        C6302f httpErrors = Harvest.getInstance().getHarvestData().getHttpErrors();
        if (httpErrors != null) {
            ArrayList<C6281e> arrayList = new ArrayList();
            for (C6281e c6281e : httpErrors.m10526b()) {
                if (C6642k.m8903e(c6281e.m10651g()) || C6642k.m8921a(c6281e.m10650h(), c6281e.m10651g())) {
                    arrayList.add(c6281e);
                }
                if (!C6642k.m8913b(c6281e.m10650h())) {
                    C6396h.m10135g(" NBSNetworkUtil.isError :  此条数据不为error 特此删除 : " + c6281e.toString());
                    arrayList.add(c6281e);
                }
            }
            for (C6281e c6281e2 : arrayList) {
                httpErrors.m10525b(c6281e2);
            }
        }
    }
}
