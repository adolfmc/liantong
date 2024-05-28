package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.EnumC11409gk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(24)
/* renamed from: com.xiaomi.push.service.ad */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11525ad {

    /* renamed from: a */
    private static C11525ad f23472a = new C11525ad();

    /* renamed from: a */
    private SpannableString f23473a;

    /* renamed from: a */
    public static C11525ad m2802a() {
        return f23472a;
    }

    private C11525ad() {
    }

    /* renamed from: a */
    private boolean m2801a() {
        return Build.VERSION.SDK_INT >= 24;
    }

    /* renamed from: a */
    private boolean m2798a(Context context) {
        if (m2785b(context) && C11533af.m2761a(context)) {
            return C11537ah.m2715a(context).m2716a(EnumC11409gk.LatestNotificationNotIntoGroupSwitch.m3637a(), false);
        }
        return false;
    }

    /* renamed from: b */
    private boolean m2785b(Context context) {
        return C11537ah.m2715a(context).m2716a(EnumC11409gk.NotificationAutoGroupSwitch.m3637a(), true);
    }

    /* renamed from: a */
    private String m2800a(Notification notification) {
        if (notification == null || notification.extras == null) {
            return null;
        }
        return notification.extras.getString("push_src_group_name");
    }

    /* renamed from: b */
    private String m2787b(Notification notification) {
        if (notification == null) {
            return null;
        }
        return m2786b(notification) ? m2800a(notification) : notification.getGroup();
    }

    /* renamed from: a */
    private boolean m2799a(Notification notification) {
        if (notification != null) {
            Object m4812a = C11176aw.m4812a((Object) notification, "isGroupSummary", (Object[]) null);
            if (m4812a instanceof Boolean) {
                return ((Boolean) m4812a).booleanValue();
            }
            return false;
        }
        return false;
    }

    /* renamed from: a */
    public String m2795a(Context context, Notification.Builder builder, String str) {
        if (m2801a() && m2798a(context)) {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle extras = builder.getExtras();
            extras.putString("push_src_group_name", str);
            extras.putLong("push_src_group_time", currentTimeMillis);
            return String.format("pushmask_%s_%s", Long.valueOf(currentTimeMillis), str);
        }
        return str;
    }

    /* renamed from: b */
    private boolean m2786b(Notification notification) {
        if (notification == null || notification.getGroup() == null || notification.extras == null) {
            return false;
        }
        long j = notification.extras.getLong("push_src_group_time");
        return notification.getGroup().equals(String.format("pushmask_%s_%s", Long.valueOf(j), m2800a(notification)));
    }

    /* renamed from: a */
    public void m2797a(Context context, int i, Notification notification) {
        if (m2801a()) {
            if (m2798a(context)) {
                try {
                    m2784b(context, i, notification);
                } catch (Exception e) {
                    AbstractC11049b.m5282a("group notify handle restore error " + e);
                }
            }
            if (m2785b(context)) {
                try {
                    m2796a(context, i, notification, true);
                } catch (Exception e2) {
                    AbstractC11049b.m5282a("group notify handle auto error " + e2);
                }
            }
        }
    }

    /* renamed from: b */
    private void m2784b(Context context, int i, Notification notification) {
        String m2722c = C11534ag.m2722c(notification);
        if (TextUtils.isEmpty(m2722c)) {
            AbstractC11049b.m5282a("group restore not extract pkg from notification:" + i);
            return;
        }
        C11533af m2760a = C11533af.m2760a(context, m2722c);
        List<StatusBarNotification> m2790a = m2790a(m2760a);
        if (m2790a == null) {
            AbstractC11049b.m5282a("group restore not get notifications");
            return;
        }
        for (StatusBarNotification statusBarNotification : m2790a) {
            Notification notification2 = statusBarNotification.getNotification();
            if (notification2 != null && m2786b(notification2) && statusBarNotification.getId() != i) {
                Notification.Builder recoverBuilder = Notification.Builder.recoverBuilder(context, statusBarNotification.getNotification());
                recoverBuilder.setGroup(m2800a(notification2));
                C11534ag.m2744a(recoverBuilder, m2799a(notification2));
                m2760a.m2765a(statusBarNotification.getId(), recoverBuilder.build());
                AbstractC11049b.m5274b("group restore notification:" + statusBarNotification.getId());
            }
        }
    }

    /* renamed from: a */
    private void m2796a(Context context, int i, Notification notification, boolean z) {
        Notification notification2;
        String m2722c = C11534ag.m2722c(notification);
        if (TextUtils.isEmpty(m2722c)) {
            AbstractC11049b.m5282a("group auto not extract pkg from notification:" + i);
            return;
        }
        List<StatusBarNotification> m2790a = m2790a(C11533af.m2760a(context, m2722c));
        if (m2790a == null) {
            AbstractC11049b.m5282a("group auto not get notifications");
            return;
        }
        String m2787b = m2787b(notification);
        HashMap hashMap = new HashMap();
        for (StatusBarNotification statusBarNotification : m2790a) {
            if (statusBarNotification.getNotification() != null && statusBarNotification.getId() != i) {
                m2788a(hashMap, statusBarNotification);
            }
        }
        for (Map.Entry<String, C11527a> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            if (!TextUtils.isEmpty(key)) {
                C11527a value = entry.getValue();
                if (z && key.equals(m2787b) && !m2786b(notification)) {
                    C11528b c11528b = new C11528b(i, notification);
                    if (m2799a(notification)) {
                        value.f23476b.add(c11528b);
                    } else {
                        value.f23475a.add(c11528b);
                    }
                }
                int size = value.f23475a.size();
                if (value.f23476b.size() <= 0) {
                    if (z && size >= 2) {
                        m2791a(context, m2722c, key, value.f23475a.get(0).f23478a);
                    }
                } else if (size <= 0) {
                    m2792a(context, m2722c, key);
                } else if (C11537ah.m2715a(context).m2716a(EnumC11409gk.NotificationGroupUpdateTimeSwitch.m3637a(), false) && (notification2 = value.f23476b.get(0).f23478a) != null) {
                    notification2.when = System.currentTimeMillis();
                    m2791a(context, m2722c, key, notification2);
                }
            }
        }
    }

    /* renamed from: a */
    private void m2788a(Map<String, C11527a> map, StatusBarNotification statusBarNotification) {
        String m2787b = m2787b(statusBarNotification.getNotification());
        C11527a c11527a = map.get(m2787b);
        if (c11527a == null) {
            c11527a = new C11527a();
            map.put(m2787b, c11527a);
        }
        C11528b c11528b = new C11528b(statusBarNotification.getId(), statusBarNotification.getNotification());
        if (m2799a(statusBarNotification.getNotification())) {
            c11527a.f23476b.add(c11528b);
        } else {
            c11527a.f23475a.add(c11528b);
        }
    }

    /* renamed from: a */
    private SpannableString m2793a(Context context, String str) {
        Resources resources;
        DisplayMetrics displayMetrics;
        int max;
        if (this.f23473a == null) {
            int i = 200;
            if (context != null && (resources = context.getResources()) != null && (displayMetrics = resources.getDisplayMetrics()) != null && (max = Math.max(displayMetrics.heightPixels, displayMetrics.widthPixels)) > 0) {
                i = max / 16;
            }
            if (TextUtils.isEmpty(str)) {
                str = "新消息";
            }
            StringBuilder sb = new StringBuilder(str.length() + i + 12);
            sb.append(str);
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(' ');
            }
            sb.append("GroupSummary");
            SpannableString spannableString = new SpannableString(sb.toString());
            spannableString.setSpan(new ForegroundColorSpan(0), str.length(), sb.length(), 33);
            this.f23473a = spannableString;
        }
        return this.f23473a;
    }

    /* renamed from: a */
    private void m2791a(Context context, String str, String str2, Notification notification) {
        Notification.Builder defaults;
        try {
            if (TextUtils.isEmpty(str2)) {
                AbstractC11049b.m5282a("group show summary group is null");
                return;
            }
            int m2734a = C11534ag.m2734a(context, str);
            if (m2734a == 0) {
                AbstractC11049b.m5282a("group show summary not get icon from " + str);
                return;
            }
            C11533af m2760a = C11533af.m2760a(context, str);
            if (Build.VERSION.SDK_INT >= 26) {
                String m2746c = m2760a.m2746c(notification.getChannelId(), "groupSummary");
                NotificationChannel m2757a = m2760a.m2757a(m2746c);
                if ("groupSummary".equals(m2746c) && m2757a == null) {
                    m2760a.m2764a(new NotificationChannel(m2746c, "group_summary", 3));
                }
                defaults = new Notification.Builder(context, m2746c);
            } else {
                defaults = new Notification.Builder(context).setPriority(0).setDefaults(-1);
            }
            C11534ag.m2744a(defaults, true);
            Notification build = defaults.setContentTitle(m2793a(context, "新消息")).setContentText("你有一条新消息").setSmallIcon(Icon.createWithResource(str, m2734a)).setAutoCancel(true).setGroup(str2).setGroupSummary(true).build();
            if (Build.VERSION.SDK_INT >= 31) {
                build.contentIntent = m2794a(context, str);
            }
            if (!C11469j.m2960c() && "com.xiaomi.xmsf".equals(context.getPackageName())) {
                C11534ag.m2738a(build, str);
            }
            int m2789a = m2789a(str, str2);
            m2760a.m2765a(m2789a, build);
            AbstractC11049b.m5274b("group show summary notify:" + m2789a);
        } catch (Exception e) {
            AbstractC11049b.m5282a("group show summary error " + e);
        }
    }

    /* renamed from: a */
    private PendingIntent m2794a(Context context, String str) {
        if (context == null && TextUtils.isEmpty(str)) {
            AbstractC11049b.m5282a("ctx or pkg must not be null in getting launch intent");
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                AbstractC11049b.m5282a("pm must not be null in getting launch intent");
                return null;
            }
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                AbstractC11049b.m5282a("targetIntent must not be null in getting launch intent");
                return null;
            }
            launchIntentForPackage.addFlags(268435456);
            if (Build.VERSION.SDK_INT >= 31) {
                return PendingIntent.getActivity(context, 0, launchIntentForPackage, 33554432);
            }
            return PendingIntent.getActivity(context, 0, launchIntentForPackage, 0);
        } catch (Throwable th) {
            AbstractC11049b.m5268d("error occurred during getting launch pendingIntent. exception:" + th);
            return null;
        }
    }

    /* renamed from: a */
    private void m2792a(Context context, String str, String str2) {
        AbstractC11049b.m5274b("group cancel summary:" + str2);
        C11533af.m2760a(context, str).m2766a(m2789a(str, str2));
    }

    /* renamed from: a */
    private int m2789a(String str, String str2) {
        return ("GroupSummary" + str + str2).hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.ad$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11527a {

        /* renamed from: a */
        List<C11528b> f23475a;

        /* renamed from: b */
        List<C11528b> f23476b;

        private C11527a() {
            this.f23475a = new ArrayList();
            this.f23476b = new ArrayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.ad$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11528b {

        /* renamed from: a */
        int f23477a;

        /* renamed from: a */
        Notification f23478a;

        public C11528b(int i, Notification notification) {
            this.f23477a = i;
            this.f23478a = notification;
        }

        public String toString() {
            return "id:" + this.f23477a;
        }
    }

    /* renamed from: a */
    private List<StatusBarNotification> m2790a(C11533af c11533af) {
        List<StatusBarNotification> m2749b = c11533af != null ? c11533af.m2749b() : null;
        if (m2749b == null || m2749b.size() == 0) {
            return null;
        }
        return m2749b;
    }
}
