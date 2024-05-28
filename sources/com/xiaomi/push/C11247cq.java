package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.cq */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11247cq {

    /* renamed from: a */
    private static int f21753a;

    /* renamed from: a */
    private static int m4475a(boolean z) {
        return z ? 1 : 0;
    }

    /* renamed from: a */
    private static SharedPreferences m4485a(Context context) {
        return context.getSharedPreferences("sp_disconnect_stats", 0);
    }

    /* renamed from: a */
    public static void m4480a(final Context context, final String str, final boolean z, final long j, final int i, final long j2, final int i2, final String str2, final int i3) {
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.push.cq.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    C11247cq.m4470c(context, str, z, j, i, j2, i2, str2, i3);
                } catch (Exception e) {
                    AbstractC11049b.m5282a("DisconnectStatsSP onDisconnection exception: " + e.getMessage());
                }
            }
        });
    }

    /* renamed from: a */
    public static void m4482a(final Context context, final long j) {
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.push.cq.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    C11247cq.m4471c(context, j);
                } catch (Exception e) {
                    AbstractC11049b.m5282a("DisconnectStatsSP onReconnection exception: " + e.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static synchronized void m4470c(Context context, String str, boolean z, long j, int i, long j2, int i2, String str2, int i3) {
        synchronized (C11247cq.class) {
            SharedPreferences m4485a = m4485a(context);
            long j3 = m4485a.getLong("start_time_for_day", 0L);
            if (j3 == 0) {
                C11244cn.m4516a("recordDisconnection not initialized");
            } else if (j - m4485a.getLong("last_discnt_time", 0L) < 60000) {
                C11244cn.m4516a("recordDisconnection anti-shake");
            } else {
                if (j - j3 < 86400000) {
                    int i4 = m4485a.getInt("discnt_count_in_day", 0);
                    if (i4 > 100) {
                        C11244cn.m4516a("recordDisconnection count > 100 in 24H cycle,abandon.");
                        return;
                    } else {
                        m4485a.edit().putInt("discnt_count_in_day", i4 + 1).apply();
                    }
                } else {
                    C11244cn.m4516a("recordDisconnection with the current time exceeds 24H cycle, go on.");
                }
                int i5 = m4485a.getInt("discnt_count", 0);
                if (i5 == m4485a.getInt("cnt_count", 0)) {
                    m4481a(context, str, m4475a(z), j, i, j2, i2, str2, i3);
                    m4485a.edit().putLong("last_discnt_time", j).putInt("discnt_count", i5 + 1).apply();
                }
                C11244cn.m4516a("recordDisconnection complete");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static synchronized void m4471c(Context context, long j) {
        synchronized (C11247cq.class) {
            SharedPreferences m4485a = m4485a(context);
            long j2 = m4485a.getLong("start_time_for_day", 0L);
            if (j2 == 0) {
                m4485a.edit().putLong("start_time_for_day", j).putLong("last_discnt_time", 0L).putInt("discnt_count_in_day", 0).putInt("discnt_count", 0).putInt("cnt_count", 0).apply();
                return;
            }
            int i = m4485a.getInt("discnt_count", 0);
            int i2 = m4485a.getInt("cnt_count", 0);
            if (i > i2) {
                m4485a.edit().putInt("cnt_count", i2 + 1).putString("connected_time", m4477a(m4485a.getString("connected_time", null), j)).apply();
            }
            if (j - j2 >= 86400000) {
                m4485a.edit().putLong("start_time_for_day", j).putInt("discnt_count_in_day", 0).apply();
                m4483a(context);
            } else if (i >= 10) {
                m4483a(context);
            }
        }
    }

    /* renamed from: a */
    private static void m4481a(Context context, String str, int i, long j, int i2, long j2, int i3, String str2, int i4) {
        C11244cn.m4516a(String.format(Locale.US, "recordDisconnectInfo host=%s, netState=%d, currentTimeMillis=%d, reason=%d, pingInterval=%d, netType=%d, wifiDigest=%s, connectedNetType=%d", str, Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(i2), Long.valueOf(j2), Integer.valueOf(i3), str2, Integer.valueOf(i4)));
        SharedPreferences m4485a = m4485a(context);
        String string = m4485a.getString("host", null);
        String string2 = m4485a.getString("network_state", null);
        String string3 = m4485a.getString("reason", null);
        String string4 = m4485a.getString("ping_interval", null);
        String string5 = m4485a.getString("network_type", null);
        String string6 = m4485a.getString("wifi_digest", null);
        String string7 = m4485a.getString("connected_network_type", null);
        String string8 = m4485a.getString("disconnect_time", null);
        String string9 = m4485a.getString("xmsf_vc", null);
        String string10 = m4485a.getString("android_vc", null);
        String m4476a = m4476a(string, str);
        String m4478a = m4478a(string2, i);
        String m4478a2 = m4478a(string3, i2);
        String m4477a = m4477a(string4, j2);
        String m4478a3 = m4478a(string5, i3);
        String m4476a2 = m4476a(string6, str2);
        String m4478a4 = m4478a(string7, i4);
        String m4477a2 = m4477a(string8, j);
        m4485a.edit().putString("host", m4476a).putString("network_state", m4478a).putString("reason", m4478a2).putString("ping_interval", m4477a).putString("network_type", m4478a3).putString("wifi_digest", m4476a2).putString("connected_network_type", m4478a4).putString("disconnect_time", m4477a2).putString("xmsf_vc", m4478a(string9, m4486a(context))).putString("android_vc", m4478a(string10, Build.VERSION.SDK_INT)).apply();
    }

    /* renamed from: a */
    private static String m4476a(String str, String str2) {
        str2 = (str2 == null || str2.length() == 0) ? "null" : "null";
        if (str == null || str.length() <= 0) {
            return str2;
        }
        return str + ";" + str2;
    }

    /* renamed from: a */
    private static String m4478a(String str, int i) {
        return m4476a(str, String.valueOf(i));
    }

    /* renamed from: a */
    private static String m4477a(String str, long j) {
        return m4476a(str, String.valueOf(j));
    }

    /* renamed from: a */
    private static void m4483a(Context context) {
        C11244cn.m4516a("upload");
        new C11246cp().m4487a(context, m4484a(context));
        m4474b(context);
    }

    /* renamed from: a */
    private static List<C11245co> m4484a(Context context) {
        SharedPreferences m4485a = m4485a(context);
        String[] m4479a = m4479a(m4485a.getString("host", null));
        if (m4479a == null || m4479a.length <= 0) {
            AbstractC11049b.m5282a("DisconnectStatsSP Cached hosts data is empty,drop.");
            return null;
        }
        String[] m4479a2 = m4479a(m4485a.getString("network_state", null));
        String[] m4479a3 = m4479a(m4485a.getString("reason", null));
        String[] m4479a4 = m4479a(m4485a.getString("ping_interval", null));
        String[] m4479a5 = m4479a(m4485a.getString("network_type", null));
        String[] m4479a6 = m4479a(m4485a.getString("wifi_digest", null));
        String[] m4479a7 = m4479a(m4485a.getString("connected_network_type", null));
        String[] m4479a8 = m4479a(m4485a.getString("disconnect_time", null));
        String[] m4479a9 = m4479a(m4485a.getString("connected_time", null));
        String[] m4479a10 = m4479a(m4485a.getString("xmsf_vc", null));
        String[] m4479a11 = m4479a(m4485a.getString("android_vc", null));
        if (m4479a2 != null && m4479a3 != null && m4479a4 != null && m4479a5 != null && m4479a6 != null && m4479a7 != null && m4479a8 != null && m4479a9 != null && m4479a10 != null && m4479a11 != null && m4479a.length == m4479a2.length && m4479a.length == m4479a3.length && m4479a.length == m4479a4.length && m4479a.length == m4479a5.length && m4479a.length == m4479a6.length && m4479a.length == m4479a7.length && m4479a.length == m4479a8.length && m4479a.length == m4479a9.length && m4479a.length == m4479a10.length && m4479a.length == m4479a11.length) {
            ArrayList arrayList = new ArrayList(m4479a.length);
            int i = 0;
            while (i < m4479a.length) {
                C11245co c11245co = new C11245co();
                c11245co.m4510a(1);
                c11245co.m4508a(m4479a[i]);
                c11245co.m4504b(C11480s.m2923a(m4479a2[i], -1));
                c11245co.m4499c(C11480s.m2923a(m4479a3[i], -1));
                String[] strArr = m4479a2;
                String[] strArr2 = m4479a;
                ArrayList arrayList2 = arrayList;
                c11245co.m4509a(C11480s.m2922a(m4479a4[i], -1L));
                c11245co.m4495d(C11480s.m2923a(m4479a5[i], -1));
                c11245co.m4502b(m4479a6[i]);
                c11245co.m4492e(C11480s.m2923a(m4479a7[i], -1));
                long m2922a = C11480s.m2922a(m4479a8[i], -1L);
                long m2922a2 = C11480s.m2922a(m4479a9[i], -1L);
                c11245co.m4503b(m2922a2 - m2922a);
                c11245co.m4498c(m2922a);
                c11245co.m4494d(m2922a2);
                c11245co.m4490f(C11480s.m2923a(m4479a10[i], -1));
                c11245co.m4488g(C11480s.m2923a(m4479a11[i], -1));
                arrayList2.add(c11245co);
                i++;
                m4479a2 = strArr;
                arrayList = arrayList2;
                m4479a4 = m4479a4;
                m4479a3 = m4479a3;
                m4479a = strArr2;
                m4479a5 = m4479a5;
            }
            return arrayList;
        }
        AbstractC11049b.m5282a("DisconnectStatsSP Cached data incorrect,drop.");
        return null;
    }

    /* renamed from: a */
    private static String[] m4479a(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return str.split(";");
    }

    /* renamed from: b */
    private static void m4474b(Context context) {
        C11244cn.m4516a("resetAfterUpload");
        m4485a(context).edit().putString("host", null).putString("network_state", null).putString("reason", null).putString("ping_interval", null).putString("network_type", null).putString("wifi_digest", null).putString("connected_network_type", null).putString("disconnect_time", null).putString("connected_time", null).putLong("last_discnt_time", 0L).putInt("discnt_count", 0).putInt("cnt_count", 0).putString("xmsf_vc", null).putString("android_vc", null).apply();
    }

    /* renamed from: a */
    private static int m4486a(Context context) {
        if (f21753a <= 0) {
            f21753a = C11469j.m2964b(context);
        }
        return f21753a;
    }
}
