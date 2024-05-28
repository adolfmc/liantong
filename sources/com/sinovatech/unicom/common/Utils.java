package com.sinovatech.unicom.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Utils {
    public static final int IO_BUFFER_SIZE = 8192;

    private Utils() {
    }

    public static void disableConnectionReuseIfNecessary() {
        if (hasHttpConnectionBug()) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    @SuppressLint({"NewApi"})
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    @SuppressLint({"NewApi"})
    public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= 9) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    public static long getUsableSpace(File file) {
        if (Build.VERSION.SDK_INT >= 9) {
            return file.getUsableSpace();
        }
        StatFs statFs = new StatFs(file.getPath());
        return statFs.getBlockSize() * statFs.getAvailableBlocks();
    }

    public static int getMemoryClass(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getMemoryClass();
    }

    public static boolean hasHttpConnectionBug() {
        return Build.VERSION.SDK_INT < 8;
    }

    public static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= 8;
    }

    public static boolean hasActionBar() {
        return Build.VERSION.SDK_INT >= 11;
    }

    private static int getDefalutDataID(Context context) {
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
        if (Build.VERSION.SDK_INT > 24) {
            return SubscriptionManager.getDefaultDataSubscriptionId();
        }
        try {
            return ((Integer) SubscriptionManager.class.getClass().getDeclaredMethod("getDefaultDataSubId", new Class[0]).invoke(subscriptionManager, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static SubscriptionInfo getSIMInfo(Context context) {
        SubscriptionInfo subscriptionInfo = null;
        for (SubscriptionInfo subscriptionInfo2 : ((SubscriptionManager) context.getSystemService("telephony_subscription_service")).getActiveSubscriptionInfoList()) {
            Log.d("xubaipei", subscriptionInfo2.toString());
            if (subscriptionInfo2.getSubscriptionId() == getDefalutDataID(context)) {
                subscriptionInfo = subscriptionInfo2;
            }
        }
        return subscriptionInfo;
    }
}
