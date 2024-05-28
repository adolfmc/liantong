package com.networkbench.agent.impl.harvest.p260a;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.com.google.gson.GsonBuilder;
import com.networkbench.com.google.gson.reflect.TypeToken;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6440f extends AbstractC6444i {

    /* renamed from: c */
    private final InterfaceC6393e f16285c;

    public C6440f(String str, boolean z) {
        super(str, z);
        this.f16285c = C6394f.m10150a();
        m9968b("ConnectionUrl");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        return m9971a("/initMobileApp?version=" + NBSAgent.getHttpDataVersion());
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        harvestResponse.setStatusCode(0);
        m9973a(harvestResponse, str);
        return harvestResponse;
    }

    /* renamed from: a */
    public void m9973a(HarvestResponse harvestResponse, String str) {
        if (harvestResponse == null || TextUtils.isEmpty(str)) {
            return;
        }
        harvestResponse.setResponseBody(str);
        synchronized (this) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("status");
                if (string.equals("success")) {
                    harvestResponse.setConfiguration(new HarvestConfiguration().parseHarvestConfigFromResult(jSONObject.getJSONObject("result")));
                } else if (string.equals("error")) {
                    Map map = (Map) new GsonBuilder().create().fromJson(str, new TypeToken<Map<String, Object>>() { // from class: com.networkbench.agent.impl.harvest.a.f.1
                    }.getType());
                    harvestResponse.setStatus("error");
                    if (map.get("result") instanceof Map) {
                        Map map2 = (Map) map.get("result");
                        harvestResponse.setErrorCode(((Double) map2.get("errorCode")).intValue());
                        harvestResponse.setResultMessage(map2.get("errorMessage").toString());
                    }
                    this.f16285c.mo10122a(harvestResponse.toString());
                }
            } catch (Exception e) {
                InterfaceC6393e interfaceC6393e = this.f16285c;
                interfaceC6393e.mo10116d("Error while unpacking JSON response during connect" + e.toString());
            }
        }
    }
}
