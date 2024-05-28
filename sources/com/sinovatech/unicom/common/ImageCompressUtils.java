package com.sinovatech.unicom.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ImageCompressUtils {
    public static final String TAG = "ImageCompressUtils";

    public static ByteArrayOutputStream compress(String str, float f, float f2) {
        float f3;
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (i2 > f || i3 > f2) {
            i = (int) Math.pow(2.0d, Math.ceil(Math.log(i2 >= i3 ? f3 / f : i3 / f2) / Math.log(2.0d)));
        }
        options.inSampleSize = i;
        options.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i4 = 100;
        decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        System.out.println(byteArrayOutputStream.toByteArray().length);
        while (byteArrayOutputStream.toByteArray().length > 102400) {
            byteArrayOutputStream.reset();
            i4 -= 5;
            decodeFile.compress(Bitmap.CompressFormat.JPEG, i4, byteArrayOutputStream);
            System.out.println(byteArrayOutputStream.toByteArray().length);
        }
        return byteArrayOutputStream;
    }

    public static ByteArrayOutputStream compress(String str, float f) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        float f2 = i2;
        float f3 = f2 * f;
        float f4 = i3;
        float f5 = f * f4;
        if (f2 > f3 || f4 > f5) {
            i = (int) Math.pow(2.0d, Math.ceil(Math.log(i2 >= i3 ? f2 / f3 : f4 / f5) / Math.log(2.0d)));
        }
        options.inSampleSize = i;
        options.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i4 = 100;
        decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        System.out.println(byteArrayOutputStream.toByteArray().length);
        while (byteArrayOutputStream.toByteArray().length > 102400) {
            byteArrayOutputStream.reset();
            i4 -= 5;
            decodeFile.compress(Bitmap.CompressFormat.JPEG, i4, byteArrayOutputStream);
            System.out.println(byteArrayOutputStream.toByteArray().length);
        }
        return byteArrayOutputStream;
    }

    public static Bitmap getBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float f = i / width;
        matrix.postScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (bitmap != null && !bitmap.equals(createBitmap) && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap fitBitmap(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float f = i / width;
        matrix.postScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (bitmap != null && !bitmap.equals(createBitmap) && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap fitBitmapShare(Bitmap bitmap, int i) {
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float f = i / width;
            matrix.postScale(f, f);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            if (bitmap != null && !bitmap.equals(createBitmap)) {
                bitmap.isRecycled();
            }
            return createBitmap;
        } catch (Exception e) {
            UIUtils.logD("ImageCompressUtils", e.getLocalizedMessage());
            return null;
        }
    }
}
