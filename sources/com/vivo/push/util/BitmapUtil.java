package com.vivo.push.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class BitmapUtil {
    /* renamed from: a */
    public static Bitmap m5410a(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = i / width;
        float f2 = i2 / height;
        try {
            Matrix matrix = new Matrix();
            matrix.postScale(f, f2);
            return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        } catch (Exception unused) {
            return bitmap;
        }
    }
}
