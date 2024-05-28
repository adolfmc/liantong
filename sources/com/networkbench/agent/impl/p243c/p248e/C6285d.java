package com.networkbench.agent.impl.p243c.p248e;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestData;
import com.networkbench.agent.impl.harvest.Harvester;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.p245b.C6262a;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.p254f.C6396h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6285d extends C6262a {
    public C6285d() {
        this.f15600b = "appStarts";
        this.f15601c = "starts";
    }

    @Override // com.networkbench.agent.impl.p243c.p245b.C6262a
    /* renamed from: a */
    public void mo10631a(HarvestableArray harvestableArray) {
        super.mo10631a(harvestableArray);
        C6369q.m10273a().m10272a(new Runnable() { // from class: com.networkbench.agent.impl.c.e.d.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (Harvest.getInstance().getHarvester() != null) {
                        Harvester harvester = Harvest.getInstance().getHarvester();
                        Harvest.getInstance().getHarvestData();
                        harvester.sendHttpData(HarvestData.getAppStartDatas());
                    }
                } catch (Throwable unused) {
                    C6396h.m10131k("核心变量没有初始化完成,不发送启动数据....");
                }
            }
        });
    }
}
