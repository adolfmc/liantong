package com.networkbench.agent.impl.p268n.p269a;

import com.networkbench.agent.impl.p268n.p269a.C6506c;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6504a extends C6506c {

    /* renamed from: j */
    private String f16480j;

    /* renamed from: l */
    private String f16481l;

    /* renamed from: m */
    private String f16482m;

    /* renamed from: n */
    private int f16483n;

    public C6504a(C6506c.C6507a c6507a) {
        super(c6507a);
        this.f16480j = "";
        this.f16481l = "";
        this.f16482m = "";
    }

    /* renamed from: a */
    public void m9703a(String str) {
        if (str == null) {
            str = "";
        }
        this.f16480j = str;
    }

    /* renamed from: b */
    public void m9702b(String str) {
        if (str == null) {
            str = "";
        }
        this.f16481l = str;
    }

    /* renamed from: c */
    public void m9701c(String str) {
        if (str == null) {
            str = "";
        }
        this.f16482m = str;
    }

    /* renamed from: a */
    public void m9704a(int i) {
        this.f16483n = i;
    }

    @Override // com.networkbench.agent.impl.p268n.p269a.C6506c, com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(this.f16480j));
        jsonArray.add(new JsonPrimitive(this.f16481l));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16483n)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16485a)));
        jsonArray.add(new JsonPrimitive(this.f16486b));
        jsonArray.add(new JsonPrimitive(this.f16487c));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16488d)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16489e)));
        jsonArray.add(new JsonPrimitive(this.f16490f));
        jsonArray.add(new JsonPrimitive(this.f16491g));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16492h)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16493i)));
        jsonArray.add(new JsonPrimitive(this.f16482m));
        return jsonArray;
    }

    @Override // com.networkbench.agent.impl.p268n.p269a.C6506c
    public String toString() {
        return "pvId:" + this.f16480j + ", pageStartTimeInSec:" + this.f16483n + ", pageUrl:" + this.f16481l + ", cdnvendor:" + this.f16482m + ", " + super.toString();
    }
}
