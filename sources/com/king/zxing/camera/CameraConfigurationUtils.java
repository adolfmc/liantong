package com.king.zxing.camera;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class CameraConfigurationUtils {
    private static final int AREA_PER_1000 = 400;
    private static final double MAX_ASPECT_DISTORTION = 0.05d;
    private static final float MAX_EXPOSURE_COMPENSATION = 1.5f;
    private static final int MAX_FPS = 20;
    private static final float MIN_EXPOSURE_COMPENSATION = 0.0f;
    private static final int MIN_FPS = 10;
    private static final int MIN_PREVIEW_PIXELS = 153600;
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final String TAG = "CameraConfiguration";

    private CameraConfigurationUtils() {
    }

    public static void setFocus(Camera.Parameters parameters, boolean z, boolean z2, boolean z3) {
        String str;
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (!z) {
            str = null;
        } else if (z3 || z2) {
            str = findSettableValue("focus mode", supportedFocusModes, "auto");
        } else {
            str = findSettableValue("focus mode", supportedFocusModes, "continuous-picture", "continuous-video", "auto");
        }
        if (!z3 && str == null) {
            str = findSettableValue("focus mode", supportedFocusModes, "macro", "edof");
        }
        if (str != null) {
            if (str.equals(parameters.getFocusMode())) {
                Log.i("CameraConfiguration", "Focus mode already set to " + str);
                return;
            }
            parameters.setFocusMode(str);
        }
    }

    public static void setTorch(Camera.Parameters parameters, boolean z) {
        String findSettableValue;
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (z) {
            findSettableValue = findSettableValue("flash mode", supportedFlashModes, "torch", "on");
        } else {
            findSettableValue = findSettableValue("flash mode", supportedFlashModes, "off");
        }
        if (findSettableValue != null) {
            if (findSettableValue.equals(parameters.getFlashMode())) {
                Log.i("CameraConfiguration", "Flash mode already set to " + findSettableValue);
                return;
            }
            Log.i("CameraConfiguration", "Setting flash mode to " + findSettableValue);
            parameters.setFlashMode(findSettableValue);
        }
    }

    public static void setBestExposure(Camera.Parameters parameters, boolean z) {
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        if (minExposureCompensation != 0 || maxExposureCompensation != 0) {
            if (exposureCompensationStep > 0.0f) {
                int round = Math.round((z ? 0.0f : 1.5f) / exposureCompensationStep);
                float f = exposureCompensationStep * round;
                int max = Math.max(Math.min(round, maxExposureCompensation), minExposureCompensation);
                if (parameters.getExposureCompensation() == max) {
                    Log.i("CameraConfiguration", "Exposure compensation already set to " + max + " / " + f);
                    return;
                }
                Log.i("CameraConfiguration", "Setting exposure compensation to " + max + " / " + f);
                parameters.setExposureCompensation(max);
                return;
            }
        }
        Log.i("CameraConfiguration", "Camera does not support exposure compensation");
    }

    public static void setBestPreviewFPS(Camera.Parameters parameters) {
        setBestPreviewFPS(parameters, 10, 20);
    }

    public static void setBestPreviewFPS(Camera.Parameters parameters, int i, int i2) {
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        Log.i("CameraConfiguration", "Supported FPS ranges: " + toString((Collection<int[]>) supportedPreviewFpsRange));
        if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.isEmpty()) {
            return;
        }
        int[] iArr = null;
        Iterator<int[]> it = supportedPreviewFpsRange.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            int[] next = it.next();
            int i3 = next[0];
            int i4 = next[1];
            if (i3 >= i * 1000 && i4 <= i2 * 1000) {
                iArr = next;
                break;
            }
        }
        if (iArr == null) {
            Log.i("CameraConfiguration", "No suitable FPS range?");
            return;
        }
        int[] iArr2 = new int[2];
        parameters.getPreviewFpsRange(iArr2);
        if (Arrays.equals(iArr2, iArr)) {
            Log.i("CameraConfiguration", "FPS range already set to " + Arrays.toString(iArr));
            return;
        }
        Log.i("CameraConfiguration", "Setting FPS range to " + Arrays.toString(iArr));
        parameters.setPreviewFpsRange(iArr[0], iArr[1]);
    }

    public static void setFocusArea(Camera.Parameters parameters) {
        if (parameters.getMaxNumFocusAreas() > 0) {
            Log.i("CameraConfiguration", "Old focus areas: " + toString((Iterable<Camera.Area>) parameters.getFocusAreas()));
            List<Camera.Area> buildMiddleArea = buildMiddleArea(400);
            Log.i("CameraConfiguration", "Setting focus area to : " + toString((Iterable<Camera.Area>) buildMiddleArea));
            parameters.setFocusAreas(buildMiddleArea);
            return;
        }
        Log.i("CameraConfiguration", "Device does not support focus areas");
    }

    public static void setMetering(Camera.Parameters parameters) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            Log.i("CameraConfiguration", "Old metering areas: " + parameters.getMeteringAreas());
            List<Camera.Area> buildMiddleArea = buildMiddleArea(400);
            Log.i("CameraConfiguration", "Setting metering area to : " + toString((Iterable<Camera.Area>) buildMiddleArea));
            parameters.setMeteringAreas(buildMiddleArea);
            return;
        }
        Log.i("CameraConfiguration", "Device does not support metering areas");
    }

    private static List<Camera.Area> buildMiddleArea(int i) {
        int i2 = -i;
        return Collections.singletonList(new Camera.Area(new Rect(i2, i2, i, i), 1));
    }

    public static void setVideoStabilization(Camera.Parameters parameters) {
        if (parameters.isVideoStabilizationSupported()) {
            if (parameters.getVideoStabilization()) {
                Log.i("CameraConfiguration", "Video stabilization already enabled");
                return;
            }
            Log.i("CameraConfiguration", "Enabling video stabilization...");
            parameters.setVideoStabilization(true);
            return;
        }
        Log.i("CameraConfiguration", "This device does not support video stabilization");
    }

    public static void setBarcodeSceneMode(Camera.Parameters parameters) {
        if ("barcode".equals(parameters.getSceneMode())) {
            Log.i("CameraConfiguration", "Barcode scene mode already set");
            return;
        }
        String findSettableValue = findSettableValue("scene mode", parameters.getSupportedSceneModes(), "barcode");
        if (findSettableValue != null) {
            parameters.setSceneMode(findSettableValue);
        }
    }

    public static void setZoom(Camera.Parameters parameters, double d) {
        if (parameters.isZoomSupported()) {
            Integer indexOfClosestZoom = indexOfClosestZoom(parameters, d);
            if (indexOfClosestZoom == null) {
                return;
            }
            if (parameters.getZoom() == indexOfClosestZoom.intValue()) {
                Log.i("CameraConfiguration", "Zoom is already set to " + indexOfClosestZoom);
                return;
            }
            Log.i("CameraConfiguration", "Setting zoom to " + indexOfClosestZoom);
            parameters.setZoom(indexOfClosestZoom.intValue());
            return;
        }
        Log.i("CameraConfiguration", "Zoom is not supported");
    }

    private static Integer indexOfClosestZoom(Camera.Parameters parameters, double d) {
        List<Integer> zoomRatios = parameters.getZoomRatios();
        Log.i("CameraConfiguration", "Zoom ratios: " + zoomRatios);
        int maxZoom = parameters.getMaxZoom();
        if (zoomRatios == null || zoomRatios.isEmpty() || zoomRatios.size() != maxZoom + 1) {
            Log.w("CameraConfiguration", "Invalid zoom ratios!");
            return null;
        }
        double d2 = d * 100.0d;
        double d3 = Double.POSITIVE_INFINITY;
        int i = 0;
        for (int i2 = 0; i2 < zoomRatios.size(); i2++) {
            double abs = Math.abs(zoomRatios.get(i2).intValue() - d2);
            if (abs < d3) {
                i = i2;
                d3 = abs;
            }
        }
        Log.i("CameraConfiguration", "Chose zoom ratio of " + (zoomRatios.get(i).intValue() / 100.0d));
        return Integer.valueOf(i);
    }

    public static void setInvertColor(Camera.Parameters parameters) {
        if ("negative".equals(parameters.getColorEffect())) {
            Log.i("CameraConfiguration", "Negative effect already set");
            return;
        }
        String findSettableValue = findSettableValue("color effect", parameters.getSupportedColorEffects(), "negative");
        if (findSettableValue != null) {
            parameters.setColorEffect(findSettableValue);
        }
    }

    public static Point findBestPreviewSizeValue(Camera.Parameters parameters, Point point) {
        double d;
        int i;
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Log.w("CameraConfiguration", "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            if (previewSize == null) {
                throw new IllegalStateException("Parameters contained no preview size!");
            }
            return new Point(previewSize.width, previewSize.height);
        }
        if (Log.isLoggable("CameraConfiguration", 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size : supportedPreviewSizes) {
                sb.append(size.width);
                sb.append('x');
                sb.append(size.height);
                sb.append(' ');
            }
            Log.i("CameraConfiguration", "Supported preview sizes: " + ((Object) sb));
        }
        if (point.x < point.y) {
            d = point.x / point.y;
        } else {
            d = point.y / point.x;
        }
        Log.i("CameraConfiguration", "screenAspectRatio: " + d);
        Camera.Size size2 = null;
        char c = 0;
        int i2 = 0;
        for (Camera.Size size3 : supportedPreviewSizes) {
            int i3 = size3.width;
            int i4 = size3.height;
            int i5 = i3 * i4;
            if (i5 < 153600) {
                i = i2;
            } else {
                char c2 = i3 < i4 ? (char) 1 : c;
                int i6 = c2 != 0 ? i3 : i4;
                int i7 = c2 != 0 ? i4 : i3;
                Object[] objArr = new Object[2];
                objArr[c] = Integer.valueOf(i6);
                objArr[1] = Integer.valueOf(i7);
                Log.i("CameraConfiguration", String.format("maybeFlipped:%d * %d", objArr));
                i = i2;
                double d2 = i6 / i7;
                Log.i("CameraConfiguration", "aspectRatio: " + d2);
                double abs = Math.abs(d2 - d);
                Log.i("CameraConfiguration", "distortion: " + abs);
                if (abs <= 0.05d) {
                    if (i6 == point.x && i7 == point.y) {
                        Point point2 = new Point(i3, i4);
                        Log.i("CameraConfiguration", "Found preview size exactly matching screen size: " + point2);
                        return point2;
                    }
                    if (i5 > i) {
                        size2 = size3;
                        i2 = i5;
                    } else {
                        i2 = i;
                    }
                    c = 0;
                }
            }
            i2 = i;
            c = 0;
        }
        if (size2 != null) {
            Point point3 = new Point(size2.width, size2.height);
            Log.i("CameraConfiguration", "Using largest suitable preview size: " + point3);
            return point3;
        }
        Camera.Size previewSize2 = parameters.getPreviewSize();
        if (previewSize2 == null) {
            throw new IllegalStateException("Parameters contained no preview size!");
        }
        Point point4 = new Point(previewSize2.width, previewSize2.height);
        Log.i("CameraConfiguration", "No suitable preview sizes, using default: " + point4);
        return point4;
    }

    private static String findSettableValue(String str, Collection<String> collection, String... strArr) {
        Log.i("CameraConfiguration", "Requesting " + str + " value from among: " + Arrays.toString(strArr));
        Log.i("CameraConfiguration", "Supported " + str + " values: " + collection);
        if (collection != null) {
            for (String str2 : strArr) {
                if (collection.contains(str2)) {
                    Log.i("CameraConfiguration", "Can set " + str + " to: " + str2);
                    return str2;
                }
            }
        }
        Log.i("CameraConfiguration", "No supported values match");
        return null;
    }

    private static String toString(Collection<int[]> collection) {
        if (collection == null || collection.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator<int[]> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(Arrays.toString(it.next()));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private static String toString(Iterable<Camera.Area> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Camera.Area area : iterable) {
            sb.append(area.rect);
            sb.append(':');
            sb.append(area.weight);
            sb.append(' ');
        }
        return sb.toString();
    }

    public static String collectStats(Camera.Parameters parameters) {
        return collectStats(parameters.flatten());
    }

    public static String collectStats(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("BOARD=");
        sb.append(Build.BOARD);
        sb.append('\n');
        sb.append("BRAND=");
        sb.append(Build.BRAND);
        sb.append('\n');
        sb.append("CPU_ABI=");
        sb.append(Build.CPU_ABI);
        sb.append('\n');
        sb.append("DEVICE=");
        sb.append(Build.DEVICE);
        sb.append('\n');
        sb.append("DISPLAY=");
        sb.append(Build.DISPLAY);
        sb.append('\n');
        sb.append("FINGERPRINT=");
        sb.append(Build.FINGERPRINT);
        sb.append('\n');
        sb.append("HOST=");
        sb.append(Build.HOST);
        sb.append('\n');
        sb.append("ID=");
        sb.append(Build.ID);
        sb.append('\n');
        sb.append("MANUFACTURER=");
        sb.append(Build.MANUFACTURER);
        sb.append('\n');
        sb.append("MODEL=");
        sb.append(Build.MODEL);
        sb.append('\n');
        sb.append("PRODUCT=");
        sb.append(Build.PRODUCT);
        sb.append('\n');
        sb.append("TAGS=");
        sb.append(Build.TAGS);
        sb.append('\n');
        sb.append("TIME=");
        sb.append(Build.TIME);
        sb.append('\n');
        sb.append("TYPE=");
        sb.append(Build.TYPE);
        sb.append('\n');
        sb.append("USER=");
        sb.append(Build.USER);
        sb.append('\n');
        sb.append("VERSION.CODENAME=");
        sb.append(Build.VERSION.CODENAME);
        sb.append('\n');
        sb.append("VERSION.INCREMENTAL=");
        sb.append(Build.VERSION.INCREMENTAL);
        sb.append('\n');
        sb.append("VERSION.RELEASE=");
        sb.append(Build.VERSION.RELEASE);
        sb.append('\n');
        sb.append("VERSION.SDK_INT=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append('\n');
        if (charSequence != null) {
            String[] split = SEMICOLON.split(charSequence);
            Arrays.sort(split);
            for (String str : split) {
                sb.append(str);
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
