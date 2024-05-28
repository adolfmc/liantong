package com.bytedance.applog;

import android.content.SharedPreferences;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: outline */
/* renamed from: com.bytedance.applog.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3535a {
    /* renamed from: a */
    public static String m17348a(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    /* renamed from: a */
    public static StringBuilder m17349a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    /* renamed from: a */
    public static void m17350a(SharedPreferences sharedPreferences, String str, String str2) {
        sharedPreferences.edit().putString(str, str2).apply();
    }
}
