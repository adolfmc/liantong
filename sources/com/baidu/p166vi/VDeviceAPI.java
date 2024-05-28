package com.baidu.p166vi;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.baidu.mapsdkplatform.comapi.util.SyncSysInfo;
import com.baidu.platform.comapi.util.NetworkUtil;
import com.baidu.platform.comapi.util.SysOSUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.VDeviceAPI */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class VDeviceAPI {

    /* renamed from: a */
    private static PowerManager.WakeLock f8195a;

    /* renamed from: b */
    private static BroadcastReceiver f8196b;

    public static String getAppVersion() {
        try {
            return VIContext.getContext().getPackageManager().getPackageInfo(VIContext.getContext().getApplicationInfo().packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static long getAvailableMemory() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) VIContext.getContext().getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / 1024;
    }

    public static String getCachePath() {
        return Environment.getDataDirectory().getAbsolutePath();
    }

    public static String getCellId() {
        return "";
    }

    public static String getCuid() {
        return SyncSysInfo.getCid();
    }

    public static int getCurrentNetworkType() {
        try {
            return Integer.parseInt(NetworkUtil.getCurrentNetMode(VIContext.getContext()));
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long getFreeSpace() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getPath());
        return (statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1024;
    }

    public static String getImei() {
        return null;
    }

    public static String getImsi() {
        TelephonyManager telephonyManager = (TelephonyManager) VIContext.getContext().getSystemService("phone");
        return null;
    }

    public static String getLac() {
        return "";
    }

    public static String getModuleFileName() {
        return VIContext.getContext().getFilesDir().getAbsolutePath();
    }

    public static VNetworkInfo getNetworkInfo(int i) {
        int i2;
        NetworkInfo networkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) VIContext.getContext().getSystemService("connectivity");
        switch (i) {
            case 2:
                i2 = 1;
                networkInfo = connectivityManager.getNetworkInfo(i2);
                break;
            case 3:
                i2 = 0;
                networkInfo = connectivityManager.getNetworkInfo(i2);
                break;
            default:
                networkInfo = null;
                break;
        }
        if (networkInfo != null) {
            return new VNetworkInfo(networkInfo);
        }
        return null;
    }

    public static String getOsVersion() {
        return "android";
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001a A[RETURN] */
    @android.annotation.TargetApi(8)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getScreenBrightness() {
        /*
            android.content.Context r0 = com.baidu.p166vi.VIContext.getContext()
            android.content.ContentResolver r0 = r0.getContentResolver()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 8
            if (r2 > r1) goto L15
            java.lang.String r1 = "screen_brightness_mode"
            int r1 = android.provider.Settings.System.getInt(r0, r1)     // Catch: java.lang.Exception -> L15
            goto L16
        L15:
            r1 = 0
        L16:
            r2 = 1
            r3 = -1
            if (r1 != r2) goto L1b
            return r3
        L1b:
            java.lang.String r1 = "screen_brightness"
            int r0 = android.provider.Settings.System.getInt(r0, r1)     // Catch: android.provider.Settings.SettingNotFoundException -> L22
            return r0
        L22:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p166vi.VDeviceAPI.getScreenBrightness():int");
    }

    public static float getScreenDensity() {
        if (VIContext.getContext() == null) {
            return 0.0f;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) VIContext.getContext().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.density;
    }

    public static int getScreenDensityDpi() {
        if (VIContext.getContext() == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) VIContext.getContext().getSystemService("window");
        if (windowManager != null && windowManager.getDefaultDisplay() != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.densityDpi;
    }

    public static long getSdcardFreeSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1024;
    }

    public static String getSdcardPath() {
        return SysOSUtil.getInstance().getCompatibleSdcardPath();
    }

    public static long getSdcardTotalSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (statFs.getBlockSize() * statFs.getBlockCount()) / 1024;
    }

    public static float getSystemMetricsX() {
        if (VIContext.getContext() == null) {
            return 0.0f;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) VIContext.getContext().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels;
    }

    public static float getSystemMetricsY() {
        if (VIContext.getContext() == null) {
            return 0.0f;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) VIContext.getContext().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels;
    }

    @Deprecated
    public static int getTelecomInfo() {
        return -1;
    }

    public static long getTotalMemory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            r1 = readLine != null ? Integer.valueOf(readLine.split("\\s+")[1]).intValue() : 0L;
            bufferedReader.close();
        } catch (IOException unused) {
        }
        return r1;
    }

    public static long getTotalSpace() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getPath());
        return (statFs.getBlockSize() * statFs.getBlockCount()) / 1024;
    }

    public static ScanResult[] getWifiHotpot() {
        return null;
    }

    public static boolean isWifiConnected() {
        NetworkInfo networkInfo = ((ConnectivityManager) VIContext.getContext().getSystemService("connectivity")).getNetworkInfo(1);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void makeCall(String str) {
    }

    public static native void onNetworkStateChanged();

    public static void openUrl(String str) {
        VIContext.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public static int sendMMS(String str, String str2, String str3, String str4) {
        return 0;
    }

    public static void sendSMS(String str, String str2) {
    }

    public static void setNetworkChangedCallback() {
        unsetNetworkChangedCallback();
        f8196b = new C3331e();
        VIContext.getContext().registerReceiver(f8196b, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static void setScreenAlwaysOn(boolean z) {
    }

    public static void setupSoftware(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        VIContext.getContext().startActivity(intent);
    }

    public static void unsetNetworkChangedCallback() {
        if (f8196b != null) {
            VIContext.getContext().unregisterReceiver(f8196b);
            f8196b = null;
        }
    }
}
