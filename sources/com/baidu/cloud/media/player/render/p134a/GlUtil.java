package com.baidu.cloud.media.player.render.p134a;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.media.player.render.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GlUtil {

    /* renamed from: a */
    public static final float[] f4395a = new float[16];

    /* renamed from: b */
    public static final float[] f4396b;

    /* renamed from: c */
    public static final float[] f4397c;

    /* renamed from: d */
    public static String f4398d = "";

    static {
        Matrix.setIdentityM(f4395a, 0);
        f4396b = new float[16];
        Matrix.setIdentityM(f4396b, 0);
        Matrix.scaleM(f4396b, 0, 1.0f, -1.0f, 1.0f);
        f4397c = new float[16];
        Matrix.setIdentityM(f4397c, 0);
        Matrix.scaleM(f4397c, 0, -1.0f, 1.0f, 1.0f);
    }

    /* renamed from: a */
    public static int m20033a(String str, String str2) {
        int m20035a;
        int m20035a2 = m20035a(35633, str);
        if (m20035a2 == 0 || (m20035a = m20035a(35632, str2)) == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        m20034a("glCreateProgram");
        if (glCreateProgram == 0) {
            Log.e("Grafika", "Could not create program");
        }
        GLES20.glAttachShader(glCreateProgram, m20035a2);
        m20034a("glAttachShader");
        GLES20.glAttachShader(glCreateProgram, m20035a);
        m20034a("glAttachShader");
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            String str3 = "Could not link program: " + GLES20.glGetProgramInfoLog(glCreateProgram);
            f4398d = str3 + ",vertexShader:" + str + ",fragmentShader:" + str2;
            Log.e("Grafika", str3);
            GLES20.glDeleteProgram(glCreateProgram);
            return 0;
        }
        return glCreateProgram;
    }

    /* renamed from: a */
    public static int m20035a(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        m20034a("glCreateShader type=" + i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] == 0) {
            String str2 = "Could not compile shader, type:" + i + ":" + GLES20.glGetShaderInfoLog(glCreateShader);
            f4398d = str2 + ",shader:" + str;
            Log.e("Grafika", str2);
            GLES20.glDeleteShader(glCreateShader);
            return 0;
        }
        return glCreateShader;
    }

    /* renamed from: a */
    public static void m20034a(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("Grafika", str + ": glError 0x" + Integer.toHexString(glGetError));
        }
    }
}
