package p397j;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: j.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C12229b {
    /* renamed from: a */
    public static int m1925a(int i) {
        float f = i;
        C12228a c12228a = C12228a.f24821f;
        if (c12228a != null) {
            return (int) (f * c12228a.f24826e);
        }
        throw new IllegalStateException("Must init before using.");
    }

    /* renamed from: a */
    public static int[] m1924a(Context context) {
        int[] iArr = new int[2];
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 14 && i3 < 17) {
            try {
                i = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception unused) {
            }
        } else if (i3 >= 17) {
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            i = point.x;
            i2 = point.y;
        }
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }
}
