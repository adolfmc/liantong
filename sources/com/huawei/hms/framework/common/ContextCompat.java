package com.huawei.hms.framework.common;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Process;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ContextCompat {
    private static final String TAG = "ContextCompat";

    public static boolean checkSelfPermission(Context context, String str) {
        if (context == null || str == null) {
            Logger.m15045w("ContextCompat", "param is null");
            return false;
        }
        try {
            return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
        } catch (RuntimeException e) {
            Logger.m15051e("ContextCompat", "dealType rethrowFromSystemServer:", e);
            return false;
        }
    }

    public static Context getProtectedStorageContext(Context context) {
        if (context != null) {
            return Build.VERSION.SDK_INT < 24 ? context : context.createDeviceProtectedStorageContext();
        }
        Logger.m15045w("ContextCompat", "context is null");
        return null;
    }

    public static Intent registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (context == null) {
            Logger.m15045w("ContextCompat", "context is null");
            return null;
        }
        try {
            return context.registerReceiver(broadcastReceiver, intentFilter);
        } catch (RuntimeException e) {
            Logger.m15051e("ContextCompat", "dealType rethrowFromSystemServer:", e);
            return null;
        }
    }

    public static Intent registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler) {
        if (context == null) {
            Logger.m15045w("ContextCompat", "context is null");
            return null;
        }
        try {
            return context.registerReceiver(broadcastReceiver, intentFilter, str, handler);
        } catch (RuntimeException e) {
            Logger.m15051e("ContextCompat", "dealType rethrowFromSystemServer:", e);
            return null;
        }
    }

    public static void unregisterReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        if (context == null) {
            Logger.m15045w("ContextCompat", "context is null");
            return;
        }
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (RuntimeException e) {
            Logger.m15051e("ContextCompat", "SystemServer error:", e);
        }
    }

    public static ComponentName startService(Context context, Intent intent) {
        if (context == null) {
            Logger.m15045w("ContextCompat", "context is null");
            return null;
        }
        try {
            return context.startService(intent);
        } catch (RuntimeException e) {
            Logger.m15051e("ContextCompat", "SystemServer error:", e);
            return null;
        }
    }

    public static Object getSystemService(Context context, String str) {
        if (context == null) {
            Logger.m15045w("ContextCompat", "context is null");
            return null;
        }
        try {
            return context.getSystemService(str);
        } catch (RuntimeException e) {
            Logger.m15051e("ContextCompat", "SystemServer error:", e);
            return null;
        }
    }
}
