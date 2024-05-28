package com.networkbench.agent.impl.socket;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestLifecycleAware;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.com.google.gson.JsonArray;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.q */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6620q extends HarvestableArray implements HarvestLifecycleAware {

    /* renamed from: a */
    private final Collection<AbstractC6619p> f17045a = new CopyOnWriteArrayList();

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvest() {
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestBefore() {
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestComplete() {
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestConnected() {
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestDeviceIdError() {
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestDisabled() {
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestDisconnected() {
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestFilter() {
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestFinalize() {
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestStart() {
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestStop() {
    }

    public C6620q() {
        Harvest.addHarvestListener(this);
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        for (AbstractC6619p abstractC6619p : this.f17045a) {
            jsonArray.add(abstractC6619p.asJson());
        }
        return jsonArray;
    }

    /* renamed from: a */
    public void m9208a(AbstractC6619p abstractC6619p) {
        synchronized (abstractC6619p) {
            this.f17045a.add(abstractC6619p);
        }
    }

    /* renamed from: b */
    public void m9206b(AbstractC6619p abstractC6619p) {
        this.f17045a.remove(abstractC6619p);
    }

    /* renamed from: a */
    public void m9209a() {
        this.f17045a.clear();
    }

    /* renamed from: b */
    public Collection<AbstractC6619p> m9207b() {
        return this.f17045a;
    }

    /* renamed from: c */
    public int m9205c() {
        return this.f17045a.size();
    }

    public String toString() {
        return "SocketDatasInfo{SocketDatas=" + this.f17045a + "}";
    }

    /* renamed from: d */
    public void m9204d() {
        for (AbstractC6619p abstractC6619p : this.f17045a) {
            abstractC6619p.mo9221b(true);
        }
        m9209a();
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestError() {
        m9209a();
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestSendFailed() {
        m9209a();
    }
}
