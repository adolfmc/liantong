package com.alipay.apmobilesecuritysdk.p107d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p106c.C1962b;
import com.alipay.security.mobile.module.p112b.C2087b;
import com.alipay.security.mobile.module.p112b.C2089d;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.d.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1967d {
    /* renamed from: a */
    public static synchronized Map<String, String> m21039a() {
        HashMap hashMap;
        synchronized (C1967d.class) {
            hashMap = new HashMap();
            try {
                new C1962b();
                hashMap.put("AE16", "");
            } catch (Throwable unused) {
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static synchronized Map<String, String> m21038a(Context context) {
        HashMap hashMap;
        synchronized (C1967d.class) {
            C2089d.m20510a();
            C2087b.m20555a();
            hashMap = new HashMap();
            hashMap.put("AE1", C2089d.m20507b());
            StringBuilder sb = new StringBuilder();
            sb.append(C2089d.m20506c() ? "1" : "0");
            hashMap.put("AE2", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(C2089d.m20509a(context) ? "1" : "0");
            hashMap.put("AE3", sb2.toString());
            hashMap.put("AE4", C2089d.m20505d());
            hashMap.put("AE5", C2089d.m20504e());
            hashMap.put("AE6", C2089d.m20503f());
            hashMap.put("AE7", C2089d.m20502g());
            hashMap.put("AE8", C2089d.m20501h());
            hashMap.put("AE9", C2089d.m20500i());
            hashMap.put("AE10", C2089d.m20499j());
            hashMap.put("AE11", C2089d.m20498k());
            hashMap.put("AE12", C2089d.m20497l());
            hashMap.put("AE13", C2089d.m20496m());
            hashMap.put("AE14", C2089d.m20495n());
            hashMap.put("AE15", C2089d.m20494o());
            hashMap.put("AE21", C2087b.m20542g());
        }
        return hashMap;
    }
}
