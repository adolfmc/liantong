package com.xiaomi.push;

import android.content.Context;
import android.preference.PreferenceManager;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.l */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC11471l {
    /* renamed from: a */
    public static void m2954a(Context context) {
    }

    /* renamed from: a */
    public static boolean m2952a(Context context, String str, boolean z) {
        m2954a(context);
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(str, z);
    }

    /* renamed from: a */
    public static void m2953a(Context context, String str, boolean z) {
        m2954a(context);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(str, z).commit();
    }

    /* renamed from: a */
    public static void m2951a(Map<String, String> map, String str, String str2) {
        if (map == null || str == null || str2 == null) {
            return;
        }
        map.put(str, str2);
    }
}
