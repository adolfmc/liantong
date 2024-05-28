package com.alipay.apmobilesecuritysdk.p107d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p108e.C1976h;
import com.alipay.security.mobile.module.p110a.C2081a;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.d.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1965b {
    /* renamed from: a */
    public static synchronized Map<String, String> m21041a(Context context, Map<String, String> map) {
        HashMap hashMap;
        synchronized (C1965b.class) {
            hashMap = new HashMap();
            String m20574a = C2081a.m20574a(map, "tid", "");
            String m20574a2 = C2081a.m20574a(map, "utdid", "");
            String m20574a3 = C2081a.m20574a(map, "userId", "");
            String m20574a4 = C2081a.m20574a(map, "appName", "");
            String m20574a5 = C2081a.m20574a(map, "appKeyClient", "");
            String m20574a6 = C2081a.m20574a(map, "tmxSessionId", "");
            String m20990f = C1976h.m20990f(context);
            String m20574a7 = C2081a.m20574a(map, "sessionId", "");
            hashMap.put("AC1", m20574a);
            hashMap.put("AC2", m20574a2);
            hashMap.put("AC3", "");
            hashMap.put("AC4", m20990f);
            hashMap.put("AC5", m20574a3);
            hashMap.put("AC6", m20574a6);
            hashMap.put("AC7", "");
            hashMap.put("AC8", m20574a4);
            hashMap.put("AC9", m20574a5);
            if (C2081a.m20573b(m20574a7)) {
                hashMap.put("AC10", m20574a7);
            }
        }
        return hashMap;
    }
}
