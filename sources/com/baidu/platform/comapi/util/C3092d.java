package com.baidu.platform.comapi.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.util.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3092d {
    /* renamed from: a */
    public static Bitmap m17687a(Drawable drawable) {
        Bitmap bitmap;
        if (drawable == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT > 26 || !(drawable instanceof BitmapDrawable)) {
            try {
                int width = !drawable.getBounds().isEmpty() ? drawable.getBounds().width() : drawable.getIntrinsicWidth();
                int height = !drawable.getBounds().isEmpty() ? drawable.getBounds().height() : drawable.getIntrinsicHeight();
                if ((drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null) {
                    if (width <= bitmap.getWidth()) {
                        width = bitmap.getWidth();
                    }
                    if (height <= bitmap.getHeight()) {
                        height = bitmap.getHeight();
                    }
                }
                if (width <= 0) {
                    width = 1;
                }
                if (height <= 0) {
                    height = 1;
                }
                Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                return createBitmap;
            } catch (Exception unused) {
                return null;
            }
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }
}
