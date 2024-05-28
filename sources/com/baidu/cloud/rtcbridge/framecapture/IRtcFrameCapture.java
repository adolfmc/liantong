package com.baidu.cloud.rtcbridge.framecapture;

import android.content.Context;
import com.baidu.rtc.camera.CameraEventCallback;
import com.webrtc.CapturerObserver;
import com.webrtc.VideoCapturer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IRtcFrameCapture {
    VideoCapturer createCameraCapturer(Context context, int i, CameraEventCallback cameraEventCallback);

    boolean isFrontCamera(int i);

    void registerCapturerObserver(CapturerObserver capturerObserver);

    void unregisterCapturerObserver(CapturerObserver capturerObserver);

    void unregisterCapturerObserverAll();
}
