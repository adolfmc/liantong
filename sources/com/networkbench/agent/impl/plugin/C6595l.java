package com.networkbench.agent.impl.plugin;

import com.networkbench.agent.impl.harvest.type.HarvestableType;
import com.networkbench.agent.impl.p243c.p246c.C6267d;
import com.networkbench.agent.impl.plugin.p274e.AbstractC6566h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.l */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6595l extends AbstractC6590h {

    /* renamed from: a */
    int f16907a;

    /* renamed from: b */
    JsonArray f16908b;

    /* renamed from: c */
    JSONObject f16909c;

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: d */
    public boolean mo9299d() {
        return true;
    }

    public C6595l(AbstractC6566h abstractC6566h, C6267d c6267d) {
        super(HarvestableType.OBJECT, abstractC6566h, c6267d);
        mo9300a(abstractC6566h.m9371e());
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    protected void mo9300a(Map<String, Object> map) {
        this.f16909c = this.f16889h.f15616d;
    }

    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        new JsonObject().add("count", new JsonPrimitive((Number) Integer.valueOf(this.f16907a)));
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("logs", this.f16908b);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("result", jsonObject);
        return jsonObject2;
    }
}
