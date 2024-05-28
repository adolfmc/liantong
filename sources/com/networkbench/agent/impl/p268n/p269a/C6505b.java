package com.networkbench.agent.impl.p268n.p269a;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6505b extends HarvestableArray {

    /* renamed from: a */
    private Collection<HarvestableArray> f16484a = Collections.synchronizedCollection(new ArrayList());

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        HarvestableArray next;
        JsonArray jsonArray = new JsonArray();
        Iterator<HarvestableArray> it = this.f16484a.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            jsonArray.add(next.asJsonArray());
        }
        return jsonArray;
    }

    /* renamed from: a */
    public Collection<HarvestableArray> m9700a() {
        return this.f16484a;
    }

    /* renamed from: a */
    public void m9699a(HarvestableArray harvestableArray) {
        if (harvestableArray != null) {
            this.f16484a.add(harvestableArray);
        }
    }

    /* renamed from: b */
    public void m9697b(HarvestableArray harvestableArray) {
        if (harvestableArray != null) {
            this.f16484a.remove(harvestableArray);
        }
    }

    /* renamed from: b */
    public int m9698b() {
        return this.f16484a.size();
    }

    /* renamed from: c */
    public void m9696c() {
        this.f16484a.clear();
    }

    public String toString() {
        return this.f16484a.toString();
    }
}
