package p397j;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: j.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C12228a {

    /* renamed from: f */
    public static C12228a f24821f;

    /* renamed from: a */
    public int f24822a;

    /* renamed from: b */
    public int f24823b;

    /* renamed from: c */
    public int f24824c = 1080;

    /* renamed from: d */
    public int f24825d = 1920;

    /* renamed from: e */
    public float f24826e;

    /* renamed from: a */
    public static void m1926a(Context context) {
        float f;
        Bundle bundle;
        if (f24821f == null) {
            C12228a c12228a = new C12228a();
            f24821f = c12228a;
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && bundle.containsKey("design_width") && applicationInfo.metaData.containsKey("design_height")) {
                    c12228a.f24824c = ((Integer) applicationInfo.metaData.get("design_width")).intValue();
                    c12228a.f24825d = ((Integer) applicationInfo.metaData.get("design_height")).intValue();
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            if (c12228a.f24825d > 0 && c12228a.f24824c > 0) {
                int[] m1924a = C12229b.m1924a(context);
                int i = m1924a[0];
                c12228a.f24822a = i;
                int i2 = m1924a[1];
                c12228a.f24823b = i2;
                if (i > i2) {
                    int i3 = i + i2;
                    int i4 = i3 - i2;
                    c12228a.f24823b = i4;
                    c12228a.f24822a = i3 - i4;
                }
                int i5 = c12228a.f24823b;
                float f2 = i5;
                int i6 = c12228a.f24822a;
                float f3 = i6;
                float f4 = c12228a.f24825d;
                float f5 = c12228a.f24824c;
                if (f2 / f3 <= f4 / f5) {
                    c12228a.f24826e = f2 / f4;
                } else {
                    c12228a.f24826e = f3 / f5;
                }
                float f6 = c12228a.f24826e;
                if (i6 < 720 || i5 < 720) {
                    if (i6 <= 480 || i5 <= 480) {
                        f = 1.2f;
                    } else {
                        int[] m1924a2 = C12229b.m1924a(context);
                        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                        f = Math.sqrt(Math.pow((double) (((float) m1924a2[1]) / displayMetrics.ydpi), 2.0d) + Math.pow((double) (((float) m1924a2[0]) / displayMetrics.xdpi), 2.0d)) < 4.0d ? 1.3f : 1.05f;
                    }
                    f6 *= f;
                }
                c12228a.f24826e = f6;
                return;
            }
            throw new RuntimeException("you must set design_width and design_height > 0");
        }
    }
}
