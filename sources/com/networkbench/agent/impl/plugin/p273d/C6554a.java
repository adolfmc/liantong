package com.networkbench.agent.impl.plugin.p273d;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.d.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6554a extends HarvestableArray {

    /* renamed from: a */
    int f16754a = -1;

    /* renamed from: b */
    int f16755b = -1;

    /* renamed from: c */
    int f16756c = -1;

    /* renamed from: d */
    float f16757d = -1.0f;

    public String toString() {
        return "PingData{ps=" + this.f16754a + ", seq=" + this.f16755b + ", ttl=" + this.f16756c + ", time=" + this.f16757d + '}';
    }

    /* renamed from: a */
    public void m9438a() {
        this.f16757d = Math.round(this.f16757d * 1000.0f) / 1000.0f;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16754a)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16755b)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16756c)));
        jsonArray.add(new JsonPrimitive((Number) Float.valueOf(this.f16757d)));
        return jsonArray;
    }
}
