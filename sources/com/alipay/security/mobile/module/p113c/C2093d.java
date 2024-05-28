package com.alipay.security.mobile.module.p113c;

import android.content.Context;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.security.mobile.module.p110a.p111a.C2084c;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.c.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2093d {
    /* renamed from: a */
    public static synchronized void m20488a(Context context, String str, String str2, String str3) {
        synchronized (C2093d.class) {
            if (!C2081a.m20577a(str)) {
                if (!C2081a.m20577a(str2) && context != null) {
                    try {
                        String m20562a = C2084c.m20562a(C2084c.m20564a(), str3);
                        HashMap hashMap = new HashMap();
                        hashMap.put(str2, m20562a);
                        C2094e.m20486a(context, str, hashMap);
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }
}
