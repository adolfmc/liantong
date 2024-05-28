package com.networkbench.agent.impl.plugin;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableType;
import com.networkbench.agent.impl.p243c.p246c.C6267d;
import com.networkbench.agent.impl.plugin.p274e.AbstractC6566h;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6594k extends AbstractC6590h {

    /* renamed from: a */
    String f16904a;

    /* renamed from: b */
    String f16905b;

    /* renamed from: c */
    JSONObject f16906c;

    public C6594k(AbstractC6566h abstractC6566h, C6267d c6267d) {
        super(HarvestableType.OBJECT, abstractC6566h, c6267d);
        this.f16890i = new C6567f(c6267d.f15616d, (String) this.f16894n.m9371e().get("hostName"));
        mo9300a(abstractC6566h.m9371e());
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9301a() {
        if (TextUtils.isEmpty(this.f16905b)) {
            this.f16891j = false;
        } else {
            this.f16891j = true;
        }
        this.f16894n.m9370f();
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    protected void mo9300a(Map<String, Object> map) {
        try {
            this.f16904a = this.f16889h.f15616d.optString("key", "");
            HashMap hashMap = (HashMap) map.get("responseHeader");
            if (hashMap != null) {
                this.f16905b = (String) hashMap.get(String.valueOf(this.f16904a).toLowerCase());
            }
        } catch (Throwable unused) {
            this.f16891j = false;
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        if (TextUtils.isEmpty(this.f16905b)) {
            this.f16891j = false;
        }
        JsonObject jsonObject = new JsonObject();
        String str = this.f16904a;
        String str2 = this.f16905b;
        if (str2 == null) {
            str2 = "";
        }
        jsonObject.add(str, new JsonPrimitive(str2));
        JsonObject jsonObject2 = new JsonObject();
        if (!C6638h.m8963w().f17182z) {
            jsonObject2.add("result", jsonObject);
        } else {
            jsonObject2.add("result", new JsonPrimitive(Harvest.getInstance().getConfiguration().encryptContentAES(jsonObject.toString())));
        }
        return jsonObject2;
    }
}
