package com.baidu.cloud.rtcbridge.framecapture;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.baidu.cloud.framecapture.FrameCaptureManager;
import com.baidu.cloud.framecapture.camera.CameraVideoCapturer;
import com.baidu.cloud.framework.frame.TextureBuffer;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import com.baidu.rtc.camera.CameraCapturer;
import com.baidu.rtc.camera.CameraEventCallback;
import com.webrtc.CapturerObserver;
import com.webrtc.EglBase;
import com.webrtc.GlRectDrawer;
import com.webrtc.GlTextureFrameBuffer;
import com.webrtc.NV21Buffer;
import com.webrtc.RendererCommon;
import com.webrtc.SurfaceTextureHelper;
import com.webrtc.TextureBufferImpl;
import com.webrtc.VideoCapturer;
import com.webrtc.VideoFrame;
import com.webrtc.VideoFrameDrawer;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RtcFrameCaptureImpl implements IRtcFrameCapture {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private RendererCommon.GlDrawer drawer;
    private GlTextureFrameBuffer frameBuffer;
    private VideoFrameDrawer frameDrawer;
    private CameraVideoCapturer mCameraCapturer;
    private List<CapturerObserver> mObservers;

    @Override // com.baidu.cloud.rtcbridge.framecapture.IRtcFrameCapture
    public VideoCapturer createCameraCapturer(Context context, int i, final CameraEventCallback cameraEventCallback) {
        CameraVideoCapturer cameraVideoCapturer = this.mCameraCapturer;
        if (cameraVideoCapturer != null) {
            cameraVideoCapturer.dispose();
        }
        this.mCameraCapturer = new FrameCaptureManager().createCameraCapture(context, i, new CameraVideoCapturer.CameraEventsHandler() { // from class: com.baidu.cloud.rtcbridge.framecapture.RtcFrameCaptureImpl.1
            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraError(String str) {
                CameraEventCallback cameraEventCallback2 = cameraEventCallback;
                if (cameraEventCallback2 != null) {
                    cameraEventCallback2.onCameraError(str);
                }
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraDisconnected() {
                CameraEventCallback cameraEventCallback2 = cameraEventCallback;
                if (cameraEventCallback2 != null) {
                    cameraEventCallback2.onCameraDisconnected();
                }
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraFreezed(String str) {
                CameraEventCallback cameraEventCallback2 = cameraEventCallback;
                if (cameraEventCallback2 != null) {
                    cameraEventCallback2.onCameraFreezed(str);
                }
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraOpening(String str, boolean z) {
                CameraEventCallback cameraEventCallback2 = cameraEventCallback;
                if (cameraEventCallback2 != null) {
                    cameraEventCallback2.onCameraOpening(str, z);
                }
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onFirstFrameAvailable() {
                CameraEventCallback cameraEventCallback2 = cameraEventCallback;
                if (cameraEventCallback2 != null) {
                    cameraEventCallback2.onFirstFrameAvailable();
                }
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraClosed() {
                CameraEventCallback cameraEventCallback2 = cameraEventCallback;
                if (cameraEventCallback2 != null) {
                    cameraEventCallback2.onCameraClosed();
                }
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraSwitchDone(boolean z) {
                CameraEventCallback cameraEventCallback2 = cameraEventCallback;
                if (cameraEventCallback2 != null) {
                    cameraEventCallback2.onCameraSwitchDone(z);
                }
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraSwitchError(String str) {
                CameraEventCallback cameraEventCallback2 = cameraEventCallback;
                if (cameraEventCallback2 != null) {
                    cameraEventCallback2.onCameraSwitchError(str);
                }
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void syncUpdateTexImage(SurfaceTexture surfaceTexture) {
                synchronized (EglBase.lock) {
                    surfaceTexture.updateTexImage();
                }
            }
        });
        return createCameraCapturer(this.mCameraCapturer);
    }

    public VideoCapturer createCameraCapturer(final CameraVideoCapturer cameraVideoCapturer) {
        if (cameraVideoCapturer == null) {
            return null;
        }
        return new CameraCapturer() { // from class: com.baidu.cloud.rtcbridge.framecapture.RtcFrameCaptureImpl.2
            @Override // com.webrtc.VideoCapturer
            public boolean isScreencast() {
                return false;
            }

            @Override // com.webrtc.VideoCapturer
            public void initialize(final SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
                RtcFrameCaptureImpl.this.registerCapturerObserver(capturerObserver);
                cameraVideoCapturer.initialize(surfaceTextureHelper.getSurfaceTexture(), surfaceTextureHelper.getOesTextureId(), surfaceTextureHelper.getHandler(), context, new com.baidu.cloud.framecapture.CapturerObserver() { // from class: com.baidu.cloud.rtcbridge.framecapture.RtcFrameCaptureImpl.2.1
                    @Override // com.baidu.cloud.framecapture.CapturerObserver
                    public void onCapturerStarted(boolean z) {
                        if (RtcFrameCaptureImpl.this.mObservers != null) {
                            for (CapturerObserver capturerObserver2 : RtcFrameCaptureImpl.this.mObservers) {
                                capturerObserver2.onCapturerStarted(z);
                            }
                        }
                    }

                    @Override // com.baidu.cloud.framecapture.CapturerObserver
                    public void onCapturerStopped() {
                        if (RtcFrameCaptureImpl.this.mObservers != null) {
                            for (CapturerObserver capturerObserver2 : RtcFrameCaptureImpl.this.mObservers) {
                                capturerObserver2.onCapturerStopped();
                            }
                        }
                    }

                    @Override // com.baidu.cloud.framecapture.CapturerObserver
                    public void onFrameCaptured(VideoFrameBuffer videoFrameBuffer) {
                        VideoFrameBuffer onFrameProcessor;
                        if (RtcFrameCaptureImpl.this.mObservers != null) {
                            for (CapturerObserver capturerObserver2 : RtcFrameCaptureImpl.this.mObservers) {
                                if ((capturerObserver2 instanceof RtcFrameCapturerObserver) && (onFrameProcessor = ((RtcFrameCapturerObserver) capturerObserver2).onFrameProcessor(videoFrameBuffer)) != null) {
                                    videoFrameBuffer = onFrameProcessor;
                                }
                            }
                            VideoFrame onFrame = RtcFrameCaptureImpl.this.onFrame(videoFrameBuffer, surfaceTextureHelper);
                            if (onFrame == null) {
                                return;
                            }
                            for (CapturerObserver capturerObserver3 : RtcFrameCaptureImpl.this.mObservers) {
                                capturerObserver3.onFrameCaptured(onFrame);
                            }
                            onFrame.release();
                        }
                    }
                });
            }

            @Override // com.webrtc.VideoCapturer
            public void startCapture(int i, int i2, int i3) {
                cameraVideoCapturer.startCapture(i, i2, i3);
            }

            @Override // com.webrtc.VideoCapturer
            public void stopCapture() throws InterruptedException {
                cameraVideoCapturer.stopCapture();
                if (RtcFrameCaptureImpl.this.drawer != null) {
                    RtcFrameCaptureImpl.this.drawer.release();
                    RtcFrameCaptureImpl.this.drawer = null;
                }
                if (RtcFrameCaptureImpl.this.frameDrawer != null) {
                    RtcFrameCaptureImpl.this.frameDrawer.release();
                    RtcFrameCaptureImpl.this.frameDrawer = null;
                }
                if (RtcFrameCaptureImpl.this.frameBuffer != null) {
                    RtcFrameCaptureImpl.this.frameBuffer.release();
                    RtcFrameCaptureImpl.this.frameBuffer = null;
                }
            }

            @Override // com.webrtc.VideoCapturer
            public void changeCaptureFormat(int i, int i2, int i3) {
                cameraVideoCapturer.changeCaptureFormat(i, i2, i3);
            }

            @Override // com.webrtc.VideoCapturer
            public void dispose() {
                cameraVideoCapturer.dispose();
                RtcFrameCaptureImpl.this.unregisterCapturerObserverAll();
            }

            @Override // com.baidu.rtc.camera.CameraCapturer
            public void switchCamera() {
                cameraVideoCapturer.switchCamera();
            }

            @Override // com.baidu.rtc.camera.CameraCapturer
            public void switchCamera(String str) {
                cameraVideoCapturer.switchCamera(str);
            }
        };
    }

    @Override // com.baidu.cloud.rtcbridge.framecapture.IRtcFrameCapture
    public boolean isFrontCamera(int i) {
        CameraVideoCapturer cameraVideoCapturer = this.mCameraCapturer;
        if (cameraVideoCapturer == null) {
            return false;
        }
        return cameraVideoCapturer.isFrontCamera(i);
    }

    @Override // com.baidu.cloud.rtcbridge.framecapture.IRtcFrameCapture
    public void registerCapturerObserver(CapturerObserver capturerObserver) {
        if (capturerObserver == null) {
            return;
        }
        if (this.mObservers == null) {
            this.mObservers = new ArrayList();
        }
        if (this.mObservers.contains(capturerObserver)) {
            return;
        }
        this.mObservers.add(capturerObserver);
    }

    @Override // com.baidu.cloud.rtcbridge.framecapture.IRtcFrameCapture
    public void unregisterCapturerObserver(CapturerObserver capturerObserver) {
        List<CapturerObserver> list = this.mObservers;
        if (list == null || capturerObserver == null) {
            return;
        }
        list.remove(capturerObserver);
    }

    @Override // com.baidu.cloud.rtcbridge.framecapture.IRtcFrameCapture
    public void unregisterCapturerObserverAll() {
        List<CapturerObserver> list = this.mObservers;
        if (list == null) {
            return;
        }
        list.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VideoFrame onFrame(VideoFrameBuffer videoFrameBuffer, SurfaceTextureHelper surfaceTextureHelper) {
        TextureBufferImpl textureBufferImpl;
        if (videoFrameBuffer.pixelFormat == null) {
            return null;
        }
        switch (videoFrameBuffer.pixelFormat) {
            case TEXTURE:
                textureBufferImpl = new TextureBufferImpl(videoFrameBuffer.width, videoFrameBuffer.height, videoFrameBuffer.textureBuffer.type == TextureBuffer.Type.OES ? VideoFrame.TextureBuffer.Type.OES : VideoFrame.TextureBuffer.Type.RGB, videoFrameBuffer.textureBuffer.textureId, RendererCommon.convertMatrixToAndroidGraphicsMatrix(videoFrameBuffer.transformMatrix), surfaceTextureHelper.getHandler(), surfaceTextureHelper.getYuvConverter(), (Runnable) null);
                break;
            case YUV:
                drawYuv(new VideoFrame(new NV21Buffer(videoFrameBuffer.data, videoFrameBuffer.width, videoFrameBuffer.height, null), 0, videoFrameBuffer.timestampNs));
                float[] fArr = new float[16];
                Matrix.setIdentityM(fArr, 0);
                textureBufferImpl = new TextureBufferImpl(videoFrameBuffer.width, videoFrameBuffer.height, VideoFrame.TextureBuffer.Type.RGB, this.frameBuffer.getTextureId(), RendererCommon.convertMatrixToAndroidGraphicsMatrix(fArr), surfaceTextureHelper.getHandler(), surfaceTextureHelper.getYuvConverter(), (Runnable) null);
                break;
            default:
                textureBufferImpl = null;
                break;
        }
        if (textureBufferImpl == null) {
            return null;
        }
        return new VideoFrame(textureBufferImpl, videoFrameBuffer.rotation, videoFrameBuffer.timestampNs);
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
}
