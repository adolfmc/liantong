package p390g;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import p470p0.C13652o;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: g.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C11947d {
    /* renamed from: a */
    public static boolean m2022a(Activity activity) {
        int i;
        int i2;
        if (activity == null) {
            return false;
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 23) {
            i2 = defaultDisplay.getMode().getPhysicalHeight();
            i = defaultDisplay.getMode().getPhysicalWidth();
        } else {
            i = 0;
            i2 = 0;
        }
        return ((int) ((((float) Math.min(i2, i)) / ((float) activity.getResources().getConfiguration().densityDpi)) * 160.0f)) >= 600;
    }

    /* renamed from: b */
    public static boolean m2021b(Activity activity) {
        float min;
        float max;
        if (activity == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            Rect bounds = activity.getWindowManager().getCurrentWindowMetrics().getBounds();
            max = Math.max(bounds.width(), bounds.height());
            min = Math.min(bounds.width(), bounds.height());
        } else {
            int i = activity.getResources().getDisplayMetrics().widthPixels;
            int i2 = activity.getResources().getDisplayMetrics().heightPixels;
            min = Math.min(i, i2);
            max = Math.max(i, i2);
        }
        boolean z = max / min < 1.0f;
        String str = "longSide" + max + "shortSide" + min;
        if (C13652o.f27494a) {
            Log.i("isLargeWindow", str);
        }
        return z;
    }
}
