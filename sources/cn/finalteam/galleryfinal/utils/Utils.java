package cn.finalteam.galleryfinal.utils;

import android.graphics.Bitmap;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Utils {
    public static String getFileName(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        int lastIndexOf2 = str.lastIndexOf(".");
        if (lastIndexOf == -1 || lastIndexOf2 == -1) {
            return null;
        }
        return str.substring(lastIndexOf + 1, lastIndexOf2);
    }

    public static void saveBitmap(Bitmap bitmap, Bitmap.CompressFormat compressFormat, File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(compressFormat, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049 A[Catch: Exception -> 0x009c, TryCatch #0 {Exception -> 0x009c, blocks: (B:3:0x0009, B:8:0x001d, B:14:0x002c, B:24:0x0076, B:28:0x0084, B:15:0x0031, B:17:0x0049, B:18:0x0051, B:9:0x0022), top: B:32:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0080 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap rotateBitmap(java.lang.String r17, int r18, int r19, int r20) {
        /*
            r0 = r17
            r1 = r18
            int r2 = r19 / 2
            int r3 = r20 / 2
            r4 = 0
            android.graphics.BitmapFactory$Options r5 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Exception -> L9c
            r5.<init>()     // Catch: java.lang.Exception -> L9c
            r6 = 1
            r5.inJustDecodeBounds = r6     // Catch: java.lang.Exception -> L9c
            android.graphics.BitmapFactory.decodeFile(r0, r5)     // Catch: java.lang.Exception -> L9c
            r7 = 90
            if (r1 == r7) goto L22
            r7 = 270(0x10e, float:3.78E-43)
            if (r1 != r7) goto L1d
            goto L22
        L1d:
            int r7 = r5.outWidth     // Catch: java.lang.Exception -> L9c
            int r8 = r5.outHeight     // Catch: java.lang.Exception -> L9c
            goto L26
        L22:
            int r7 = r5.outHeight     // Catch: java.lang.Exception -> L9c
            int r8 = r5.outWidth     // Catch: java.lang.Exception -> L9c
        L26:
            r9 = 0
            if (r7 > r2) goto L31
            if (r8 <= r3) goto L2c
            goto L31
        L2c:
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeFile(r17)     // Catch: java.lang.Exception -> L9c
            goto L55
        L31:
            float r7 = (float) r7     // Catch: java.lang.Exception -> L9c
            float r10 = (float) r2     // Catch: java.lang.Exception -> L9c
            float r7 = r7 / r10
            float r8 = (float) r8     // Catch: java.lang.Exception -> L9c
            float r10 = (float) r3     // Catch: java.lang.Exception -> L9c
            float r8 = r8 / r10
            r5.inJustDecodeBounds = r9     // Catch: java.lang.Exception -> L9c
            java.io.File r10 = new java.io.File     // Catch: java.lang.Exception -> L9c
            r10.<init>(r0)     // Catch: java.lang.Exception -> L9c
            long r10 = r10.length()     // Catch: java.lang.Exception -> L9c
            r12 = 512000(0x7d000, double:2.529616E-318)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 <= 0) goto L51
            float r7 = java.lang.Math.max(r7, r8)     // Catch: java.lang.Exception -> L9c
            int r7 = (int) r7     // Catch: java.lang.Exception -> L9c
            r5.inSampleSize = r7     // Catch: java.lang.Exception -> L9c
            r9 = r6
        L51:
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeFile(r0, r5)     // Catch: java.lang.Exception -> L9c
        L55:
            if (r1 <= 0) goto L75
            android.graphics.Matrix r15 = new android.graphics.Matrix     // Catch: java.lang.Exception -> L73
            r15.<init>()     // Catch: java.lang.Exception -> L73
            float r1 = (float) r1     // Catch: java.lang.Exception -> L73
            r15.postRotate(r1)     // Catch: java.lang.Exception -> L73
            r11 = 0
            r12 = 0
            int r13 = r0.getWidth()     // Catch: java.lang.Exception -> L73
            int r14 = r0.getHeight()     // Catch: java.lang.Exception -> L73
            r16 = 1
            r10 = r0
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r10, r11, r12, r13, r14, r15, r16)     // Catch: java.lang.Exception -> L73
            r4 = r0
            goto L76
        L73:
            r4 = r0
            goto L9c
        L75:
            r4 = r0
        L76:
            int r0 = r4.getWidth()     // Catch: java.lang.Exception -> L9c
            int r1 = r4.getHeight()     // Catch: java.lang.Exception -> L9c
            if (r0 > r2) goto L82
            if (r1 <= r3) goto L9c
        L82:
            if (r9 == 0) goto L9c
            float r0 = (float) r0     // Catch: java.lang.Exception -> L9c
            float r2 = (float) r2     // Catch: java.lang.Exception -> L9c
            float r2 = r0 / r2
            float r1 = (float) r1     // Catch: java.lang.Exception -> L9c
            float r3 = (float) r3     // Catch: java.lang.Exception -> L9c
            float r3 = r1 / r3
            float r2 = java.lang.Math.max(r2, r3)     // Catch: java.lang.Exception -> L9c
            float r0 = r0 / r2
            int r0 = (int) r0     // Catch: java.lang.Exception -> L9c
            float r1 = r1 / r2
            int r1 = (int) r1     // Catch: java.lang.Exception -> L9c
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r4, r0, r1, r6)     // Catch: java.lang.Exception -> L9c
            r4.recycle()     // Catch: java.lang.Exception -> L9c
            return r0
        L9c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.finalteam.galleryfinal.utils.Utils.rotateBitmap(java.lang.String, int, int, int):android.graphics.Bitmap");
    }

    public static int getRandom(int i, int i2) {
        return (new Random().nextInt(i2) % ((i2 - i) + 1)) + i;
    }
}
