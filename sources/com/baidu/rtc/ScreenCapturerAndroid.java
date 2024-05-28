package com.baidu.rtc;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.baidu.rtc.CaptureScreenService;
import com.baidu.rtc.RTCAudioSamples;
import com.baidu.rtc.config.BRTCScreenShareParams;
import com.webrtc.CapturerObserver;
import com.webrtc.Logging;
import com.webrtc.SurfaceTextureHelper;
import com.webrtc.ThreadUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@TargetApi(21)
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ScreenCapturerAndroid {
    private boolean isDisposed;
    private ScreenAudioCapturer mAudioCapturer;
    private boolean mCapturing;
    private Context mContext;
    private Handler mHandler;
    private BRTCScreenShareParams mScreenShareParams;
    private boolean mServiceBind;
    @Nullable
    private MediaProjection mediaProjection;
    private final MediaProjection.Callback mediaProjectionCallback;
    @Nullable
    private MediaProjectionManager mediaProjectionManager;
    private final Intent mediaProjectionPermissionResultData;
    private ServiceConnection conn = new ServiceConnection() { // from class: com.baidu.rtc.ScreenCapturerAndroid.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ScreenCapturerAndroid.this.log("onServiceConnected");
            CaptureScreenService service = ((CaptureScreenService.CaptureScreenServiceBinder) iBinder).getService();
            ScreenCapturerAndroid.this.mediaProjection = service.getMediaProjection();
            ScreenCapturerAndroid.this.startCaptureInternal();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ScreenCapturerAndroid.this.log("onServiceDisconnected");
        }
    };
    private ScreenVideoCapturer mVideoCapturer = new ScreenVideoCapturer();

    public ScreenCapturerAndroid(Intent intent, MediaProjection.Callback callback, RTCAudioSamples.RTCExternalSamplesReadyCallback rTCExternalSamplesReadyCallback) {
        this.mediaProjectionPermissionResultData = intent;
        this.mediaProjectionCallback = callback;
        this.mAudioCapturer = new ScreenAudioCapturer(rTCExternalSamplesReadyCallback);
    }

    public synchronized void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
        if (this.isDisposed) {
            return;
        }
        this.mContext = context;
        if (capturerObserver != null && surfaceTextureHelper != null) {
            this.mHandler = surfaceTextureHelper.getHandler();
            this.mVideoCapturer.initialize(surfaceTextureHelper, context, capturerObserver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCaptureInternal() {
        MediaProjection mediaProjection = this.mediaProjection;
        if (mediaProjection == null) {
            log("mediaProjection is null");
            MediaProjection.Callback callback = this.mediaProjectionCallback;
            if (callback != null) {
                callback.onStop();
                return;
            }
            return;
        }
        mediaProjection.registerCallback(this.mediaProjectionCallback, this.mHandler);
        if (this.mScreenShareParams.mEnableVideoCapture) {
            this.mVideoCapturer.startCapture(this.mediaProjection, this.mScreenShareParams.mVideoCaptureParams, this.mediaProjectionCallback);
        }
        if (this.mScreenShareParams.mEnableAudioCapture) {
            if (this.mContext != null && Build.VERSION.SDK_INT >= 29) {
                ((AudioManager) this.mContext.getSystemService("audio")).setAllowedCapturePolicy(1);
            }
            this.mAudioCapturer.startCapture(this.mediaProjection, this.mScreenShareParams.mAudioCaptureParams);
        }
    }

    public synchronized void startCapture(BRTCScreenShareParams bRTCScreenShareParams) {
        if (this.isDisposed) {
            return;
        }
        if (this.mCapturing) {
            return;
        }
        this.mScreenShareParams = bRTCScreenShareParams;
        this.mCapturing = true;
        log("startCapture -- SDK_INT " + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= 29) {
            Intent intent = new Intent(this.mContext, CaptureScreenService.class);
            intent.putExtra("code", -1);
            intent.putExtra("data", this.mediaProjectionPermissionResultData);
            intent.putExtra("intent_class", this.mContext.getClass().getName());
            this.mContext.bindService(intent, this.conn, 1);
            this.mServiceBind = true;
            log("startCapture -- bindService");
        } else {
            this.mediaProjectionManager = (MediaProjectionManager) this.mContext.getSystemService("media_projection");
            this.mediaProjection = this.mediaProjectionManager.getMediaProjection(-1, this.mediaProjectionPermissionResultData);
            startCaptureInternal();
        }
    }

    public synchronized void stopCapture() {
        if (this.isDisposed) {
            return;
        }
        if (this.mCapturing) {
            this.mCapturing = false;
            if (Build.VERSION.SDK_INT >= 29 && this.mContext != null) {
                this.mContext.unbindService(this.conn);
                this.mServiceBind = false;
                log("stopCapture -- unbindService");
            }
            if (this.mScreenShareParams.mEnableVideoCapture) {
                this.mVideoCapturer.stopCapture();
            }
            if (this.mScreenShareParams.mEnableAudioCapture) {
                this.mAudioCapturer.stopCapture();
            }
            ThreadUtils.invokeAtFrontUninterruptibly(this.mHandler, new Runnable() { // from class: com.baidu.rtc.ScreenCapturerAndroid.2
                @Override // java.lang.Runnable
                public void run() {
                    if (ScreenCapturerAndroid.this.mediaProjection != null) {
                        ScreenCapturerAndroid.this.mediaProjection.unregisterCallback(ScreenCapturerAndroid.this.mediaProjectionCallback);
                        ScreenCapturerAndroid.this.mediaProjection.stop();
                        ScreenCapturerAndroid.this.mediaProjection = null;
                    }
                }
            });
        }
    }

    public synchronized void updateScreenCaptureParameters(BRTCScreenShareParams bRTCScreenShareParams) {
        updateScreenShareVideoParams(bRTCScreenShareParams);
        updateScreenShareAudioParams(bRTCScreenShareParams);
        this.mScreenShareParams = bRTCScreenShareParams;
    }

    private void updateScreenShareVideoParams(BRTCScreenShareParams bRTCScreenShareParams) {
        if (bRTCScreenShareParams.mEnableVideoCapture == this.mScreenShareParams.mEnableVideoCapture && bRTCScreenShareParams.mEnableVideoCapture && bRTCScreenShareParams.isChangeVideoFormat(this.mScreenShareParams)) {
            this.mVideoCapturer.changeCaptureFormat(bRTCScreenShareParams.mVideoCaptureParams.videoWidth, bRTCScreenShareParams.mVideoCaptureParams.videoHeight, bRTCScreenShareParams.mVideoCaptureParams.videoFps);
            log("updateScreenShareParams changeVideoFormat ");
        }
    }

    private void updateScreenShareAudioParams(BRTCScreenShareParams bRTCScreenShareParams) {
        if (bRTCScreenShareParams.mEnableAudioCapture != this.mScreenShareParams.mEnableAudioCapture) {
            if (bRTCScreenShareParams.mEnableAudioCapture) {
                this.mAudioCapturer.startCapture(this.mediaProjection, bRTCScreenShareParams.mAudioCaptureParams);
                log("updateScreenShareParams startAudioCapture ");
                return;
            }
            this.mAudioCapturer.stopCapture();
            log("updateScreenShareParams stopAudioCapture ");
        }
    }

    public synchronized void dispose() {
        this.isDisposed = true;
        try {
            if (Build.VERSION.SDK_INT >= 29 && this.mContext != null && this.mServiceBind) {
                this.mContext.unbindService(this.conn);
                this.mServiceBind = false;
                log("dispose -- unbindService");
            }
        } catch (Exception e) {
            log("dispose -- " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        Logging.m5305d(ScreenCapturerAndroid.class.getName(), str);
    }
}
