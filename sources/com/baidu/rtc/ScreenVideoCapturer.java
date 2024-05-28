package com.baidu.rtc;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import com.baidu.rtc.config.BRTCScreenShareParams;
import com.webrtc.CapturerObserver;
import com.webrtc.Logging;
import com.webrtc.SurfaceTextureHelper;
import com.webrtc.ThreadUtils;
import com.webrtc.VideoFrame;
import com.webrtc.VideoSink;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ScreenVideoCapturer implements VideoSink {
    private static final int DISPLAY_FLAGS = 3;
    private static final int VIRTUAL_DISPLAY_DPI = 400;
    private CapturerObserver capturerObserver;
    private int fps;
    private int height;
    private MediaProjection mMediaProjection;
    private int mScreenDensity = 400;
    private int mScreenHeight;
    private int mScreenWidth;
    private long numCapturedFrames;
    private SurfaceTextureHelper surfaceTextureHelper;
    private VirtualDisplay virtualDisplay;
    private int width;

    public boolean isScreencast() {
        return true;
    }

    public synchronized void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
        WindowManager windowManager;
        if (capturerObserver == null) {
            return;
        }
        this.capturerObserver = capturerObserver;
        this.surfaceTextureHelper = surfaceTextureHelper;
        if (context != null && (windowManager = (WindowManager) context.getSystemService("window")) != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            defaultDisplay.getSize(new Point());
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            this.mScreenDensity = displayMetrics.densityDpi;
            this.mScreenWidth = displayMetrics.widthPixels;
            this.mScreenHeight = displayMetrics.heightPixels;
        }
    }

    public void startCapture(MediaProjection mediaProjection, BRTCScreenShareParams.BRTCScreenShareVideoParams bRTCScreenShareVideoParams, MediaProjection.Callback callback) {
        if (bRTCScreenShareVideoParams != null) {
            this.width = bRTCScreenShareVideoParams.videoWidth;
            this.height = bRTCScreenShareVideoParams.videoHeight;
            this.fps = bRTCScreenShareVideoParams.videoFps;
        }
        if (this.width == 0 || this.height == 0) {
            this.width = this.mScreenWidth;
            this.height = this.mScreenHeight;
        }
        if (this.fps == 0) {
            this.fps = 10;
        }
        this.mMediaProjection = mediaProjection;
        createVirtualDisplay();
        if (this.virtualDisplay == null) {
            log("virtualDisplay is null");
            if (callback != null) {
                callback.onStop();
                return;
            }
            return;
        }
        CapturerObserver capturerObserver = this.capturerObserver;
        if (capturerObserver != null) {
            capturerObserver.onCapturerStarted(true);
        }
        this.surfaceTextureHelper.setFps(this.fps);
        this.surfaceTextureHelper.startListening(this);
    }

    public synchronized void stopCapture() {
        ThreadUtils.invokeAtFrontUninterruptibly(this.surfaceTextureHelper.getHandler(), new Runnable() { // from class: com.baidu.rtc.ScreenVideoCapturer.1
            @Override // java.lang.Runnable
            public void run() {
                ScreenVideoCapturer.this.surfaceTextureHelper.stopListening();
                if (ScreenVideoCapturer.this.capturerObserver != null) {
                    ScreenVideoCapturer.this.capturerObserver.onCapturerStopped();
                }
                if (ScreenVideoCapturer.this.virtualDisplay != null) {
                    ScreenVideoCapturer.this.virtualDisplay.release();
                    ScreenVideoCapturer.this.virtualDisplay = null;
                }
            }
        });
    }

    public synchronized void changeCaptureFormat(int i, int i2, final int i3) {
        this.width = i;
        this.height = i2;
        this.fps = i3;
        if (this.virtualDisplay != null) {
            ThreadUtils.invokeAtFrontUninterruptibly(this.surfaceTextureHelper.getHandler(), new Runnable() { // from class: com.baidu.rtc.ScreenVideoCapturer.2
                @Override // java.lang.Runnable
                public void run() {
                    ScreenVideoCapturer.this.virtualDisplay.release();
                    ScreenVideoCapturer.this.createVirtualDisplay();
                    ScreenVideoCapturer.this.surfaceTextureHelper.setFps(i3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createVirtualDisplay() {
        try {
            this.surfaceTextureHelper.setTextureSize(this.width, this.height);
            this.virtualDisplay = this.mMediaProjection.createVirtualDisplay("WebRTC_ScreenVideoCapture", this.width, this.height, this.mScreenDensity, 1, new Surface(this.surfaceTextureHelper.getSurfaceTexture()), null, null);
        } catch (Exception e) {
            log("createVirtualDisplay exception ----- " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override // com.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        this.numCapturedFrames++;
        CapturerObserver capturerObserver = this.capturerObserver;
        if (capturerObserver != null) {
            capturerObserver.onFrameCaptured(videoFrame);
        }
    }

    public long getNumCapturedFrames() {
        return this.numCapturedFrames;
    }

    private void log(String str) {
        Logging.m5305d(ScreenCapturerAndroid.class.getName(), str);
    }
}
