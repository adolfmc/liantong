package com.huawei.hms.common.internal;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.adapter.AvailableUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.hms.utils.Util;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ConnectionErrorMessages {
    /* renamed from: a */
    private static boolean m15110a(Context context) {
        return context != null && Util.isAvailableLibExist(context) && AvailableUtil.isInstallerLibExist(context);
    }

    public static String getErrorDialogButtonMessage(Activity activity, int i) {
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(activity.getApplicationContext());
        }
        if (i == 1) {
            if (m15110a(activity)) {
                return ResourceLoaderUtil.getString("hms_install");
            }
            return ResourceLoaderUtil.getString("hms_confirm");
        } else if (i != 2) {
            return ResourceLoaderUtil.getString("hms_confirm");
        } else {
            if (m15110a(activity)) {
                return ResourceLoaderUtil.getString("hms_update");
            }
            return ResourceLoaderUtil.getString("hms_confirm");
        }
    }

    public static String getErrorMessage(Activity activity, int i) {
        if (activity == null) {
            return null;
        }
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(activity.getApplicationContext());
        }
        if (i == 1 || i == 2) {
            return m15110a(activity) ? ResourceLoaderUtil.getString("hms_update_title") : activity.getString(ResourceLoaderUtil.getStringId("hms_apk_not_installed_hints"), new Object[]{Util.getAppName(activity, activity.getPackageName())});
        }
        return null;
    }

    public static String getErrorTitle(Activity activity, int i) {
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(activity.getApplicationContext());
        }
        if (i == 1) {
            if (m15110a(activity)) {
                return ResourceLoaderUtil.getString("hms_install_message");
            }
            return null;
        } else if (i == 2) {
            if (m15110a(activity)) {
                return ResourceLoaderUtil.getString("hms_update_message");
            }
            return null;
        } else if (i != 3) {
            if (i != 9) {
                HMSLog.m14112e("HuaweiApiAvailability", "Unexpected error code " + i);
                return null;
            }
            HMSLog.m14112e("HuaweiApiAvailability", "Huawei Mobile Services is invalid. Cannot recover.");
            return null;
        } else {
            return ResourceLoaderUtil.getString("hms_bindfaildlg_message");
        }
    }
}
