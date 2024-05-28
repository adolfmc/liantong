package android.support.p083v4.view;

import android.os.Build;
import android.view.ScaleGestureDetector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.view.ScaleGestureDetectorCompat */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ScaleGestureDetectorCompat {
    private ScaleGestureDetectorCompat() {
    }

    @Deprecated
    public static void setQuickScaleEnabled(Object obj, boolean z) {
        setQuickScaleEnabled((ScaleGestureDetector) obj, z);
    }

    public static void setQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            scaleGestureDetector.setQuickScaleEnabled(z);
        }
    }

    @Deprecated
    public static boolean isQuickScaleEnabled(Object obj) {
        return isQuickScaleEnabled((ScaleGestureDetector) obj);
    }

    public static boolean isQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector) {
        if (Build.VERSION.SDK_INT >= 19) {
            return scaleGestureDetector.isQuickScaleEnabled();
        }
        return false;
    }
}
