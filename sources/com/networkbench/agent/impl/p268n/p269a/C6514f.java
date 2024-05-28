package com.networkbench.agent.impl.p268n.p269a;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.a.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6514f extends HarvestableObject {

    /* renamed from: a */
    private String f16599a;

    /* renamed from: b */
    private int f16600b;

    /* renamed from: c */
    private C6505b f16601c = new C6505b();

    /* renamed from: d */
    private C6505b f16602d = new C6505b();

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("webviewPerfMetrics2"));
        jsonObject.add("interval", new JsonPrimitive((Number) Long.valueOf(C6638h.m8963w().m8966t())));
        jsonObject.add("timestamp", new JsonPrimitive((Number) Long.valueOf(TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS))));
        jsonObject.add("dev", NBSAgent.getDeviceData().asJson());
        jsonObject.add("pf", this.f16601c.asJsonArray());
        jsonObject.add("err", this.f16602d.asJsonArray());
        return jsonObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("type:webviewPerfMetrics2");
        sb.append(", pf:" + this.f16601c.toString());
        sb.append(", err:" + this.f16602d.toString());
        return sb.toString();
    }

    /* renamed from: a */
    public void m9578a() {
        this.f16602d.m9696c();
        this.f16601c.m9696c();
    }

    /* renamed from: b */
    public C6505b m9577b() {
        return this.f16601c;
    }

    /* renamed from: c */
    public C6505b m9576c() {
        return this.f16602d;
    }
}
