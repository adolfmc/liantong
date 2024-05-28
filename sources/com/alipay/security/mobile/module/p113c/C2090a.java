package com.alipay.security.mobile.module.p113c;

import android.content.Context;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.security.mobile.module.p110a.p111a.C2084c;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.c.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2090a {
    /* renamed from: a */
    public static String m20493a(Context context, String str, String str2) {
        String m20487a;
        synchronized (C2090a.class) {
            String str3 = null;
            if (context != null) {
                if (!C2081a.m20577a(str) && !C2081a.m20577a(str2)) {
                    try {
                        m20487a = C2094e.m20487a(context, str, str2, "");
                    } catch (Throwable unused) {
                    }
                    if (C2081a.m20577a(m20487a)) {
                        return null;
                    }
                    str3 = C2084c.m20559b(C2084c.m20564a(), m20487a);
                    return str3;
                }
            }
            return null;
        }
    }

    /* renamed from: a */
    public static void m20492a(Context context, String str, String str2, String str3) {
        synchronized (C2090a.class) {
            if (C2081a.m20577a(str) || C2081a.m20577a(str2) || context == null) {
                return;
            }
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
