package com.baidu.cloud.framecapture.camera;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.baidu.cloud.framecapture.CapturerObserver;
import com.baidu.cloud.framecapture.camera.CameraSession;
import com.baidu.cloud.framecapture.camera.CameraVideoCapturer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@TargetApi(21)
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Camera2Capturer extends CameraCapturer {
    @Nullable
    private final CameraManager cameraManager;
    private final Context context;

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

    public Camera2Capturer(Context context, String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        super(str, cameraEventsHandler, new Camera2Enumerator(context));
        this.context = context;
        this.cameraManager = (CameraManager) context.getSystemService("camera");
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraCapturer
    protected void createCameraSession(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, Context context, SurfaceTexture surfaceTexture, int i, String str, int i2, int i3, int i4) {
        Camera2Session.create(createSessionCallback, events, context, this.cameraManager, surfaceTexture, i, str, i2, i3, i4);
    }
}
