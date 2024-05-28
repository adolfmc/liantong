package com.webrtc;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import com.webrtc.EglBase;
import com.webrtc.VideoFrame;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SurfaceTextureHelper {
    private static final String TAG = "SurfaceTextureHelper";
    private final EglBase eglBase;
    private boolean enableFpsControll;
    private int frameRotation;
    private final Handler handler;
    private boolean hasPendingTexture;
    private boolean isQuitting;
    private volatile boolean isTextureInUse;
    @Nullable
    private VideoSink listener;
    private boolean mDrawStarted;
    private Handler mFpsHandler;
    private int mTargetFps;
    private int mTargetFrameDur;
    int num;
    private final int oesTextureId;
    @Nullable
    private VideoSink pendingListener;
    final Runnable setListenerRunnable;
    private final SurfaceTexture surfaceTexture;
    private int textureHeight;
    private int textureWidth;
    @Nullable
    private final TimestampAligner timestampAligner;
    private final YuvConverter yuvConverter;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface FrameRefMonitor {
        void onDestroyBuffer(VideoFrame.TextureBuffer textureBuffer);

        void onNewBuffer(VideoFrame.TextureBuffer textureBuffer);

        void onReleaseBuffer(VideoFrame.TextureBuffer textureBuffer);

        void onRetainBuffer(VideoFrame.TextureBuffer textureBuffer);
    }

    public static SurfaceTextureHelper create(final String str, final EglBase.Context context, final boolean z, final boolean z2) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper());
        return (SurfaceTextureHelper) ThreadUtils.invokeAtFrontUninterruptibly(handler, new Callable<SurfaceTextureHelper>() { // from class: com.webrtc.SurfaceTextureHelper.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            @Nullable
            public SurfaceTextureHelper call() {
                try {
                    return new SurfaceTextureHelper(EglBase.Context.this, handler, z, z2);
                } catch (RuntimeException e) {
                    Logging.m5303e(SurfaceTextureHelper.TAG, str + " create failure", e);
                    return null;
                }
            }
        });
    }

    public static SurfaceTextureHelper create(String str, EglBase.Context context) {
        return create(str, context, false, true);
    }

    public static SurfaceTextureHelper create(String str, EglBase.Context context, boolean z) {
        return create(str, context, false, z);
    }

    private SurfaceTextureHelper(EglBase.Context context, Handler handler, boolean z, boolean z2) {
        this.yuvConverter = new YuvConverter();
        this.mDrawStarted = false;
        this.enableFpsControll = false;
        this.setListenerRunnable = new Runnable() { // from class: com.webrtc.SurfaceTextureHelper.2
            @Override // java.lang.Runnable
            public void run() {
                Logging.m5305d(SurfaceTextureHelper.TAG, "Setting listener to " + SurfaceTextureHelper.this.pendingListener);
                SurfaceTextureHelper surfaceTextureHelper = SurfaceTextureHelper.this;
                surfaceTextureHelper.listener = surfaceTextureHelper.pendingListener;
                SurfaceTextureHelper.this.pendingListener = null;
                if (SurfaceTextureHelper.this.hasPendingTexture) {
                    SurfaceTextureHelper.this.updateTexImage();
                    SurfaceTextureHelper.this.hasPendingTexture = false;
                }
            }
        };
        this.num = 0;
        if (handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("SurfaceTextureHelper must be created on the handler thread");
        }
        this.handler = handler;
        this.timestampAligner = z ? new TimestampAligner() : null;
        this.mFpsHandler = new Handler(Looper.myLooper()) { // from class: com.webrtc.SurfaceTextureHelper.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 1) {
                    if (!SurfaceTextureHelper.this.enableFpsControll || SurfaceTextureHelper.this.mDrawStarted) {
                        return;
                    }
                    SurfaceTextureHelper.this.mFpsHandler.sendMessage(SurfaceTextureHelper.this.mFpsHandler.obtainMessage(2));
                    SurfaceTextureHelper.this.mDrawStarted = true;
                } else if (message.what == 2) {
                    SurfaceTextureHelper.this.num++;
                    new SimpleDateFormat("yyyyMMDD HH:mm:ss", Locale.getDefault()).format(new Date());
                    SurfaceTextureHelper.this.tryDeliverTextureFrame();
                    SurfaceTextureHelper.this.mFpsHandler.sendMessageDelayed(SurfaceTextureHelper.this.mFpsHandler.obtainMessage(2), SurfaceTextureHelper.this.mTargetFrameDur);
                }
            }
        };
        this.eglBase = EglBase.CC.create(context, EglBase.CONFIG_PIXEL_BUFFER);
        try {
            this.eglBase.createDummyPbufferSurface();
            this.eglBase.makeCurrent();
            this.oesTextureId = GlUtil.generateTexture(36197);
            this.surfaceTexture = new SurfaceTexture(this.oesTextureId);
            if (z2) {
                setOnFrameAvailableListener(this.surfaceTexture, new SurfaceTexture.OnFrameAvailableListener() { // from class: com.webrtc.-$$Lambda$SurfaceTextureHelper$ftcO51vVHg-JooxSoG1B2nb2ttY
                    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
                    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
                        SurfaceTextureHelper.lambda$new$0(SurfaceTextureHelper.this, surfaceTexture);
                    }
                }, handler);
            }
        } catch (RuntimeException e) {
            this.eglBase.release();
            handler.getLooper().quit();
            throw e;
        }
    }

    public static /* synthetic */ void lambda$new$0(SurfaceTextureHelper surfaceTextureHelper, SurfaceTexture surfaceTexture) {
        surfaceTextureHelper.hasPendingTexture = true;
        if (surfaceTextureHelper.enableFpsControll) {
            Handler handler = surfaceTextureHelper.mFpsHandler;
            handler.sendMessage(handler.obtainMessage(1));
            return;
        }
        surfaceTextureHelper.tryDeliverTextureFrame();
    }

    @TargetApi(21)
    private static void setOnFrameAvailableListener(SurfaceTexture surfaceTexture, SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener, Handler handler) {
        if (Build.VERSION.SDK_INT >= 21) {
            surfaceTexture.setOnFrameAvailableListener(onFrameAvailableListener, handler);
        } else {
            surfaceTexture.setOnFrameAvailableListener(onFrameAvailableListener);
        }
    }

    public void setFps(int i) {
        this.mTargetFps = i;
        int i2 = this.mTargetFps;
        if (i2 > 0) {
            this.enableFpsControll = true;
            this.mTargetFrameDur = 1000 / i2;
        }
    }

    public void startListening(VideoSink videoSink) {
        if (this.listener != null || this.pendingListener != null) {
            throw new IllegalStateException("SurfaceTextureHelper listener has already been set.");
        }
        this.pendingListener = videoSink;
        this.handler.post(this.setListenerRunnable);
    }

    public void stopListening() {
        Logging.m5305d(TAG, "stopListening()");
        this.handler.removeCallbacks(this.setListenerRunnable);
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() { // from class: com.webrtc.-$$Lambda$SurfaceTextureHelper$GIujoGeMFsnja_Yl8ybquZyY27Q
            @Override // java.lang.Runnable
            public final void run() {
                SurfaceTextureHelper.lambda$stopListening$1(SurfaceTextureHelper.this);
            }
        });
    }

    public static /* synthetic */ void lambda$stopListening$1(SurfaceTextureHelper surfaceTextureHelper) {
        surfaceTextureHelper.mFpsHandler.removeCallbacksAndMessages(null);
        surfaceTextureHelper.mDrawStarted = false;
        surfaceTextureHelper.listener = null;
        surfaceTextureHelper.pendingListener = null;
    }

    public void setTextureSize(final int i, final int i2) {
        if (i <= 0) {
            throw new IllegalArgumentException("Texture width must be positive, but was " + i);
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("Texture height must be positive, but was " + i2);
        } else {
            this.surfaceTexture.setDefaultBufferSize(i, i2);
            this.handler.post(new Runnable() { // from class: com.webrtc.-$$Lambda$SurfaceTextureHelper$ik4lMvY6B-1wlacLDPxQQdwh8VQ
                @Override // java.lang.Runnable
                public final void run() {
                    SurfaceTextureHelper.lambda$setTextureSize$2(SurfaceTextureHelper.this, i, i2);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$setTextureSize$2(SurfaceTextureHelper surfaceTextureHelper, int i, int i2) {
        surfaceTextureHelper.textureWidth = i;
        surfaceTextureHelper.textureHeight = i2;
    }

    public void setFrameRotation(final int i) {
        this.handler.post(new Runnable() { // from class: com.webrtc.-$$Lambda$SurfaceTextureHelper$QeyPUySuwU4VrOFd4aeP-KTQAWg
            @Override // java.lang.Runnable
            public final void run() {
                SurfaceTextureHelper.this.frameRotation = i;
            }
        });
    }

    public int getOesTextureId() {
        return this.oesTextureId;
    }

    public YuvConverter getYuvConverter() {
        return this.yuvConverter;
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    public Handler getHandler() {
        return this.handler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void returnTextureFrame() {
        this.handler.post(new Runnable() { // from class: com.webrtc.-$$Lambda$SurfaceTextureHelper$iHlJo2vQTbEKYacMfAMJvHDluJM
            @Override // java.lang.Runnable
            public final void run() {
                SurfaceTextureHelper.lambda$returnTextureFrame$4(SurfaceTextureHelper.this);
            }
        });
    }

    public static /* synthetic */ void lambda$returnTextureFrame$4(SurfaceTextureHelper surfaceTextureHelper) {
        surfaceTextureHelper.isTextureInUse = false;
        if (surfaceTextureHelper.isQuitting) {
            surfaceTextureHelper.release();
        } else if (surfaceTextureHelper.enableFpsControll) {
        } else {
            surfaceTextureHelper.tryDeliverTextureFrame();
        }
    }

    public boolean isTextureInUse() {
        return this.isTextureInUse;
    }

    public void dispose() {
        Logging.m5305d(TAG, "dispose()");
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() { // from class: com.webrtc.-$$Lambda$SurfaceTextureHelper$hPeN5HS-DDz7VxUbB_itmvzRhJs
            @Override // java.lang.Runnable
            public final void run() {
                SurfaceTextureHelper.lambda$dispose$5(SurfaceTextureHelper.this);
            }
        });
    }

    public static /* synthetic */ void lambda$dispose$5(SurfaceTextureHelper surfaceTextureHelper) {
        surfaceTextureHelper.isQuitting = true;
        if (surfaceTextureHelper.isTextureInUse) {
            return;
        }
        surfaceTextureHelper.release();
    }

    @Deprecated
    public VideoFrame.I420Buffer textureToYuv(VideoFrame.TextureBuffer textureBuffer) {
        return textureBuffer.toI420();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTexImage() {
        synchronized (EglBase.lock) {
            this.surfaceTexture.updateTexImage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryDeliverTextureFrame() {
        boolean z;
        int i;
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Wrong thread.");
        }
        if (!this.enableFpsControll || this.hasPendingTexture) {
            z = false;
        } else {
            this.hasPendingTexture = true;
            z = true;
        }
        if (this.isQuitting || !this.hasPendingTexture || this.isTextureInUse || this.listener == null) {
            return;
        }
        this.isTextureInUse = true;
        this.hasPendingTexture = false;
        if (!z) {
            updateTexImage();
        }
        float[] fArr = new float[16];
        this.surfaceTexture.getTransformMatrix(fArr);
        long timestamp = this.surfaceTexture.getTimestamp();
        TimestampAligner timestampAligner = this.timestampAligner;
        if (timestampAligner != null) {
            timestamp = timestampAligner.translateTimestamp(timestamp);
        }
        if (this.enableFpsControll) {
            timestamp = System.nanoTime();
        }
        int i2 = this.textureWidth;
        if (i2 == 0 || (i = this.textureHeight) == 0) {
            throw new RuntimeException("Texture size has not been set.");
        }
        VideoFrame videoFrame = new VideoFrame(new TextureBufferImpl(i2, i, VideoFrame.TextureBuffer.Type.OES, this.oesTextureId, RendererCommon.convertMatrixToAndroidGraphicsMatrix(fArr), this.handler, this.yuvConverter, new Runnable() { // from class: com.webrtc.-$$Lambda$SurfaceTextureHelper$E1VeaJyK5MugPYbBS6OVyJ0F3Hs
            @Override // java.lang.Runnable
            public final void run() {
                SurfaceTextureHelper.this.returnTextureFrame();
            }
        }), this.frameRotation, timestamp);
        this.listener.onFrame(videoFrame);
        videoFrame.release();
    }

    private void release() {
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Wrong thread.");
        }
        if (this.isTextureInUse || !this.isQuitting) {
            throw new IllegalStateException("Unexpected release.");
        }
        this.yuvConverter.release();
        GLES20.glDeleteTextures(1, new int[]{this.oesTextureId}, 0);
        this.surfaceTexture.release();
        this.eglBase.release();
        this.handler.getLooper().quit();
        TimestampAligner timestampAligner = this.timestampAligner;
        if (timestampAligner != null) {
            timestampAligner.dispose();
        }
    }
}
