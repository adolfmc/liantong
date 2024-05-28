package com.networkbench.agent.impl.p243c.p247d;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.p245b.C6262a;
import com.networkbench.agent.impl.p254f.C6396h;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.d.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6276h extends C6262a {
    public C6276h() {
        this.f15601c = "pages";
        this.f15600b = "pageMetrics";
    }

    /* renamed from: a */
    public void m10681a(List<HarvestableArray> list) {
        if (m10682a(list.size())) {
            for (HarvestableArray harvestableArray : list) {
                mo10631a(harvestableArray);
            }
            return;
        }
        this.f15599a.addAll(list);
    }

    @Override // com.networkbench.agent.impl.p243c.p245b.C6262a
    /* renamed from: a */
    public void mo10631a(HarvestableArray harvestableArray) {
        try {
            if (m10682a(1)) {
                this.f15599a.remove(0);
            }
            this.f15599a.add(harvestableArray);
        } catch (Throwable th) {
            C6396h.m10132j("NBSPageData add() has an error : " + th);
        }
    }

    /* renamed from: a */
    private boolean m10682a(int i) {
        return this.f15599a.size() + i > Harvest.getInstance().getConfiguration().getUiPages();
    }
}
