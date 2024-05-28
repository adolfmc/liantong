package com.huawei.hms.framework.common;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Utils {
    private static final String TAG = "Utils";

    public static long getCurrentTime(boolean z) {
        if (z) {
            return SystemClock.elapsedRealtime();
        }
        return System.currentTimeMillis();
    }

    public static boolean is64Bit(Context context) {
        if (context == null) {
            Logger.m15052e("Utils", "Null context, please check it.");
            return false;
        }
        Context applicationContext = context.getApplicationContext() == null ? context : context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 23) {
            return Process.is64Bit();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128).nativeLibraryDir.contains("64");
            } catch (PackageManager.NameNotFoundException unused) {
                Logger.m15052e("Utils", "Get application info failed: name not found, try to get baseContext.");
                if (context instanceof ContextWrapper) {
                    Context baseContext = ((ContextWrapper) context).getBaseContext();
                    if (baseContext == null) {
                        Logger.m15045w("Utils", "Get baseContext failed: null. Return default: is64-bit.");
                        return true;
                    }
                    try {
                        return baseContext.getPackageManager().getApplicationInfo(baseContext.getPackageName(), 128).nativeLibraryDir.contains("64");
                    } catch (PackageManager.NameNotFoundException unused2) {
                        Logger.m15052e("Utils", "Get baseContext application info failed: name not found");
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
