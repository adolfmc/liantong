package com.huawei.hms.update.p225ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.AndroidException;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.hms.utils.UIUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.update.ui.NotInstalledHmsDialogHelper */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class NotInstalledHmsDialogHelper {
    /* renamed from: a */
    private static void m14101a(Context context) {
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(context.getApplicationContext());
        }
    }

    public static String getAppName(Activity activity) {
        return m14100a(activity, activity.getPackageName());
    }

    public static int getConfirmResId(Activity activity) {
        Checker.checkNonNull(activity, "activity must not be null");
        m14101a(activity);
        return ResourceLoaderUtil.getStringId("hms_confirm");
    }

    public static AlertDialog.Builder getDialogBuilder(Activity activity) {
        Checker.checkNonNull(activity, "activity must not be null");
        m14101a(activity);
        return new AlertDialog.Builder(activity, UIUtil.getDialogThemeId(activity)).setMessage(activity.getString(ResourceLoaderUtil.getStringId("hms_apk_not_installed_hints"), new Object[]{m14100a(activity, activity.getPackageName())}));
    }

    /* renamed from: a */
    private static String m14100a(Context context, String str) {
        if (context == null) {
            HMSLog.m14112e("NotInstalledHmsDialogHelper", "In getAppName, context is null.");
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.m14112e("NotInstalledHmsDialogHelper", "In getAppName, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            if (TextUtils.isEmpty(str)) {
                str = context.getPackageName();
            }
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128));
            return applicationLabel == null ? "" : applicationLabel.toString();
        } catch (AndroidException | RuntimeException unused) {
            HMSLog.m14112e("NotInstalledHmsDialogHelper", "In getAppName, Failed to get app name.");
            return "";
        }
    }
}
