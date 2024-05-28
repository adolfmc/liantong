package com.baidu.cloud.mediaprocess.device;

import android.hardware.Camera;
import android.util.Log;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CameraCaptureDevice implements Camera.AutoFocusCallback {

    /* renamed from: a */
    public Camera f4555a;

    /* renamed from: b */
    public int f4556b;

    /* renamed from: c */
    public int f4557c;

    /* renamed from: d */
    public int f4558d;

    /* renamed from: e */
    public int f4559e;

    /* renamed from: g */
    public int f4561g;

    /* renamed from: j */
    public int f4564j;

    /* renamed from: f */
    public Camera.Size f4560f = null;

    /* renamed from: h */
    public volatile boolean f4562h = false;

    /* renamed from: i */
    public String f4563i = "auto";

    /* renamed from: k */
    public OutPort<VideoFrameBuffer> f4565k = new OutPortFactory().createOutPort();

    public CameraCaptureDevice(int i, int i2, int i3, int i4, int i5) {
        this.f4556b = 0;
        this.f4558d = 0;
        this.f4559e = 0;
        this.f4561g = 0;
        this.f4564j = 0;
        this.f4558d = i;
        this.f4559e = i2;
        this.f4561g = i3;
        this.f4556b = i4;
        this.f4564j = i5;
    }

    public boolean canSwitchCamera() {
        return Camera.getNumberOfCameras() > 1;
    }

    public void closeCamera() {
        if (this.f4555a != null) {
            stopCameraPreview();
            this.f4555a.release();
            this.f4555a = null;
            Log.d("CameraCaptureDevice", "releaseCamera -- done");
        }
    }

    public void doAutoFocus() {
        Camera camera = this.f4555a;
        if (camera == null) {
            return;
        }
        try {
            Camera.Parameters parameters = camera.getParameters();
            List<String> supportedFocusModes = parameters.getSupportedFocusModes();
            if (supportedFocusModes == null || !supportedFocusModes.contains("continuous-video")) {
                return;
            }
            parameters.setFocusMode("continuous-video");
            this.f4555a.setParameters(parameters);
        } catch (Exception unused) {
            Log.e("CameraCaptureDevice", "set auto continuous focus failed!");
        }
    }

    public void focusToPoint(int i, int i2, int i3, int i4) {
        if (this.f4555a == null || !this.f4562h) {
            return;
        }
        Camera.Parameters parameters = this.f4555a.getParameters();
        CameraUtils.chooseFocusPoint(parameters, this.f4563i, i, i2, i3, i4);
        this.f4555a.cancelAutoFocus();
        this.f4555a.setParameters(parameters);
        try {
            this.f4555a.autoFocus(this);
        } catch (Throwable unused) {
            Log.e("CameraCaptureDevice", "Touch to auto focus failed!");
        }
    }

    public int getAdaptedVideoHeight() {
        return this.f4559e;
    }

    public int getAdaptedVideoWidth() {
        return this.f4558d;
    }

    public Camera getCamera() {
        return this.f4555a;
    }

    public Camera.Size getCameraSize() {
        return this.f4560f;
    }

    public int getCurrentCameraId() {
        return this.f4556b;
    }

    public int getMaxZoomFactor() {
        Camera camera = this.f4555a;
        if (camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            if (parameters.isZoomSupported()) {
                return parameters.getMaxZoom();
            }
            return 0;
        }
        return -1;
    }

    public OutPort<VideoFrameBuffer> getVideoOutPort() {
        return this.f4565k;
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public void onAutoFocus(boolean z, Camera camera) {
        String str;
        String str2;
        if (z) {
            str = "CameraCaptureDevice";
            str2 = "Auto-Focus succeeded!";
        } else {
            str = "CameraCaptureDevice";
            str2 = "Auto-Focus failed!";
        }
        Log.d(str, str2);
    }

    public synchronized boolean openCamera(int i, int i2, int i3, int i4) {
        Camera.Size size;
        if (this.f4555a != null) {
            return true;
        }
        Log.i("CameraCaptureDevice", String.format("Calling openCamera with resolution [%dx%d].", Integer.valueOf(i), Integer.valueOf(i2)));
        this.f4561g = i3;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        int i5 = 0;
        while (true) {
            if (i5 >= numberOfCameras) {
                break;
            }
            Camera.getCameraInfo(i5, cameraInfo);
            if (cameraInfo.facing == i4) {
                this.f4555a = Camera.open(i5);
                break;
            }
            i5++;
        }
        if (this.f4555a == null) {
            return false;
        }
        this.f4556b = i4;
        StringBuilder sb = new StringBuilder();
        sb.append("Current camera Id was set to ");
        sb.append(i4);
        Log.i("CameraCaptureDevice", sb.toString());
        Camera.Parameters parameters = this.f4555a.getParameters();
        Camera.Size choosePreviewSize = i >= i2 ? CameraUtils.choosePreviewSize(parameters, i, i2) : CameraUtils.choosePreviewSize(parameters, i2, i);
        if (this.f4564j % 180 == 0) {
            Camera camera = this.f4555a;
            camera.getClass();
            size = new Camera.Size(camera, choosePreviewSize.width, choosePreviewSize.height);
        } else {
            Camera camera2 = this.f4555a;
            camera2.getClass();
            size = new Camera.Size(camera2, choosePreviewSize.height, choosePreviewSize.width);
        }
        this.f4560f = size;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("camera.size realSize=");
        sb2.append(choosePreviewSize.width);
        sb2.append(";");
        sb2.append(choosePreviewSize.height);
        Log.d("CameraCaptureDevice", sb2.toString());
        this.f4558d = i;
        this.f4559e = i2;
        this.f4555a.setDisplayOrientation(this.f4564j);
        this.f4557c = CameraUtils.chooseFixedPreviewFps(parameters, i3 * 1000);
        parameters.setRecordingHint(true);
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes != null && supportedFocusModes.contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
        this.f4555a.setParameters(parameters);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(choosePreviewSize.width);
        sb3.append("x");
        sb3.append(choosePreviewSize.height);
        sb3.append(" @");
        sb3.append(this.f4557c / 1000.0f);
        sb3.append("fps");
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append("Camera config: ");
        sb5.append(sb4);
        Log.i("CameraCaptureDevice", sb5.toString());
        return true;
    }

    public boolean setZoomFactor(int i) {
        Camera camera = this.f4555a;
        if (camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            if (parameters.isZoomSupported() && i <= parameters.getMaxZoom() && i >= 0) {
                try {
                    parameters.setZoom(i);
                    this.f4555a.setParameters(parameters);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
        return true;
    }

    public void startCameraPreview() {
        Camera camera;
        if (this.f4562h || !this.f4565k.isPortLinked() || (camera = this.f4555a) == null) {
            return;
        }
        this.f4565k.onConfigure(camera);
        this.f4555a.startPreview();
        this.f4562h = true;
    }

    public void stopCameraPreview() {
        if (this.f4562h && this.f4555a != null) {
            Log.d("CameraCaptureDevice", "stop camera preview");
            this.f4562h = false;
            this.f4555a.stopPreview();
        }
    }

    public void switchCamera(int i) {
        if (this.f4556b == i) {
            return;
        }
        closeCamera();
        openCamera(this.f4558d, this.f4559e, this.f4561g, i);
    }

    public void toggleFlash(boolean z) {
        if (this.f4555a == null || this.f4556b == 1) {
            return;
        }
        String str = z ? "torch" : "off";
        Camera.Parameters parameters = this.f4555a.getParameters();
        CameraUtils.chooseFlashMode(parameters, str);
        this.f4555a.setParameters(parameters);
    }
}
