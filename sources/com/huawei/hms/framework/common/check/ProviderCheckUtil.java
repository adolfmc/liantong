package com.huawei.hms.framework.common.check;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ProviderCheckUtil {
    private static String TAG = "ProviderCheckUtil";

    public static boolean isValid(Uri uri) {
        if (uri != null) {
            PackageManager packageManager = ContextHolder.getAppContext().getPackageManager();
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(uri.getAuthority(), 0);
            if (resolveContentProvider != null) {
                ApplicationInfo applicationInfo = resolveContentProvider.applicationInfo;
                if (applicationInfo != null) {
                    String str = applicationInfo.packageName;
                    String str2 = TAG;
                    Logger.m15047v(str2, "Target provider service's package name is : " + str);
                    return str != null && packageManager.checkSignatures("com.huawei.hwid", str) == 0;
                }
                return false;
            }
            Logger.m15045w(TAG, "Invalid param");
            return false;
        }
        return false;
    }
}
