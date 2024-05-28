package com.baidu.cloud.media.player.render.p134a;

import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.media.player.render.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FullFrameRect {

    /* renamed from: h */
    private static float f4381h = 1.0f;

    /* renamed from: i */
    private static float[] f4382i;

    /* renamed from: j */
    private static short[] f4383j;

    /* renamed from: a */
    private int f4384a;

    /* renamed from: b */
    private int f4385b;

    /* renamed from: c */
    private int f4386c;

    /* renamed from: d */
    private int f4387d;

    /* renamed from: e */
    private int f4388e;

    /* renamed from: f */
    private FloatBuffer f4389f;

    /* renamed from: g */
    private ShortBuffer f4390g;

    /* renamed from: k */
    private FloatBuffer f4391k;

    /* renamed from: l */
    private float[] f4392l = {0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};

    /* renamed from: m */
    private int f4393m = 0;

    /* renamed from: n */
    private String f4394n = "";

    static {
        float f = f4381h;
        f4382i = new float[]{-f, f, 0.0f, -f, -f, 0.0f, f, -f, 0.0f, f, f, 0.0f};
        f4383j = new short[]{0, 1, 2, 0, 2, 3};
    }

    /* renamed from: a */
    public void m20041a() {
        this.f4384a = GlUtil.m20033a("attribute vec4 vPosition;attribute vec4 vTexCoordinate;uniform mat4 textureTransform;varying vec2 v_TexCoordinate;void main() {   v_TexCoordinate = (textureTransform * vTexCoordinate).xy;   gl_Position = vPosition;}", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;uniform samplerExternalOES texture;varying vec2 v_TexCoordinate;void main () {    vec4 color = texture2D(texture, v_TexCoordinate);    gl_FragColor = color;}");
        int i = this.f4384a;
        if (i == 0) {
            throw new RuntimeException("Unable to create program");
        }
        this.f4385b = GLES20.glGetUniformLocation(i, "texture");
        this.f4386c = GLES20.glGetAttribLocation(this.f4384a, "vTexCoordinate");
        this.f4387d = GLES20.glGetAttribLocation(this.f4384a, "vPosition");
        this.f4388e = GLES20.glGetUniformLocation(this.f4384a, "textureTransform");
    }

    /* renamed from: b */
    public void m20037b() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(f4383j.length * 2);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f4390g = allocateDirect.asShortBuffer();
        this.f4390g.put(f4383j);
        this.f4390g.position(0);
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(f4382i.length * 4);
        allocateDirect2.order(ByteOrder.nativeOrder());
        this.f4389f = allocateDirect2.asFloatBuffer();
        this.f4389f.put(f4382i);
        this.f4389f.position(0);
    }

    /* renamed from: a */
    public int m20038a(int[] iArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.f4392l.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f4391k = allocateDirect.asFloatBuffer();
        this.f4391k.put(this.f4392l);
        this.f4391k.position(0);
        GLES20.glActiveTexture(33984);
        GLES20.glGenTextures(1, iArr, 0);
        m20039a("Texture generate");
        GLES20.glBindTexture(36197, iArr[0]);
        m20039a("Texture bind");
        return iArr[0];
    }

    /* renamed from: a */
    public void m20039a(String str) {
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError == 0) {
                return;
            }
            Log.e("SurfaceTest", str + ": glError " + GLUtils.getEGLErrorString(glGetError));
        }
    }

    /* renamed from: a */
    public void m20040a(int i, float[] fArr) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        GLES20.glUseProgram(this.f4384a);
        GLES20.glEnableVertexAttribArray(this.f4387d);
        GLES20.glVertexAttribPointer(this.f4387d, 3, 5126, false, 12, (Buffer) this.f4389f);
        GLES20.glBindTexture(36197, i);
        GLES20.glActiveTexture(33984);
        GLES20.glUniform1i(this.f4385b, 0);
        GLES20.glEnableVertexAttribArray(this.f4386c);
        GLES20.glVertexAttribPointer(this.f4386c, 4, 5126, false, 0, (Buffer) this.f4391k);
        GLES20.glUniformMatrix4fv(this.f4388e, 1, false, fArr, 0);
        GLES20.glDrawElements(4, f4383j.length, 5123, this.f4390g);
        GLES20.glDisableVertexAttribArray(this.f4387d);
        GLES20.glDisableVertexAttribArray(this.f4386c);
    }

    /* renamed from: b */
    public void m20036b(int[] iArr) {
        GLES20.glDeleteTextures(1, iArr, 0);
        GLES20.glDeleteProgram(this.f4384a);
        this.f4384a = -1;
    }
}
