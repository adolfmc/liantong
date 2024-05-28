package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.cw */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11256cw {

    /* renamed from: a */
    private static int f21799a = 0;

    /* renamed from: a */
    private static boolean f21800a = true;

    /* renamed from: a */
    private static int m4416a(boolean z) {
        return z ? 1 : 0;
    }

    /* renamed from: a */
    private static SharedPreferences m4423a(Context context) {
        return context.getSharedPreferences("sp_power_stats", 0);
    }

    /* renamed from: a */
    public static void m4418a(final Context context, final long j, final boolean z) {
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.push.cw.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    C11256cw.m4407i(context, j, z);
                } catch (Exception e) {
                    AbstractC11049b.m5282a("PowerStatsSP onSendMsg exception: " + e.getMessage());
                }
            }
        });
    }

    /* renamed from: b */
    public static void m4414b(final Context context, final long j, final boolean z) {
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.push.cw.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    C11256cw.m4406j(context, j, z);
                } catch (Exception e) {
                    AbstractC11049b.m5282a("PowerStatsSP onReceiveMsg exception: " + e.getMessage());
                }
            }
        });
    }

    /* renamed from: c */
    public static void m4413c(final Context context, final long j, final boolean z) {
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.push.cw.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    C11256cw.m4405k(context, j, z);
                } catch (Exception e) {
                    AbstractC11049b.m5282a("PowerStatsSP onPing exception: " + e.getMessage());
                }
            }
        });
    }

    /* renamed from: d */
    public static void m4412d(final Context context, final long j, final boolean z) {
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.push.cw.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    C11256cw.m4404l(context, j, z);
                } catch (Exception e) {
                    AbstractC11049b.m5282a("PowerStatsSP onPong exception: " + e.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public static synchronized void m4407i(Context context, long j, boolean z) {
        int i;
        synchronized (C11256cw.class) {
            C11253ct.m4459a("recordSendMsg start");
            int m4416a = m4416a(z);
            SharedPreferences m4423a = m4423a(context);
            long j2 = m4423a.getLong("start_time", 0L);
            if (j2 <= 0) {
                m4417a(context, m4423a, j, m4416a);
            }
            if (m4416a == 1) {
                int i2 = m4423a.getInt("on_up_count", 0) + 1;
                m4423a.edit().putInt("on_up_count", i2).apply();
                i = i2;
            } else {
                int i3 = m4423a.getInt("off_up_count", 0) + 1;
                m4423a.edit().putInt("off_up_count", i3).apply();
                i = i3;
            }
            m4419a(context, j2, j, i, m4416a);
            C11253ct.m4459a("recordSendMsg complete");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public static synchronized void m4406j(Context context, long j, boolean z) {
        int i;
        synchronized (C11256cw.class) {
            C11253ct.m4459a("recordReceiveMsg start");
            int m4416a = m4416a(z);
            SharedPreferences m4423a = m4423a(context);
            long j2 = m4423a.getLong("start_time", 0L);
            if (j2 <= 0) {
                m4417a(context, m4423a, j, m4416a);
            }
            if (m4416a == 1) {
                int i2 = m4423a.getInt("on_down_count", 0) + 1;
                m4423a.edit().putInt("on_down_count", i2).apply();
                i = i2;
            } else {
                int i3 = m4423a.getInt("off_down_count", 0) + 1;
                m4423a.edit().putInt("off_down_count", i3).apply();
                i = i3;
            }
            m4419a(context, j2, j, i, m4416a);
            C11253ct.m4459a("recordReceiveMsg complete");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public static synchronized void m4405k(Context context, long j, boolean z) {
        int i;
        synchronized (C11256cw.class) {
            C11253ct.m4459a("recordPing start");
            int m4416a = m4416a(z);
            SharedPreferences m4423a = m4423a(context);
            long j2 = m4423a.getLong("start_time", 0L);
            if (j2 <= 0) {
                m4417a(context, m4423a, j, m4416a);
            }
            if (m4416a == 1) {
                int i2 = m4423a.getInt("on_ping_count", 0) + 1;
                m4423a.edit().putInt("on_ping_count", i2).apply();
                i = i2;
            } else {
                int i3 = m4423a.getInt("off_ping_count", 0) + 1;
                m4423a.edit().putInt("off_ping_count", i3).apply();
                i = i3;
            }
            m4419a(context, j2, j, i, m4416a);
            C11253ct.m4459a("recordPing complete");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public static synchronized void m4404l(Context context, long j, boolean z) {
        int i;
        synchronized (C11256cw.class) {
            C11253ct.m4459a("recordPong start");
            int m4416a = m4416a(z);
            SharedPreferences m4423a = m4423a(context);
            long j2 = m4423a.getLong("start_time", 0L);
            if (j2 <= 0) {
                m4417a(context, m4423a, j, m4416a);
            }
            if (m4416a == 1) {
                int i2 = m4423a.getInt("on_pong_count", 0) + 1;
                m4423a.edit().putInt("on_pong_count", i2).apply();
                i = i2;
            } else {
                int i3 = m4423a.getInt("off_pong_count", 0) + 1;
                m4423a.edit().putInt("off_pong_count", i3).apply();
                i = i3;
            }
            m4419a(context, j2, j, i, m4416a);
            C11253ct.m4459a("recordPong complete");
        }
    }

    /* renamed from: a */
    private static void m4417a(Context context, SharedPreferences sharedPreferences, long j, int i) {
        C11253ct.m4459a("recordInit");
        sharedPreferences.edit().putLong("start_time", j).putInt("current_screen_state", i).putLong("current_screen_state_start_time", j).putInt("xmsf_vc", m4424a(context)).putInt("android_vc", Build.VERSION.SDK_INT).apply();
    }

    /* renamed from: a */
    private static void m4419a(Context context, long j, long j2, int i, int i2) {
        if (j > 0) {
            if (m4421a(context) || i >= 1073741823 || j2 - j >= 86400000) {
                m4423a(context).edit().putLong("end_time", j2).apply();
                m4420a(context, j2, i2);
            }
        }
    }

    /* renamed from: a */
    private static void m4420a(Context context, long j, int i) {
        C11253ct.m4459a("upload");
        new C11255cv().m4425a(context, m4422a(context));
        m4415b(context, j, i);
    }

    /* renamed from: a */
    private static C11254cu m4422a(Context context) {
        SharedPreferences m4423a = m4423a(context);
        C11254cu c11254cu = new C11254cu();
        c11254cu.m4451a(m4423a.getInt("off_up_count", 0));
        c11254cu.m4447b(m4423a.getInt("off_down_count", 0));
        c11254cu.m4443c(m4423a.getInt("off_ping_count", 0));
        c11254cu.m4439d(m4423a.getInt("off_pong_count", 0));
        c11254cu.m4450a(m4423a.getLong("off_duration", 0L));
        c11254cu.m4436e(m4423a.getInt("on_up_count", 0));
        c11254cu.m4434f(m4423a.getInt("on_down_count", 0));
        c11254cu.m4432g(m4423a.getInt("on_ping_count", 0));
        c11254cu.m4430h(m4423a.getInt("on_pong_count", 0));
        c11254cu.m4446b(m4423a.getLong("on_duration", 0L));
        c11254cu.m4442c(m4423a.getLong("start_time", 0L));
        c11254cu.m4438d(m4423a.getLong("end_time", 0L));
        c11254cu.m4428i(m4423a.getInt("xmsf_vc", 0));
        c11254cu.m4426j(m4423a.getInt("android_vc", 0));
        return c11254cu;
    }

    /* renamed from: b */
    private static void m4415b(Context context, long j, int i) {
        C11253ct.m4459a("reset");
        m4423a(context).edit().clear().putLong("start_time", j).putInt("current_screen_state", i).putLong("current_screen_state_start_time", j).putInt("xmsf_vc", m4424a(context)).putInt("android_vc", Build.VERSION.SDK_INT).apply();
    }

    /* renamed from: a */
    private static boolean m4421a(Context context) {
        boolean z = false;
        if (f21800a) {
            f21800a = false;
            SharedPreferences m4423a = m4423a(context);
            int i = m4423a.getInt("xmsf_vc", 0);
            int i2 = m4423a.getInt("android_vc", 0);
            if (i != 0 && i2 != 0 && (i != m4424a(context) || i2 != Build.VERSION.SDK_INT)) {
                z = true;
            }
        }
        C11253ct.m4459a("isVcChanged = " + z);
        return z;
    }

    /* renamed from: a */
    private static int m4424a(Context context) {
        if (f21799a <= 0) {
            f21799a = C11469j.m2964b(context);
        }
        return f21799a;
    }
}
