package com.huawei.hms.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AndroidException;
import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AvailableUtil {

    /* renamed from: a */
    private static final Object f10919a = new Object();

    /* renamed from: b */
    private static boolean f10920b;

    /* renamed from: c */
    private static boolean f10921c;

    public static boolean isInstallerLibExist(Context context) {
        Bundle bundle;
        Object obj;
        if (f10920b) {
            HMSLog.m14110i("AvailableUtil", "installerInit exist: " + f10921c);
            return f10921c;
        }
        synchronized (f10919a) {
            if (!f10920b) {
                boolean z = false;
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    HMSLog.m14112e("AvailableUtil", "In isAvailableLibExist, Failed to get 'PackageManager' instance.");
                    try {
                        Class.forName("com.huawei.hms.update.manager.UpdateManager");
                        z = true;
                    } catch (ClassNotFoundException unused) {
                        HMSLog.m14112e("AvailableUtil", "In isInstallerLibExist, Failed to find class UpdateManager.");
                    }
                    f10921c = z;
                    f10920b = true;
                } else {
                    try {
                        try {
                            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
                            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && (obj = bundle.get("availableHMSCoreInstaller")) != null && String.valueOf(obj).equalsIgnoreCase("yes")) {
                                HMSLog.m14110i("AvailableUtil", "available exist: true");
                                z = true;
                            }
                        } catch (AndroidException unused2) {
                            HMSLog.m14112e("AvailableUtil", "In isInstallerLibExist, Failed to read meta data for the availableHMSCoreInstaller.");
                        }
                    } catch (RuntimeException e) {
                        HMSLog.m14111e("AvailableUtil", "In isInstallerLibExist, Failed to read meta data for the availableHMSCoreInstaller.", e);
                    }
                    f10921c = z;
                    f10920b = true;
                }
            }
        }
        HMSLog.m14110i("AvailableUtil", "available exist: " + f10921c);
        return f10921c;
    }
}
