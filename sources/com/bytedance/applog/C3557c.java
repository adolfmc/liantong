package com.bytedance.applog;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashSet;

/* renamed from: com.bytedance.applog.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3557c implements Application.ActivityLifecycleCallbacks {

    /* renamed from: b */
    public static int f8379b;

    /* renamed from: c */
    public static C3711v1 f8380c;

    /* renamed from: d */
    public static C3711v1 f8381d;

    /* renamed from: e */
    public static long f8382e;

    /* renamed from: f */
    public static String f8383f;

    /* renamed from: g */
    public static Object f8384g;

    /* renamed from: h */
    public static boolean f8385h;

    /* renamed from: i */
    public static final HashSet<Integer> f8386i = new HashSet<>(8);

    /* renamed from: a */
    public final IPicker f8387a;

    public C3557c(IPicker iPicker) {
        this.f8387a = iPicker;
    }

    /* renamed from: a */
    public static C3711v1 m17325a() {
        C3711v1 c3711v1 = f8380c;
        C3711v1 c3711v12 = f8381d;
        if (c3711v12 != null) {
            return c3711v12;
        }
        if (c3711v1 != null) {
            return c3711v1;
        }
        return null;
    }

    /* renamed from: a */
    public static C3711v1 m17322a(String str, String str2, long j, String str3) {
        C3711v1 c3711v1 = new C3711v1();
        if (!TextUtils.isEmpty(str2)) {
            str = str + ":" + str2;
        }
        c3711v1.f8861m = str;
        c3711v1.m17233a(j);
        c3711v1.f8859k = -1L;
        if (str3 == null) {
            str3 = "";
        }
        c3711v1.f8860l = str3;
        C3591h.m17294a(c3711v1);
        return c3711v1;
    }

    /* renamed from: a */
    public static void m17324a(Object obj) {
        obj.getClass().getName();
    }

    /* renamed from: b */
    public static void m17321b() {
    }

    /* renamed from: a */
    public void m17323a(String str, int i) {
        f8380c = m17322a(str, "", System.currentTimeMillis(), f8383f);
        f8380c.f8862n = !f8386i.remove(Integer.valueOf(i)) ? 1 : 0;
        IPicker iPicker = this.f8387a;
        if (iPicker == null || !f8385h) {
            return;
        }
        iPicker.show(true);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        f8386i.add(Integer.valueOf(activity.hashCode()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        f8386i.remove(Integer.valueOf(activity.hashCode()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        C3711v1 c3711v1 = f8381d;
        C3711v1 c3711v12 = f8380c;
        if (c3711v12 != null) {
            f8383f = c3711v12.f8861m;
            f8382e = System.currentTimeMillis();
            C3711v1 c3711v13 = f8380c;
            long j = f8382e;
            C3711v1 c3711v14 = (C3711v1) c3711v13.m24467clone();
            c3711v14.m17233a(j);
            long j2 = j - c3711v13.f8576b;
            if (j2 >= 0) {
                c3711v14.f8859k = j2;
            } else {
                C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
            }
            C3591h.m17294a(c3711v14);
            f8380c = null;
            if (activity != null && !activity.isChild()) {
                f8384g = null;
            }
        }
        IPicker iPicker = this.f8387a;
        if (iPicker != null) {
            iPicker.show(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        f8380c = m17322a(activity.getClass().getName(), "", System.currentTimeMillis(), f8383f);
        f8380c.f8862n = !f8386i.remove(Integer.valueOf(activity.hashCode())) ? 1 : 0;
        if (!activity.isChild()) {
            activity.getWindow().getDecorView().hashCode();
            f8384g = activity;
        }
        IPicker iPicker = this.f8387a;
        if (iPicker == null || !f8385h) {
            return;
        }
        iPicker.show(true);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        f8379b++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (f8383f != null) {
            f8379b--;
            if (f8379b <= 0) {
                f8383f = null;
                f8382e = 0L;
            }
        }
    }
}
