package com.example.asus.detectionandalign.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.utils.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4292d {
    /* renamed from: a */
    public static Bitmap m15952a(Bitmap bitmap, int i, int i2, boolean z) {
        int i3;
        int i4;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (height < i) {
            return bitmap;
        }
        int i5 = i / 2;
        int i6 = i2 / 2;
        int i7 = width / 2;
        int i8 = height / 2;
        if (width < i2) {
            i4 = 0;
            i3 = width;
        } else {
            i3 = i2;
            i4 = i7 - i6;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i4, i8 - i5, i3, i, (Matrix) null, false);
        if (z && bitmap != null && !bitmap.equals(createBitmap) && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }
}
