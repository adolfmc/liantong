package com.baidu.rtc;

import android.os.SystemClock;
import com.baidu.rtc.RTCVideoCapture;
import com.webrtc.Logging;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class myOuterRTCVideoCapture extends RTCVideoCapture {
    RTCVideoCapture.RTCCapturerObserver capturerObserver = null;
    private final int frameWidth = 480;
    private final int frameHeight = 640;
    private final Timer timer = new Timer();
    private final TimerTask tickTask = new TimerTask() { // from class: com.baidu.rtc.myOuterRTCVideoCapture.1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            myOuterRTCVideoCapture.this.tick();
        }
    };

    @Override // com.baidu.rtc.RTCVideoCapture, com.webrtc.VideoCapturer
    public void changeCaptureFormat(int i, int i2, int i3) {
    }

    @Override // com.baidu.rtc.RTCVideoCapture, com.webrtc.VideoCapturer
    public void dispose() {
    }

    @Override // com.baidu.rtc.RTCVideoCapture, com.webrtc.VideoCapturer
    public boolean isScreencast() {
        return false;
    }

    @Override // com.baidu.rtc.RTCVideoCapture
    public void initialize(RTCVideoCapture.RTCCapturerObserver rTCCapturerObserver) {
        this.capturerObserver = rTCCapturerObserver;
    }

    public void tick() {
        RTCVideoFrame nextFrame = getNextFrame();
        this.capturerObserver.onFrameCaptured(nextFrame);
        nextFrame.release();
    }

    @Override // com.baidu.rtc.RTCVideoCapture, com.webrtc.VideoCapturer
    public void startCapture(int i, int i2, int i3) {
        Logging.m5304e("selfVideoCapture", "startCapture.");
        this.timer.schedule(this.tickTask, 0L, 66L);
    }

    @Override // com.baidu.rtc.RTCVideoCapture, com.webrtc.VideoCapturer
    public void stopCapture() {
        this.timer.cancel();
    }

    public RTCVideoFrame getNextFrame() {
        long nanos = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
        RTCI420Buffer allocate = RTCI420Buffer.allocate(480, 640);
        allocate.getDataY();
        allocate.getDataU();
        allocate.getDataV();
        allocate.getStrideY();
        allocate.getStrideU();
        allocate.getStrideV();
        return new RTCVideoFrame(allocate, 0, nanos);
    }
}
