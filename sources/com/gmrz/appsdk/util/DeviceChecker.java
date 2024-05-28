package com.gmrz.appsdk.util;

import android.content.Context;
import android.content.pm.PackageManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DeviceChecker {
    private static final String TAG = "DeviceChecker";

    public static boolean isDeviceInstalledSamsungpass(Context context) {
        try {
            context.getPackageManager().getApplicationInfo("samsungpass", 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Logger.m15757e("DeviceChecker", "android.content.pm.PackageManager$NameNotFoundException: samsungpass");
            return false;
        }
    }

    public static boolean isSamsungFidoClient(Context context) {
        try {
            context.getPackageManager().getApplicationInfo("com.sec.android.fido.uaf.client", 8192);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
