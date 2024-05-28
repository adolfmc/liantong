package com.alipay.apmobilesecuritysdk.p108e;

import android.content.Context;
import android.content.SharedPreferences;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.security.mobile.module.p110a.p111a.C2083b;
import com.alipay.security.mobile.module.p113c.C2090a;
import com.alipay.security.mobile.module.p113c.C2094e;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.e.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1976h {

    /* renamed from: a */
    private static String f3495a = "";

    /* renamed from: a */
    public static long m21003a(Context context) {
        String m20493a = C2090a.m20493a(context, "vkeyid_settings", "update_time_interval");
        if (C2081a.m20573b(m20493a)) {
            try {
                return Long.parseLong(m20493a);
            } catch (Exception unused) {
                return 86400000L;
            }
        }
        return 86400000L;
    }

    /* renamed from: a */
    public static void m21002a(Context context, String str) {
        m21000a(context, "update_time_interval", str);
    }

    /* renamed from: a */
    public static void m21001a(Context context, String str, long j) {
        C2090a.m20492a(context, "vkeyid_settings", "vkey_valid" + str, String.valueOf(j));
    }

    /* renamed from: a */
    private static void m21000a(Context context, String str, String str2) {
        C2090a.m20492a(context, "vkeyid_settings", str, str2);
    }

    /* renamed from: a */
    public static void m20999a(Context context, boolean z) {
        m21000a(context, "log_switch", z ? "1" : "0");
    }

    /* renamed from: b */
    public static String m20998b(Context context) {
        return C2090a.m20493a(context, "vkeyid_settings", "last_apdid_env");
    }

    /* renamed from: b */
    public static void m20997b(Context context, String str) {
        m21000a(context, "last_machine_boot_time", str);
    }

    /* renamed from: c */
    public static void m20995c(Context context, String str) {
        m21000a(context, "last_apdid_env", str);
    }

    /* renamed from: c */
    public static boolean m20996c(Context context) {
        String m20493a = C2090a.m20493a(context, "vkeyid_settings", "log_switch");
        return m20493a != null && "1".equals(m20493a);
    }

    /* renamed from: d */
    public static String m20994d(Context context) {
        return C2090a.m20493a(context, "vkeyid_settings", "dynamic_key");
    }

    /* renamed from: d */
    public static void m20993d(Context context, String str) {
        m21000a(context, "agent_switch", str);
    }

    /* renamed from: e */
    public static String m20992e(Context context) {
        return C2090a.m20493a(context, "vkeyid_settings", "apse_degrade");
    }

    /* renamed from: e */
    public static void m20991e(Context context, String str) {
        m21000a(context, "dynamic_key", str);
    }

    /* renamed from: f */
    public static String m20990f(Context context) {
        String str;
        SharedPreferences.Editor edit;
        synchronized (C1976h.class) {
            if (C2081a.m20577a(f3495a)) {
                String m20487a = C2094e.m20487a(context, "alipay_vkey_random", "random", "");
                f3495a = m20487a;
                if (C2081a.m20577a(m20487a)) {
                    f3495a = C2083b.m20565a(UUID.randomUUID().toString());
                    String str2 = f3495a;
                    if (str2 != null && (edit = context.getSharedPreferences("alipay_vkey_random", 0).edit()) != null) {
                        edit.putString("random", str2);
                        edit.commit();
                    }
                }
            }
            str = f3495a;
        }
        return str;
    }

    /* renamed from: f */
    public static void m20989f(Context context, String str) {
        m21000a(context, "webrtc_url", str);
    }

    /* renamed from: g */
    public static void m20988g(Context context, String str) {
        m21000a(context, "apse_degrade", str);
    }

    /* renamed from: h */
    public static long m20987h(Context context, String str) {
        try {
            String m20493a = C2090a.m20493a(context, "vkeyid_settings", "vkey_valid" + str);
            if (C2081a.m20577a(m20493a)) {
                return 0L;
            }
            return Long.parseLong(m20493a);
        } catch (Throwable unused) {
            return 0L;
        }
    }
}
