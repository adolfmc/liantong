package com.baidu.cloud.mediaprocess.gles;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GlUtil {
    public static final float[] IDENTITY_MATRIX = new float[16];
    public static final int NO_TEXTURE = -1;
    public static final String TAG = "Grafika";
    public static final float[] VERT_FLIP_MATRIX;
    public static String mGLErrorMsg = "";

    static {
        Matrix.setIdentityM(IDENTITY_MATRIX, 0);
        VERT_FLIP_MATRIX = new float[16];
        Matrix.setIdentityM(VERT_FLIP_MATRIX, 0);
        Matrix.scaleM(VERT_FLIP_MATRIX, 0, 1.0f, -1.0f, 1.0f);
    }

    /* renamed from: a */
    public static int m19853a(int i, int i2) {
        int i3 = i2 == 0 ? 9729 : 9728;
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        checkGlError("glGenTextures");
        int i4 = iArr[0];
        GLES20.glBindTexture(i, i4);
        checkGlError("glBindTexture " + i4);
        float f = (float) i3;
        GLES20.glTexParameterf(i, 10241, f);
        GLES20.glTexParameterf(i, 10240, f);
        GLES20.glTexParameteri(i, 10242, 33071);
        GLES20.glTexParameteri(i, 10243, 33071);
        checkGlError("glTexParameter");
        return i4;
    }

    public static void bindTextureToFBO(int i, int i2, int i3) {
        GLES20.glBindFramebuffer(36160, i3);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glBindTexture(i2, i);
        GLES20.glFramebufferTexture2D(36160, 36064, i2, i, 0);
    }

    public static void checkGlError(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("Grafika", str + ": glError 0x" + Integer.toHexString(glGetError));
        }
    }

    public static void checkLocation(int i, String str) {
        if (i >= 0) {
            return;
        }
        throw new RuntimeException("Unable to locate '" + str + "' in program");
    }

    public static int createFBOForTexture(int i, int i2) {
        int createFrameBufferObject = createFrameBufferObject();
        checkGlError("createFrameBufferObject");
        bindTextureToFBO(i, i2, createFrameBufferObject);
        return createFrameBufferObject;
    }

    public static FloatBuffer createFloatBuffer(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    public static int createFrameBufferObject() {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        return iArr[0];
    }

    public static int createImageTexture(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i4 = iArr[0];
        checkGlError("glGenTextures");
        GLES20.glBindTexture(3553, i4);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        checkGlError("loadImageTexture");
        GLES20.glTexImage2D(3553, 0, i3, i, i2, 0, i3, 5121, byteBuffer);
        checkGlError("loadImageTexture");
        return i4;
    }

    public static int createProgram(String str, String str2) {
        int loadShader;
        int loadShader2 = loadShader(35633, str);
        if (loadShader2 == 0 || (loadShader = loadShader(35632, str2)) == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        checkGlError("glCreateProgram");
        if (glCreateProgram == 0) {
            Log.e("Grafika", "Could not create program");
        }
        GLES20.glAttachShader(glCreateProgram, loadShader2);
        checkGlError("glAttachShader");
        GLES20.glAttachShader(glCreateProgram, loadShader);
        checkGlError("glAttachShader");
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            String str3 = "Could not link program: " + GLES20.glGetProgramInfoLog(glCreateProgram);
            mGLErrorMsg = str3 + ",vertexShader:" + str + ",fragmentShader:" + str2;
            Log.e("Grafika", str3);
            GLES20.glDeleteProgram(glCreateProgram);
            return 0;
        }
        return glCreateProgram;
    }

    public static int createTextureObject(int i) {
        return m19853a(i, 0);
    }

    public static int createTextureObject(int i, int i2, int i3) {
        int m19853a = m19853a(i, 0);
        GLES20.glTexImage2D(i, 0, 6408, i2, i3, 0, 6408, 5121, null);
        checkGlError("createTextureObject");
        return m19853a;
    }

    public static int createTextureObject(int i, int i2, int i3, int i4) {
        int m19853a = m19853a(i, i2);
        GLES20.glTexImage2D(i, 0, 6408, i3, i4, 0, 6408, 5121, null);
        return m19853a;
    }

    public static void destroyFramebufferObject(int i) {
        if (i == -1) {
            return;
        }
        GLES20.glDeleteFramebuffers(1, new int[]{i}, 0);
        checkGlError("glDeleteFramebuffers");
    }

    public static void destroyTextureObject(int i) {
        if (i == -1) {
            return;
        }
        GLES20.glDeleteTextures(1, new int[]{i}, 0);
        checkGlError("glDeleteTextures");
    }

    public static int loadShader(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        checkGlError("glCreateShader type=" + i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] == 0) {
            String str2 = "Could not compile shader, type:" + i + ":" + GLES20.glGetShaderInfoLog(glCreateShader);
            mGLErrorMsg = str2 + ",shader:" + str;
            Log.e("Grafika", str2);
            GLES20.glDeleteShader(glCreateShader);
            return 0;
        }
        return glCreateShader;
    }

    public static void logVersionInfo() {
        Log.i("Grafika", "vendor  : " + GLES20.glGetString(7936));
        Log.i("Grafika", "renderer: " + GLES20.glGetString(7937));
        Log.i("Grafika", "version : " + GLES20.glGetString(7938));
    }
}
