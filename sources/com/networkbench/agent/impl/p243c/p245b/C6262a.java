package com.networkbench.agent.impl.p243c.p245b;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.b.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6262a extends HarvestableObject {

    /* renamed from: a */
    public final List<HarvestableArray> f15599a = new CopyOnWriteArrayList();

    /* renamed from: b */
    public String f15600b = "";

    /* renamed from: c */
    protected String f15601c = "";

    /* renamed from: a */
    public JsonArray m10751a() {
        JsonArray jsonArray = new JsonArray();
        for (HarvestableArray harvestableArray : this.f15599a) {
            jsonArray.add(harvestableArray.asJson());
        }
        return jsonArray;
    }

    /* renamed from: a */
    public void mo10631a(HarvestableArray harvestableArray) {
        try {
            this.f15599a.add(harvestableArray);
        } catch (Exception e) {
            C6396h.m10132j("NBSEventActions add() has an error : " + e);
        }
    }

    /* renamed from: b */
    public void m10749b(HarvestableArray harvestableArray) {
        this.f15599a.remove(harvestableArray);
    }

    /* renamed from: d */
    private String m10747d() {
        return this.f15600b;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive(m10747d()));
        jsonObject.add("interval", new JsonPrimitive((Number) Long.valueOf(C6638h.m8963w().m8966t())));
        jsonObject.add("timestamp", new JsonPrimitive((Number) Long.valueOf(TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS))));
        jsonObject.add("dev", NBSAgent.getDeviceData().asJson());
        jsonObject.add(this.f15601c, m10751a());
        return jsonObject;
    }

    /* renamed from: b */
    public void m10750b() {
        this.f15599a.clear();
    }

    /* renamed from: c */
    public int m10748c() {
        return this.f15599a.size();
    }
}
