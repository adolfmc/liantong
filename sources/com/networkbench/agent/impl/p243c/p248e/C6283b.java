package com.networkbench.agent.impl.p243c.p248e;

import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6283b extends HarvestableObject {

    /* renamed from: a */
    private int f15682a;

    /* renamed from: a */
    public int m10637a() {
        return this.f15682a;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("hotStartMetrics"));
        jsonObject.add("interval", new JsonPrimitive((Number) Long.valueOf(C6638h.m8963w().m8966t())));
        jsonObject.add("timestamp", new JsonPrimitive((Number) Long.valueOf(TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS))));
        jsonObject.add("count", new JsonPrimitive((Number) Integer.valueOf(this.f15682a)));
        return jsonObject;
    }

    /* renamed from: b */
    public void m10636b() {
        this.f15682a = 0;
    }

    /* renamed from: c */
    public void m10635c() {
        this.f15682a++;
        C6396h.m10133i(" driveCount : " + this.f15682a);
    }
}
