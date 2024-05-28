package com.networkbench.agent.impl.plugin.p270a;

import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6531a extends HarvestableObject {

    /* renamed from: b */
    int f16670b;

    /* renamed from: d */
    int f16672d;

    /* renamed from: e */
    int f16673e;

    /* renamed from: f */
    int f16674f;

    /* renamed from: g */
    int f16675g;

    /* renamed from: h */
    int f16676h;

    /* renamed from: i */
    int f16677i;

    /* renamed from: j */
    int f16678j;

    /* renamed from: a */
    boolean f16669a = false;

    /* renamed from: c */
    String f16671c = "";

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        try {
            if (Integer.valueOf(this.f16671c).intValue() != this.f16672d + this.f16673e) {
                this.f16671c = String.valueOf(this.f16672d + this.f16673e);
            }
        } catch (Throwable unused) {
            this.f16671c = String.valueOf(this.f16672d + this.f16673e);
        }
        jsonObject.add("rg", new JsonPrimitive(Boolean.valueOf(this.f16669a)));
        jsonObject.add("nt", new JsonPrimitive((Number) Integer.valueOf(this.f16670b)));
        String str = this.f16671c;
        if (str == null) {
            str = "";
        }
        jsonObject.add("no", new JsonPrimitive(str));
        jsonObject.add("mcc", new JsonPrimitive((Number) Integer.valueOf(this.f16672d)));
        jsonObject.add("mnc", new JsonPrimitive((Number) Integer.valueOf(this.f16673e)));
        jsonObject.add("cid", new JsonPrimitive((Number) Integer.valueOf(this.f16674f)));
        jsonObject.add("pci", new JsonPrimitive((Number) Integer.valueOf(this.f16675g)));
        jsonObject.add("earfcn", new JsonPrimitive((Number) Integer.valueOf(this.f16676h)));
        jsonObject.add("rsrp", new JsonPrimitive((Number) Integer.valueOf(this.f16677i)));
        jsonObject.add("rsrq", new JsonPrimitive((Number) Integer.valueOf(this.f16678j)));
        return jsonObject;
    }
}
