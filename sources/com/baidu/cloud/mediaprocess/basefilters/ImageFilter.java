package com.baidu.cloud.mediaprocess.basefilters;

import android.graphics.PointF;
import android.opengl.GLES20;
import android.util.Log;
import com.baidu.cloud.mediaprocess.gles.Drawable2d;
import com.baidu.cloud.mediaprocess.gles.GlUtil;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.LinkedList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImageFilter {
    public static final String FRAGMENT_SHADER = "precision mediump float;varying vec2 textureCoordinate;uniform sampler2D inputImageTexture;void main() {    gl_FragColor = texture2D(inputImageTexture, textureCoordinate);}";
    public static final String FRAGMENT_SHADER_WITHOUT_MATRIX = "precision mediump float;varying vec2 textureCoordinate;uniform sampler2D inputImageTexture;void main() {    gl_FragColor = texture2D(inputImageTexture, textureCoordinate);}";
    public static final int NO_IMAGE = -1;
    public static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;uniform mat4 uTexMatrix;attribute vec4 position;attribute vec4 inputTextureCoordinate;varying vec2 textureCoordinate;void main() {    gl_Position = uMVPMatrix * position;    textureCoordinate = (uTexMatrix * inputTextureCoordinate).xy;}";
    public static final String VERTEX_SHADER_WITHOUT_MATRIX = "attribute vec4 position;attribute vec4 inputTextureCoordinate;varying vec2 textureCoordinate;void main() {    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;}";

    /* renamed from: a */
    public final LinkedList<Runnable> f4505a;

    /* renamed from: b */
    public String f4506b;

    /* renamed from: c */
    public String f4507c;

    /* renamed from: d */
    public int f4508d;

    /* renamed from: e */
    public int f4509e;

    /* renamed from: f */
    public int f4510f;

    /* renamed from: g */
    public int f4511g;
    public boolean mIsInitialized;
    public float[] mMvpMatrix;
    public int mOutputHeight;
    public int mOutputWidth;
    public final Drawable2d mRectDrawable;
    public float[] mTexMatrix;
    public int mTextureTarget;
    public int mUniformMVPMatrix;
    public int mUniformTexMatrix;

    public ImageFilter() {
        this("uniform mat4 uMVPMatrix;uniform mat4 uTexMatrix;attribute vec4 position;attribute vec4 inputTextureCoordinate;varying vec2 textureCoordinate;void main() {    gl_Position = uMVPMatrix * position;    textureCoordinate = (uTexMatrix * inputTextureCoordinate).xy;}", "precision mediump float;varying vec2 textureCoordinate;uniform sampler2D inputImageTexture;void main() {    gl_FragColor = texture2D(inputImageTexture, textureCoordinate);}");
    }

    public ImageFilter(String str, String str2) {
        this.mRectDrawable = new Drawable2d(Drawable2d.Prefab.FULL_RECTANGLE);
        this.f4506b = null;
        this.f4507c = null;
        this.mMvpMatrix = null;
        this.mTexMatrix = null;
        this.f4505a = new LinkedList<>();
        this.mTextureTarget = 3553;
        this.f4506b = str;
        this.f4507c = str2;
    }

    /* renamed from: a */
    public final void m19974a() {
        synchronized (this.f4505a) {
            while (!this.f4505a.isEmpty()) {
                this.f4505a.removeFirst().run();
            }
        }
    }

    public void draw(int i) {
        draw(i, 0);
    }

    public void draw(int i, int i2) {
        if (this.mMvpMatrix == null) {
            this.mMvpMatrix = (float[]) GlUtil.IDENTITY_MATRIX.clone();
        }
        if (this.mTexMatrix == null) {
            this.mTexMatrix = (float[]) GlUtil.IDENTITY_MATRIX.clone();
        }
        GlUtil.checkGlError("draw start");
        GLES20.glBindFramebuffer(36160, i2);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glViewport(0, 0, this.mOutputWidth, this.mOutputHeight);
        GLES20.glUseProgram(this.f4508d);
        GlUtil.checkGlError("glUseProgram");
        m19974a();
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(this.mTextureTarget, i);
        GLES20.glUniform1i(this.f4511g, 0);
        int i3 = this.mUniformMVPMatrix;
        if (i3 >= 0) {
            GLES20.glUniformMatrix4fv(i3, 1, false, this.mMvpMatrix, 0);
            GlUtil.checkGlError("glUniformMatrix4fv for mMvpMatrix");
        }
        int i4 = this.mUniformTexMatrix;
        if (i4 >= 0) {
            GLES20.glUniformMatrix4fv(i4, 1, false, this.mTexMatrix, 0);
            GlUtil.checkGlError("glUniformMatrix4fv for mTexMatrix");
        }
        GLES20.glEnableVertexAttribArray(this.f4509e);
        GlUtil.checkGlError("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.f4509e, 2, 5126, false, 0, (Buffer) this.mRectDrawable.getVertexArray());
        GlUtil.checkGlError("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(this.f4510f);
        GlUtil.checkGlError("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.f4510f, 2, 5126, false, 0, (Buffer) this.mRectDrawable.getTexCoordArray());
        GlUtil.checkGlError("glVertexAttribPointer");
        onDrawPre();
        GLES20.glDrawArrays(5, 0, 4);
        GlUtil.checkGlError("glDrawArrays");
        GLES20.glDisableVertexAttribArray(this.f4509e);
        GLES20.glDisableVertexAttribArray(this.f4510f);
        GLES20.glBindTexture(this.mTextureTarget, 0);
        GLES20.glUseProgram(0);
    }

    public int getAttribPosition() {
        return this.f4509e;
    }

    public int getAttribTextureCoordinate() {
        return this.f4510f;
    }

    public int getOutputHeight() {
        return this.mOutputHeight;
    }

    public int getOutputWidth() {
        return this.mOutputWidth;
    }

    public int getProgram() {
        return this.f4508d;
    }

    public int getUniformMvpMatrix() {
        return this.mUniformMVPMatrix;
    }

    public int getUniformTexMatrix() {
        return this.mUniformTexMatrix;
    }

    public int getUniformTexture() {
        return this.f4511g;
    }

    public final void init() {
        if (this.mIsInitialized) {
            return;
        }
        onInit();
        this.mIsInitialized = true;
        onInitialized();
    }

    public boolean isInitialized() {
        return this.mIsInitialized;
    }

    public void onDrawPre() {
    }

    public void onInit() {
        this.f4508d = GlUtil.createProgram(this.f4506b, this.f4507c);
        int i = this.f4508d;
        if (i == 0) {
            throw new RuntimeException("Unable to create program");
        }
        this.f4509e = GLES20.glGetAttribLocation(i, "position");
        GlUtil.checkLocation(this.f4509e, "position");
        this.f4510f = GLES20.glGetAttribLocation(this.f4508d, "inputTextureCoordinate");
        GlUtil.checkLocation(this.f4510f, "inputTextureCoordinate");
        this.mUniformMVPMatrix = GLES20.glGetUniformLocation(this.f4508d, "uMVPMatrix");
        GlUtil.checkGlError("getting location of uMVPMatrix");
        this.mUniformTexMatrix = GLES20.glGetUniformLocation(this.f4508d, "uTexMatrix");
        GlUtil.checkGlError("getting location of uTexMatrix");
        this.f4511g = GLES20.glGetUniformLocation(this.f4508d, "inputImageTexture");
        GlUtil.checkLocation(this.f4511g, "inputImageTexture");
    }

    public void onInitialized() {
        this.mOutputWidth = 0;
        this.mOutputHeight = 0;
    }

    public void onInputSizeChanged(int i, int i2) {
    }

    public void onOutputSizeChanged(int i, int i2) {
        this.mOutputWidth = i;
        this.mOutputHeight = i2;
    }

    public void onReleased() {
        this.mOutputWidth = 0;
        this.mOutputHeight = 0;
    }

    public void release() {
        Log.d("ImageFilter", "deleting program " + this.f4508d);
        GLES20.glDeleteProgram(this.f4508d);
        this.mIsInitialized = false;
        this.f4508d = -1;
        onReleased();
    }

    public void runOnDraw(Runnable runnable) {
        synchronized (this.f4505a) {
            this.f4505a.addLast(runnable);
        }
    }

    public void setFloat(final int i, final float f) {
        runOnDraw(new Runnable(this) { // from class: com.baidu.cloud.mediaprocess.basefilters.ImageFilter.2
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(i, f);
            }
        });
    }

    public void setFloatArray(final int i, final float[] fArr) {
        runOnDraw(new Runnable(this) { // from class: com.baidu.cloud.mediaprocess.basefilters.ImageFilter.6
            @Override // java.lang.Runnable
            public void run() {
                int i2 = i;
                float[] fArr2 = fArr;
                GLES20.glUniform1fv(i2, fArr2.length, FloatBuffer.wrap(fArr2));
            }
        });
    }

    public void setFloatVec2(final int i, final float[] fArr) {
        runOnDraw(new Runnable(this) { // from class: com.baidu.cloud.mediaprocess.basefilters.ImageFilter.3
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform2fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    public void setFloatVec3(final int i, final float[] fArr) {
        runOnDraw(new Runnable(this) { // from class: com.baidu.cloud.mediaprocess.basefilters.ImageFilter.4
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform3fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    public void setFloatVec4(final int i, final float[] fArr) {
        runOnDraw(new Runnable(this) { // from class: com.baidu.cloud.mediaprocess.basefilters.ImageFilter.5
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform4fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    public void setInputTextureType(int i) {
        this.mTextureTarget = i;
    }

    public void setInteger(final int i, final int i2) {
        runOnDraw(new Runnable(this) { // from class: com.baidu.cloud.mediaprocess.basefilters.ImageFilter.1
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1i(i, i2);
            }
        });
    }

    public void setMVPMatrix(float[] fArr) {
        this.mMvpMatrix = fArr;
    }

    public void setPoint(final int i, final PointF pointF) {
        runOnDraw(new Runnable(this) { // from class: com.baidu.cloud.mediaprocess.basefilters.ImageFilter.7
            @Override // java.lang.Runnable
            public void run() {
                PointF pointF2 = pointF;
                GLES20.glUniform2fv(i, 1, new float[]{pointF2.x, pointF2.y}, 0);
            }
        });
    }

    public void setTextureMatrix(float[] fArr) {
        this.mTexMatrix = fArr;
    }

    public void setUniformMatrix3f(final int i, final float[] fArr) {
        runOnDraw(new Runnable(this) { // from class: com.baidu.cloud.mediaprocess.basefilters.ImageFilter.8
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniformMatrix3fv(i, 1, false, fArr, 0);
            }
        });
    }

    public void setUniformMatrix4f(final int i, final float[] fArr) {
        runOnDraw(new Runnable(this) { // from class: com.baidu.cloud.mediaprocess.basefilters.ImageFilter.9
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniformMatrix4fv(i, 1, false, fArr, 0);
            }
        });
    }
}
