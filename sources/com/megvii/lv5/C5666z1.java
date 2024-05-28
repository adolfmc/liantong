package com.megvii.lv5;

import android.opengl.GLES20;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.z1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5666z1 {

    /* renamed from: a */
    public int f13948a;

    /* renamed from: c */
    public boolean f13950c;

    /* renamed from: d */
    public ByteBuffer f13951d;

    /* renamed from: e */
    public ByteBuffer f13952e;

    /* renamed from: f */
    public ByteBuffer f13953f;

    /* renamed from: g */
    public ByteBuffer f13954g;

    /* renamed from: j */
    public int f13957j;

    /* renamed from: k */
    public int f13958k;

    /* renamed from: l */
    public int f13959l;

    /* renamed from: m */
    public int f13960m;

    /* renamed from: n */
    public int f13961n;

    /* renamed from: o */
    public int f13962o;

    /* renamed from: p */
    public float[] f13963p;

    /* renamed from: r */
    public int f13965r;

    /* renamed from: s */
    public int f13966s;

    /* renamed from: b */
    public int[] f13949b = null;

    /* renamed from: h */
    public int f13955h = -1;

    /* renamed from: i */
    public int f13956i = -1;

    /* renamed from: q */
    public final float[] f13964q = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};

    /* renamed from: a */
    public static int m12882a(int i) {
        if (i != -1) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
        }
        return -1;
    }

    /* renamed from: a */
    public static int m12881a(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        int i5;
        if (i4 == -1) {
            int[] iArr = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            i5 = iArr[0];
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, i3, i, i2, 0, i3, 5121, null);
        } else {
            i5 = i4;
        }
        GLES20.glBindTexture(3553, i5);
        GLES20.glTexSubImage2D(3553, 0, 0, 0, i, i2, i3, 5121, byteBuffer);
        return i5;
    }

    /* renamed from: a */
    public void m12880a(byte[] bArr, int i, int i2, int i3, boolean z) {
        if (i2 == 0 || i3 == 0) {
            return;
        }
        if (!this.f13950c) {
            this.f13950c = true;
            this.f13961n = i2;
            this.f13962o = i3;
            this.f13948a = C5379a2.m13616a("attribute vec2 a_position;                         \nattribute vec2 a_texCoord;                         \nvarying vec2 v_texCoord;                           \nvoid main(){                                       \n   gl_Position = vec4(a_position, 1, 1);                       \n   v_texCoord = a_texCoord;                        \n}                                                  \n", "precision mediump float;\nvarying vec2 v_texCoord;\nuniform sampler2D y_texture;\nuniform sampler2D uv_texture;\nvoid main (void){\n   float r, g, b, y, u, v;\n   y = texture2D(y_texture, v_texCoord).r;\n   u = texture2D(uv_texture, v_texCoord).a - 0.5;\n   v = texture2D(uv_texture, v_texCoord).r - 0.5;\n   r = y + 1.370705*v;\n   g = y - 0.337633*u - 0.698001*v;\n   b = y + 1.732446*u;\n   gl_FragColor = vec4(r, g, b, 1.0);\n}\n");
            int[] iArr = new int[1];
            this.f13949b = iArr;
            GLES20.glGenFramebuffers(1, iArr, 0);
            this.f13955h = -1;
            this.f13956i = -1;
            this.f13957j = GLES20.glGetAttribLocation(this.f13948a, "a_position");
            this.f13958k = GLES20.glGetAttribLocation(this.f13948a, "a_texCoord");
            this.f13959l = GLES20.glGetUniformLocation(this.f13948a, "y_texture");
            this.f13960m = GLES20.glGetUniformLocation(this.f13948a, "uv_texture");
            int i4 = i2 * i3;
            this.f13951d = ByteBuffer.allocateDirect(i4);
            this.f13952e = ByteBuffer.allocateDirect(i4 / 2);
            this.f13951d.order(ByteOrder.nativeOrder());
            this.f13952e.order(ByteOrder.nativeOrder());
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.f13964q.length * 4);
            this.f13953f = allocateDirect;
            allocateDirect.order(ByteOrder.nativeOrder());
            this.f13953f.asFloatBuffer().put(this.f13964q);
            this.f13953f.position(0);
            this.f13963p = z ? C5379a2.f12362c : C5379a2.f12364e;
            ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(this.f13963p.length * 4);
            this.f13954g = allocateDirect2;
            allocateDirect2.order(ByteOrder.nativeOrder());
            this.f13954g.asFloatBuffer().put(this.f13963p);
            this.f13954g.position(0);
        } else if (this.f13961n != i2 || this.f13962o != i3) {
            this.f13961n = i2;
            this.f13962o = i3;
            m12882a(this.f13955h);
            this.f13955h = -1;
            m12882a(this.f13956i);
            this.f13956i = -1;
            int i5 = i2 * i3;
            this.f13951d = ByteBuffer.allocateDirect(i5);
            this.f13952e = ByteBuffer.allocateDirect(i5 / 2);
            this.f13951d.order(ByteOrder.nativeOrder());
            this.f13952e.order(ByteOrder.nativeOrder());
        }
        GLES20.glViewport(0, 0, this.f13965r, this.f13966s);
        GLES20.glBindFramebuffer(36160, this.f13949b[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, i, 0);
        GLES20.glUseProgram(this.f13948a);
        GLES20.glUniform1i(this.f13959l, 0);
        GLES20.glUniform1i(this.f13960m, 1);
        GLES20.glEnableVertexAttribArray(this.f13957j);
        GLES20.glEnableVertexAttribArray(this.f13958k);
        GLES20.glVertexAttribPointer(this.f13957j, 2, 5126, false, 0, (Buffer) this.f13953f);
        GLES20.glVertexAttribPointer(this.f13958k, 2, 5126, false, 0, (Buffer) this.f13954g);
        GLES20.glActiveTexture(33984);
        int i6 = i2 * i3;
        this.f13951d = ByteBuffer.wrap(bArr, 0, i6);
        this.f13952e.put(bArr, i6, i6 / 2);
        this.f13952e.position(0);
        this.f13955h = m12881a(this.f13951d, i2, i3, 6409, this.f13955h);
        this.f13951d = null;
        GLES20.glActiveTexture(33985);
        this.f13956i = m12881a(this.f13952e, i2 / 2, i3 / 2, 6410, this.f13956i);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f13957j);
        GLES20.glDisableVertexAttribArray(this.f13958k);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glUseProgram(0);
    }
}
