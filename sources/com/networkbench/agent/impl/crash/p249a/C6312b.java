package com.networkbench.agent.impl.crash.p249a;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6312b extends HarvestableObject {

    /* renamed from: c */
    private static final int f15851c = 60;

    /* renamed from: a */
    public int f15852a = 60;

    /* renamed from: b */
    JsonArray f15853b;

    public C6312b(JsonArray jsonArray) {
        this.f15853b = jsonArray;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("customError"));
        jsonObject.add("interval", new JsonPrimitive((Number) Integer.valueOf(this.f15852a)));
        jsonObject.add("timestamp", new JsonPrimitive((Number) Long.valueOf(TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS))));
        jsonObject.add("dev", NBSAgent.getDeviceData().asJson());
        jsonObject.add("errs", this.f15853b);
        return jsonObject;
    }
}
