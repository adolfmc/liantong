package com.xiaomi.clientreport.data;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11197bl;
import com.xiaomi.push.C11469j;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.xiaomi.clientreport.data.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11052a {
    public String clientInterfaceId;
    private String pkgName;
    public int production;
    public int reportType;
    private String sdkVersion;

    /* renamed from: os */
    private String f21286os = C11197bl.m4719a();
    private String miuiVersion = C11469j.m2976a();

    public void setAppPackageName(String str) {
        this.pkgName = str;
    }

    public String getPackageName() {
        return this.pkgName;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("production", this.production);
            jSONObject.put("reportType", this.reportType);
            jSONObject.put("clientInterfaceId", this.clientInterfaceId);
            jSONObject.put("os", this.f21286os);
            jSONObject.put("miuiVersion", this.miuiVersion);
            jSONObject.put("pkgName", this.pkgName);
            jSONObject.put("sdkVersion", this.sdkVersion);
            return jSONObject;
        } catch (JSONException e) {
            AbstractC11049b.m5276a(e);
            return null;
        }
    }

    public String toJsonString() {
        JSONObject json = toJson();
        return json == null ? "" : !(json instanceof JSONObject) ? json.toString() : NBSJSONObjectInstrumentation.toString(json);
    }
}
