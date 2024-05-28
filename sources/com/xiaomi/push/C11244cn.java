package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.C11603m;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.cn */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11244cn {

    /* renamed from: a */
    private static int f21723a = -1;

    /* renamed from: a */
    private static C11250cr f21724a;

    /* renamed from: a */
    private static String f21725a;

    /* renamed from: a */
    public static void m4519a(Context context, AbstractC11356fa abstractC11356fa) {
        if (m4520a(context)) {
            if (f21724a == null) {
                f21724a = new C11250cr(context);
            }
            abstractC11356fa.m3891a(f21724a);
            m4516a("startStats");
        }
    }

    /* renamed from: b */
    public static void m4515b(Context context, AbstractC11356fa abstractC11356fa) {
        C11250cr c11250cr = f21724a;
        if (c11250cr != null) {
            abstractC11356fa.m3879b(c11250cr);
            f21724a = null;
            m4516a("stopStats");
        }
    }

    /* renamed from: a */
    private static boolean m4520a(Context context) {
        return C11241ck.m4526a(context);
    }

    /* renamed from: a */
    public static void m4517a(Context context, String str, int i) {
        if (!m4520a(context)) {
            m4516a("onDisconnection shouldSampling = false");
            return;
        }
        C11247cq.m4480a(context, str, C11169au.m4835b(context), System.currentTimeMillis(), i, C11603m.m2511a(context).m2505b(), m4522a(context), m4523a(), f21723a);
        m4516a("onDisconnection");
    }

    /* renamed from: a */
    public static void m4521a(Context context) {
        if (!m4520a(context)) {
            m4516a("onReconnection shouldSampling = false");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        f21723a = m4522a(context);
        C11247cq.m4482a(context, currentTimeMillis);
        m4516a("onReconnection connectedNetworkType = " + f21723a);
    }

    /* renamed from: a */
    public static void m4518a(Context context, String str) {
        if (!m4520a(context)) {
            m4516a("onWifiChanged shouldSampling = false");
            return;
        }
        m4516a("onWifiChanged wifiDigest = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        m4514b("W-" + str);
    }

    /* renamed from: a */
    private static int m4522a(Context context) {
        String str;
        try {
            C11175av m4855a = C11169au.m4855a();
            if (m4855a != null) {
                if (m4855a.m4826a() == 0) {
                    String m4819b = m4855a.m4819b();
                    if (TextUtils.isEmpty(m4819b) || "UNKNOWN".equalsIgnoreCase(m4819b)) {
                        str = null;
                    } else {
                        str = "M-" + m4819b;
                    }
                    m4514b(str);
                    return 0;
                }
                if (m4855a.m4826a() != 1 && m4855a.m4826a() != 6) {
                    m4514b(null);
                    return -1;
                }
                m4514b("WIFI-ID-UNKNOWN");
                return 1;
            }
            m4514b(null);
            return -1;
        } catch (Exception e) {
            AbstractC11049b.m5268d("DisconnectStatsHelper getNetType occurred error: " + e.getMessage());
            m4514b(null);
            return -1;
        }
    }

    /* renamed from: b */
    private static synchronized void m4514b(String str) {
        synchronized (C11244cn.class) {
            if ("WIFI-ID-UNKNOWN".equals(str)) {
                if (f21725a == null || !f21725a.startsWith("W-")) {
                    f21725a = null;
                }
            } else {
                f21725a = str;
            }
            m4516a("updateNetId new networkId = " + str + ", finally netId = " + f21725a);
        }
    }

    /* renamed from: a */
    private static synchronized String m4523a() {
        String str;
        synchronized (C11244cn.class) {
            str = f21725a;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m4516a(String str) {
        C11241ck.m4524a("Push-DiscntStats", str);
    }
}
