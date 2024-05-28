package com.sinovatech.unicom.separatemodule.idcard.newaction.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.util.DisplayMetrics;
import android.util.Log;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NewCamera2Utils {
    private static final String TAG = "NewCamera2Utils 当前是拍摄页面";
    public static Bitmap bitmap;
    public static byte[] photoData;

    public static Bitmap big(Bitmap bitmap2) {
        Matrix matrix = new Matrix();
        matrix.postScale(1.2f, 1.2f);
        return Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
    }

    public static Bitmap big(Bitmap bitmap2, float f) {
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
    }

    public static byte[] compressImage(Bitmap bitmap2) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            for (int i = 90; byteArrayOutputStream.toByteArray().length / 1024 > 150 && i >= 10; i -= 10) {
                byteArrayOutputStream.reset();
                bitmap2.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            Log.e("NewCamera2Utils 当前是拍摄页面", e.getMessage());
            return BitmapToBytes(bitmap2);
        }
    }

    public static Bitmap Bytes2Bimap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static byte[] BitmapToBytes(Bitmap bitmap2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static int getScreenWidth(Activity activity) {
        return activity.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0094 A[Catch: IOException -> 0x0090, TRY_LEAVE, TryCatch #5 {IOException -> 0x0090, blocks: (B:43:0x008c, B:47:0x0094), top: B:55:0x008c }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap ImageSizeCompress(android.app.Activity r9, android.net.Uri r10) {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
            java.io.InputStream r1 = r1.openInputStream(r10)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
            android.graphics.BitmapFactory$Options r2 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r2.<init>()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r3 = 1
            r2.inJustDecodeBounds = r3     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            android.graphics.BitmapFactory.decodeStream(r1, r0, r2)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            android.content.res.Resources r4 = r9.getResources()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            android.util.DisplayMetrics r4 = r4.getDisplayMetrics()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            int r5 = r4.heightPixels     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            int r4 = r4.widthPixels     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            int r6 = r2.outHeight     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            int r7 = r2.outWidth     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            float r6 = (float) r6     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            float r5 = (float) r5     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            float r6 = r6 / r5
            double r5 = (double) r6     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            double r5 = java.lang.Math.ceil(r5)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            int r5 = (int) r5     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            float r6 = (float) r7     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            float r4 = (float) r4     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            float r6 = r6 / r4
            double r6 = (double) r6     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            double r6 = java.lang.Math.ceil(r6)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            int r4 = (int) r6     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            int r4 = java.lang.Math.max(r5, r4)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            if (r4 <= r3) goto L3e
            r2.inSampleSize = r4     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
        L3e:
            r3 = 0
            r2.inJustDecodeBounds = r3     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            android.content.ContentResolver r9 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            java.io.InputStream r9 = r9.openInputStream(r10)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            android.graphics.Bitmap r10 = android.graphics.BitmapFactory.decodeStream(r9, r0, r2)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L63
            if (r1 == 0) goto L55
            r1.close()     // Catch: java.io.IOException -> L53
            goto L55
        L53:
            r9 = move-exception
            goto L5b
        L55:
            if (r9 == 0) goto L5e
            r9.close()     // Catch: java.io.IOException -> L53
            goto L5e
        L5b:
            r9.printStackTrace()
        L5e:
            return r10
        L5f:
            r10 = move-exception
            r0 = r9
            r9 = r10
            goto L8a
        L63:
            r10 = move-exception
            r8 = r10
            r10 = r9
            r9 = r8
            goto L73
        L68:
            r9 = move-exception
            goto L8a
        L6a:
            r9 = move-exception
            r10 = r0
            goto L73
        L6d:
            r9 = move-exception
            r1 = r0
            goto L8a
        L70:
            r9 = move-exception
            r10 = r0
            r1 = r10
        L73:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L88
            if (r1 == 0) goto L7e
            r1.close()     // Catch: java.io.IOException -> L7c
            goto L7e
        L7c:
            r9 = move-exception
            goto L84
        L7e:
            if (r10 == 0) goto L87
            r10.close()     // Catch: java.io.IOException -> L7c
            goto L87
        L84:
            r9.printStackTrace()
        L87:
            return r0
        L88:
            r9 = move-exception
            r0 = r10
        L8a:
            if (r1 == 0) goto L92
            r1.close()     // Catch: java.io.IOException -> L90
            goto L92
        L90:
            r10 = move-exception
            goto L98
        L92:
            if (r0 == 0) goto L9b
            r0.close()     // Catch: java.io.IOException -> L90
            goto L9b
        L98:
            r10.printStackTrace()
        L9b:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.idcard.newaction.utils.NewCamera2Utils.ImageSizeCompress(android.app.Activity, android.net.Uri):android.graphics.Bitmap");
    }

    public static Bitmap rotateImage(Bitmap bitmap2, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
    }

    public static int getExifOrientation(String str) {
        ExifInterface exifInterface;
        int attributeInt;
        try {
            exifInterface = new ExifInterface(str);
        } catch (IOException unused) {
            exifInterface = null;
        }
        if (exifInterface == null || (attributeInt = exifInterface.getAttributeInt("Orientation", -1)) == -1) {
            return 0;
        }
        if (attributeInt != 3) {
            if (attributeInt != 6) {
                if (attributeInt != 8) {
                    return 0;
                }
                return SubsamplingScaleImageView.ORIENTATION_270;
            }
            return 90;
        }
        return 180;
    }

    public static Bitmap rotateImage(int i, Bitmap bitmap2) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap2, int i) {
        if (i < 0) {
            i += 360;
        }
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        Matrix matrix = new Matrix();
        float f = width;
        float f2 = height;
        matrix.setRotate(i, f / 2.0f, f2 / 2.0f);
        float f3 = 0.0f;
        if (i == 90) {
            f = f2;
        } else if (i == 270) {
            f3 = f;
            f = 0.0f;
        } else if (i != 180) {
            return bitmap2;
        } else {
            f3 = f2;
            height = width;
            width = height;
        }
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        matrix.postTranslate(f - fArr[2], f3 - fArr[5]);
        Bitmap createBitmap = Bitmap.createBitmap(height, width, Bitmap.Config.RGB_565);
        new Canvas(createBitmap).drawBitmap(bitmap2, matrix, new Paint());
        return createBitmap;
    }
}
