package com.networkbench.agent.impl.plugin;

import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6567f implements InterfaceC6530a {

    /* renamed from: a */
    private JSONObject f16803a;

    /* renamed from: b */
    private String f16804b;

    public C6567f(JSONObject jSONObject, String str) {
        this.f16803a = jSONObject;
        this.f16804b = str;
    }

    @Override // com.networkbench.agent.impl.plugin.InterfaceC6530a
    /* renamed from: a */
    public boolean mo9368a() {
        JSONArray optJSONArray;
        try {
            if (this.f16803a == null) {
                return true;
            }
            JSONObject jSONObject = this.f16803a;
            if (jSONObject.optJSONObject("clientCondition") != null && (optJSONArray = jSONObject.optJSONObject("clientCondition").optJSONArray("host")) != null && optJSONArray.length() != 0) {
                String optString = jSONObject.optString("host");
                String str = this.f16804b;
                if (str != null) {
                    optString = str;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    if (optString.contains(optJSONArray.optString(i))) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
