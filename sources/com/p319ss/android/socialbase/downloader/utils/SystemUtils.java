package com.p319ss.android.socialbase.downloader.utils;

import android.content.Context;
import android.content.pm.ServiceInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.utils.SystemUtils */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SystemUtils {
    public static boolean checkServiceExists(Context context, String str, String str2) {
        ServiceInfo[] serviceInfoArr;
        try {
            for (ServiceInfo serviceInfo : context.getPackageManager().getPackageInfo(str, 4).services) {
                if (serviceInfo.exported && serviceInfo.enabled && serviceInfo.permission == null && serviceInfo.name.equals(str2)) {
                    return true;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }
}
