package com.baidu.cloud.rtcbridge.frameprocessor;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.baidu.cloud.frameprocessor.FrameProcessorChain;
import com.baidu.cloud.frameprocessor.p133ar.ARProcessor;
import com.baidu.cloud.frameprocessor.processor.ForegroundParam;
import com.baidu.cloud.frameprocessor.processor.ForegroundProcessor;
import com.baidu.cloud.frameprocessor.processor.IFrameProcessor;
import com.baidu.cloud.framework.frame.TextureBuffer;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import com.baidu.minivideo.arface.DuArResConfig;
import com.webrtc.GlRectDrawer;
import com.webrtc.GlTextureFrameBuffer;
import com.webrtc.NV21Buffer;
import com.webrtc.RendererCommon;
import com.webrtc.TextureBufferImpl;
import com.webrtc.VideoFrame;
import com.webrtc.VideoFrameDrawer;
import com.webrtc.VideoProcessor;
import com.webrtc.VideoSink;
import com.webrtc.YuvConverter;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CameraVideoProcessorImpl implements CameraVideoProcessor {
    private WeakReference<Context> context;
    private RendererCommon.GlDrawer drawer;
    private GlTextureFrameBuffer frameBuffer;
    private VideoFrameDrawer frameDrawer;
    private Handler handler;
    protected FrameProcessorChain mProcessorChain;
    private VideoSink sink;
    private GenerateWatermarkUtil watermarkUtil;
    private YuvConverter yuvConverter;
    private BRTCFrameProcessorParams settings = new BRTCFrameProcessorParams();
    private boolean changedBeauty = true;
    private boolean changedHumanSeg = true;

    @Override // com.webrtc.VideoProcessor
    public /* synthetic */ void onFrameCaptured(VideoFrame videoFrame, VideoProcessor.FrameAdaptationParameters frameAdaptationParameters) {
        VideoProcessor.CC.$default$onFrameCaptured(this, videoFrame, frameAdaptationParameters);
    }

    @Override // com.webrtc.VideoProcessor
    public void setSink(@Nullable VideoSink videoSink) {
        this.sink = videoSink;
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.CameraVideoProcessor
    public void initialize(Context context, BRTCFrameProcessorParams bRTCFrameProcessorParams) {
        this.context = new WeakReference<>(context.getApplicationContext());
        this.settings = bRTCFrameProcessorParams;
        List<IFrameProcessor> buildProcessors = RtcFrameProcessorImpl.buildProcessors(context);
        this.mProcessorChain = new FrameProcessorChain();
        this.mProcessorChain.setProcessors(buildProcessors);
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.CameraVideoProcessor
    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        FrameProcessorChain frameProcessorChain = this.mProcessorChain;
        if (frameProcessorChain == null) {
            return;
        }
        frameProcessorChain.setSurfaceTexture(surfaceTexture);
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcBeautyManager
    public void setBeautyEnable(final boolean z) {
        FrameProcessorChain frameProcessorChain = this.mProcessorChain;
        if (frameProcessorChain != null) {
            frameProcessorChain.runOnDraw(new Runnable() { // from class: com.baidu.cloud.rtcbridge.frameprocessor.CameraVideoProcessorImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    CameraVideoProcessorImpl.this.settings.enableBeauty = z;
                    CameraVideoProcessorImpl.this.changedBeauty = true;
                }
            });
            return;
        }
        this.settings.enableBeauty = z;
        this.changedBeauty = true;
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcBeautyManager
    public void setBeautyValue(final RtcBeautyType rtcBeautyType, final float f) {
        FrameProcessorChain frameProcessorChain = this.mProcessorChain;
        if (frameProcessorChain != null) {
            frameProcessorChain.runOnDraw(new Runnable() { // from class: com.baidu.cloud.rtcbridge.frameprocessor.CameraVideoProcessorImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    CameraVideoProcessorImpl.this.settings.beautyValues.put(rtcBeautyType, Float.valueOf(f));
                    CameraVideoProcessorImpl.this.changedBeauty = true;
                }
            });
            return;
        }
        this.settings.beautyValues.put(rtcBeautyType, Float.valueOf(f));
        this.changedBeauty = true;
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcBeautyManager
    public void setBeautyValue(final RtcBeautyType rtcBeautyType, final String str) {
        FrameProcessorChain frameProcessorChain = this.mProcessorChain;
        if (frameProcessorChain != null) {
            frameProcessorChain.runOnDraw(new Runnable() { // from class: com.baidu.cloud.rtcbridge.frameprocessor.CameraVideoProcessorImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    CameraVideoProcessorImpl.this.settings.beautyValues.put(rtcBeautyType, str);
                }
            });
        } else {
            this.settings.beautyValues.put(rtcBeautyType, str);
        }
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcHumanSegManager
    public void enableHumanSeg(final boolean z, final BRTCEffectParams bRTCEffectParams) {
        FrameProcessorChain frameProcessorChain = this.mProcessorChain;
        if (frameProcessorChain != null) {
            frameProcessorChain.runOnDraw(new Runnable() { // from class: com.baidu.cloud.rtcbridge.frameprocessor.CameraVideoProcessorImpl.4
                @Override // java.lang.Runnable
                public void run() {
                    CameraVideoProcessorImpl.this.settings.enableHumanSeg = z;
                    CameraVideoProcessorImpl.this.settings.humanSegParams = bRTCEffectParams;
                    CameraVideoProcessorImpl.this.changedHumanSeg = true;
                }
            });
            return;
        }
        BRTCFrameProcessorParams bRTCFrameProcessorParams = this.settings;
        bRTCFrameProcessorParams.enableHumanSeg = z;
        bRTCFrameProcessorParams.humanSegParams = bRTCEffectParams;
        this.changedHumanSeg = true;
    }

    @Override // com.webrtc.CapturerObserver
    public void onCapturerStarted(boolean z) {
        FrameProcessorChain frameProcessorChain = this.mProcessorChain;
        if (frameProcessorChain != null && z) {
            frameProcessorChain.init();
        }
    }

    @Override // com.webrtc.CapturerObserver
    public void onCapturerStopped() {
        GenerateWatermarkUtil generateWatermarkUtil;
        FrameProcessorChain frameProcessorChain = this.mProcessorChain;
        if (frameProcessorChain != null) {
            frameProcessorChain.release();
        }
        YuvConverter yuvConverter = this.yuvConverter;
        if (yuvConverter != null) {
            yuvConverter.release();
            this.yuvConverter = null;
        }
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
        RendererCommon.GlDrawer glDrawer = this.drawer;
        if (glDrawer != null) {
            glDrawer.release();
            this.drawer = null;
        }
        VideoFrameDrawer videoFrameDrawer = this.frameDrawer;
        if (videoFrameDrawer != null) {
            videoFrameDrawer.release();
            this.frameDrawer = null;
        }
        GlTextureFrameBuffer glTextureFrameBuffer = this.frameBuffer;
        if (glTextureFrameBuffer != null) {
            glTextureFrameBuffer.release();
            this.frameBuffer = null;
        }
        BRTCFrameProcessorParams bRTCFrameProcessorParams = this.settings;
        if (bRTCFrameProcessorParams == null || (generateWatermarkUtil = this.watermarkUtil) == null) {
            return;
        }
        generateWatermarkUtil.release(bRTCFrameProcessorParams.watermarkParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onFramePreProcess() {
        BRTCFrameProcessorParams bRTCFrameProcessorParams = this.settings;
        if (bRTCFrameProcessorParams != null) {
            List<ForegroundParam> list = null;
            if (bRTCFrameProcessorParams.enableWatermark) {
                if (this.watermarkUtil == null) {
                    this.watermarkUtil = new GenerateWatermarkUtil();
                }
                list = this.watermarkUtil.generateWatermark(this.context.get(), this.settings.watermarkParams);
            }
            if (this.mProcessorChain.getProcessors() == null) {
                return;
            }
            for (IFrameProcessor iFrameProcessor : this.mProcessorChain.getProcessors()) {
                if (iFrameProcessor instanceof ForegroundProcessor) {
                    iFrameProcessor.setProcessorEnable(this.settings.enableWatermark);
                    if (this.settings.enableWatermark && list != null) {
                        ((ForegroundProcessor) iFrameProcessor).setWatermarkParams(list);
                    }
                } else if (iFrameProcessor instanceof ARProcessor) {
                    ARProcessor aRProcessor = (ARProcessor) iFrameProcessor;
                    aRProcessor.setProcessorEnable(this.settings.enableBeauty || this.settings.enableHumanSeg);
                    aRProcessor.setEnableDefaultBeauty(this.settings.enableBeauty);
                    if (this.changedBeauty) {
                        if (this.settings.enableBeauty) {
                            for (Map.Entry<RtcBeautyType, Object> entry : this.settings.beautyValues.entrySet()) {
                                if (entry.getValue() instanceof Float) {
                                    aRProcessor.setBeautyValue(BeautyTypeConvertor.convert2BeautyType(entry.getKey()), ((Float) entry.getValue()).floatValue());
                                } else if (entry.getValue() instanceof String) {
                                    aRProcessor.setBeautyValue(BeautyTypeConvertor.convert2BeautyType(entry.getKey()), (String) entry.getValue());
                                }
                            }
                        }
                        this.changedBeauty = false;
                    }
                    if (this.changedHumanSeg) {
                        if (!this.settings.enableHumanSeg) {
                            aRProcessor.clearCase();
                        } else {
                            aRProcessor.clearCase();
                            aRProcessor.loadCase(this.settings.humanSegParams.resourcePath, this.settings.humanSegParams.resourceId, DuArResConfig.getdlModelsPath() != null ? DuArResConfig.getdlModelsPath() + "humanSeg" : "");
                        }
                        this.changedHumanSeg = false;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean checkProcess() {
        FrameProcessorChain frameProcessorChain;
        if (this.settings == null || (frameProcessorChain = this.mProcessorChain) == null) {
            return false;
        }
        frameProcessorChain.runPendingTasks();
        return this.settings.enableBeauty || this.settings.enableWatermark || this.settings.enableHumanSeg;
    }

    @Override // com.webrtc.CapturerObserver
    public void onFrameCaptured(VideoFrame videoFrame) {
        Handler handler;
        YuvConverter yuvConverter;
        VideoFrame.Buffer textureBufferImpl;
        if (!checkProcess()) {
            this.sink.onFrame(videoFrame);
            return;
        }
        VideoFrame.Buffer buffer = videoFrame.getBuffer();
        VideoFrameBuffer videoFrameBuffer = new VideoFrameBuffer();
        videoFrameBuffer.width = buffer.getWidth();
        videoFrameBuffer.height = buffer.getHeight();
        videoFrameBuffer.rotation = videoFrame.getRotation();
        videoFrameBuffer.timestampNs = videoFrame.getTimestampNs();
        boolean z = buffer instanceof TextureBufferImpl;
        if (z) {
            videoFrameBuffer.pixelFormat = VideoFrameBuffer.PIXEL_FORMAT.TEXTURE;
            VideoFrame.TextureBuffer textureBuffer = (VideoFrame.TextureBuffer) buffer;
            videoFrameBuffer.textureBuffer = new TextureBuffer(textureBuffer.getTextureId(), textureBuffer.getType() == VideoFrame.TextureBuffer.Type.OES ? TextureBuffer.Type.OES : TextureBuffer.Type.RGB);
            videoFrameBuffer.transformMatrix = RendererCommon.convertMatrixFromAndroidGraphicsMatrix(textureBuffer.getTransformMatrix());
        } else {
            drawYuv(new VideoFrame(buffer, 0, videoFrame.getTimestampNs()));
            videoFrameBuffer.pixelFormat = VideoFrameBuffer.PIXEL_FORMAT.TEXTURE;
            videoFrameBuffer.textureBuffer = new TextureBuffer(this.frameBuffer.getTextureId(), TextureBuffer.Type.RGB);
            float[] fArr = new float[16];
            Matrix.setIdentityM(fArr, 0);
            videoFrameBuffer.transformMatrix = fArr;
        }
        onFramePreProcess();
        VideoFrameBuffer onFrame = this.mProcessorChain.onFrame(videoFrameBuffer);
        if (onFrame.data != null) {
            textureBufferImpl = new NV21Buffer(onFrame.data, onFrame.width, onFrame.height, null);
        } else if (onFrame.textureBuffer == null) {
            this.sink.onFrame(videoFrame);
            return;
        } else {
            if (z) {
                TextureBufferImpl textureBufferImpl2 = (TextureBufferImpl) buffer;
                handler = textureBufferImpl2.getToI420Handler();
                yuvConverter = textureBufferImpl2.getYuvConverter();
            } else {
                if (this.yuvConverter == null) {
                    this.yuvConverter = new YuvConverter();
                }
                YuvConverter yuvConverter2 = this.yuvConverter;
                if (this.handler == null) {
                    this.handler = new Handler();
                }
                handler = this.handler;
                yuvConverter = yuvConverter2;
            }
            textureBufferImpl = new TextureBufferImpl(onFrame.width, onFrame.height, onFrame.textureBuffer.type == TextureBuffer.Type.OES ? VideoFrame.TextureBuffer.Type.OES : VideoFrame.TextureBuffer.Type.RGB, onFrame.textureBuffer.textureId, RendererCommon.convertMatrixToAndroidGraphicsMatrix(onFrame.transformMatrix), handler, yuvConverter, (Runnable) null);
        }
        this.sink.onFrame(new VideoFrame(textureBufferImpl, onFrame.rotation, onFrame.timestampNs));
    }

    private void drawYuv(VideoFrame videoFrame) {
        if (this.frameDrawer == null) {
            this.frameDrawer = new VideoFrameDrawer();
        }
        if (this.drawer == null) {
            this.drawer = new GlRectDrawer();
        }
        int width = videoFrame.getBuffer().getWidth();
        int height = videoFrame.getBuffer().getHeight();
        if (this.frameBuffer == null) {
            this.frameBuffer = new GlTextureFrameBuffer(6408);
        }
        this.frameBuffer.setSize(width, height);
        GLES20.glBindFramebuffer(36160, this.frameBuffer.getFrameBufferId());
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.frameBuffer.getTextureId(), 0);
        this.frameDrawer.drawFrame(videoFrame, this.drawer, null, 0, 0, width, height);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcWatermarkManager
    public void enableWatermark(final boolean z, final BRTCWatermarkParams bRTCWatermarkParams) {
        FrameProcessorChain frameProcessorChain = this.mProcessorChain;
        if (frameProcessorChain != null) {
            frameProcessorChain.runOnDraw(new Runnable() { // from class: com.baidu.cloud.rtcbridge.frameprocessor.CameraVideoProcessorImpl.5
                @Override // java.lang.Runnable
                public void run() {
                    CameraVideoProcessorImpl.this.settings.enableWatermark = z;
                    CameraVideoProcessorImpl.this.settings.watermarkParams = bRTCWatermarkParams;
                }
            });
            return;
        }
        BRTCFrameProcessorParams bRTCFrameProcessorParams = this.settings;
        bRTCFrameProcessorParams.enableWatermark = z;
        bRTCFrameProcessorParams.watermarkParams = bRTCWatermarkParams;
    }
}
