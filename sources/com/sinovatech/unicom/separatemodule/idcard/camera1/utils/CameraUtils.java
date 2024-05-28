package com.sinovatech.unicom.separatemodule.idcard.camera1.utils;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.media.AudioManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CameraUtils {
    private static final double MAX_ASPECT_DISTORTION = 0.15d;
    private static final int MAX_FPS = 20;
    private static final int MIN_FPS = 10;
    private static final int MIN_PREVIEW_PIXELS = 153600;
    private static final String TAG = "CameraUtils";

    private CameraUtils() {
    }

    public static Camera openFrontFacingCameraGingerbread() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        PrintStream printStream = System.out;
        printStream.println("cameraCount = " + numberOfCameras);
        Camera camera = null;
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 1) {
                try {
                    camera = Camera.open(i);
                } catch (RuntimeException e) {
                    Log.e("", "Camera failed to open: " + e.getLocalizedMessage());
                }
            }
        }
        return camera;
    }

    public static void setCameraSound(final boolean z, final Context context) {
        new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.utils.CameraUtils.1
            @Override // java.lang.Runnable
            public void run() {
                ((AudioManager) context.getSystemService("audio")).setStreamMute(1, z);
            }
        }).start();
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
        if (str == null || str.equals(parameters.getFocusMode())) {
            return;
        }
        parameters.setFocusMode(str);
    }

    public static void setBestPreviewFPS(Camera.Parameters parameters) {
        setBestPreviewFPS(parameters, 10, 20);
    }

    public static void setBestPreviewFPS(Camera.Parameters parameters, int i, int i2) {
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
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
            return;
        }
        int[] iArr2 = new int[2];
        parameters.getPreviewFpsRange(iArr2);
        if (Arrays.equals(iArr2, iArr)) {
            return;
        }
        parameters.setPreviewFpsRange(iArr[0], iArr[1]);
    }

    public static void setBarcodeSceneMode(Camera.Parameters parameters, String... strArr) {
        String findSettableValue = findSettableValue("scene mode", parameters.getSupportedSceneModes(), strArr);
        if (findSettableValue != null) {
            parameters.setSceneMode(findSettableValue);
        }
    }

    public static Point findBestPreviewSizeValue(Camera.Parameters parameters, Point point) {
        double d;
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Log.w("CameraUtils", "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            if (previewSize == null) {
                throw new IllegalStateException("Parameters contained no preview size!");
            }
            return new Point(previewSize.width, previewSize.height);
        }
        ArrayList<Camera.Size> arrayList = new ArrayList(supportedPreviewSizes);
        Collections.sort(arrayList, new Comparator<Camera.Size>() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.utils.CameraUtils.2
            @Override // java.util.Comparator
            public int compare(Camera.Size size, Camera.Size size2) {
                int i = size.height * size.width;
                int i2 = size2.height * size2.width;
                if (i2 < i) {
                    return -1;
                }
                return i2 > i ? 1 : 0;
            }
        });
        if (Log.isLoggable("CameraUtils", 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size : arrayList) {
                sb.append(size.width);
                sb.append('x');
                sb.append(size.height);
                sb.append(' ');
            }
            Log.i("CameraUtils", "Supported preview sizes: " + ((Object) sb));
        }
        if (point.x > point.y) {
            d = point.x / point.y;
        } else {
            d = point.y / point.x;
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (it.hasNext()) {
                Camera.Size size2 = (Camera.Size) it.next();
                int i = size2.width;
                int i2 = size2.height;
                if (i * i2 < 153600) {
                    it.remove();
                } else {
                    boolean z = i < i2;
                    int i3 = z ? i2 : i;
                    int i4 = z ? i : i2;
                    if (Math.abs((i3 / i4) - d) > 0.15d) {
                        it.remove();
                    } else if (i3 == point.x && i4 == point.y) {
                        Point point2 = new Point(i, i2);
                        Log.i("CameraUtils", "Found preview size exactly matching screen size: " + point2);
                        return point2;
                    }
                }
            } else if (!arrayList.isEmpty()) {
                Camera.Size size3 = (Camera.Size) arrayList.get(0);
                Point point3 = new Point(size3.width, size3.height);
                Log.i("CameraUtils", "Using largest suitable preview size: " + point3);
                return point3;
            } else {
                Camera.Size previewSize2 = parameters.getPreviewSize();
                if (previewSize2 == null) {
                    throw new IllegalStateException("Parameters contained no preview size!");
                }
                Point point4 = new Point(previewSize2.width, previewSize2.height);
                Log.i("CameraUtils", "No suitable preview sizes, using default: " + point4);
                return point4;
            }
        }
    }

    public static Point findBestPictureSizeValue(Camera.Parameters parameters, Point point) {
        double d;
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        if (supportedPictureSizes == null) {
            Log.w("CameraUtils", "Device returned no supported preview sizes; using default");
            Camera.Size pictureSize = parameters.getPictureSize();
            if (pictureSize == null) {
                throw new IllegalStateException("Parameters contained no preview size!");
            }
            return new Point(pictureSize.width, pictureSize.height);
        }
        ArrayList<Camera.Size> arrayList = new ArrayList(supportedPictureSizes);
        Collections.sort(arrayList, new Comparator<Camera.Size>() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.utils.CameraUtils.3
            @Override // java.util.Comparator
            public int compare(Camera.Size size, Camera.Size size2) {
                int i = size.height * size.width;
                int i2 = size2.height * size2.width;
                if (i2 < i) {
                    return -1;
                }
                return i2 > i ? 1 : 0;
            }
        });
        if (Log.isLoggable("CameraUtils", 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size : arrayList) {
                sb.append(size.width);
                sb.append('x');
                sb.append(size.height);
                sb.append(' ');
            }
            Log.i("CameraUtils", "Supported picture sizes: " + ((Object) sb));
        }
        if (point.x > point.y) {
            d = point.x / point.y;
        } else {
            d = point.y / point.x;
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (it.hasNext()) {
                Camera.Size size2 = (Camera.Size) it.next();
                int i = size2.width;
                int i2 = size2.height;
                if (i * i2 < 153600) {
                    it.remove();
                } else {
                    boolean z = i < i2;
                    int i3 = z ? i2 : i;
                    int i4 = z ? i : i2;
                    if (Math.abs((i3 / i4) - d) > 0.15d) {
                        it.remove();
                    } else if (i3 == point.x && i4 == point.y) {
                        Point point2 = new Point(i, i2);
                        Log.i("CameraUtils", "Found preview size exactly matching screen size: " + point2);
                        return point2;
                    }
                }
            } else if (!arrayList.isEmpty()) {
                Camera.Size size3 = (Camera.Size) arrayList.get(0);
                Point point3 = new Point(size3.width, size3.height);
                Log.i("CameraUtils", "Using largest suitable preview size: " + point3);
                return point3;
            } else {
                Camera.Size pictureSize2 = parameters.getPictureSize();
                if (pictureSize2 == null) {
                    throw new IllegalStateException("Parameters contained no preview size!");
                }
                Point point4 = new Point(pictureSize2.width, pictureSize2.height);
                Log.i("CameraUtils", "No suitable preview sizes, using default: " + point4);
                return point4;
            }
        }
    }

    private static String findSettableValue(String str, Collection<String> collection, String... strArr) {
        Log.i("CameraUtils", "Requesting " + str + " value from among: " + Arrays.toString(strArr));
        Log.i("CameraUtils", "Supported " + str + " values: " + collection);
        if (collection != null) {
            for (String str2 : strArr) {
                if (collection.contains(str2)) {
                    Log.i("CameraUtils", "Can set " + str + " to: " + str2);
                    return str2;
                }
            }
        }
        Log.i("CameraUtils", "No supported values match");
        return null;
    }

    public static Point calculateViewSize(Point point, Point point2) {
        Point point3 = new Point();
        float f = point.x / point.y;
        if (f > point2.x / point2.y) {
            point3.x = point2.x;
            point3.y = (int) (point2.x / f);
        } else {
            point3.y = point2.y;
            point3.x = (int) (point2.y * f);
        }
        return point3;
    }

    public static int[] getRawScreenSize(Context context) {
        int[] iArr = new int[2];
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                i = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception unused) {
            }
        }
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(defaultDisplay, point);
                i = point.x;
                i2 = point.y;
            } catch (Exception unused2) {
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }
}
