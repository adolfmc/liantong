package com.baidu.rtc.camera;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface CameraEventsHandler {
    void onCameraClosed();

    void onCameraDisconnected();

    void onCameraError(String str);

    void onCameraFreezed(String str);

    void onCameraOpening(String str, boolean z);

    void onCameraSwitchDone(boolean z);

    void onCameraSwitchError(String str);

    void onFirstFrameAvailable();
}
