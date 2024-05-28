package com.alipay.apmobilesecuritysdk.p108e;

import android.content.Context;
import android.content.SharedPreferences;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.security.mobile.module.p110a.p111a.C2084c;
import com.alipay.security.mobile.module.p113c.C2094e;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.e.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1975g {
    /* renamed from: a */
    public static synchronized String m21005a(Context context, String str) {
        synchronized (C1975g.class) {
            String m20487a = C2094e.m20487a(context, "openapi_file_pri", "openApi" + str, "");
            if (C2081a.m20577a(m20487a)) {
                return "";
            }
            String m20559b = C2084c.m20559b(C2084c.m20564a(), m20487a);
            return C2081a.m20577a(m20559b) ? "" : m20559b;
        }
    }

    /* renamed from: a */
    public static synchronized void m21007a() {
        synchronized (C1975g.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m21006a(Context context) {
        synchronized (C1975g.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (edit != null) {
                edit.clear();
                edit.commit();
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m21004a(Context context, String str, String str2) {
        synchronized (C1975g.class) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
                if (edit != null) {
                    edit.putString("openApi" + str, C2084c.m20562a(C2084c.m20564a(), str2));
                    edit.commit();
                }
            } catch (Throwable unused) {
            }
        }
    }
}
