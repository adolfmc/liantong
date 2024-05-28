package com.networkbench.agent.impl.p243c;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.com.google.gson.JsonArray;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6303g extends HarvestableArray {

    /* renamed from: a */
    private Collection<C6304h> f15801a = new ConcurrentLinkedQueue();

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        for (C6304h c6304h : this.f15801a) {
            if (c6304h != null) {
                jsonArray.add(c6304h.asJson());
            }
        }
        return jsonArray;
    }

    /* renamed from: a */
    public void m10521a(C6304h c6304h) {
        if (c6304h != null) {
            this.f15801a.add(c6304h);
        }
    }

    /* renamed from: a */
    public void m10522a() {
        this.f15801a.clear();
    }
}
