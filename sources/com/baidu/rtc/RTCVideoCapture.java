package com.baidu.rtc;

import android.content.Context;
import com.webrtc.CapturerObserver;
import com.webrtc.SurfaceTextureHelper;
import com.webrtc.VideoCapturer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class RTCVideoCapture implements VideoCapturer {
    private CapturerObserver innnerCapturerObserver = null;
    private RTCCapturerObserver outterCapturerObserver = new RTCCapturerObserver() { // from class: com.baidu.rtc.RTCVideoCapture.1
        @Override // com.baidu.rtc.RTCVideoCapture.RTCCapturerObserver
        public void onCapturerStarted(boolean z) {
            RTCVideoCapture.this.innnerCapturerObserver.onCapturerStarted(z);
        }

        @Override // com.baidu.rtc.RTCVideoCapture.RTCCapturerObserver
        public void onCapturerStopped() {
            RTCVideoCapture.this.innnerCapturerObserver.onCapturerStopped();
        }

        @Override // com.baidu.rtc.RTCVideoCapture.RTCCapturerObserver
        public void onFrameCaptured(RTCVideoFrame rTCVideoFrame) {
            RTCVideoCapture.this.innnerCapturerObserver.onFrameCaptured(rTCVideoFrame);
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface RTCCapturerObserver {
        void onCapturerStarted(boolean z);

        void onCapturerStopped();

        void onFrameCaptured(RTCVideoFrame rTCVideoFrame);
    }

    @Override // com.webrtc.VideoCapturer
    public void changeCaptureFormat(int i, int i2, int i3) {
    }

    @Override // com.webrtc.VideoCapturer
    public void dispose() {
    }

    public abstract void initialize(RTCCapturerObserver rTCCapturerObserver);

    @Override // com.webrtc.VideoCapturer
    public boolean isScreencast() {
        return false;
    }

    @Override // com.webrtc.VideoCapturer
    public void startCapture(int i, int i2, int i3) {
    }

    @Override // com.webrtc.VideoCapturer
    public void stopCapture() {
    }

    @Override // com.webrtc.VideoCapturer
    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
        this.innnerCapturerObserver = capturerObserver;
        initialize(this.outterCapturerObserver);
    }
}
