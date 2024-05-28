package android.support.p083v4.view.animation;

import android.graphics.Path;
import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.view.animation.PathInterpolatorCompat */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class PathInterpolatorCompat {
    private PathInterpolatorCompat() {
    }

    public static Interpolator create(Path path) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new PathInterpolator(path);
        }
        return new PathInterpolatorApi14(path);
    }

    public static Interpolator create(float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new PathInterpolator(f, f2);
        }
        return new PathInterpolatorApi14(f, f2);
    }

    public static Interpolator create(float f, float f2, float f3, float f4) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new PathInterpolator(f, f2, f3, f4);
        }
        return new PathInterpolatorApi14(f, f2, f3, f4);
    }
}
