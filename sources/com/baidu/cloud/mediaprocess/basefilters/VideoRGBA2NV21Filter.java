package com.baidu.cloud.mediaprocess.basefilters;

import android.opengl.GLES20;
import android.util.Log;
import com.baidu.cloud.mediaprocess.gles.GlUtil;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoRGBA2NV21Filter extends ImageFilter {

    /* renamed from: h */
    public int f4533h;

    /* renamed from: i */
    public int f4534i;

    /* renamed from: j */
    public int f4535j;

    /* renamed from: k */
    public int f4536k;

    /* renamed from: l */
    public int f4537l;

    /* renamed from: m */
    public ByteBuffer f4538m;

    /* renamed from: n */
    public byte[] f4539n;

    public VideoRGBA2NV21Filter() {
        super("uniform mat4 uMVPMatrix;uniform mat4 uTexMatrix;attribute vec4 position;attribute vec4 inputTextureCoordinate;varying vec2 textureCoordinate;void main() {    gl_Position = uMVPMatrix * position;    textureCoordinate = (uTexMatrix * inputTextureCoordinate).xy;}", "precision mediump float;\nvarying vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nuniform float inputImageWidth;\nuniform int onlyYColor;\nuniform int isMirrored;const mediump vec3 ycoeff = vec3( 0.257,  0.504,  0.098);\nconst mediump vec3 ucoeff = vec3(-0.148, -0.291,  0.439);\nconst mediump vec3 vcoeff = vec3( 0.439, -0.368, -0.071);\nvoid main() {\n   float widthOfPixel = 1.0 / inputImageWidth;\n   widthOfPixel = isMirrored == 1 ? -widthOfPixel : widthOfPixel;\n   vec2 coords1 = vec2(max(0.0, textureCoordinate.x - 1.5*widthOfPixel), textureCoordinate.y);\n   vec2 coords2 = vec2(max(0.0, textureCoordinate.x - 0.5*widthOfPixel), textureCoordinate.y);\n   vec2 coords3 = vec2(min(1.0, textureCoordinate.x + 0.5*widthOfPixel), textureCoordinate.y);\n   vec2 coords4 = vec2(min(1.0, textureCoordinate.x + 1.5*widthOfPixel), textureCoordinate.y);\n   vec2 coords5 = vec2(max(0.0, textureCoordinate.x - 1.0*widthOfPixel), textureCoordinate.y);\n   vec2 coords6 = vec2(min(1.0, textureCoordinate.x + 1.0*widthOfPixel), textureCoordinate.y);\n\n   float flag = onlyYColor == 1 ? 1.0 : 0.0;\n   gl_FragColor.r = (dot(texture2D(inputImageTexture, coords5).rgb, vcoeff) + 0.5) * (1.0 - flag) + (dot(texture2D(inputImageTexture, coords1).rgb, ycoeff) + 0.0625) * flag;\n   gl_FragColor.g = (dot(texture2D(inputImageTexture, coords5).rgb, ucoeff) + 0.5) * (1.0 - flag) + (dot(texture2D(inputImageTexture, coords2).rgb, ycoeff) + 0.0625) * flag;\n   gl_FragColor.b = (dot(texture2D(inputImageTexture, coords6).rgb, vcoeff) + 0.5) * (1.0 - flag) + (dot(texture2D(inputImageTexture, coords3).rgb, ycoeff) + 0.0625) * flag;\n   gl_FragColor.a = (dot(texture2D(inputImageTexture, coords6).rgb, ucoeff) + 0.5) * (1.0 - flag) + (dot(texture2D(inputImageTexture, coords4).rgb, ycoeff) + 0.0625) * flag;\n}\n");
        this.f4533h = -1;
        this.f4534i = -1;
        this.f4535j = -1;
        this.f4536k = -1;
        this.f4537l = -1;
    }

    /* renamed from: b */
    public final void m19972b() {
        int i = this.f4536k;
        if (i != -1) {
            GlUtil.destroyTextureObject(i);
            this.f4536k = -1;
        }
        int i2 = this.f4537l;
        if (i2 != -1) {
            GlUtil.destroyFramebufferObject(i2);
            this.f4537l = -1;
        }
    }

    @Override // com.baidu.cloud.mediaprocess.basefilters.ImageFilter
    public void draw(int i) {
        if (this.f4538m == null) {
            this.f4539n = new byte[((this.mOutputWidth * this.mOutputHeight) * 3) / 2];
            this.f4538m = ByteBuffer.wrap(this.f4539n);
        }
        if (this.mMvpMatrix == null) {
            this.mMvpMatrix = (float[]) GlUtil.VERT_FLIP_MATRIX.clone();
        }
        if (this.mTexMatrix == null) {
            this.mTexMatrix = (float[]) GlUtil.IDENTITY_MATRIX.clone();
        }
        if (this.f4536k == -1 || this.f4537l == -1) {
            int i2 = this.mOutputWidth >> 2;
            int i3 = (this.mOutputHeight * 3) / 2;
            Log.i("VideoBGRA2NV21Filter", "Calling createThumbnailTextureAndFBO()");
            if (this.f4536k != -1 && this.f4537l != -1) {
                m19972b();
            }
            this.f4536k = GlUtil.createTextureObject(3553, 0, i2, i3);
            this.f4537l = GlUtil.createFBOForTexture(this.f4536k, 3553);
        }
        GLES20.glBindFramebuffer(36160, this.f4537l);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glUseProgram(getProgram());
        int i4 = this.f4533h;
        if (i4 > 0) {
            GLES20.glUniform1f(i4, this.mOutputWidth);
        }
        if (this.mUniformMVPMatrix >= 0) {
            GLES20.glUniformMatrix4fv(getUniformMvpMatrix(), 1, false, this.mMvpMatrix, 0);
        }
        if (this.mUniformTexMatrix >= 0) {
            GLES20.glUniformMatrix4fv(getUniformTexMatrix(), 1, false, this.mTexMatrix, 0);
        }
        int i5 = this.f4535j;
        if (i5 < 0 || this.mMvpMatrix[0] >= 0.0f) {
            GLES20.glUniform1i(this.f4535j, 0);
        } else {
            GLES20.glUniform1i(i5, 1);
        }
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(getUniformTexture(), 0);
        GLES20.glEnableVertexAttribArray(getAttribPosition());
        GLES20.glVertexAttribPointer(getAttribPosition(), 2, 5126, false, 0, (Buffer) this.mRectDrawable.getVertexArray());
        GLES20.glEnableVertexAttribArray(getAttribTextureCoordinate());
        GLES20.glVertexAttribPointer(getAttribTextureCoordinate(), 2, 5126, false, 0, (Buffer) this.mRectDrawable.getTexCoordArray());
        this.f4538m.clear();
        GLES20.glViewport(0, 0, this.mOutputWidth >> 2, this.mOutputHeight);
        GLES20.glUniform1i(this.f4534i, 1);
        GLES20.glDrawArrays(5, 0, 4);
        int i6 = this.mOutputHeight;
        GLES20.glViewport(0, i6, this.mOutputWidth >> 2, i6 >> 1);
        GLES20.glUniform1i(this.f4534i, 0);
        GLES20.glDrawArrays(5, 0, 4);
        this.f4538m.flip();
        GLES20.glDisableVertexAttribArray(getAttribPosition());
        GLES20.glDisableVertexAttribArray(getAttribTextureCoordinate());
        GLES20.glBindTexture(3553, 0);
        GLES20.glUseProgram(0);
    }

    public byte[] getYUVData() {
        GLES20.glReadPixels(0, 0, this.mOutputWidth >> 2, (this.mOutputHeight * 3) / 2, 6408, 5121, this.f4538m);
        return this.f4538m.array();
    }

    @Override // com.baidu.cloud.mediaprocess.basefilters.ImageFilter
    public void onInitialized() {
        super.onInitialized();
        this.f4533h = GLES20.glGetUniformLocation(getProgram(), "inputImageWidth");
        GlUtil.checkLocation(this.f4533h, "inputImageWidth");
        this.f4534i = GLES20.glGetUniformLocation(getProgram(), "onlyYColor");
        GlUtil.checkLocation(this.f4534i, "onlyYColor");
        this.f4535j = GLES20.glGetUniformLocation(getProgram(), "isMirrored");
        GlUtil.checkLocation(this.f4535j, "isMirrored");
    }

    @Override // com.baidu.cloud.mediaprocess.basefilters.ImageFilter
    public void onOutputSizeChanged(int i, int i2) {
        if (this.mOutputWidth == i && this.mOutputHeight == i2) {
            return;
        }
        this.mOutputWidth = i;
        this.mOutputHeight = i2;
        m19972b();
    }

    @Override // com.baidu.cloud.mediaprocess.basefilters.ImageFilter
    public void onReleased() {
        m19972b();
        super.onReleased();
    }
}
