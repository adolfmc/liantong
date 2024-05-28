package com.baidu.cloud.frameprocessor.gles;

import android.opengl.GLES20;
import android.util.Log;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Texture2dProgram {
    private static final String FRAGMENT_SHADER_2D = "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nuniform float alpha;\nvoid main() {\n    vec4 color = texture2D(sTexture, vTextureCoord);\n    gl_FragColor = vec4(color.rgb, color.a) * alpha;\n    //gl_FragColor = color * alpha;\n}\n";
    private static final String FRAGMENT_SHADER_EXT = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float alpha;\nvoid main() {\n    vec4 color = texture2D(sTexture, vTextureCoord);\n    gl_FragColor = vec4(color.rgb, color.a) * alpha;\n}\n";
    private static final String FRAGMENT_SHADER_EXT_BW = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    vec4 tc = texture2D(sTexture, vTextureCoord);\n    float color = tc.r * 0.3 + tc.g * 0.59 + tc.b * 0.11;\n    gl_FragColor = vec4(color, color, color, 1.0);\n}\n";
    private static final String FRAGMENT_SHADER_EXT_FILT = "#extension GL_OES_EGL_image_external : require\n#define KERNEL_SIZE 9\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float uKernel[KERNEL_SIZE];\nuniform vec2 uTexOffset[KERNEL_SIZE];\nuniform float uColorAdjust;\nvoid main() {\n    int i = 0;\n    vec4 sum = vec4(0.0);\n    if (vTextureCoord.x < vTextureCoord.y - 0.005) {\n        for (i = 0; i < KERNEL_SIZE; i++) {\n            vec4 texc = texture2D(sTexture, vTextureCoord + uTexOffset[i]);\n            sum += texc * uKernel[i];\n        }\n    sum += uColorAdjust;\n    } else if (vTextureCoord.x > vTextureCoord.y + 0.005) {\n        sum = texture2D(sTexture, vTextureCoord);\n    } else {\n        sum.r = 1.0;\n    }\n    gl_FragColor = sum;\n}\n";
    public static final int KERNEL_SIZE = 9;
    public static final String KEY_ALPHA = "alpha";
    private static final String TAG = "Grafika";
    protected static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n";
    private float mColorAdjust;
    protected int mProgramHandle;
    protected ProgramType mProgramType;
    private float[] mTexOffset;
    protected int mTextureTarget;
    private int maPositionLoc;
    private int maTextureCoordLoc;
    private int muColorAdjustLoc;
    private int muKernelLoc;
    private int muMVPMatrixLoc;
    private int muTexMatrixLoc;
    private int muTexOffsetLoc;
    private float[] mKernel = new float[9];
    Map<String, Integer> mLocationParams = new HashMap();
    Map<String, Integer> mTextureParams = new HashMap();
    Map<String, Integer> mIntParams = new HashMap();
    Map<String, Float> mFloatParams = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum ProgramType {
        TEXTURE_2D,
        TEXTURE_EXT,
        TEXTURE_EXT_BW,
        TEXTURE_EXT_FILT,
        RGB2NV21,
        NV212RGB,
        TEXTURE_2D_AR_ALPHA
    }

    public Texture2dProgram(ProgramType programType) {
        this.mProgramType = programType;
        switch (programType) {
            case TEXTURE_2D:
                this.mTextureTarget = 3553;
                this.mProgramHandle = GlUtil.createProgram("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nuniform float alpha;\nvoid main() {\n    vec4 color = texture2D(sTexture, vTextureCoord);\n    gl_FragColor = vec4(color.rgb, color.a) * alpha;\n    //gl_FragColor = color * alpha;\n}\n");
                break;
            case TEXTURE_EXT:
                this.mTextureTarget = 36197;
                this.mProgramHandle = GlUtil.createProgram("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float alpha;\nvoid main() {\n    vec4 color = texture2D(sTexture, vTextureCoord);\n    gl_FragColor = vec4(color.rgb, color.a) * alpha;\n}\n");
                break;
            case TEXTURE_EXT_BW:
                this.mTextureTarget = 36197;
                this.mProgramHandle = GlUtil.createProgram("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    vec4 tc = texture2D(sTexture, vTextureCoord);\n    float color = tc.r * 0.3 + tc.g * 0.59 + tc.b * 0.11;\n    gl_FragColor = vec4(color, color, color, 1.0);\n}\n");
                break;
            case TEXTURE_EXT_FILT:
                this.mTextureTarget = 36197;
                this.mProgramHandle = GlUtil.createProgram("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\n#define KERNEL_SIZE 9\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float uKernel[KERNEL_SIZE];\nuniform vec2 uTexOffset[KERNEL_SIZE];\nuniform float uColorAdjust;\nvoid main() {\n    int i = 0;\n    vec4 sum = vec4(0.0);\n    if (vTextureCoord.x < vTextureCoord.y - 0.005) {\n        for (i = 0; i < KERNEL_SIZE; i++) {\n            vec4 texc = texture2D(sTexture, vTextureCoord + uTexOffset[i]);\n            sum += texc * uKernel[i];\n        }\n    sum += uColorAdjust;\n    } else if (vTextureCoord.x > vTextureCoord.y + 0.005) {\n        sum = texture2D(sTexture, vTextureCoord);\n    } else {\n        sum.r = 1.0;\n    }\n    gl_FragColor = sum;\n}\n");
                break;
        }
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init() {
        if (this.mProgramHandle == 0) {
            return;
        }
        Log.d("Grafika", "Created program " + this.mProgramHandle + " (" + this.mProgramType + ")");
        this.maPositionLoc = GLES20.glGetAttribLocation(this.mProgramHandle, "aPosition");
        GlUtil.checkLocation(this.maPositionLoc, "aPosition");
        this.maTextureCoordLoc = GLES20.glGetAttribLocation(this.mProgramHandle, "aTextureCoord");
        GlUtil.checkLocation(this.maTextureCoordLoc, "aTextureCoord");
        this.muMVPMatrixLoc = GLES20.glGetUniformLocation(this.mProgramHandle, "uMVPMatrix");
        GlUtil.checkLocation(this.muMVPMatrixLoc, "uMVPMatrix");
        this.muTexMatrixLoc = GLES20.glGetUniformLocation(this.mProgramHandle, "uTexMatrix");
        GlUtil.checkLocation(this.muTexMatrixLoc, "uTexMatrix");
        this.muKernelLoc = GLES20.glGetUniformLocation(this.mProgramHandle, "uKernel");
        if (this.muKernelLoc < 0) {
            this.muKernelLoc = -1;
            this.muTexOffsetLoc = -1;
            this.muColorAdjustLoc = -1;
            return;
        }
        this.muTexOffsetLoc = GLES20.glGetUniformLocation(this.mProgramHandle, "uTexOffset");
        GlUtil.checkLocation(this.muTexOffsetLoc, "uTexOffset");
        this.muColorAdjustLoc = GLES20.glGetUniformLocation(this.mProgramHandle, "uColorAdjust");
        GlUtil.checkLocation(this.muColorAdjustLoc, "uColorAdjust");
        setKernel(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f}, 0.0f);
        setTexSize(256, 256);
    }

    public void release() {
        Log.d("Grafika", "deleting program " + this.mProgramHandle);
        GLES20.glDeleteProgram(this.mProgramHandle);
        this.mProgramHandle = -1;
    }

    public ProgramType getProgramType() {
        return this.mProgramType;
    }

    public void setKernel(float[] fArr, float f) {
        if (fArr.length != 9) {
            throw new IllegalArgumentException("Kernel size is " + fArr.length + " vs. 9");
        }
        System.arraycopy(fArr, 0, this.mKernel, 0, 9);
        this.mColorAdjust = f;
    }

    public void setTexSize(int i, int i2) {
        float f = 1.0f / i;
        float f2 = 1.0f / i2;
        float f3 = -f;
        float f4 = -f2;
        this.mTexOffset = new float[]{f3, f4, 0.0f, f4, f, f4, f3, 0.0f, 0.0f, 0.0f, f, 0.0f, f3, f2, 0.0f, f2, f, f2};
    }

    public void draw(float[] fArr, FloatBuffer floatBuffer, int i, int i2, int i3, int i4, float[] fArr2, FloatBuffer floatBuffer2, int i5, int i6) {
        GlUtil.checkGlError("draw start");
        GLES20.glUseProgram(this.mProgramHandle);
        GlUtil.checkGlError("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(this.mTextureTarget, i5);
        GLES20.glUniformMatrix4fv(this.muMVPMatrixLoc, 1, false, fArr, 0);
        GlUtil.checkGlError("glUniformMatrix4fv");
        GLES20.glUniformMatrix4fv(this.muTexMatrixLoc, 1, false, fArr2, 0);
        GlUtil.checkGlError("glUniformMatrix4fv");
        GLES20.glEnableVertexAttribArray(this.maPositionLoc);
        GlUtil.checkGlError("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.maPositionLoc, i3, 5126, false, i4, (Buffer) floatBuffer);
        GlUtil.checkGlError("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(this.maTextureCoordLoc);
        GlUtil.checkGlError("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.maTextureCoordLoc, 2, 5126, false, i6, (Buffer) floatBuffer2);
        GlUtil.checkGlError("glVertexAttribPointer");
        int i7 = this.muKernelLoc;
        if (i7 >= 0) {
            GLES20.glUniform1fv(i7, 9, this.mKernel, 0);
            GLES20.glUniform2fv(this.muTexOffsetLoc, 9, this.mTexOffset, 0);
            GLES20.glUniform1f(this.muColorAdjustLoc, this.mColorAdjust);
        }
        onDrawArraysBefore();
        GLES20.glDrawArrays(5, i, i2);
        GlUtil.checkGlError("glDrawArrays");
        GLES20.glDisableVertexAttribArray(this.maPositionLoc);
        GLES20.glDisableVertexAttribArray(this.maTextureCoordLoc);
        GLES20.glBindTexture(this.mTextureTarget, 0);
        GLES20.glUseProgram(0);
    }

    protected void onDrawArraysBefore() {
        if (this.mFloatParams.get("alpha") == null) {
            this.mFloatParams.put("alpha", Float.valueOf(1.0f));
        }
        int i = 1;
        for (Map.Entry<String, Integer> entry : this.mTextureParams.entrySet()) {
            String key = entry.getKey();
            Integer num = this.mLocationParams.get(key);
            if (num == null || num.intValue() == 0) {
                num = Integer.valueOf(GLES20.glGetUniformLocation(this.mProgramHandle, key));
                this.mLocationParams.put(key, num);
            }
            GLES20.glActiveTexture(33984 + i);
            GLES20.glBindTexture(3553, entry.getValue().intValue());
            GLES20.glUniform1i(num.intValue(), i);
            i++;
        }
        for (Map.Entry<String, Integer> entry2 : this.mIntParams.entrySet()) {
            String key2 = entry2.getKey();
            Integer num2 = this.mLocationParams.get(key2);
            if (num2 == null || num2.intValue() == 0) {
                num2 = Integer.valueOf(GLES20.glGetUniformLocation(this.mProgramHandle, key2));
                this.mLocationParams.put(key2, num2);
            }
            GLES20.glUniform1i(num2.intValue(), entry2.getValue().intValue());
        }
        for (Map.Entry<String, Float> entry3 : this.mFloatParams.entrySet()) {
            String key3 = entry3.getKey();
            Integer num3 = this.mLocationParams.get(key3);
            if (num3 == null || num3.intValue() == 0) {
                num3 = Integer.valueOf(GLES20.glGetUniformLocation(this.mProgramHandle, key3));
                this.mLocationParams.put(key3, num3);
            }
            GLES20.glUniform1f(num3.intValue(), entry3.getValue().floatValue());
        }
    }

    public void applyProgramTextureParam(String str, int i) {
        this.mTextureParams.put(str, Integer.valueOf(i));
    }

    public void applyProgramIntParam(String str, int i) {
        this.mIntParams.put(str, Integer.valueOf(i));
    }

    public void applyProgramFloatParam(String str, float f) {
        this.mFloatParams.put(str, Float.valueOf(f));
    }
}
