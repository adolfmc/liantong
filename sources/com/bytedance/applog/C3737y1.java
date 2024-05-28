package com.bytedance.applog;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.y1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3737y1 {

    /* renamed from: a */
    public static boolean f8949a;

    /* renamed from: b */
    public static Class f8950b;

    /* renamed from: c */
    public static Method f8951c;

    /* renamed from: d */
    public static boolean f8952d = m17002a("com.tencent.smtt.sdk.WebView");

    /* renamed from: e */
    public static boolean f8953e = m17002a("android.support.v7.widget.RecyclerView");

    /* renamed from: f */
    public static boolean f8954f = m17002a("android.support.v4.view.ViewPager");

    /* renamed from: g */
    public static boolean f8955g = m17002a("android.support.v4.widget.SwipeRefreshLayout");

    /* renamed from: h */
    public static boolean f8956h;

    /* renamed from: i */
    public static boolean f8957i;

    /* renamed from: j */
    public static boolean f8958j;

    /* renamed from: k */
    public static boolean f8959k;

    /* renamed from: l */
    public static boolean f8960l;

    /* renamed from: m */
    public static boolean f8961m;

    /* renamed from: n */
    public static boolean f8962n;

    static {
        m17002a("android.support.v4.app.Fragment");
        m17002a("android.support.v4.app.FragmentActivity");
        f8956h = m17002a("android.support.v7.app.AlertDialog");
        f8957i = m17002a("android.support.v7.view.menu.ListMenuItemView");
        f8958j = m17002a("androidx.recyclerview.widget.RecyclerView");
        f8959k = m17002a("androidx.viewpager.widget.ViewPager");
        f8960l = m17002a("androidx.swiperefreshlayout.widget.SwipeRefreshLayout");
        m17002a("androidx.fragment.app.Fragment");
        m17002a("androidx.fragment.app.FragmentActivity");
        f8961m = m17002a("androidx.appcompat.app.AlertDialog");
        f8962n = m17002a("androidx.appcompat.view.menu.ListMenuItemView");
    }

    /* renamed from: a */
    public static Class<?> m17004a(Class<?> cls) {
        while (cls != null && !cls.equals(ViewGroup.class)) {
            try {
                f8951c = cls.getDeclaredMethod("getChildAdapterPosition", View.class);
            } catch (NoSuchMethodException unused) {
            }
            if (f8951c == null) {
                try {
                    f8951c = cls.getDeclaredMethod("getChildPosition", View.class);
                } catch (NoSuchMethodException unused2) {
                }
            }
            if (f8951c != null) {
                return cls;
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m17003a(Object obj) {
        return f8958j && (obj instanceof RecyclerView);
    }

    /* renamed from: a */
    public static boolean m17002a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m17001b(Object obj) {
        return f8953e && (obj instanceof android.support.p086v7.widget.RecyclerView);
    }
}
