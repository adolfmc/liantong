package android.support.p083v4.graphics;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.graphics.BitmapCompat */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class BitmapCompat {
    public static boolean hasMipMap(@NonNull Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 18) {
            return bitmap.hasMipMap();
        }
        return false;
    }

    public static void setHasMipMap(@NonNull Bitmap bitmap, boolean z) {
        if (Build.VERSION.SDK_INT >= 18) {
            bitmap.setHasMipMap(z);
        }
    }

    public static int getAllocationByteCount(@NonNull Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            return bitmap.getAllocationByteCount();
        }
        return bitmap.getByteCount();
    }

    private BitmapCompat() {
    }
}
