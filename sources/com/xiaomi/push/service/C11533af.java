package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11455i;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.EnumC11409gk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;

/* renamed from: com.xiaomi.push.service.af */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11533af {

    /* renamed from: a */
    private static Context f23487a;

    /* renamed from: a */
    private static Object f23488a;

    /* renamed from: a */
    private static WeakHashMap<Integer, C11533af> f23489a = new WeakHashMap<>();

    /* renamed from: a */
    private static boolean f23490a;

    /* renamed from: a */
    private String f23491a;

    /* renamed from: b */
    private String f23492b;

    private C11533af(String str) {
        this.f23491a = str;
    }

    /* renamed from: a */
    public Context m2771a() {
        return f23487a;
    }

    /* renamed from: a */
    public String m2770a() {
        return this.f23491a;
    }

    public String toString() {
        return "NotificationManagerHelper{" + this.f23491a + "}";
    }

    /* renamed from: a */
    public static C11533af m2760a(Context context, String str) {
        m2762a(context);
        int hashCode = str.hashCode();
        C11533af c11533af = f23489a.get(Integer.valueOf(hashCode));
        if (c11533af == null) {
            C11533af c11533af2 = new C11533af(str);
            f23489a.put(Integer.valueOf(hashCode), c11533af2);
            return c11533af2;
        }
        return c11533af;
    }

    /* renamed from: a */
    public static boolean m2761a(Context context) {
        m2762a(context);
        return m2768a();
    }

    /* renamed from: a */
    public static String m2753a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            String m2752a = m2752a("mipush|%s|%s", str2, "");
            if (str.startsWith(m2752a)) {
                return m2752a("mipush_%s_%s", str2, str.replace(m2752a, ""));
            }
        }
        return str;
    }

    /* renamed from: a */
    private static void m2762a(Context context) {
        if (f23487a == null) {
            f23487a = context.getApplicationContext();
            NotificationManager m2772a = m2772a();
            Boolean bool = (Boolean) C11176aw.m4812a((Object) m2772a, "isSystemConditionProviderEnabled", "xmsf_fake_condition_provider_path");
            m2755a("fwk is support.init:" + bool);
            f23490a = bool != null ? bool.booleanValue() : false;
            if (f23490a) {
                f23488a = C11176aw.m4812a((Object) m2772a, "getService", new Object[0]);
            }
        }
    }

    /* renamed from: a */
    private static NotificationManager m2772a() {
        return (NotificationManager) f23487a.getSystemService("notification");
    }

    /* renamed from: a */
    private static boolean m2768a() {
        if (C11469j.m2974a() && C11537ah.m2715a(f23487a).m2716a(EnumC11409gk.NotificationBelongToAppSwitch.m3637a(), true)) {
            return f23490a;
        }
        return false;
    }

    /* renamed from: a */
    private static int m2758a(String str) {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                return f23487a.getPackageManager().getPackageUid(str, 0);
            } catch (Exception unused) {
                return -1;
            }
        }
        return -1;
    }

    /* renamed from: a */
    private static Object m2751a(List list) {
        return Class.forName("android.content.pm.ParceledListSlice").getConstructor(List.class).newInstance(list);
    }

    /* renamed from: a */
    private static <T> T m2759a(Object obj) {
        if (obj != null) {
            try {
                return (T) obj.getClass().getMethod("getList", new Class[0]).invoke(obj, new Object[0]);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static String m2752a(String str, String str2, String str3) {
        return TextUtils.isEmpty(str) ? "" : String.format(str, str2, str3);
    }

    /* renamed from: b */
    public static String m2747b(String str, String str2) {
        return m2752a(m2768a() ? "mipush|%s|%s" : "mipush_%s_%s", str, str2);
    }

    /* renamed from: b */
    private String m2748b(String str) {
        return m2747b(this.f23491a, str);
    }

    /* renamed from: b */
    String m2750b() {
        if (TextUtils.isEmpty(this.f23492b)) {
            this.f23492b = m2748b("default");
        }
        return this.f23492b;
    }

    /* renamed from: a */
    public boolean m2754a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith(m2748b(""));
    }

    /* renamed from: a */
    public String m2756a(String str) {
        if (TextUtils.isEmpty(str)) {
            return m2750b();
        }
        return C11469j.m2972a(m2771a()) ? m2748b(str) : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public String m2746c(String str, String str2) {
        return m2768a() ? str : str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(26)
    /* renamed from: a */
    public void m2764a(NotificationChannel notificationChannel) {
        String str = this.f23491a;
        try {
            if (m2768a()) {
                int m2758a = m2758a(str);
                if (m2758a != -1) {
                    C11176aw.m4804b(f23488a, "createNotificationChannelsForPackage", str, Integer.valueOf(m2758a), m2751a(Arrays.asList(notificationChannel)));
                }
            } else {
                m2772a().createNotificationChannel(notificationChannel);
            }
        } catch (Exception e) {
            m2755a("createNotificationChannel error" + e);
        }
    }

    @TargetApi(26)
    /* renamed from: a */
    public NotificationChannel m2757a(String str) {
        NotificationChannel notificationChannel = null;
        try {
            if (m2768a()) {
                List<NotificationChannel> m2769a = m2769a();
                if (m2769a != null) {
                    for (NotificationChannel notificationChannel2 : m2769a) {
                        if (str.equals(notificationChannel2.getId())) {
                            notificationChannel = notificationChannel2;
                            break;
                        }
                    }
                }
            } else {
                notificationChannel = m2772a().getNotificationChannel(str);
            }
        } catch (Exception e) {
            m2755a("getNotificationChannel error" + e);
        }
        return notificationChannel;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:10:0x0038
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @android.annotation.TargetApi(26)
    /* renamed from: a */
    public java.util.List<android.app.NotificationChannel> m2769a() {
        /*
            r9 = this;
            java.lang.String r0 = r9.f23491a
            r1 = 0
            boolean r2 = m2768a()     // Catch: java.lang.Exception -> L7a
            if (r2 == 0) goto L3d
            int r2 = m2758a(r0)     // Catch: java.lang.Exception -> L7a
            r3 = -1
            if (r2 == r3) goto L3b
            java.lang.Object r3 = com.xiaomi.push.service.C11533af.f23488a     // Catch: java.lang.Exception -> L7a
            java.lang.String r4 = "getNotificationChannelsForPackage"
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> L7a
            r6 = 0
            r5[r6] = r0     // Catch: java.lang.Exception -> L7a
            r7 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Exception -> L7a
            r5[r7] = r2     // Catch: java.lang.Exception -> L7a
            r2 = 2
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch: java.lang.Exception -> L7a
            r5[r2] = r6     // Catch: java.lang.Exception -> L7a
            java.lang.Object r2 = com.xiaomi.push.C11176aw.m4812a(r3, r4, r5)     // Catch: java.lang.Exception -> L7a
            java.lang.Object r2 = m2759a(r2)     // Catch: java.lang.Exception -> L7a
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Exception -> L7a
            java.lang.String r1 = "mipush|%s|%s"
            r8 = r2
            r2 = r1
            r1 = r8
            goto L47
        L38:
            r0 = move-exception
            r1 = r2
            goto L7b
        L3b:
            r2 = r1
            goto L47
        L3d:
            android.app.NotificationManager r2 = m2772a()     // Catch: java.lang.Exception -> L7a
            java.util.List r1 = r2.getNotificationChannels()     // Catch: java.lang.Exception -> L7a
            java.lang.String r2 = "mipush_%s_%s"
        L47:
            boolean r3 = com.xiaomi.push.C11469j.m2974a()     // Catch: java.lang.Exception -> L7a
            if (r3 == 0) goto L8f
            if (r1 == 0) goto L8f
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: java.lang.Exception -> L7a
            r3.<init>()     // Catch: java.lang.Exception -> L7a
            java.lang.String r4 = ""
            java.lang.String r0 = m2752a(r2, r0, r4)     // Catch: java.lang.Exception -> L7a
            java.util.Iterator r2 = r1.iterator()     // Catch: java.lang.Exception -> L7a
        L5e:
            boolean r4 = r2.hasNext()     // Catch: java.lang.Exception -> L7a
            if (r4 == 0) goto L78
            java.lang.Object r4 = r2.next()     // Catch: java.lang.Exception -> L7a
            android.app.NotificationChannel r4 = (android.app.NotificationChannel) r4     // Catch: java.lang.Exception -> L7a
            java.lang.String r5 = r4.getId()     // Catch: java.lang.Exception -> L7a
            boolean r5 = r5.startsWith(r0)     // Catch: java.lang.Exception -> L7a
            if (r5 == 0) goto L5e
            r3.add(r4)     // Catch: java.lang.Exception -> L7a
            goto L5e
        L78:
            r1 = r3
            goto L8f
        L7a:
            r0 = move-exception
        L7b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getNotificationChannels error "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            m2755a(r0)
        L8f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11533af.m2769a():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2763a(NotificationChannel notificationChannel, boolean z) {
        String str = this.f23491a;
        try {
            if (z) {
                int m2758a = m2758a(str);
                if (m2758a != -1) {
                    C11176aw.m4804b(f23488a, "updateNotificationChannelForPackage", str, Integer.valueOf(m2758a), notificationChannel);
                }
            } else {
                m2764a(notificationChannel);
            }
        } catch (Exception e) {
            m2755a("updateNotificationChannel error " + e);
        }
    }

    /* renamed from: a */
    public void m2765a(int i, Notification notification) {
        String str = this.f23491a;
        NotificationManager m2772a = m2772a();
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (!m2768a()) {
                m2772a.notify(i, notification);
                return;
            }
            if (i2 >= 19) {
                notification.extras.putString("xmsf_target_package", str);
            }
            if (i2 >= 29) {
                m2772a.notifyAsPackage(str, null, i, notification);
            } else {
                m2772a.notify(i, notification);
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public void m2766a(int i) {
        String str = this.f23491a;
        try {
            if (m2768a()) {
                int m3056a = C11455i.m3056a();
                String packageName = m2771a().getPackageName();
                if (Build.VERSION.SDK_INT >= 30) {
                    C11176aw.m4804b(f23488a, "cancelNotificationWithTag", str, packageName, null, Integer.valueOf(i), Integer.valueOf(m3056a));
                } else {
                    C11176aw.m4804b(f23488a, "cancelNotificationWithTag", str, null, Integer.valueOf(i), Integer.valueOf(m3056a));
                }
                m2755a("cancel succ:" + i);
                return;
            }
            m2772a().cancel(i);
        } catch (Exception e) {
            m2755a("cancel error" + e);
        }
    }

    /* renamed from: b */
    public List<StatusBarNotification> m2749b() {
        StatusBarNotification[] m2767a;
        String str = this.f23491a;
        NotificationManager m2772a = m2772a();
        ArrayList arrayList = null;
        try {
            if (m2768a()) {
                int m3056a = C11455i.m3056a();
                if (m3056a != -1) {
                    return (List) m2759a(C11176aw.m4812a(f23488a, "getAppActiveNotifications", str, Integer.valueOf(m3056a)));
                }
                return null;
            }
            if (Build.VERSION.SDK_INT >= 23) {
                m2767a = m2772a.getActiveNotifications();
            } else {
                m2767a = m2767a();
            }
            if (m2767a == null || m2767a.length <= 0) {
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            try {
                for (StatusBarNotification statusBarNotification : m2767a) {
                    if (str.equals(C11534ag.m2722c(statusBarNotification.getNotification()))) {
                        arrayList2.add(statusBarNotification);
                    }
                }
                return arrayList2;
            } catch (Throwable th) {
                th = th;
                arrayList = arrayList2;
                m2755a("getActiveNotifications error " + th);
                return arrayList;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    private StatusBarNotification[] m2767a() {
        if (C11469j.m2972a(m2771a())) {
            try {
                Object m4812a = C11176aw.m4812a(f23488a, "getActiveNotifications", m2771a().getPackageName());
                if (m4812a instanceof StatusBarNotification[]) {
                    return (StatusBarNotification[]) m4812a;
                }
                return null;
            } catch (Throwable th) {
                m2755a("getAllNotifications error " + th);
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    static void m2755a(String str) {
        AbstractC11049b.m5282a("NMHelper:" + str);
    }
}
