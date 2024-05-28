package com.networkbench.agent.impl.harvest.p260a;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.p243c.p246c.C6264a;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.GsonBuilder;
import com.networkbench.com.google.gson.reflect.TypeToken;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6446k extends AbstractC6444i {
    public C6446k(String str, boolean z) {
        super(str, z);
        m9968b("ExtensionUrl");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        return m9965c();
    }

    /* renamed from: c */
    private String m9965c() {
        return m9971a("/getTaskDefs?version=" + NBSAgent.getTaskDataVersion() + "&token=" + C6638h.m8963w().m9075L());
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        try {
            m9966b(str, harvestResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.mo9947a(str, harvestResponse);
    }

    /* renamed from: b */
    private void m9966b(String str, HarvestResponse harvestResponse) {
        if (harvestResponse == null || TextUtils.isEmpty(str)) {
            return;
        }
        harvestResponse.setResponseBody(str);
        synchronized (this) {
            try {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String string = jSONObject.getString("status");
                    if (string.equals("success")) {
                        try {
                            harvestResponse.setExtensionConfig(new C6264a().m10746a(jSONObject.getJSONObject("result")));
                        } catch (Throwable unused) {
                        }
                    } else if (string.equals("error")) {
                        Map map = (Map) new GsonBuilder().create().fromJson(str, new TypeToken<Map<String, Object>>() { // from class: com.networkbench.agent.impl.harvest.a.k.1
                        }.getType());
                        harvestResponse.setStatus("error");
                        if (map.get("result") instanceof Map) {
                            Map map2 = (Map) map.get("result");
                            harvestResponse.setErrorCode(((Double) map2.get("errorCode")).intValue());
                            harvestResponse.setResultMessage(map2.get("errorMessage").toString());
                        }
                        this.f16287a.mo10122a(harvestResponse.toString());
                    }
                } catch (Exception e) {
                    this.f16287a.mo10121a("Error while unpacking ExtensionUrl response during connect", e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
