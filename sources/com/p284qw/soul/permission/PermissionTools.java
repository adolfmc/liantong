package com.p284qw.soul.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.debug.PermissionDebug;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.PermissionTools */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PermissionTools {
    private static final String TAG = "PermissionTools";

    public static boolean isOldPermissionSystem(Context context) {
        return Build.VERSION.SDK_INT < 23 || context.getApplicationInfo().targetSdkVersion < 23;
    }

    public static boolean isActivityAvailable(Activity activity) {
        if (activity == null) {
            return false;
        }
        if (activity.isFinishing()) {
            String str = TAG;
            PermissionDebug.m8226d(str, " activity is finishing :" + activity.getClass().getSimpleName());
            return false;
        } else if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
            return true;
        } else {
            String str2 = TAG;
            PermissionDebug.m8226d(str2, " activity is destroyed :" + activity.getClass().getSimpleName());
            return false;
        }
    }

    @CheckResult
    @Nullable
    public static Intent getSpecialPermissionIntent(Context context, Special special) {
        switch (special) {
            case SYSTEM_ALERT:
                return getDrawOverPermissionIntent(context);
            case UNKNOWN_APP_SOURCES:
                return getInstallPermissionIntent(context);
            default:
                return getAppManageIntent(context);
        }
    }

    public static Intent getAppManageIntent(Context context) {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            return intent;
        } catch (Exception unused) {
            return new Intent("android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Permission[] convert(List<Permission> list) {
        return (Permission[]) list.toArray(new Permission[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean assertMainThread() {
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void toast(Context context, String str) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, str, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void jumpAppDetail(Activity activity, int i) {
        if (!isActivityAvailable(activity)) {
            PermissionDebug.m8225e(TAG, "activity status error");
            return;
        }
        Intent appManageIntent = getAppManageIntent(activity);
        if (appManageIntent == null) {
            PermissionDebug.m8225e(TAG, "get system intent failed");
        }
        activity.startActivityForResult(appManageIntent, i);
    }

    private static Intent getInstallPermissionIntent(Context context) {
        Uri parse = Uri.parse("package:" + context.getPackageName());
        if (Build.VERSION.SDK_INT >= 26) {
            return new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", parse);
        }
        return null;
    }

    private static Intent getDrawOverPermissionIntent(Context context) {
        Uri parse = Uri.parse("package:" + context.getPackageName());
        if (Build.VERSION.SDK_INT >= 23) {
            return new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", parse);
        }
        return getAppManageIntent(context);
    }
}
