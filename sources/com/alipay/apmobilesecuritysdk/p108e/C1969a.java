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
/* renamed from: com.alipay.apmobilesecuritysdk.e.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1969a {
    /* renamed from: a */
    private static C1970b m21029a(String str) {
        try {
            if (C2081a.m20577a(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            return new C1970b(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString("timestamp"));
        } catch (Exception e) {
            C1961a.m21046a(e);
            return null;
        }
    }

    /* renamed from: a */
    public static synchronized void m21032a() {
        synchronized (C1969a.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m21031a(Context context) {
        synchronized (C1969a.class) {
            C1978a.m20967a(context, "vkeyid_profiles_v3", "deviceid", "");
            C1978a.m20965a("wxcasxx_v3", "wxcasxx", "");
        }
    }

    /* renamed from: a */
    public static synchronized void m21030a(Context context, C1970b c1970b) {
        synchronized (C1969a.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", c1970b.f3482a);
                jSONObject.put("deviceInfoHash", c1970b.f3483b);
                jSONObject.put("timestamp", c1970b.f3484c);
                String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
                C1978a.m20967a(context, "vkeyid_profiles_v3", "deviceid", jSONObject2);
                C1978a.m20965a("wxcasxx_v3", "wxcasxx", jSONObject2);
            } catch (Exception e) {
                C1961a.m21046a(e);
            }
        }
    }

    /* renamed from: b */
    public static synchronized C1970b m21028b() {
        synchronized (C1969a.class) {
            String m20966a = C1978a.m20966a("wxcasxx_v3", "wxcasxx");
            if (C2081a.m20577a(m20966a)) {
                return null;
            }
            return m21029a(m20966a);
        }
    }

    /* renamed from: b */
    public static synchronized C1970b m21027b(Context context) {
        C1970b m21029a;
        synchronized (C1969a.class) {
            String m20968a = C1978a.m20968a(context, "vkeyid_profiles_v3", "deviceid");
            if (C2081a.m20577a(m20968a)) {
                m20968a = C1978a.m20966a("wxcasxx_v3", "wxcasxx");
            }
            m21029a = m21029a(m20968a);
        }
        return m21029a;
    }

    /* renamed from: c */
    public static synchronized C1970b m21026c(Context context) {
        synchronized (C1969a.class) {
            String m20968a = C1978a.m20968a(context, "vkeyid_profiles_v3", "deviceid");
            if (C2081a.m20577a(m20968a)) {
                return null;
            }
            return m21029a(m20968a);
        }
    }
}
