package com.alipay.apmobilesecuritysdk.p107d;

import android.content.Context;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.security.mobile.module.p112b.C2086a;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.d.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1964a {
    /* renamed from: a */
    public static synchronized Map<String, String> m21042a(Context context, Map<String, String> map) {
        HashMap hashMap;
        synchronized (C1964a.class) {
            String m20574a = C2081a.m20574a(map, "appchannel", "");
            hashMap = new HashMap();
            hashMap.put("AA1", context.getPackageName());
            C2086a.m20557a();
            hashMap.put("AA2", C2086a.m20556a(context));
            hashMap.put("AA3", "APPSecuritySDK-ALIPAYSDK");
            hashMap.put("AA4", "3.4.0.201910161639");
            hashMap.put("AA6", m20574a);
        }
        return hashMap;
    }
}
