package com.baidu.cloud.framecapture;

import android.content.Context;
import android.os.Build;
import com.baidu.cloud.framecapture.camera.Camera1Enumerator;
import com.baidu.cloud.framecapture.camera.Camera2Enumerator;
import com.baidu.cloud.framecapture.camera.CameraEnumerator;
import com.baidu.cloud.framecapture.camera.CameraVideoCapturer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FrameCaptureManager {
    private static final String TAG = "com.baidu.cloud.framecapture.FrameCaptureManager";

    private boolean useCamera2(Context context) {
        if (Build.MODEL.contains("LIO-AL00")) {
            return false;
        }
        return Camera2Enumerator.isSupported(context);
    }

    private boolean captureToTexture() {
        return !Build.MODEL.contains("SABRESD-MX6DQ");
    }

    public CameraVideoCapturer createCameraCapture(Context context, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        return createCameraCapture(context, -1, cameraEventsHandler);
    }

    public CameraVideoCapturer createCameraCapture(Context context, int i, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        CameraEnumerator camera1Enumerator;
        if (useCamera2(context)) {
            Logging.m20092d(TAG, "Creating capturer using camera2 API.");
            camera1Enumerator = new Camera2Enumerator(context);
        } else {
            Logging.m20092d(TAG, "Creating capturer using camera1 API.");
            camera1Enumerator = new Camera1Enumerator(captureToTexture());
        }
        return createCameraCaptureInternal(camera1Enumerator, i, cameraEventsHandler);
    }

    private CameraVideoCapturer createCameraCaptureInternal(CameraEnumerator cameraEnumerator, int i, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        CameraVideoCapturer createCapturer;
        String[] deviceNames = cameraEnumerator.getDeviceNames();
        if (i <= 0 || i >= deviceNames.length || (createCapturer = cameraEnumerator.createCapturer(deviceNames[i], cameraEventsHandler)) == null) {
            Logging.m20092d(TAG, "Looking for front facing cameras.");
            for (String str : deviceNames) {
                if (cameraEnumerator.isFrontFacing(str)) {
                    Logging.m20092d(TAG, "Creating front facing camera capturer.");
                    CameraVideoCapturer createCapturer2 = cameraEnumerator.createCapturer(str, cameraEventsHandler);
                    if (createCapturer2 != null) {
                        return createCapturer2;
                    }
                }
            }
            Logging.m20092d(TAG, "Looking for other cameras.");
            for (String str2 : deviceNames) {
                if (!cameraEnumerator.isFrontFacing(str2)) {
                    Logging.m20092d(TAG, "Creating other camera capturer.");
                    CameraVideoCapturer createCapturer3 = cameraEnumerator.createCapturer(str2, cameraEventsHandler);
                    if (createCapturer3 != null) {
                        return createCapturer3;
                    }
                }
            }
            return null;
        }
        return createCapturer;
    }
}
