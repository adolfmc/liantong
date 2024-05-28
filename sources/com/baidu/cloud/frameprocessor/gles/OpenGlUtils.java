package com.baidu.cloud.frameprocessor.gles;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;
import java.nio.IntBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OpenGlUtils {
    public static final float[] CUBE = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    public static final float[] IDENTITY_MATRIX = new float[16];
    public static final int NO_TEXTURE = -1;

    static {
        Matrix.setIdentityM(IDENTITY_MATRIX, 0);
    }

    public static Bitmap decodeBitmap(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, 720, 1280);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 == i4) {
            i2 = i;
        }
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static int createTexture2DObject() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i = iArr[0];
        GLES20.glBindTexture(3553, i);
        GLES20.glTexParameterf(3553, 10241, 9728.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        return i;
    }

    public static int createTexture2DObjectMipmap() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i = iArr[0];
        GLES20.glBindTexture(3553, i);
        GLES20.glTexParameterf(3553, 10241, 9985.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        return i;
    }

    public static int createTextureOESObject() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i = iArr[0];
        GLES20.glBindTexture(36197, i);
        GLES20.glTexParameterf(36197, 10241, 9728.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        return i;
    }

    public static int[] createTextureOESObject(int i) {
        int[] iArr = new int[i];
        GLES20.glGenTextures(1, iArr, 0);
        for (int i2 : iArr) {
            GLES20.glBindTexture(36197, i2);
            GLES20.glTexParameterf(36197, 10241, 9728.0f);
            GLES20.glTexParameterf(36197, 10240, 9729.0f);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
        }
        return iArr;
    }

    public static int loadTexture(Bitmap bitmap, int i) {
        return loadTexture(bitmap, i, true);
    }

    public static int loadTexture(Bitmap bitmap, int i, boolean z) {
        int[] iArr = new int[1];
        if (i == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
        } else {
            GLES20.glBindTexture(3553, i);
            GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
            iArr[0] = i;
        }
        if (z) {
            bitmap.recycle();
        }
        return iArr[0];
    }

    public static int loadTexture(IntBuffer intBuffer, int i, int i2, int i3) {
        int[] iArr = new int[1];
        if (i3 == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, intBuffer);
        } else {
            GLES20.glBindTexture(3553, i3);
            GLES20.glTexSubImage2D(3553, 0, 0, 0, i, i2, 6408, 5121, intBuffer);
            iArr[0] = i3;
        }
        return iArr[0];
    }

    public static int loadTextureAsBitmap(IntBuffer intBuffer, Camera.Size size, int i) {
        return loadTexture(Bitmap.createBitmap(intBuffer.array(), size.width, size.height, Bitmap.Config.ARGB_8888), i);
    }

    public static void glDeleteTextures(int i) {
        if (i == 0) {
            return;
        }
        GLES20.glDeleteTextures(1, new int[]{i}, 0);
    }

    public static int loadShader(String str, int i) {
        int[] iArr = new int[1];
        int glCreateShader = GLES20.glCreateShader(i);
        checkGlError("glCreateShader type=" + i + " source : " + str + "\n");
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] == 0) {
            Log.d("Load Shader Failed", "Compilation\n" + GLES20.glGetShaderInfoLog(glCreateShader));
            GLES20.glDeleteShader(glCreateShader);
            return 0;
        }
        return glCreateShader;
    }

    public static int loadProgram(String str, String str2) {
        int[] iArr = new int[1];
        int loadShader = loadShader(str, 35633);
        if (loadShader == 0) {
            throw new RuntimeException("gl Load Program Vertex Shader Failed");
        }
        int loadShader2 = loadShader(str2, 35632);
        if (loadShader2 == 0) {
            throw new RuntimeException("gl Load Program Fragment Shader Failed");
        }
        int glCreateProgram = GLES20.glCreateProgram();
        checkGlError("glCreateProgram");
        GLES20.glAttachShader(glCreateProgram, loadShader);
        checkGlError("glAttachShader");
        GLES20.glAttachShader(glCreateProgram, loadShader2);
        checkGlError("glAttachShader");
        GLES20.glLinkProgram(glCreateProgram);
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] <= 0) {
            GLES20.glDeleteProgram(glCreateProgram);
            throw new RuntimeException("gl Load Program Linking Failed");
        }
        GLES20.glDeleteShader(loadShader);
        GLES20.glDeleteShader(loadShader2);
        return glCreateProgram;
    }

    public static void checkGlError(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError == 1281) {
            Log.d("zmy", "---> GL_INVALID_VALUE : glError 0x" + Integer.toHexString(glGetError));
        } else if (glGetError != 0) {
            String str2 = str + ": glError 0x" + Integer.toHexString(glGetError);
        }
    }
}
