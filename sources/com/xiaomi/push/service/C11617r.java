package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.push.C11184bb;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.service.r */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11617r {

    /* renamed from: a */
    private static C11617r f23738a;

    /* renamed from: a */
    private Context f23739a;

    /* renamed from: a */
    private List<String> f23740a = new ArrayList();

    /* renamed from: b */
    private final List<String> f23741b = new ArrayList();

    /* renamed from: c */
    private final List<String> f23742c = new ArrayList();

    /* renamed from: a */
    public static C11617r m2420a(Context context) {
        if (f23738a == null) {
            f23738a = new C11617r(context);
        }
        return f23738a;
    }

    private C11617r(Context context) {
        String[] split;
        String[] split2;
        String[] split3;
        this.f23739a = context.getApplicationContext();
        if (this.f23739a == null) {
            this.f23739a = context;
        }
        SharedPreferences sharedPreferences = this.f23739a.getSharedPreferences("mipush_app_info", 0);
        for (String str : sharedPreferences.getString("unregistered_pkg_names", "").split(",")) {
            if (TextUtils.isEmpty(str)) {
                this.f23740a.add(str);
            }
        }
        for (String str2 : sharedPreferences.getString("disable_push_pkg_names", "").split(",")) {
            if (!TextUtils.isEmpty(str2)) {
                this.f23741b.add(str2);
            }
        }
        for (String str3 : sharedPreferences.getString("disable_push_pkg_names_cache", "").split(",")) {
            if (!TextUtils.isEmpty(str3)) {
                this.f23742c.add(str3);
            }
        }
    }

    /* renamed from: a */
    public boolean m2418a(String str) {
        boolean contains;
        synchronized (this.f23740a) {
            contains = this.f23740a.contains(str);
        }
        return contains;
    }

    /* renamed from: b */
    public boolean m2416b(String str) {
        boolean contains;
        synchronized (this.f23741b) {
            contains = this.f23741b.contains(str);
        }
        return contains;
    }

    /* renamed from: c */
    public boolean m2414c(String str) {
        boolean contains;
        synchronized (this.f23742c) {
            contains = this.f23742c.contains(str);
        }
        return contains;
    }

    /* renamed from: a */
    public void m2419a(String str) {
        synchronized (this.f23740a) {
            if (!this.f23740a.contains(str)) {
                this.f23740a.add(str);
                this.f23739a.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", C11184bb.m4753a(this.f23740a, ",")).commit();
            }
        }
    }

    /* renamed from: b */
    public void m2417b(String str) {
        synchronized (this.f23741b) {
            if (!this.f23741b.contains(str)) {
                this.f23741b.add(str);
                this.f23739a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names", C11184bb.m4753a(this.f23741b, ",")).commit();
            }
        }
    }

    /* renamed from: c */
    public void m2415c(String str) {
        synchronized (this.f23742c) {
            if (!this.f23742c.contains(str)) {
                this.f23742c.add(str);
                this.f23739a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names_cache", C11184bb.m4753a(this.f23742c, ",")).commit();
            }
        }
    }

    /* renamed from: d */
    public void m2413d(String str) {
        synchronized (this.f23740a) {
            if (this.f23740a.contains(str)) {
                this.f23740a.remove(str);
                this.f23739a.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", C11184bb.m4753a(this.f23740a, ",")).commit();
            }
        }
    }

    /* renamed from: e */
    public void m2412e(String str) {
        synchronized (this.f23741b) {
            if (this.f23741b.contains(str)) {
                this.f23741b.remove(str);
                this.f23739a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names", C11184bb.m4753a(this.f23741b, ",")).commit();
            }
        }
    }

    /* renamed from: f */
    public void m2411f(String str) {
        synchronized (this.f23742c) {
            if (this.f23742c.contains(str)) {
                this.f23742c.remove(str);
                this.f23739a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names_cache", C11184bb.m4753a(this.f23742c, ",")).commit();
            }
        }
    }
}
