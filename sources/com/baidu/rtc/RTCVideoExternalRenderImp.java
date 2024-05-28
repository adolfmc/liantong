package com.baidu.rtc;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.baidu.rtc.BaiduRtcRoom;
import com.webrtc.EglBase;
import com.webrtc.GlRectDrawer;
import com.webrtc.Logging;
import com.webrtc.RendererCommon;
import com.webrtc.SurfaceEglRenderer;
import com.webrtc.ThreadUtils;
import com.webrtc.VideoFrame;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RTCVideoExternalRenderImp extends RTCVideoExternalRender implements RendererCommon.RendererEvents {
    private static final String TAG = "ExternalRenderImp";
    private SurfaceEglRenderer eglRenderer;
    private Boolean hasSurface;
    private SurfaceHolder holder;
    private boolean isGetDimension;
    private RendererCommon.RendererEvents rendererEvents;
    private BaiduRtcRoom.BaiduRtcRoomDelegate roomDelegate;
    private int rotatedFrameHeight;
    private int rotatedFrameWidth;
    private Surface surface;
    private long userId;
    private int videoHeight;
    private int videoRotation;
    private int videoWidth;

    public RTCVideoExternalRenderImp(BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate, long j) {
        super(null, j);
        this.videoWidth = 0;
        this.videoHeight = 0;
        this.videoRotation = 0;
        this.isGetDimension = false;
        this.hasSurface = false;
        this.roomDelegate = baiduRtcRoomDelegate;
        this.userId = j;
        this.eglRenderer = new SurfaceEglRenderer(String.valueOf(j));
    }

    public RTCVideoExternalRenderImp(EglBase.Context context, RendererCommon.RendererEvents rendererEvents, long j) {
        super(null, j);
        this.videoWidth = 0;
        this.videoHeight = 0;
        this.videoRotation = 0;
        this.isGetDimension = false;
        this.hasSurface = false;
        this.userId = j;
        this.eglRenderer = new SurfaceEglRenderer(String.valueOf(j));
        init(context, rendererEvents);
    }

    @Override // com.baidu.rtc.RTCVideoExternalRender
    public void init() {
        Logging.m5305d("ExternalRenderImp", this.userId + " : External renderer init");
        init(EglBase.CC.create().getEglBaseContext(), null);
    }

    public void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents) {
        init(context, rendererEvents, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    public void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents, int[] iArr, RendererCommon.GlDrawer glDrawer) {
        ThreadUtils.checkIsOnMainThread();
        this.rendererEvents = rendererEvents;
        this.rotatedFrameWidth = 0;
        this.rotatedFrameHeight = 0;
        this.eglRenderer.init(context, this, iArr, glDrawer);
    }

    @Override // com.baidu.rtc.RTCVideoExternalRender
    public void setSurface(Surface surface) {
        if (surface == null || this.surface == surface) {
            Logging.m5305d("ExternalRenderImp", this.userId + " : Set remote surface fail! cause surface: " + surface);
            return;
        }
        if (this.hasSurface.booleanValue() && this.surface != null) {
            releaseSurface();
        }
        this.surface = surface;
        this.holder = new SurfaceHolder() { // from class: com.baidu.rtc.RTCVideoExternalRenderImp.1
            @Override // android.view.SurfaceHolder
            public void addCallback(SurfaceHolder.Callback callback) {
            }

            @Override // android.view.SurfaceHolder
            public Rect getSurfaceFrame() {
                return null;
            }

            @Override // android.view.SurfaceHolder
            public boolean isCreating() {
                return true;
            }

            @Override // android.view.SurfaceHolder
            public Canvas lockCanvas() {
                return null;
            }

            @Override // android.view.SurfaceHolder
            public Canvas lockCanvas(Rect rect) {
                return null;
            }

            @Override // android.view.SurfaceHolder
            public void removeCallback(SurfaceHolder.Callback callback) {
            }

            @Override // android.view.SurfaceHolder
            public void setFixedSize(int i, int i2) {
            }

            @Override // android.view.SurfaceHolder
            public void setFormat(int i) {
            }

            @Override // android.view.SurfaceHolder
            public void setKeepScreenOn(boolean z) {
            }

            @Override // android.view.SurfaceHolder
            public void setSizeFromLayout() {
            }

            @Override // android.view.SurfaceHolder
            public void setType(int i) {
            }

            @Override // android.view.SurfaceHolder
            public void unlockCanvasAndPost(Canvas canvas) {
            }

            @Override // android.view.SurfaceHolder
            public Surface getSurface() {
                return RTCVideoExternalRenderImp.this.surface;
            }
        };
        this.eglRenderer.surfaceCreated(this.holder);
        this.hasSurface = true;
        Logging.m5305d("ExternalRenderImp", this.userId + " : External renderer set surface =" + this.surface);
    }

    @Override // com.baidu.rtc.RTCVideoExternalRender
    public Surface getSurface() {
        return this.surface;
    }

    @Override // com.baidu.rtc.RTCVideoExternalRender
    public boolean hasSurface() {
        return this.hasSurface.booleanValue();
    }

    public void setLayoutAspect(float f) {
        this.eglRenderer.setLayoutAspectRatio(f);
    }

    @Override // com.baidu.rtc.RTCVideoExternalRender
    public void changeSurfaceSize(int i, int i2) {
        setLayoutAspect(i / i2);
    }

    @Override // com.baidu.rtc.RTCVideoExternalRender
    public void releaseSurface() {
        this.eglRenderer.surfaceDestroyed(this.holder);
        this.hasSurface = false;
        this.surface = null;
        Logging.m5305d("ExternalRenderImp", this.userId + " : External renderer release surface " + this.surface);
    }

    public void setMirror(boolean z) {
        this.eglRenderer.setMirror(z);
    }

    public void setFpsReduction(float f) {
        this.eglRenderer.setFpsReduction(f);
    }

    public void disableFpsReduction() {
        this.eglRenderer.disableFpsReduction();
    }

    public void pauseVideo() {
        this.eglRenderer.pauseVideo();
    }

    @Override // com.baidu.rtc.RTCVideoExternalRender
    public void clearImage() {
        this.eglRenderer.clearImage();
    }

    public int getVideoWidth() {
        return this.videoWidth;
    }

    public int getVideoHeight() {
        return this.videoHeight;
    }

    public int getVideoRotation() {
        return this.videoRotation;
    }

    public void setRoomDelegate(BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate) {
        this.roomDelegate = baiduRtcRoomDelegate;
    }

    @Override // com.baidu.rtc.RTCVideoExternalRender, com.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        Logging.m5305d("ExternalRenderImp", this.userId + " : onFrame time stamp:" + videoFrame.getTimestampNs());
        if (!this.isGetDimension) {
            this.videoWidth = videoFrame.getBuffer().getWidth();
            this.videoHeight = videoFrame.getBuffer().getHeight();
            this.videoRotation = videoFrame.getRotation();
            this.isGetDimension = true;
        }
        this.eglRenderer.onFrame(videoFrame);
    }

    @Override // com.baidu.rtc.RTCVideoExternalRender
    public void release() {
        Logging.m5305d("ExternalRenderImp", this.userId + " : External renderer release");
        this.eglRenderer.release();
    }

    @Override // com.webrtc.RendererCommon.RendererEvents
    public void onFirstFrameRendered() {
        RendererCommon.RendererEvents rendererEvents = this.rendererEvents;
        if (rendererEvents != null) {
            rendererEvents.onFirstFrameRendered();
        }
    }

    @Override // com.webrtc.RendererCommon.RendererEvents
    public void onFrameResolutionChanged(int i, int i2, int i3) {
        this.videoWidth = i;
        this.videoHeight = i2;
        this.videoRotation = i3;
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.roomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onRoomEventUpdate(500, this.userId, null);
        }
        RendererCommon.RendererEvents rendererEvents = this.rendererEvents;
        if (rendererEvents != null) {
            rendererEvents.onFrameResolutionChanged(i, i2, i3);
        }
    }
}
