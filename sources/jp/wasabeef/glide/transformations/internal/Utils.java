package jp.wasabeef.glide.transformations.internal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Utils {
    private Utils() {
    }

    public static Drawable getMaskDrawable(Context context, int i) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable = context.getDrawable(i);
        } else {
            drawable = context.getResources().getDrawable(i);
        }
        if (drawable != null) {
            return drawable;
        }
        throw new IllegalArgumentException("maskId is invalid");
    }
}
