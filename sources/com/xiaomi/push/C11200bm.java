package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.xiaomi.push.bm */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11200bm {

    /* renamed from: a */
    private static volatile C11200bm f21611a;

    /* renamed from: a */
    private Context f21612a;

    private C11200bm(Context context) {
        this.f21612a = context;
    }

    /* renamed from: a */
    public static C11200bm m4710a(Context context) {
        if (f21611a == null) {
            synchronized (C11200bm.class) {
                if (f21611a == null) {
                    f21611a = new C11200bm(context);
                }
            }
        }
        return f21611a;
    }

    /* renamed from: a */
    public synchronized void m4706a(String str, String str2, String str3) {
        SharedPreferences.Editor edit = this.f21612a.getSharedPreferences(str, 4).edit();
        edit.putString(str2, str3);
        edit.commit();
    }

    /* renamed from: a */
    public synchronized void m4708a(String str, String str2, long j) {
        SharedPreferences.Editor edit = this.f21612a.getSharedPreferences(str, 4).edit();
        edit.putLong(str2, j);
        edit.commit();
    }

    /* renamed from: a */
    public synchronized String m4707a(String str, String str2, String str3) {
        try {
        } catch (Throwable unused) {
            return str3;
        }
        return this.f21612a.getSharedPreferences(str, 4).getString(str2, str3);
    }

    /* renamed from: a */
    public synchronized long m4709a(String str, String str2, long j) {
        try {
        } catch (Throwable unused) {
            return j;
        }
        return this.f21612a.getSharedPreferences(str, 4).getLong(str2, j);
    }
}
