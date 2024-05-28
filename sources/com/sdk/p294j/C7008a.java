package com.sdk.p294j;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.p290f.C6998d;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"ApplySharedPref"})
/* renamed from: com.sdk.j.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7008a {

    /* renamed from: a */
    public static final String f18156a = "com.sdk.j.a";

    /* renamed from: b */
    public static final Boolean f18157b = Boolean.valueOf(C6998d.f18135a);

    /* renamed from: a */
    public static Long m8158a(Context context, String str) {
        long j = 0;
        try {
            j = context.getSharedPreferences("ZzxCache", 0).getLong(str, 0L);
        } catch (Exception e) {
            LogUtils.m8186e(f18156a, e.getMessage(), f18157b);
        }
        return Long.valueOf(j);
    }

    /* renamed from: a */
    public static String m8156a(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences("ZzxCache", 0).getString(str, str2);
        } catch (Exception e) {
            LogUtils.m8186e(f18156a, e.getMessage(), f18157b);
            return "";
        }
    }

    /* renamed from: a */
    public static void m8157a(Context context, String str, Long l) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("ZzxCache", 0).edit();
            edit.putLong(str, l.longValue());
            edit.commit();
        } catch (Exception e) {
            LogUtils.m8186e(f18156a, e.getMessage(), f18157b);
        }
    }

    /* renamed from: b */
    public static String m8155b(Context context, String str) {
        try {
            return context.getSharedPreferences("ZzxCache", 0).getString(str, "");
        } catch (Exception e) {
            LogUtils.m8186e(f18156a, e.getMessage(), f18157b);
            return "";
        }
    }

    /* renamed from: b */
    public static boolean m8154b(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("ZzxCache", 0).edit();
            edit.putString(str, str2);
            return edit.commit();
        } catch (Exception e) {
            LogUtils.m8186e(f18156a, e.getMessage(), f18157b);
            return false;
        }
    }
}
