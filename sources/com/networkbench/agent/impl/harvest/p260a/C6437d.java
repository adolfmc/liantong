package com.networkbench.agent.impl.harvest.p260a;

import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.com.google.gson.GsonBuilder;
import com.networkbench.com.google.gson.reflect.TypeToken;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6437d extends AbstractC6444i {
    public C6437d(String str, boolean z) {
        super(str, z);
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        return this.f16288b;
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        harvestResponse.setResponseBody(str);
        return super.mo9947a(str, harvestResponse);
    }

    /* renamed from: c */
    private Map<String, Object> m9974c(String str) {
        return (Map) new GsonBuilder().create().fromJson(str, new TypeToken<Map<String, Object>>() { // from class: com.networkbench.agent.impl.harvest.a.d.1
        }.getType());
    }
}
