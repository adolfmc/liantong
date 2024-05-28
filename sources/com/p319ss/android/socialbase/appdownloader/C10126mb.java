package com.p319ss.android.socialbase.appdownloader;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.ss.android.socialbase.appdownloader.mb */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10126mb {

    /* renamed from: b */
    public String f19533b;

    /* renamed from: h */
    public String f19534h;

    /* renamed from: hj */
    public String f19535hj;

    /* renamed from: mb */
    public String f19536mb;

    /* renamed from: ox */
    public int f19537ox = -1;

    /* renamed from: mb */
    public String m6739mb() {
        JSONObject m6736ox = m6736ox();
        return !(m6736ox instanceof JSONObject) ? m6736ox.toString() : NBSJSONObjectInstrumentation.toString(m6736ox);
    }

    /* renamed from: ox */
    public JSONObject m6736ox() {
        JSONObject jSONObject = new JSONObject();
        m6737mb(jSONObject);
        return jSONObject;
    }

    /* renamed from: mb */
    public void m6737mb(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            jSONObject.put("ah_plan_type", this.f19536mb);
            jSONObject.put("error_code", String.valueOf(this.f19537ox));
            jSONObject.put("error_msg", this.f19533b);
            jSONObject.put("real_device_plan", this.f19535hj);
            jSONObject.put("device_plans", this.f19534h);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: mb */
    public static C10126mb m6738mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        C10126mb c10126mb = new C10126mb();
        try {
            JSONObject jSONObject = new JSONObject(str);
            c10126mb.f19534h = jSONObject.optString("device_plans", null);
            c10126mb.f19535hj = jSONObject.optString("real_device_plan", null);
            c10126mb.f19533b = jSONObject.optString("error_msg", null);
            c10126mb.f19536mb = jSONObject.optString("ah_plan_type", null);
            String optString = jSONObject.optString("error_code");
            if (TextUtils.isEmpty(optString)) {
                c10126mb.f19537ox = -1;
            } else {
                c10126mb.f19537ox = Integer.parseInt(optString);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return c10126mb;
    }
}
