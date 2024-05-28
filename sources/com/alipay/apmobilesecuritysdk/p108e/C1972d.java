package com.alipay.apmobilesecuritysdk.p108e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p106c.C1961a;
import com.alipay.apmobilesecuritysdk.p109f.C1978a;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.apmobilesecuritysdk.e.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1972d {
    /* renamed from: a */
    private static C1971c m21022a(String str) {
        try {
            if (C2081a.m20577a(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            return new C1971c(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString("timestamp"), jSONObject.optString("tid"), jSONObject.optString("utdid"));
        } catch (Exception e) {
            C1961a.m21046a(e);
            return null;
        }
    }

    /* renamed from: a */
    public static synchronized void m21025a() {
        synchronized (C1972d.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m21024a(Context context) {
        synchronized (C1972d.class) {
            C1978a.m20967a(context, "vkeyid_profiles_v4", "key_deviceid_v4", "");
            C1978a.m20965a("wxcasxx_v4", "key_wxcasxx_v4", "");
        }
    }

    /* renamed from: a */
    public static synchronized void m21023a(Context context, C1971c c1971c) {
        synchronized (C1972d.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", c1971c.f3485a);
                jSONObject.put("deviceInfoHash", c1971c.f3486b);
                jSONObject.put("timestamp", c1971c.f3487c);
                jSONObject.put("tid", c1971c.f3488d);
                jSONObject.put("utdid", c1971c.f3489e);
                String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
                C1978a.m20967a(context, "vkeyid_profiles_v4", "key_deviceid_v4", jSONObject2);
                C1978a.m20965a("wxcasxx_v4", "key_wxcasxx_v4", jSONObject2);
            } catch (Exception e) {
                C1961a.m21046a(e);
            }
        }
    }

    /* renamed from: b */
    public static synchronized C1971c m21021b() {
        synchronized (C1972d.class) {
            String m20966a = C1978a.m20966a("wxcasxx_v4", "key_wxcasxx_v4");
            if (C2081a.m20577a(m20966a)) {
                return null;
            }
            return m21022a(m20966a);
        }
    }

    /* renamed from: b */
    public static synchronized C1971c m21020b(Context context) {
        C1971c m21022a;
        synchronized (C1972d.class) {
            String m20968a = C1978a.m20968a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            if (C2081a.m20577a(m20968a)) {
                m20968a = C1978a.m20966a("wxcasxx_v4", "key_wxcasxx_v4");
            }
            m21022a = m21022a(m20968a);
        }
        return m21022a;
    }

    /* renamed from: c */
    public static synchronized C1971c m21019c(Context context) {
        synchronized (C1972d.class) {
            String m20968a = C1978a.m20968a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            if (C2081a.m20577a(m20968a)) {
                return null;
            }
            return m21022a(m20968a);
        }
    }
}
