package com.megvii.lv5;

import android.content.Context;
import android.opengl.GLES20;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.a2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5379a2 {

    /* renamed from: a */
    public static final float[] f12360a;

    /* renamed from: b */
    public static final float[] f12361b;

    /* renamed from: c */
    public static final float[] f12362c;

    /* renamed from: d */
    public static final float[] f12363d;

    /* renamed from: e */
    public static final float[] f12364e;

    /* renamed from: f */
    public static final float[] f12365f;

    static {
        C5379a2.class.desiredAssertionStatus();
        f12360a = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        f12361b = new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
        f12362c = new float[]{1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};
        f12363d = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        f12364e = new float[]{0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f};
        f12365f = new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    }

    /* renamed from: a */
    public static int m13619a(int i, int i2) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
        return iArr[0];
    }

    /* renamed from: a */
    public static int m13617a(String str, int i) {
        int[] iArr = new int[1];
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] == 0) {
            String str2 = "Compilation\n" + GLES20.glGetShaderInfoLog(glCreateShader);
            return 0;
        }
        return glCreateShader;
    }

    /* renamed from: a */
    public static int m13616a(String str, String str2) {
        int m13617a;
        int[] iArr = new int[1];
        int m13617a2 = m13617a(str, 35633);
        if (m13617a2 == 0 || (m13617a = m13617a(str2, 35632)) == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glCreateProgram, m13617a2);
        GLES20.glAttachShader(glCreateProgram, m13617a);
        GLES20.glLinkProgram(glCreateProgram);
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] <= 0) {
            return 0;
        }
        GLES20.glDeleteShader(m13617a2);
        GLES20.glDeleteShader(m13617a);
        return glCreateProgram;
    }

    /* renamed from: a */
    public static String m13618a(Context context, int i) {
        String str = null;
        try {
            InputStream openRawResource = context.getResources().openRawResource(i);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = openRawResource.read();
                if (read == -1) {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    openRawResource.close();
                    String str2 = new String(byteArray, "UTF-8");
                    try {
                        return str2.replaceAll("\\r\\n", "\n");
                    } catch (Exception e) {
                        str = str2;
                        e = e;
                        e.printStackTrace();
                        return str;
                    }
                }
                byteArrayOutputStream.write(read);
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* renamed from: a */
    public static float[] m13615a(float[] fArr, C5545r c5545r) {
        int length = fArr.length;
        float[] fArr2 = new float[length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = (fArr[i] + 1.0f) / 2.0f;
        }
        float[] fArr3 = new float[length];
        for (int i2 = 0; i2 < length / 2; i2++) {
            int i3 = i2 * 2;
            float f = c5545r.f13202a;
            fArr3[i3] = f + ((c5545r.f13204c - f) * fArr2[i3]);
            int i4 = i3 + 1;
            float f2 = c5545r.f13205d;
            fArr3[i4] = (1.0f - f2) + ((f2 - c5545r.f13203b) * fArr2[i4]);
        }
        for (int i5 = 0; i5 < length; i5++) {
            fArr3[i5] = (fArr3[i5] * 2.0f) - 1.0f;
        }
        return fArr3;
    }
}
