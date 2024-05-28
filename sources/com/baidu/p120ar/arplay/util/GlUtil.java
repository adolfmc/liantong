package com.baidu.p120ar.arplay.util;

import android.graphics.Bitmap;
import android.hardware.Camera;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.util.GlUtil */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GlUtil {
    public static final float[] BOTH_FLIP_MATRIX;
    public static final float[] IDENTITY_MATRIX = new float[16];
    public static final float[] MIRRORING_MATRIX;
    public static final int NO_TEXTURE = -1;
    private static final int SIZEOF_FLOAT = 4;
    public static final String TAG = "GlUtil";
    public static final float[] VERT_FLIP_MATRIX;

    public static void checkGlError(String str) {
    }

    public static void checkLocation(int i, String str) {
    }

    static {
        Matrix.setIdentityM(IDENTITY_MATRIX, 0);
        MIRRORING_MATRIX = new float[16];
        Matrix.setIdentityM(MIRRORING_MATRIX, 0);
        Matrix.scaleM(MIRRORING_MATRIX, 0, -1.0f, 1.0f, 1.0f);
        VERT_FLIP_MATRIX = new float[16];
        Matrix.setIdentityM(VERT_FLIP_MATRIX, 0);
        Matrix.scaleM(VERT_FLIP_MATRIX, 0, 1.0f, -1.0f, 1.0f);
        BOTH_FLIP_MATRIX = new float[16];
        Matrix.setIdentityM(BOTH_FLIP_MATRIX, 0);
        Matrix.scaleM(BOTH_FLIP_MATRIX, 0, -1.0f, -1.0f, 1.0f);
    }

    private GlUtil() {
    }

    public static int createProgram(String str, String str2) {
        int loadShader;
        int loadShader2 = loadShader(35633, str);
        if (loadShader2 == 0 || (loadShader = loadShader(35632, str2)) == 0) {
            return 0;
        }
        int glCreateProgram = GLES30.glCreateProgram();
        checkGlError("glCreateProgram");
        if (glCreateProgram == 0) {
            Log.e("GlUtil", "Could not create program");
        }
        GLES30.glAttachShader(glCreateProgram, loadShader2);
        checkGlError("glAttachShader");
        GLES30.glAttachShader(glCreateProgram, loadShader);
        checkGlError("glAttachShader");
        GLES30.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES30.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            Log.e("GlUtil", "Could not link program: ");
            Log.e("GlUtil", GLES30.glGetProgramInfoLog(glCreateProgram));
            GLES30.glDeleteProgram(glCreateProgram);
            return 0;
        }
        return glCreateProgram;
    }

    public static int loadShader(int i, String str) {
        int glCreateShader = GLES30.glCreateShader(i);
        checkGlError("glCreateShader type=" + i);
        GLES30.glShaderSource(glCreateShader, str);
        GLES30.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES30.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] == 0) {
            Log.e("GlUtil", "Could not compile shader " + i + ":");
            Log.e("GlUtil", "error=" + GLES30.glGetError() + ";log=" + GLES30.glGetShaderInfoLog(glCreateShader));
            GLES30.glDeleteShader(glCreateShader);
            return 0;
        }
        return glCreateShader;
    }

    public static int createTextureObject() {
        int[] iArr = new int[1];
        GLES30.glGenTextures(iArr.length, iArr, 0);
        return iArr[0];
    }

    public static int createTextureObject(int i) {
        return createTextureObject(i, 0);
    }

    private static int createTextureObject(int i, int i2) {
        int i3 = i2 == 0 ? 9729 : 9728;
        int[] iArr = new int[1];
        GLES30.glGenTextures(1, iArr, 0);
        checkGlError("glGenTextures");
        int i4 = iArr[0];
        GLES30.glBindTexture(i, i4);
        checkGlError("glBindTexture " + i4);
        float f = (float) i3;
        GLES30.glTexParameterf(i, 10241, f);
        GLES30.glTexParameterf(i, 10240, f);
        GLES30.glTexParameteri(i, 10242, 33071);
        GLES30.glTexParameteri(i, 10243, 33071);
        checkGlError("glTexParameter");
        return i4;
    }

    public static int createTextureObject(int i, int i2, int i3, int i4) {
        int createTextureObject = createTextureObject(i, i2);
        GLES30.glTexImage2D(i, 0, 6408, i3, i4, 0, 6408, 5121, null);
        return createTextureObject;
    }

    public static int createTextureObject(int i, int i2, int i3) {
        int createTextureObject = createTextureObject(i);
        GLES30.glTexImage2D(i, 0, 6408, i2, i3, 0, 6408, 5121, null);
        checkGlError("createTextureObject");
        return createTextureObject;
    }

    public static void destroyTextureObject(int i) {
        if (i == -1) {
            return;
        }
        GLES30.glDeleteTextures(1, new int[]{i}, 0);
        checkGlError("glDeleteTextures");
    }

    public static int createFrameBufferObject() {
        int[] iArr = new int[1];
        GLES30.glGenFramebuffers(1, iArr, 0);
        return iArr[0];
    }

    public static void bindTextureToFBO(int i, int i2, int i3) {
        GLES30.glBindFramebuffer(36160, i3);
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES30.glBindTexture(i2, i);
        GLES30.glFramebufferTexture2D(36160, 36064, i2, i, 0);
    }

    public static int createFBOForTexture(int i, int i2) {
        int createFrameBufferObject = createFrameBufferObject();
        checkGlError("createFrameBufferObject");
        bindTextureToFBO(i, i2, createFrameBufferObject);
        return createFrameBufferObject;
    }

    public static void destroyFramebufferObject(int i) {
        if (i == -1) {
            return;
        }
        GLES30.glDeleteFramebuffers(1, new int[]{i}, 0);
        checkGlError("glDeleteFramebuffers");
    }

    public static int createRenderbuffers() {
        int[] iArr = new int[1];
        GLES30.glGenRenderbuffers(1, iArr, 0);
        return iArr[0];
    }

    public static void bindRenderbuffersToFBO(int i, int i2, int i3, int i4, int i5) {
        GLES30.glBindRenderbuffer(36161, i4);
        GLES30.glRenderbufferStorage(36161, i, i2, i3);
        GLES30.glBindRenderbuffer(36161, 0);
        GLES30.glBindFramebuffer(36160, i5);
        GLES30.glFramebufferRenderbuffer(36160, 36096, 36161, i4);
        GLES30.glBindFramebuffer(36160, 0);
    }

    public static void destroyRenderbuffers(int i) {
        if (i == -1) {
            return;
        }
        GLES30.glDeleteRenderbuffers(1, new int[]{i}, 0);
        checkGlError("glDeleteRenderbuffers");
    }

    public static int loadTexture(Bitmap bitmap, int i) {
        return loadTexture(bitmap, i, true);
    }

    public static int loadTexture(Bitmap bitmap, int i, boolean z) {
        int[] iArr = new int[1];
        if (i == -1) {
            GLES30.glGenTextures(1, iArr, 0);
            GLES30.glBindTexture(3553, iArr[0]);
            GLES30.glTexParameterf(3553, 10240, 9729.0f);
            GLES30.glTexParameterf(3553, 10241, 9729.0f);
            GLES30.glTexParameterf(3553, 10242, 33071.0f);
            GLES30.glTexParameterf(3553, 10243, 33071.0f);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
        } else {
            GLES30.glBindTexture(3553, i);
            GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
            iArr[0] = i;
        }
        if (z) {
            bitmap.recycle();
        }
        return iArr[0];
    }

    public static int loadTexture(IntBuffer intBuffer, Camera.Size size, int i) {
        int[] iArr = new int[1];
        if (i == -1) {
            GLES30.glGenTextures(1, iArr, 0);
            GLES30.glBindTexture(3553, iArr[0]);
            GLES30.glTexParameterf(3553, 10240, 9729.0f);
            GLES30.glTexParameterf(3553, 10241, 9729.0f);
            GLES30.glTexParameterf(3553, 10242, 33071.0f);
            GLES30.glTexParameterf(3553, 10243, 33071.0f);
            GLES30.glTexImage2D(3553, 0, 6408, size.width, size.height, 0, 6408, 5121, intBuffer);
        } else {
            GLES30.glBindTexture(3553, i);
            GLES30.glTexSubImage2D(3553, 0, 0, 0, size.width, size.height, 6408, 5121, intBuffer);
            iArr[0] = i;
        }
        return iArr[0];
    }

    public static int loadTexture(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, int i5) {
        int[] iArr = new int[1];
        if (i5 == -1) {
            GLES30.glGenTextures(1, iArr, 0);
            GLES30.glBindTexture(3553, iArr[0]);
            GLES30.glTexParameterf(3553, 10240, 9729.0f);
            GLES30.glTexParameterf(3553, 10241, 9729.0f);
            GLES30.glTexParameterf(3553, 10242, 33071.0f);
            GLES30.glTexParameterf(3553, 10243, 33071.0f);
            GLES30.glTexImage2D(3553, 0, 6408, i3, i4, 0, 6408, 5121, byteBuffer);
        } else {
            GLES30.glBindTexture(3553, i5);
            GLES30.glTexSubImage2D(3553, 0, i, i2, i3, i4, 6408, 5121, byteBuffer);
            iArr[0] = i5;
        }
        return iArr[0];
    }

    public static int loadTextureAsBitmap(IntBuffer intBuffer, Camera.Size size, int i) {
        return loadTexture(Bitmap.createBitmap(intBuffer.array(), size.width, size.height, Bitmap.Config.ARGB_8888), i);
    }

    public static int createImageTexture(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int[] iArr = new int[1];
        GLES30.glGenTextures(1, iArr, 0);
        int i4 = iArr[0];
        checkGlError("glGenTextures");
        GLES30.glBindTexture(3553, i4);
        GLES30.glTexParameteri(3553, 10241, 9729);
        GLES30.glTexParameteri(3553, 10240, 9729);
        checkGlError("loadImageTexture");
        GLES30.glTexImage2D(3553, 0, i3, i, i2, 0, i3, 5121, byteBuffer);
        checkGlError("loadImageTexture");
        return i4;
    }

    public static FloatBuffer createFloatBuffer(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    public static void logVersionInfo() {
        Log.i("GlUtil", "vendor  : " + GLES30.glGetString(7936));
        Log.i("GlUtil", "renderer: " + GLES30.glGetString(7937));
        Log.i("GlUtil", "version : " + GLES30.glGetString(7938));
    }
}
