package com.huawei.hms.framework.common;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ActivityUtil {
    private static final String TAG = "ActivityUtil";

    public static boolean isForeground(Context context) {
        ActivityManager activityManager;
        if (context == null || (activityManager = (ActivityManager) ContextCompat.getSystemService(context, "activity")) == null) {
            return false;
        }
        List<ActivityManager.RunningAppProcessInfo> list = null;
        try {
            list = activityManager.getRunningAppProcesses();
        } catch (RuntimeException e) {
            Logger.m15044w("ActivityUtil", "activityManager getRunningAppProcesses occur exception: ", e);
        }
        if (list != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : list) {
                if (runningAppProcessInfo.processName != null && runningAppProcessInfo.processName.equals(context.getPackageName()) && runningAppProcessInfo.importance == 100) {
                    Logger.m15047v("ActivityUtil", "isForeground true");
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static PendingIntent getActivities(Context context, int i, Intent[] intentArr, int i2) {
        if (context == null) {
            Logger.m15045w("ActivityUtil", "context is null");
            return null;
        }
        try {
            return PendingIntent.getActivities(context, i, intentArr, i2);
        } catch (RuntimeException e) {
            Logger.m15051e("ActivityUtil", "dealType rethrowFromSystemServer:", e);
            return null;
        }
    }
}
