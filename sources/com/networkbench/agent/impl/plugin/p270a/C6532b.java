package com.networkbench.agent.impl.plugin.p270a;

import com.networkbench.agent.impl.harvest.type.HarvestableType;
import com.networkbench.agent.impl.p243c.p246c.C6267d;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.p274e.AbstractC6566h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6532b extends AbstractC6590h {

    /* renamed from: a */
    JSONObject f16679a;

    /* renamed from: b */
    private int f16680b;

    /* renamed from: c */
    private JSONArray f16681c;

    /* renamed from: d */
    private List<C6531a> f16682d;

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: d */
    public boolean mo9299d() {
        return true;
    }

    public C6532b(AbstractC6566h abstractC6566h, C6267d c6267d) {
        super(HarvestableType.OBJECT, abstractC6566h, c6267d);
        this.f16682d = new ArrayList();
        mo9300a(abstractC6566h.m9371e());
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9301a() {
        try {
            this.f16682d = C6533c.m9505a().m9492a(this.f16681c, this.f16680b);
            if (this.f16682d != null && this.f16682d.size() > 0) {
                this.f16891j = true;
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9300a(Map<String, Object> map) {
        try {
            this.f16679a = this.f16889h.f15616d;
            if (this.f16679a == null) {
                this.f16891j = false;
                return;
            }
            this.f16681c = this.f16679a.optJSONArray("type");
            this.f16680b = this.f16679a.optInt("limit");
        } catch (Throwable unused) {
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonArray jsonArray = new JsonArray();
        for (C6531a c6531a : this.f16682d) {
            jsonArray.add(c6531a.asJson());
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("cellInfos", jsonArray);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("result", jsonObject);
        return jsonObject2;
    }
}
