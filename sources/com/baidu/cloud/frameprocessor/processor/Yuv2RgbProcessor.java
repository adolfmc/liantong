package com.baidu.cloud.frameprocessor.processor;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.baidu.cloud.frameprocessor.gles.FullFrameRect;
import com.baidu.cloud.frameprocessor.gles.GlUtil;
import com.baidu.cloud.frameprocessor.gles.OpenGlUtils;
import com.baidu.cloud.frameprocessor.gles.RGB2YUVProgram;
import com.baidu.cloud.frameprocessor.gles.Texture2dProgram;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Yuv2RgbProcessor extends BaseFrameProcessor {
    private FullFrameRect mYUV2RGBRect;
    private ByteBuffer uvBuffer;
    private ByteBuffer yBuffer;
    private int mGLYTextureId = -1;
    private int mGLUVTextureId = -1;

    @Override // com.baidu.cloud.frameprocessor.processor.BaseFrameProcessor, com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public void init(FullFrameRect fullFrameRect, FullFrameRect fullFrameRect2) {
        super.init(fullFrameRect, fullFrameRect2);
        this.mYUV2RGBRect = new FullFrameRect(new RGB2YUVProgram(Texture2dProgram.ProgramType.NV212RGB));
    }

    @Override // com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public VideoFrameBuffer onFrame(VideoFrameBuffer videoFrameBuffer) {
        if (!this.mEnable || videoFrameBuffer == null || videoFrameBuffer.data == null) {
            return videoFrameBuffer;
        }
        int i = videoFrameBuffer.width;
        int i2 = videoFrameBuffer.height;
        if (this.yBuffer == null) {
            this.yBuffer = ByteBuffer.allocate(i * i2);
        }
        if (this.uvBuffer == null) {
            this.uvBuffer = ByteBuffer.allocate((i * i2) / 2);
        }
        this.yBuffer.clear();
        int i3 = i * i2;
        this.yBuffer.put(videoFrameBuffer.data, 0, i3);
        this.yBuffer.position(0);
        this.uvBuffer.clear();
        this.uvBuffer.put(videoFrameBuffer.data, i3, i3 / 2);
        this.uvBuffer.position(0);
        if (this.mGLYTextureId == -1) {
            this.mGLYTextureId = OpenGlUtils.createTexture2DObject();
        }
        GLES20.glBindTexture(3553, this.mGLYTextureId);
        GLES20.glTexImage2D(3553, 0, 6409, i, i2, 0, 6409, 5121, this.yBuffer);
        if (this.mGLUVTextureId == -1) {
            this.mGLUVTextureId = OpenGlUtils.createTexture2DObject();
        }
        GLES20.glBindTexture(3553, this.mGLUVTextureId);
        GLES20.glTexImage2D(3553, 0, 6410, i / 2, i2 / 2, 0, 6410, 5121, this.uvBuffer);
        int width = getWidth(videoFrameBuffer);
        int height = getHeight(videoFrameBuffer);
        initDefaultFbo(width, height);
        bindDefaultFbo();
        GLES20.glViewport(0, 0, width, height);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        this.mYUV2RGBRect.getProgram().applyProgramFloatParam("nv21Weight", 0.0f);
        this.mYUV2RGBRect.getProgram().applyProgramTextureParam("y_texture", this.mGLYTextureId);
        this.mYUV2RGBRect.getProgram().applyProgramTextureParam("uv_texture", this.mGLUVTextureId);
        float[] fArr = new float[16];
        Matrix.setIdentityM(fArr, 0);
        if (videoFrameBuffer.rotation == 270 || videoFrameBuffer.rotation == 90) {
            Matrix.scaleM(fArr, 0, 1.0f, -1.0f, 1.0f);
        }
        if (videoFrameBuffer.rotation != 0) {
            Matrix.rotateM(fArr, 0, videoFrameBuffer.rotation % 360, 0.0f, 0.0f, 1.0f);
        }
        this.mYUV2RGBRect.drawFrame(fArr, 0, GlUtil.IDENTITY_MATRIX);
        unbindFbo();
        return getOutputData(videoFrameBuffer);
    }

    @Override // com.baidu.cloud.frameprocessor.processor.BaseFrameProcessor, com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public void release() {
        super.release();
        FullFrameRect fullFrameRect = this.mYUV2RGBRect;
        if (fullFrameRect != null) {
            fullFrameRect.release(true);
            this.mYUV2RGBRect = null;
        }
        int i = this.mGLYTextureId;
        if (i != -1) {
            GlUtil.destroyTextureObject(i);
            this.mGLYTextureId = -1;
        }
        int i2 = this.mGLUVTextureId;
        if (i2 != -1) {
            GlUtil.destroyTextureObject(i2);
            this.mGLUVTextureId = -1;
        }
    }
}
