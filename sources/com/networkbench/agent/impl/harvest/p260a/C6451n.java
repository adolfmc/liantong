package com.networkbench.agent.impl.harvest.p260a;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6646o;
import com.networkbench.com.google.gson.GsonBuilder;
import com.networkbench.com.google.gson.reflect.TypeToken;
import java.net.HttpURLConnection;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.n */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6451n extends AbstractC6444i {

    /* renamed from: c */
    private static final String f16306c = "Date";

    public C6451n(String str, boolean z) {
        super(str, z);
        m9968b("RedirectUrl");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        return m9971a("/getMobileRedirectHost");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        try {
            m9955a(harvestResponse, str);
        } catch (Exception e) {
            this.f16287a.mo10121a("error redirect getHarvestResponse", e);
        }
        return harvestResponse;
    }

    /* renamed from: c */
    private void m9950c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        new C6646o(C6638h.m8963w().m9076K()).m8853a("initresult", str);
    }

    /* renamed from: a */
    private void m9955a(HarvestResponse harvestResponse, String str) {
        harvestResponse.setResponseBody(str);
        InterfaceC6393e interfaceC6393e = this.f16287a;
        interfaceC6393e.mo10122a("RedirectUrl:" + str);
        Map<String, Object> map = (Map) new GsonBuilder().create().fromJson(str, new TypeToken<Map<String, Object>>() { // from class: com.networkbench.agent.impl.harvest.a.n.1
        }.getType());
        if (map == null || map.size() == 0) {
            this.f16287a.mo10122a("Failed to retrieve collector response: cause responseResult is null");
            return;
        }
        String str2 = (String) map.get("status");
        if ("success".equals(str2)) {
            m9950c(str);
            m9954a(harvestResponse, map);
        } else if ("error".equals(str2)) {
            m9952b(harvestResponse, map);
        }
    }

    /* renamed from: a */
    private void m9954a(HarvestResponse harvestResponse, Map<String, Object> map) {
        harvestResponse.setStatus("success");
        InterfaceC6393e interfaceC6393e = this.f16287a;
        interfaceC6393e.mo10122a("result content:" + map.get("result"));
        harvestResponse.setResultMessage(map.containsKey("result") ? map.get("result").toString() : "");
        m9953a(map);
    }

    /* renamed from: a */
    private void m9953a(Map<String, Object> map) {
        C6450m.m9963a().m9961a(map);
    }

    /* renamed from: b */
    private void m9952b(HarvestResponse harvestResponse, Map<String, Object> map) {
        harvestResponse.setStatus("error");
        if (map.get("result") instanceof Map) {
            Map map2 = (Map) map.get("result");
            harvestResponse.setErrorCode(((Double) map2.get("errorCode")).intValue());
            harvestResponse.setResultMessage(map2.get("errorMessage").toString());
        }
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: b */
    public void mo9951b(HttpURLConnection httpURLConnection) {
        if (httpURLConnection.getHeaderField("Date") != null) {
            C6450m.m9963a().m9962a(httpURLConnection.getHeaderField("Date"));
        }
    }
}
