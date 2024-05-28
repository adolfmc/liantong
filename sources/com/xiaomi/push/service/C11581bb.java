package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.C11480s;
import com.xiaomi.push.Notification$BuilderC11307dv;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.bb */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11581bb {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2576a(Context context, Map<String, String> map, Notification$BuilderC11307dv notification$BuilderC11307dv, long j) {
        if (map == null || notification$BuilderC11307dv == null || !C11469j.m2972a(context) || !m2574a(map)) {
            return;
        }
        int m2575a = m2575a(map);
        int m2571b = m2571b(map);
        if (m2575a > 0 && m2571b <= m2575a) {
            notification$BuilderC11307dv.setPriority(2);
            Bundle bundle = new Bundle();
            bundle.putLong("mipush_org_when", j);
            bundle.putBoolean("mipush_n_top_flag", true);
            if (m2571b > 0) {
                bundle.putInt("mipush_n_top_fre", m2571b);
            }
            bundle.putInt("mipush_n_top_prd", m2575a);
            notification$BuilderC11307dv.addExtras(bundle);
            return;
        }
        AbstractC11049b.m5268d("set top notification failed - period:" + m2575a + " frequency:" + m2571b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(19)
    /* renamed from: a */
    public static void m2577a(Context context, String str, int i, String str2, Notification notification) {
        if (C11469j.m2972a(context) && notification != null && notification.extras.getBoolean("mipush_n_top_flag", false)) {
            m2570c(context, str, i, str2, notification);
        }
    }

    /* renamed from: a */
    private static boolean m2574a(Map<String, String> map) {
        String str = map.get("notification_top_repeat");
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        boolean parseBoolean = Boolean.parseBoolean(str);
        AbstractC11049b.m5270c("top notification' repeat is " + parseBoolean);
        return parseBoolean;
    }

    /* renamed from: a */
    private static int m2575a(Map<String, String> map) {
        return Math.max(0, C11480s.m2923a(map.get("notification_top_period"), 0));
    }

    /* renamed from: b */
    private static int m2571b(Map<String, String> map) {
        return Math.max(0, C11480s.m2923a(map.get("notification_top_frequency"), 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(19)
    /* renamed from: c */
    public static void m2570c(Context context, String str, int i, String str2, Notification notification) {
        C11533af m2760a;
        Notification m2579a;
        int i2;
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || Build.VERSION.SDK_INT < 26 || (m2579a = m2579a(notification, i, str2, (m2760a = C11533af.m2760a(context, str)))) == null) {
            return;
        }
        boolean z = notification != null;
        if (m2579a.getGroupAlertBehavior() != 1) {
            C11176aw.m4813a((Object) m2579a, "mGroupAlertBehavior", (Object) 1);
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = m2579a.extras.getLong("mipush_org_when", 0L);
        int i3 = m2579a.extras.getInt("mipush_n_top_fre", 0);
        int i4 = m2579a.extras.getInt("mipush_n_top_prd", 0);
        if (i4 <= 0 || i4 < i3) {
            return;
        }
        long j2 = (i4 * 1000) + j;
        if (j >= currentTimeMillis || currentTimeMillis >= j2) {
            i2 = 0;
        } else {
            i2 = i3 > 0 ? (int) Math.min((j2 - currentTimeMillis) / 1000, i3) : i4;
        }
        if (!z) {
            if (i2 > 0) {
                m2579a.when = currentTimeMillis;
                AbstractC11049b.m5282a("update top notification: " + str2);
                m2760a.m2765a(i, m2579a);
            } else {
                Notification.Builder recoverBuilder = Notification.Builder.recoverBuilder(context, m2579a);
                recoverBuilder.setPriority(0);
                recoverBuilder.setWhen(currentTimeMillis);
                Bundle extras = recoverBuilder.getExtras();
                if (extras != null) {
                    extras.remove("mipush_n_top_flag");
                    extras.remove("mipush_org_when");
                    extras.remove("mipush_n_top_fre");
                    extras.remove("mipush_n_top_prd");
                    recoverBuilder.setExtras(extras);
                }
                AbstractC11049b.m5282a("update top notification to common: " + str2);
                m2760a.m2765a(i, recoverBuilder.build());
            }
        }
        if (i2 > 0) {
            AbstractC11049b.m5282a("schedule top notification next update delay: " + i2);
            C11134ae.m4937a(context).m4925a(m2573b(i, str2));
            C11134ae.m4937a(context).m4924b(m2578a(context, str, i, str2, (Notification) null), i2);
        }
    }

    @TargetApi(19)
    /* renamed from: a */
    private static Notification m2579a(Notification notification, int i, String str, C11533af c11533af) {
        if (notification != null) {
            if (str.equals(notification.extras.getString("message_id"))) {
                return notification;
            }
            return null;
        }
        List<StatusBarNotification> m2749b = c11533af.m2749b();
        if (m2749b != null) {
            for (StatusBarNotification statusBarNotification : m2749b) {
                Notification notification2 = statusBarNotification.getNotification();
                String string = notification2.extras.getString("message_id");
                if (i == statusBarNotification.getId() && str.equals(string)) {
                    return notification2;
                }
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m2573b(int i, String str) {
        return "n_top_update_" + i + "_" + str;
    }

    /* renamed from: a */
    private static C11134ae.AbstractRunnableC11137a m2578a(final Context context, final String str, final int i, final String str2, final Notification notification) {
        return new C11134ae.AbstractRunnableC11137a() { // from class: com.xiaomi.push.service.bb.1
            @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
            /* renamed from: a */
            public String mo2289a() {
                return C11581bb.m2573b(i, str2);
            }

            @Override // java.lang.Runnable
            @TargetApi(19)
            public void run() {
                C11581bb.m2570c(context, str, i, str2, notification);
            }
        };
    }
}
