package com.baidu.cloud.mediaprocess.device;

import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CameraUtils {
    /* renamed from: a */
    public static Rect m19970a(float f, float f2, int i, int i2, float f3) {
        int intValue = Float.valueOf(f3 * 200.0f).intValue();
        int i3 = (int) (((f2 / i2) * 2000.0f) - 1000.0f);
        int i4 = intValue / 2;
        int i5 = ((int) (((f / i) * 2000.0f) - 1000.0f)) - i4;
        if (i5 > 1000) {
            i5 = 1000;
        } else if (i5 < -1000) {
            i5 = -1000;
        }
        int i6 = i3 - i4;
        if (i6 > 1000) {
            i6 = 1000;
        } else if (i6 < -1000) {
            i6 = -1000;
        }
        float f4 = i5;
        if (f4 < -1000.0f) {
            f4 = -1000.0f;
        }
        float f5 = i6;
        float f6 = f5 >= -1000.0f ? f5 : -1000.0f;
        float f7 = i5 + intValue;
        if (f7 > 1000.0f) {
            f7 = 1000.0f;
        }
        float f8 = i6 + intValue;
        if (f8 > 1000.0f) {
            f8 = 1000.0f;
        }
        RectF rectF = new RectF(f4, f6, f7, f8);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public static int chooseFixedPreviewFps(Camera.Parameters parameters, int i) {
        int[] iArr = new int[2];
        int i2 = Integer.MAX_VALUE;
        for (int[] iArr2 : parameters.getSupportedPreviewFpsRange()) {
            Log.d("CameraUtils", "entry: " + iArr2[0] + " - " + iArr2[1]);
            int min = Math.min(Math.abs(i - iArr2[0]), Math.abs(i - iArr2[1]));
            if (min < i2) {
                iArr = iArr2;
                i2 = min;
            }
        }
        parameters.setPreviewFpsRange(iArr[0], iArr[1]);
        return i;
    }

    public static void chooseFlashMode(Camera.Parameters parameters, String str) {
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes == null || !supportedFlashModes.contains(str)) {
            return;
        }
        parameters.setFlashMode(str);
    }

    public static void chooseFocusMode(Camera.Parameters parameters, String str) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes == null || !supportedFocusModes.contains(str)) {
            return;
        }
        parameters.setFocusMode(str);
    }

    public static void chooseFocusPoint(Camera.Parameters parameters, String str, int i, int i2, int i3, int i4) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes == null || !supportedFocusModes.contains(str)) {
            return;
        }
        parameters.setFocusMode(str);
        float f = i;
        float f2 = i2;
        Rect m19970a = m19970a(f, f2, i3, i4, 1.0f);
        Rect m19970a2 = m19970a(f, f2, i3, i4, 1.5f);
        if (parameters.getMaxNumFocusAreas() > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Camera.Area(m19970a, 600));
            parameters.setFocusAreas(arrayList);
        }
        if (parameters.getMaxNumMeteringAreas() > 0) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new Camera.Area(m19970a2, 600));
            parameters.setMeteringAreas(arrayList2);
        }
    }

    public static Camera.Size choosePreviewSize(Camera.Parameters parameters, int i, int i2) {
        Camera.Size size = null;
        if (parameters.getSupportedPreviewSizes() != null) {
            float f = i / i2;
            int i3 = i * i2;
            float f2 = 0.0f;
            int i4 = 0;
            for (Camera.Size size2 : parameters.getSupportedPreviewSizes()) {
                Log.d("CameraUtils", "supported preview size=" + size2.width + "x" + size2.height);
                int i5 = size2.width;
                int i6 = size2.height;
                float f3 = ((float) i5) / ((float) i6);
                int i7 = i5 * i6;
                if (size != null) {
                    if (((Math.abs(i4 - i3) * 1.0f) / i3) + (Math.abs(f2 - f) / f) >= ((Math.abs(i7 - i3) * 1.0f) / f) + (Math.abs(f3 - f) / f)) {
                    }
                }
                Log.i("CameraUtils", "selected camera size via ratio: " + size2.width + "x" + size2.height);
                size = size2;
                i4 = i7;
                f2 = f3;
            }
        }
        if (size == null && (size = parameters.getPreferredPreviewSizeForVideo()) != null) {
            Log.i("CameraUtils", "Camera preferred preview size for video is " + size.width + "x" + size.height);
        }
        if (size != null) {
            parameters.setPreviewSize(size.width, size.height);
        }
        return size;
    }

    public static void chooseSceneMode(Camera.Parameters parameters, String str) {
        List<String> supportedSceneModes = parameters.getSupportedSceneModes();
        if (supportedSceneModes == null || !supportedSceneModes.contains(str)) {
            return;
        }
        parameters.setSceneMode(str);
    }

    public static void chooseWhiteBalance(Camera.Parameters parameters, String str) {
        List<String> supportedWhiteBalance = parameters.getSupportedWhiteBalance();
        if (supportedWhiteBalance == null || !supportedWhiteBalance.contains(str)) {
            return;
        }
        parameters.setWhiteBalance(str);
    }

    public static int getCameraRotation(int i, int i2) {
        try {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i2 == 0 ? 0 : 1, cameraInfo);
            int i3 = cameraInfo.orientation;
            int i4 = (cameraInfo.facing == 1 ? 360 - ((i3 + i) % 360) : (i3 - i) + 360) % 360;
            StringBuilder sb = new StringBuilder();
            sb.append("getCameraRotation previewRotation=");
            sb.append(i4);
            Log.d("CameraUtils", sb.toString());
            return i4;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
