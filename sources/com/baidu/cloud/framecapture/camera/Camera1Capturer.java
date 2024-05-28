package com.baidu.cloud.framecapture.camera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import com.baidu.cloud.framecapture.CapturerObserver;
import com.baidu.cloud.framecapture.camera.CameraSession;
import com.baidu.cloud.framecapture.camera.CameraVideoCapturer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Camera1Capturer extends CameraCapturer {
    private final boolean captureToTexture;

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer, com.baidu.cloud.framecapture.camera.CameraVideoCapturer
    public /* bridge */ /* synthetic */ void changeCaptureFormat(int i, int i2, int i3) {
        super.changeCaptureFormat(i, i2, i3);
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer, com.baidu.cloud.framecapture.camera.CameraVideoCapturer
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer, com.baidu.cloud.framecapture.camera.CameraVideoCapturer
    public /* bridge */ /* synthetic */ void initialize(SurfaceTexture surfaceTexture, int i, Handler handler, Context context, CapturerObserver capturerObserver) {
        super.initialize(surfaceTexture, i, handler, context, capturerObserver);
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer, com.baidu.cloud.framecapture.camera.CameraVideoCapturer
    public /* bridge */ /* synthetic */ boolean isFrontCamera(int i) {
        return super.isFrontCamera(i);
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer, android.graphics.SurfaceTexture.OnFrameAvailableListener
    public /* bridge */ /* synthetic */ void onFrameAvailable(SurfaceTexture surfaceTexture) {
        super.onFrameAvailable(surfaceTexture);
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer
    public /* bridge */ /* synthetic */ void printStackTrace() {
        super.printStackTrace();
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer, com.baidu.cloud.framecapture.camera.CameraVideoCapturer
    public /* bridge */ /* synthetic */ void startCapture(int i, int i2, int i3) {
        super.startCapture(i, i2, i3);
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer, com.baidu.cloud.framecapture.camera.CameraVideoCapturer
    public /* bridge */ /* synthetic */ void stopCapture() {
        super.stopCapture();
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer, com.baidu.cloud.framecapture.camera.CameraVideoCapturer
    public /* bridge */ /* synthetic */ void switchCamera() {
        super.switchCamera();
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer, com.baidu.cloud.framecapture.camera.CameraVideoCapturer
    public /* bridge */ /* synthetic */ void switchCamera(String str) {
        super.switchCamera(str);
    }

    public Camera1Capturer(String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler, boolean z) {
        super(str, cameraEventsHandler, new Camera1Enumerator(z));
        this.captureToTexture = z;
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer
    protected void createCameraSession(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, Context context, SurfaceTexture surfaceTexture, int i, String str, int i2, int i3, int i4) {
        Camera1Session.create(createSessionCallback, events, this.captureToTexture, context, surfaceTexture, i, Camera1Enumerator.getCameraIndex(str), i2, i3, i4);
    }
}
