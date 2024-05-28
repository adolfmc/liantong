package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.secure.android.common.ssl.util.g */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5120g {

    /* renamed from: a */
    private static final String f12117a = "aegis";

    /* renamed from: b */
    private static SharedPreferences f12118b;

    /* renamed from: a */
    public static long m13846a(String str, long j, Context context) {
        return m13843b(context).getLong(str, j);
    }

    /* renamed from: b */
    public static synchronized SharedPreferences m13843b(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (C5120g.class) {
            if (f12118b == null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    f12118b = context.createDeviceProtectedStorageContext().getSharedPreferences("aegis", 0);
                } else {
                    f12118b = context.getApplicationContext().getSharedPreferences("aegis", 0);
                }
            }
            sharedPreferences = f12118b;
        }
        return sharedPreferences;
    }

    /* renamed from: a */
    public static int m13847a(String str, int i, Context context) {
        return m13843b(context).getInt(str, i);
    }

    /* renamed from: a */
    public static String m13844a(String str, String str2, Context context) {
        return m13843b(context).getString(str, str2);
    }

    /* renamed from: a */
    public static void m13845a(String str, Context context) {
        m13843b(context).edit().remove(str).apply();
    }

    /* renamed from: a */
    public static void m13848a(Context context) {
        m13843b(context).edit().clear().apply();
    }

    /* renamed from: b */
    public static void m13841b(String str, long j, Context context) {
        m13843b(context).edit().putLong(str, j).apply();
    }

    /* renamed from: b */
    public static void m13842b(String str, int i, Context context) {
        m13843b(context).edit().putInt(str, i).apply();
    }

    /* renamed from: b */
    public static void m13840b(String str, String str2, Context context) {
        m13843b(context).edit().putString(str, str2).apply();
    }
}
