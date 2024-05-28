package com.megvii.lv5;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.y2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5660y2 {
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
        if (r3 == null) goto L12;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m12891a(android.content.Context r4, int r5) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]
            r2 = 0
            r3 = 0
            android.content.res.Resources r4 = r4.getResources()     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L24
            java.io.InputStream r3 = r4.openRawResource(r5)     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L24
        L13:
            int r4 = r3.read(r1)     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L24
            r5 = -1
            if (r4 == r5) goto L1e
            r0.write(r1, r2, r4)     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L24
            goto L13
        L1e:
            r0.close()     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L24
            goto L2a
        L22:
            r4 = move-exception
            goto L67
        L24:
            r4 = move-exception
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L22
            if (r3 == 0) goto L32
        L2a:
            r3.close()     // Catch: java.io.IOException -> L2e
            goto L32
        L2e:
            r4 = move-exception
            r4.printStackTrace()
        L32:
            byte[] r4 = r0.toByteArray()
            int r5 = r4.length
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeByteArray(r4, r2, r5)
            r5 = 1
            int[] r0 = new int[r5]
            android.opengl.GLES20.glGenTextures(r5, r0, r2)
            r5 = r0[r2]
            r1 = 3553(0xde1, float:4.979E-42)
            android.opengl.GLES20.glBindTexture(r1, r5)
            r5 = 10241(0x2801, float:1.435E-41)
            r3 = 9729(0x2601, float:1.3633E-41)
            android.opengl.GLES20.glTexParameteri(r1, r5, r3)
            r5 = 10240(0x2800, float:1.4349E-41)
            android.opengl.GLES20.glTexParameteri(r1, r5, r3)
            r5 = 10242(0x2802, float:1.4352E-41)
            r3 = 1191259904(0x47012f00, float:33071.0)
            android.opengl.GLES20.glTexParameterf(r1, r5, r3)
            r5 = 10243(0x2803, float:1.4354E-41)
            android.opengl.GLES20.glTexParameterf(r1, r5, r3)
            android.opengl.GLUtils.texImage2D(r1, r2, r4, r2)
            r4 = r0[r2]
            return r4
        L67:
            if (r3 == 0) goto L71
            r3.close()     // Catch: java.io.IOException -> L6d
            goto L71
        L6d:
            r5 = move-exception
            r5.printStackTrace()
        L71:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5660y2.m12891a(android.content.Context, int):int");
    }
}
