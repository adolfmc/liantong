package cn.finalteam.galleryfinal.widget.zoonview;

import android.content.Context;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class VersionedGestureDetector {
    public static GestureDetector newInstance(Context context, OnGestureListener onGestureListener) {
        GestureDetector froyoGestureDetector;
        int i = Build.VERSION.SDK_INT;
        if (i < 5) {
            froyoGestureDetector = new CupcakeGestureDetector(context);
        } else if (i < 8) {
            froyoGestureDetector = new EclairGestureDetector(context);
        } else {
            froyoGestureDetector = new FroyoGestureDetector(context);
        }
        froyoGestureDetector.setOnGestureListener(onGestureListener);
        return froyoGestureDetector;
    }
}
