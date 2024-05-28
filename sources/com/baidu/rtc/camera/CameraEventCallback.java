package com.baidu.rtc.camera;

import com.baidu.rtc.logreport.RtcLogCapturer;
import com.baidu.rtc.logreport.RtcLogEvent;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CameraEventCallback implements CameraEventsHandler {
    public CameraEventsHandlerAdapter mHandler;

    public void setCameraEventsHandler(CameraEventsHandlerAdapter cameraEventsHandlerAdapter) {
        this.mHandler = cameraEventsHandlerAdapter;
    }

    @Override // com.baidu.rtc.camera.CameraEventsHandler
    public void onCameraError(String str) {
        CameraEventsHandlerAdapter cameraEventsHandlerAdapter = this.mHandler;
        if (cameraEventsHandlerAdapter != null) {
            cameraEventsHandlerAdapter.onCameraError(str);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.OPEN_CAMERA_FAILED, "CameraEventCallback-onCameraError", -1, RtcLogCapturer.getErrorInfo(str));
    }

    @Override // com.baidu.rtc.camera.CameraEventsHandler
    public void onCameraDisconnected() {
        CameraEventsHandlerAdapter cameraEventsHandlerAdapter = this.mHandler;
        if (cameraEventsHandlerAdapter != null) {
            cameraEventsHandlerAdapter.onCameraDisconnected();
        }
        RtcLogCapturer.reportLog(RtcLogEvent.CAMERA_CAPTURE_ERROR, "CameraEventCallback-onCameraDisconnected", -2, "camera disconnected");
    }

    @Override // com.baidu.rtc.camera.CameraEventsHandler
    public void onCameraFreezed(String str) {
        CameraEventsHandlerAdapter cameraEventsHandlerAdapter = this.mHandler;
        if (cameraEventsHandlerAdapter != null) {
            cameraEventsHandlerAdapter.onCameraFreezed(str);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.CAMERA_CAPTURE_ERROR, "CameraEventCallback-onCameraFreezed", -4, RtcLogCapturer.getErrorInfo(str));
    }

    @Override // com.baidu.rtc.camera.CameraEventsHandler
    public void onCameraOpening(String str, boolean z) {
        CameraEventsHandlerAdapter cameraEventsHandlerAdapter = this.mHandler;
        if (cameraEventsHandlerAdapter != null) {
            cameraEventsHandlerAdapter.onCameraOpening(str, z);
        }
    }

    @Override // com.baidu.rtc.camera.CameraEventsHandler
    public void onFirstFrameAvailable() {
        CameraEventsHandlerAdapter cameraEventsHandlerAdapter = this.mHandler;
        if (cameraEventsHandlerAdapter != null) {
            cameraEventsHandlerAdapter.onFirstFrameAvailable();
        }
        RtcLogCapturer.reportLog(RtcLogEvent.OPEN_CAMERA_SUCCESS, "CameraEventCallback-onFirstFrameAvailable");
    }

    @Override // com.baidu.rtc.camera.CameraEventsHandler
    public void onCameraClosed() {
        CameraEventsHandlerAdapter cameraEventsHandlerAdapter = this.mHandler;
        if (cameraEventsHandlerAdapter != null) {
            cameraEventsHandlerAdapter.onCameraClosed();
        }
        RtcLogCapturer.reportLog(RtcLogEvent.CAMERA_CAPTURE_ERROR, "CameraEventCallback-onCameraClosed", -3, "camera closed");
    }

    @Override // com.baidu.rtc.camera.CameraEventsHandler
    public void onCameraSwitchDone(boolean z) {
        CameraEventsHandlerAdapter cameraEventsHandlerAdapter = this.mHandler;
        if (cameraEventsHandlerAdapter != null) {
            cameraEventsHandlerAdapter.onCameraSwitchDone(z);
        }
    }

    @Override // com.baidu.rtc.camera.CameraEventsHandler
    public void onCameraSwitchError(String str) {
        CameraEventsHandlerAdapter cameraEventsHandlerAdapter = this.mHandler;
        if (cameraEventsHandlerAdapter != null) {
            cameraEventsHandlerAdapter.onCameraSwitchError(str);
        }
    }
}
