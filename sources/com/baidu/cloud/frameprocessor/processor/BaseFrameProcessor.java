package com.baidu.cloud.frameprocessor.processor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.baidu.cloud.frameprocessor.gles.FullFrameRect;
import com.baidu.cloud.frameprocessor.gles.GlUtil;
import com.baidu.cloud.frameprocessor.gles.OpenGlUtils;
import com.baidu.cloud.framework.frame.TextureBuffer;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class BaseFrameProcessor implements IFrameProcessor {
    protected FullFrameRect mFullScreen2D;
    protected FullFrameRect mFullScreenEXT;
    protected VideoFrameBuffer mOutputData;
    private TextureBuffer mOutputTextureBuffer;
    protected SurfaceTexture mSurfaceTexture;
    protected boolean mSwitchCameraFlag;
    protected boolean mPreProcessRotation = true;
    protected int mOES22DFrameBuffer = -1;
    protected int mOES22DFboTexId = -1;
    protected int mFrameBuffer = -1;
    protected int mFboTexId = -1;
    protected boolean mEnable = true;

    @Override // com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public void init(FullFrameRect fullFrameRect, FullFrameRect fullFrameRect2) {
        this.mFullScreenEXT = fullFrameRect;
        this.mFullScreen2D = fullFrameRect2;
    }

    @Override // com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public void release() {
        releaseDefaultFbo();
    }

    @Override // com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public void setSwitchCameraFlag(boolean z) {
        this.mSwitchCameraFlag = z;
    }

    @Override // com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public void setProcessorEnable(boolean z) {
        this.mEnable = z;
    }

    protected void releaseDefaultFbo() {
        int i = this.mFrameBuffer;
        if (i != -1) {
            GlUtil.destroyFramebufferObject(i);
            this.mFrameBuffer = -1;
        }
        int i2 = this.mFboTexId;
        if (i2 != -1) {
            GlUtil.destroyTextureObject(i2);
            this.mFboTexId = -1;
        }
        int i3 = this.mOES22DFrameBuffer;
        if (i3 != -1) {
            GlUtil.destroyFramebufferObject(i3);
            this.mOES22DFrameBuffer = -1;
        }
        int i4 = this.mOES22DFboTexId;
        if (i4 != -1) {
            GlUtil.destroyTextureObject(i4);
            this.mOES22DFboTexId = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initDefaultFbo(int i, int i2) {
        if (this.mFrameBuffer == -1) {
            this.mFrameBuffer = GlUtil.createFrameBufferObject();
        }
        if (this.mFboTexId == -1) {
            this.mFboTexId = OpenGlUtils.createTexture2DObject();
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
            GLES20.glBindTexture(3553, 0);
        }
    }

    protected void initOES22DFbo(int i, int i2) {
        if (this.mOES22DFrameBuffer == -1) {
            this.mOES22DFrameBuffer = GlUtil.createFrameBufferObject();
        }
        if (this.mOES22DFboTexId == -1) {
            this.mOES22DFboTexId = OpenGlUtils.createTexture2DObject();
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
            GLES20.glBindTexture(3553, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void bindDefaultFbo() {
        bindFbo(this.mFrameBuffer, this.mFboTexId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void bindFbo(int i, int i2) {
        GLES20.glBindFramebuffer(36160, i);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, i2, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void unbindFbo() {
        GLES20.glBindFramebuffer(36160, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void glClearColor(float f, float f2, float f3, float f4) {
        GLES20.glClearColor(f, f2, f3, f4);
        GLES20.glClear(16640);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void drawInputFrame(int i, int i2, VideoFrameBuffer videoFrameBuffer) {
        float[] fArr;
        GLES20.glViewport(0, 0, i, i2);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        if (this.mPreProcessRotation) {
            fArr = calculateMatrix(videoFrameBuffer);
        } else {
            fArr = new float[16];
            Matrix.setIdentityM(fArr, 0);
        }
        (videoFrameBuffer.textureBuffer.type == TextureBuffer.Type.RGB ? this.mFullScreen2D : this.mFullScreenEXT).drawFrame(fArr, videoFrameBuffer.textureBuffer.textureId, videoFrameBuffer.transformMatrix);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public VideoFrameBuffer getOutputData(VideoFrameBuffer videoFrameBuffer) {
        return getOutputData(videoFrameBuffer, this.mFboTexId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public VideoFrameBuffer getOutputData(VideoFrameBuffer videoFrameBuffer, int i) {
        if (this.mOutputData == null) {
            this.mOutputData = new VideoFrameBuffer();
        }
        VideoFrameBuffer videoFrameBuffer2 = this.mOutputData;
        videoFrameBuffer2.data = null;
        videoFrameBuffer2.width = this.mPreProcessRotation ? getRotatedWidth(videoFrameBuffer) : videoFrameBuffer.width;
        this.mOutputData.height = this.mPreProcessRotation ? getRotatedHeight(videoFrameBuffer) : videoFrameBuffer.height;
        this.mOutputData.rotation = this.mPreProcessRotation ? 0 : videoFrameBuffer.rotation;
        this.mOutputData.timestampNs = videoFrameBuffer.timestampNs;
        TextureBuffer textureBuffer = this.mOutputTextureBuffer;
        if (textureBuffer == null) {
            this.mOutputTextureBuffer = new TextureBuffer(i, TextureBuffer.Type.RGB);
        } else {
            textureBuffer.textureId = i;
            textureBuffer.type = TextureBuffer.Type.RGB;
        }
        VideoFrameBuffer videoFrameBuffer3 = this.mOutputData;
        videoFrameBuffer3.textureBuffer = this.mOutputTextureBuffer;
        videoFrameBuffer3.transformMatrix = OpenGlUtils.IDENTITY_MATRIX;
        this.mOutputData.pixelFormat = VideoFrameBuffer.PIXEL_FORMAT.TEXTURE;
        return this.mOutputData;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getWidth(VideoFrameBuffer videoFrameBuffer) {
        return this.mPreProcessRotation ? getRotatedWidth(videoFrameBuffer) : videoFrameBuffer.width;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getHeight(VideoFrameBuffer videoFrameBuffer) {
        return this.mPreProcessRotation ? getRotatedHeight(videoFrameBuffer) : videoFrameBuffer.height;
    }

    private int getRotatedWidth(VideoFrameBuffer videoFrameBuffer) {
        return videoFrameBuffer.rotation % 180 == 0 ? videoFrameBuffer.width : videoFrameBuffer.height;
    }

    private int getRotatedHeight(VideoFrameBuffer videoFrameBuffer) {
        return videoFrameBuffer.rotation % 180 == 0 ? videoFrameBuffer.height : videoFrameBuffer.width;
    }

    protected float[] calculateMatrix(VideoFrameBuffer videoFrameBuffer) {
        float[] fArr = new float[16];
        Matrix.setIdentityM(fArr, 0);
        if (videoFrameBuffer.rotation == 270 || videoFrameBuffer.rotation == 90) {
            Matrix.scaleM(fArr, 0, -1.0f, -1.0f, 1.0f);
        }
        if (videoFrameBuffer.rotation != 0) {
            Matrix.rotateM(fArr, 0, videoFrameBuffer.rotation % 360, 0.0f, 0.0f, 1.0f);
        }
        return fArr;
    }

    @Override // com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.mSurfaceTexture = surfaceTexture;
    }

    protected void testYUV(byte[] bArr, int i, int i2) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
        try {
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            decodeByteArray.recycle();
        }
    }

    protected void testRGB(int i, int i2, boolean z) {
        Bitmap createBitmap;
        int i3 = i * i2 * 4;
        IntBuffer allocate = IntBuffer.allocate(i3);
        GLES20.glReadPixels(0, 0, i, i2, 6408, 5121, allocate);
        if (z) {
            int[] iArr = new int[i3];
            int[] array = allocate.array();
            for (int i4 = 0; i4 < i2; i4++) {
                for (int i5 = 0; i5 < i; i5++) {
                    iArr[(((i2 - i4) - 1) * i) + i5] = array[(i4 * i) + i5];
                }
            }
            createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            createBitmap.copyPixelsFromBuffer(IntBuffer.wrap(iArr));
        } else {
            createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            createBitmap.copyPixelsFromBuffer(allocate);
        }
        createBitmap.recycle();
    }
}
