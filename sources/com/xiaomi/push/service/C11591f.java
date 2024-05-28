package com.xiaomi.push.service;

import android.app.NotificationChannel;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.C11480s;
import com.xiaomi.push.service.C11534ag;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11591f {

    /* renamed from: a */
    private static final int[] f23679a = {1, 2, 4, 8, 16};

    /* renamed from: a */
    private static final SparseArray<C11534ag.C11536a<String, String, String>> f23678a = new SparseArray<C11534ag.C11536a<String, String, String>>(5) { // from class: com.xiaomi.push.service.f.1
        {
            put(1, C11534ag.f23497b);
            put(2, C11534ag.f23498c);
            put(4, C11534ag.f23499d);
            put(8, C11534ag.f23501f);
            put(16, C11534ag.f23500e);
        }
    };

    /* renamed from: b */
    private static final SparseArray<Integer> f23680b = new SparseArray<Integer>(5) { // from class: com.xiaomi.push.service.f.2
        {
            put(1, 32);
            put(2, 16);
            put(4, 8);
            put(8, 4);
            put(16, 2);
        }
    };

    /* renamed from: a */
    private static boolean m2557a(int i, int i2) {
        return i >= 4 || (i2 & 2) > 0 || (i2 & 1) > 0 || (i2 & 8) > 0 || (i2 & 16) > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m2547a(String str, String str2) {
        int i = m2544a(str, str2, 8) ? 8 : 0;
        if (m2544a(str, str2, 16)) {
            i |= 16;
        }
        if (m2544a(str, str2, 1)) {
            i |= 1;
        }
        if (m2544a(str, str2, 2)) {
            i |= 2;
        }
        return m2544a(str, str2, 4) ? i | 4 : i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2552a(Context context, String str, String str2, int i, String str3, boolean z, int i2) {
        if (C11469j.m2972a(context) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            int m2923a = C11480s.m2923a(str3, 0);
            boolean m2557a = m2557a(i, m2923a);
            if (z) {
                m2543a(str, str2, m2923a, i2);
                if (m2557a) {
                    synchronized (C11591f.class) {
                        m2551a(m2556a(context), m2923a, str2);
                    }
                    return;
                }
                return;
            }
            synchronized (C11591f.class) {
                SharedPreferences m2556a = m2556a(context);
                if (m2557a || m2556a.contains(str2)) {
                    m2550a(m2556a, m2923a, str, str2, i2);
                    if (m2557a) {
                        m2551a(m2556a, m2923a, str2);
                    } else {
                        m2549a(m2556a, str2);
                    }
                }
            }
        } else if (C11469j.m2972a(context)) {
            AbstractC11049b.m5282a("ChannelPC: can`t setup permission with permissionCode:" + String.valueOf(str3) + " channelId:" + String.valueOf(str2) + " targetPkg:" + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2555a(Context context, String str) {
        List<NotificationChannel> m2769a;
        if (!C11469j.m2972a(context) || TextUtils.isEmpty(str) || (m2769a = C11533af.m2760a(context, str).m2769a()) == null) {
            return;
        }
        synchronized (C11591f.class) {
            SharedPreferences m2556a = m2556a(context);
            ArrayList arrayList = new ArrayList();
            for (NotificationChannel notificationChannel : m2769a) {
                String str2 = (String) C11176aw.m4814a(notificationChannel, "mId");
                if (!TextUtils.isEmpty(str2) && m2556a.contains(str2)) {
                    arrayList.add(str2);
                }
            }
            if (arrayList.size() > 0) {
                m2548a(m2556a, arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2543a(String str, String str2, int i, int i2) {
        int[] iArr;
        for (int i3 : f23679a) {
            if ((f23680b.get(i3).intValue() & i2) == 0) {
                m2542a(str, str2, i3, (i & i3) > 0);
            } else {
                AbstractC11049b.m5282a("ChannelPermissions.grantPermission:" + str + ":" + str2 + ": <" + i3 + "> :stoped by userLock");
            }
        }
    }

    /* renamed from: a */
    private static void m2542a(String str, String str2, int i, boolean z) {
        boolean m2730a = C11534ag.m2730a(C11479r.m2934a(), str, str2, f23678a.get(i), z);
        AbstractC11049b.m5282a("ChannelPermissions.grantPermission:" + str + ":" + str2 + ": <" + i + "=" + z + "> :" + m2730a);
    }

    /* renamed from: a */
    public static int m2545a(String str, String str2, int i) {
        return C11534ag.m2731a(C11479r.m2934a(), str, str2, f23678a.get(i));
    }

    /* renamed from: a */
    public static Bundle m2546a(String str, String str2) {
        return C11534ag.m2732a(C11479r.m2934a(), str, str2);
    }

    /* renamed from: a */
    private static boolean m2544a(String str, String str2, int i) {
        boolean z = C11534ag.m2731a(C11479r.m2934a(), str, str2, f23678a.get(i)) == 1;
        AbstractC11049b.m5282a("ChannelPermissions.checkPermission:" + str + ":" + str2 + ": <" + i + "=" + z + ">");
        return z;
    }

    /* renamed from: a */
    public static int m2554a(Context context, String str, NotificationChannel notificationChannel) {
        if (Build.VERSION.SDK_INT >= 26 && context != null && !TextUtils.isEmpty(str) && notificationChannel != null) {
            int i = (notificationChannel.getImportance() != 0 ? 1 : 2) | 0;
            int i2 = 4;
            if (C11534ag.m2745a()) {
                Bundle m2546a = m2546a(str, notificationChannel.getId());
                if (m2546a.containsKey(C11534ag.f23501f.f23506c)) {
                    if (!m2546a.getBoolean(C11534ag.f23501f.f23506c) || notificationChannel.getImportance() < 4) {
                        i2 = 8;
                    }
                    i |= i2;
                }
                if (m2546a.containsKey(C11534ag.f23500e.f23506c)) {
                    i |= m2546a.getBoolean(C11534ag.f23500e.f23506c) ? 16 : 32;
                }
            } else {
                int m2545a = m2545a(str, notificationChannel.getId(), 8);
                if (m2545a == 1) {
                    i = notificationChannel.getImportance() >= 4 ? i | 4 : i | 8;
                } else if (m2545a == 0) {
                    i |= 8;
                }
                int m2545a2 = m2545a(str, notificationChannel.getId(), 16);
                if (m2545a2 == 1) {
                    i |= 16;
                } else if (m2545a2 == 0) {
                    i |= 32;
                }
            }
            return (notificationChannel.getSound() != null ? i | 64 : i | 128) | (notificationChannel.shouldVibrate() ? 256 : 512);
        }
        AbstractC11049b.m5282a("context|packageName|channel must not be null ");
        return 0;
    }

    /* renamed from: a */
    public static int m2553a(Context context, String str, String str2) {
        if (Build.VERSION.SDK_INT >= 26 && context != null && !TextUtils.isEmpty(str)) {
            C11533af m2760a = C11533af.m2760a(context, str);
            if (m2760a != null) {
                NotificationChannel m2757a = m2760a.m2757a(m2760a.m2756a(str2));
                if (m2757a != null) {
                    int i = (m2757a.getImportance() != 0 ? 1 : 2) | 0;
                    int m2545a = m2545a(str, m2757a.getId(), 8);
                    if (m2545a == 1) {
                        i = m2757a.getImportance() >= 4 ? i | 4 : i | 8;
                    } else if (m2545a == 0) {
                        i |= 8;
                    }
                    int m2545a2 = m2545a(str, m2757a.getId(), 16);
                    return m2545a2 == 1 ? i | 16 : m2545a2 == 0 ? i | 32 : i;
                }
                AbstractC11049b.m5282a("Channel must not be null");
                return 0;
            }
            AbstractC11049b.m5282a("create NMHelper error");
            return 0;
        }
        AbstractC11049b.m5282a("Must greater than or equal android O and context|packageName not be null");
        return 0;
    }

    /* renamed from: a */
    private static void m2550a(SharedPreferences sharedPreferences, int i, String str, String str2, int i2) {
        if (sharedPreferences.getInt(str2, 0) != i) {
            m2543a(str, str2, i, i2);
        }
    }

    /* renamed from: a */
    private static void m2551a(SharedPreferences sharedPreferences, int i, String str) {
        sharedPreferences.edit().putInt(str, i).commit();
    }

    /* renamed from: a */
    private static void m2549a(SharedPreferences sharedPreferences, final String str) {
        m2548a(sharedPreferences, new ArrayList<String>() { // from class: com.xiaomi.push.service.f.3
            {
                add(str);
            }
        });
    }

    /* renamed from: a */
    private static void m2548a(SharedPreferences sharedPreferences, List<String> list) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (String str : list) {
            edit.remove(str);
        }
        edit.commit();
    }

    /* renamed from: a */
    private static SharedPreferences m2556a(Context context) {
        return context.getSharedPreferences("ch_permission_cache_file", 0);
    }
}
