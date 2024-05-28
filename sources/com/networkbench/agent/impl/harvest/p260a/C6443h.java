package com.networkbench.agent.impl.harvest.p260a;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.util.C6638h;
import java.text.MessageFormat;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6443h extends AbstractC6444i {
    public C6443h(String str, boolean z) {
        super(str, z);
        m9968b("CrashDataUrl");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        if (TextUtils.isEmpty(C6638h.m8963w().m9075L())) {
            return MessageFormat.format("{0}?version={1}", m9971a("/reportCrash"), NBSAgent.getHttpDataVersion());
        }
        return MessageFormat.format("{0}?version={1}&token={2}", m9971a("/reportCrash"), NBSAgent.getHttpDataVersion(), C6638h.m8963w().m9075L());
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        m9972c(str);
        return super.mo9947a(str, harvestResponse);
    }

    /* renamed from: c */
    private void m9972c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject(jSONObject.getString("result"));
            if ("error".equals(jSONObject.getString("status"))) {
                jSONObject2.getString("errorMessage");
            }
        } catch (JSONException unused) {
        }
    }
}
