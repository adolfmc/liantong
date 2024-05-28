package com.mabeijianxi.smallvideorecord2;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.TypedValue;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DeviceUtils {
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= 8;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= 12;
    }

    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean hasJellyBeanMr1() {
        return Build.VERSION.SDK_INT >= 17;
    }

    public static boolean hasJellyBeanMr2() {
        return Build.VERSION.SDK_INT >= 18;
    }

    public static boolean hasKitkat() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static int getSDKVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    public static String getSDKVersion() {
        return Build.VERSION.SDK;
    }

    public static String getReleaseVersion() {
        return StringUtils.makeSafe(Build.VERSION.RELEASE);
    }

    public static boolean isZte() {
        return getDeviceModel().toLowerCase().indexOf("zte") != -1;
    }

    public static boolean isSamsung() {
        return getManufacturer().toLowerCase().indexOf("samsung") != -1;
    }

    public static boolean isHTC() {
        return getManufacturer().toLowerCase().indexOf("htc") != -1;
    }

    public static boolean isDevice(String... strArr) {
        String deviceModel = getDeviceModel();
        if (strArr != null && deviceModel != null) {
            for (String str : strArr) {
                if (deviceModel.indexOf(str) != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getDeviceModel() {
        return StringUtils.trim(Build.MODEL);
    }

    public static String getManufacturer() {
        return StringUtils.trim(Build.MANUFACTURER);
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static boolean isHoneycombTablet(Context context) {
        return hasHoneycomb() && isTablet(context);
    }

    public static int dipToPX(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static String getCpuInfo() {
        try {
            if (new File("/proc/cpuinfo").exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                return readLine != null ? readLine.split(":")[1].trim().split(" ")[0] : readLine;
            }
            return "";
        } catch (IOException | Exception unused) {
            return "";
        }
    }

    public static boolean isSupportCameraLedFlash(PackageManager packageManager) {
        FeatureInfo[] systemAvailableFeatures;
        if (packageManager != null && (systemAvailableFeatures = packageManager.getSystemAvailableFeatures()) != null) {
            for (FeatureInfo featureInfo : systemAvailableFeatures) {
                if (featureInfo != null && "android.hardware.camera.flash".equals(featureInfo.name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSupportCameraHardware(Context context) {
        return context != null && context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public static int getScreenWidth(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }
}
