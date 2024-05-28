package com.alipay.apmobilesecuritysdk.p107d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p106c.C1961a;
import com.alipay.apmobilesecuritysdk.p108e.C1973e;
import com.alipay.apmobilesecuritysdk.p108e.C1974f;
import com.alipay.apmobilesecuritysdk.p109f.C1978a;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.security.mobile.module.p112b.C2087b;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.apmobilesecuritysdk.d.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1966c {
    /* renamed from: a */
    public static Map<String, String> m21040a(Context context) {
        C2087b m20555a = C2087b.m20555a();
        HashMap hashMap = new HashMap();
        C1974f m21018a = C1973e.m21018a(context);
        String m20554a = C2087b.m20554a(context);
        String m20551b = C2087b.m20551b(context);
        String m20533k = C2087b.m20533k(context);
        String m20529m = C2087b.m20529m(context);
        if (m21018a != null) {
            if (C2081a.m20577a(m20554a)) {
                m20554a = m21018a.m21017a();
            }
            if (C2081a.m20577a(m20551b)) {
                m20551b = m21018a.m21015b();
            }
            if (C2081a.m20577a(m20533k)) {
                m20533k = m21018a.m21013c();
            }
            if (C2081a.m20577a(m20529m)) {
                m20529m = m21018a.m21009e();
            }
        }
        C1974f c1974f = new C1974f(m20554a, m20551b, m20533k, "", m20529m);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("imei", c1974f.m21017a());
                jSONObject.put("imsi", c1974f.m21015b());
                jSONObject.put("mac", c1974f.m21013c());
                jSONObject.put("bluetoothmac", c1974f.m21011d());
                jSONObject.put("gsi", c1974f.m21009e());
                String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
                C1978a.m20965a("device_feature_file_name", "device_feature_file_key", jSONObject2);
                C1978a.m20967a(context, "device_feature_prefs_name", "device_feature_prefs_key", jSONObject2);
            } catch (Exception e) {
                C1961a.m21046a(e);
            }
        }
        hashMap.put("AD1", m20554a);
        hashMap.put("AD2", m20551b);
        hashMap.put("AD3", C2087b.m20543f(context));
        hashMap.put("AD5", C2087b.m20539h(context));
        hashMap.put("AD6", C2087b.m20537i(context));
        hashMap.put("AD7", C2087b.m20535j(context));
        hashMap.put("AD8", m20533k);
        hashMap.put("AD9", C2087b.m20531l(context));
        hashMap.put("AD10", m20529m);
        hashMap.put("AD11", C2087b.m20548d());
        hashMap.put("AD12", m20555a.m20546e());
        hashMap.put("AD13", C2087b.m20544f());
        hashMap.put("AD14", C2087b.m20540h());
        hashMap.put("AD15", C2087b.m20538i());
        hashMap.put("AD16", C2087b.m20536j());
        hashMap.put("AD17", "");
        hashMap.put("AD19", C2087b.m20527n(context));
        hashMap.put("AD20", C2087b.m20534k());
        hashMap.put("AD22", "");
        hashMap.put("AD23", C2087b.m20523p(context));
        hashMap.put("AD24", C2081a.m20567g(C2087b.m20541g(context)));
        hashMap.put("AD26", C2087b.m20545e(context));
        hashMap.put("AD27", C2087b.m20524p());
        hashMap.put("AD28", C2087b.m20520r());
        hashMap.put("AD29", C2087b.m20516t());
        hashMap.put("AD30", C2087b.m20522q());
        hashMap.put("AD31", C2087b.m20518s());
        hashMap.put("AD32", C2087b.m20528n());
        hashMap.put("AD33", C2087b.m20526o());
        hashMap.put("AD34", C2087b.m20519r(context));
        hashMap.put("AD35", C2087b.m20517s(context));
        hashMap.put("AD36", C2087b.m20521q(context));
        hashMap.put("AD37", C2087b.m20530m());
        hashMap.put("AD38", C2087b.m20532l());
        hashMap.put("AD39", C2087b.m20549c(context));
        hashMap.put("AD40", C2087b.m20547d(context));
        hashMap.put("AD41", C2087b.m20552b());
        hashMap.put("AD42", C2087b.m20550c());
        hashMap.put("AL3", C2087b.m20525o(context));
        return hashMap;
    }
}
