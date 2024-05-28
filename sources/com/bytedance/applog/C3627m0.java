package com.bytedance.applog;

import android.content.SharedPreferences;

/* renamed from: com.bytedance.applog.m0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3627m0 {

    /* renamed from: h */
    public static final long[][] f8566h = {new long[]{120000, 0, 12}, new long[]{120000, 5, 1}, new long[]{240000, 5, 1}, new long[]{480000, 4, 1}, new long[]{960000, 2, 1}};

    /* renamed from: a */
    public String f8567a;

    /* renamed from: b */
    public C3726x f8568b;

    /* renamed from: c */
    public int f8569c;

    /* renamed from: d */
    public int f8570d;

    /* renamed from: e */
    public int f8571e;

    /* renamed from: f */
    public long f8572f;

    /* renamed from: g */
    public long f8573g;

    public C3627m0(String str, C3726x c3726x) {
        this.f8568b = c3726x;
        this.f8567a = str;
        this.f8569c = 0;
        if (System.currentTimeMillis() - this.f8568b.f8900e.getLong(C3535a.m17348a(new StringBuilder(), this.f8567a, "downgrade_time"), 0L) < 10800000) {
            SharedPreferences sharedPreferences = this.f8568b.f8900e;
            this.f8569c = sharedPreferences.getInt(this.f8567a + "downgrade_index", 0);
            return;
        }
        SharedPreferences.Editor edit = this.f8568b.f8900e.edit();
        SharedPreferences.Editor remove = edit.remove(this.f8567a + "downgrade_time");
        remove.remove(this.f8567a + "downgrade_index").apply();
    }

    /* renamed from: a */
    public final boolean m17235a() {
        return this.f8568b.f8897b.isCongestionControlEnable();
    }
}
