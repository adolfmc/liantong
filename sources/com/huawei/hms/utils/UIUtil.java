package com.huawei.hms.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.hms.support.common.ActivityMgr;
import com.huawei.hms.support.log.HMSLog;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UIUtil {
    /* renamed from: a */
    private static int m14029a(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier("androidhwext:style/Theme.Emui", null, null);
    }

    public static Activity getActiveActivity(Activity activity, Context context) {
        if (isBackground(context)) {
            HMSLog.m14110i("UIUtil", "isBackground" + isBackground(context));
            return null;
        } else if (activity == null) {
            HMSLog.m14110i("UIUtil", "activity is null");
            return ActivityMgr.INST.getCurrentActivity();
        } else if (activity.isFinishing()) {
            HMSLog.m14110i("UIUtil", "activity isFinishing is " + activity.isFinishing());
            return ActivityMgr.INST.getCurrentActivity();
        } else {
            return activity;
        }
    }

    public static int getDialogThemeId(Activity activity) {
        if (m14029a(activity) == 0 || Build.VERSION.SDK_INT < 16) {
            return (activity != null && (activity.getResources().getConfiguration().uiMode & 48) == 32) ? 2 : 3;
        }
        return 0;
    }

    public static boolean isActivityFullscreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) == 1024;
    }

    public static boolean isBackground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null) {
            return true;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        if (activityManager == null || keyguardManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return true;
        }
        String processName = getProcessName(context, Process.myPid());
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (TextUtils.equals(runningAppProcessInfo.processName, processName)) {
                HMSLog.m14110i("UIUtil", "appProcess.importance is " + runningAppProcessInfo.importance);
                boolean z = runningAppProcessInfo.importance == 100;
                boolean isKeyguardLocked = keyguardManager.isKeyguardLocked();
                HMSLog.m14110i("UIUtil", "isForground is " + z + "***  isLockedState is " + isKeyguardLocked);
                return !z || isKeyguardLocked;
            }
        }
        return true;
    }

    public static Intent modifyIntentBehaviorsSafe(Intent intent) {
        if (intent == null) {
            return null;
        }
        String action = intent.getAction();
        if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action) || "android.media.action.IMAGE_CAPTURE".equals(action) || "android.media.action.IMAGE_CAPTURE_SECURE".equals(action) || "android.media.action.VIDEO_CAPTURE".equals(action)) {
            intent.setAction("android.intent.action.VIEW");
        }
        int flags = intent.getFlags();
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            flags &= -129;
        }
        if (i >= 19) {
            flags &= -65;
        }
        intent.setFlags(flags & (-2) & (-3));
        return intent;
    }

    public static String getProcessName(Context context, int i) {
        ActivityManager activityManager;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null || (activityManager = (ActivityManager) context.getSystemService("activity")) == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == i) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }
}
