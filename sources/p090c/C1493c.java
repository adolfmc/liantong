package p090c;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p390g.C11944a;
import p390g.C11945b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: c.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1493c {

    /* renamed from: a */
    public static Map<String, C1499i> f2506a = new ConcurrentHashMap();

    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.Map<java.lang.String, c.i>, java.util.concurrent.ConcurrentHashMap] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.util.Map<java.lang.String, c.i>, java.util.concurrent.ConcurrentHashMap] */
    /* renamed from: a */
    public static void m22194a(Activity activity, float f, boolean z) {
        float f2;
        int m22186d;
        float f3;
        int i;
        float f4;
        float f5;
        C11945b.m2025a(activity, "activity == null");
        if (z) {
            f2 = C1497g.m22187c().f2514c.f24293a;
        } else {
            f2 = C1497g.m22187c().f2514c.f24294b;
        }
        if (f2 <= 0.0f) {
            f2 = f;
        }
        if (z) {
            m22186d = C1497g.m22187c().f2520i;
        } else {
            m22186d = C1497g.m22187c().m22186d();
        }
        String str = f + "|" + f2 + "|" + z + "|" + C1497g.m22187c().f2524m + "|" + C1497g.m22187c().f2517f + "|" + m22186d;
        C1499i c1499i = (C1499i) f2506a.get(str);
        if (c1499i == null) {
            if (z) {
                f3 = (C1497g.m22187c().f2520i * 1.0f) / f;
            } else {
                f3 = (C1497g.m22187c().m22186d() * 1.0f) / f;
            }
            f4 = (C1497g.m22187c().f2526o ? 1.0f : (C1497g.m22187c().f2517f * 1.0f) / C1497g.m22187c().f2515d) * f3;
            i = (int) (160.0f * f3);
            if (z) {
                f5 = (C1497g.m22187c().f2520i * 1.0f) / f2;
            } else {
                f5 = (C1497g.m22187c().m22186d() * 1.0f) / f2;
            }
            f2506a.put(str, new C1499i(f3, i, f4, f5));
        } else {
            f3 = c1499i.f2530a;
            i = c1499i.f2531b;
            f4 = c1499i.f2532c;
            f5 = c1499i.f2533d;
        }
        DisplayMetrics m22193a = m22193a(activity.getResources());
        C1497g m22187c = C1497g.m22187c();
        C11945b.m2025a(m22187c.f2512a, "Please call the AutoSizeConfig#init() first");
        DisplayMetrics m22193a2 = m22193a(m22187c.f2512a.getResources());
        if (m22193a == null) {
            m22193a = activity.getResources().getDisplayMetrics();
        }
        m22192a(m22193a, f3, i, f4, f5);
        if (m22193a2 != null) {
            m22192a(m22193a2, f3, i, f4, f5);
        } else {
            C1497g m22187c2 = C1497g.m22187c();
            C11945b.m2025a(m22187c2.f2512a, "Please call the AutoSizeConfig#init() first");
            m22192a(m22187c2.f2512a.getResources().getDisplayMetrics(), f3, i, f4, f5);
        }
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[11];
        objArr[0] = activity.getClass().getName();
        objArr[1] = activity.getClass().getSimpleName();
        objArr[2] = Boolean.valueOf(z);
        objArr[3] = z ? "designWidthInDp" : "designHeightInDp";
        objArr[4] = Float.valueOf(f);
        objArr[5] = z ? "designWidthInSubunits" : "designHeightInSubunits";
        objArr[6] = Float.valueOf(f2);
        objArr[7] = Float.valueOf(f3);
        objArr[8] = Float.valueOf(f4);
        objArr[9] = Integer.valueOf(i);
        objArr[10] = Float.valueOf(f5);
        C11944a.m2026a(String.format(locale, "The %s has been adapted! \n%s Info: isBaseOnWidth = %s, %s = %f, %s = %f, targetDensity = %f, targetScaledDensity = %f, targetDensityDpi = %d, targetXdpi = %f", objArr));
    }

    /* renamed from: a */
    public static DisplayMetrics m22193a(Resources resources) {
        if (C1497g.m22187c().f2527p && C1497g.m22187c().f2528q != null) {
            try {
                return (DisplayMetrics) C1497g.m22187c().f2528q.get(resources);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* renamed from: a */
    public static void m22192a(DisplayMetrics displayMetrics, float f, int i, float f2, float f3) {
        float f4;
        if (C1497g.m22187c().f2514c.f24295c) {
            displayMetrics.density = f;
            displayMetrics.densityDpi = i;
        }
        if (C1497g.m22187c().f2514c.f24296d) {
            displayMetrics.scaledDensity = f2;
        }
        int ordinal = C1497g.m22187c().f2514c.f24297e.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal != 3) {
                    return;
                }
                f4 = 25.4f;
            }
            displayMetrics.xdpi = f3;
        }
        f4 = 72.0f;
        f3 *= f4;
        displayMetrics.xdpi = f3;
    }
}
