package com.huawei.hms.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidException;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.common.util.AGCUtils;
import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Util {

    /* renamed from: a */
    private static boolean f11857a;

    /* renamed from: b */
    private static boolean f11858b;

    /* renamed from: c */
    private static final Object f11859c = new Object();

    /* renamed from: d */
    private static String f11860d;

    public static int compareHmsVersion(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            return 1;
        }
        if (StringUtil.checkVersion(str) && StringUtil.checkVersion(str2)) {
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            if (2 < split.length && 2 < split2.length) {
                if (Integer.parseInt(split[0]) > Integer.parseInt(split2[0])) {
                    return 1;
                }
                if (Integer.parseInt(split[0]) < Integer.parseInt(split2[0])) {
                    return -1;
                }
                if (Integer.parseInt(split[1]) > Integer.parseInt(split2[1])) {
                    return 1;
                }
                if (Integer.parseInt(split[1]) < Integer.parseInt(split2[1])) {
                    return -1;
                }
                if (Integer.parseInt(split[2]) > Integer.parseInt(split2[2])) {
                    return 1;
                }
                if (Integer.parseInt(split[2]) < Integer.parseInt(split2[2])) {
                    return -1;
                }
            }
        }
        return 0;
    }

    public static Activity getActiveActivity(Activity activity, Context context) {
        return UIUtil.getActiveActivity(activity, context);
    }

    public static String getAppId(Context context) {
        return AGCUtils.getAppId(context);
    }

    public static String getAppName(Context context, String str) {
        if (context == null) {
            HMSLog.m14112e("Util", "In getAppName, context is null.");
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.m14112e("Util", "In getAppName, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            if (TextUtils.isEmpty(str)) {
                str = context.getPackageName();
            }
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128));
            return applicationLabel == null ? "" : applicationLabel.toString();
        } catch (AndroidException | RuntimeException unused) {
            HMSLog.m14112e("Util", "In getAppName, Failed to get app name.");
            return "";
        }
    }

    public static String getCpId(Context context) {
        return AGCUtils.getCpId(context);
    }

    public static int getHmsVersion(Context context) {
        Bundle bundle;
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.m14112e("Util", "In getHmsVersion, Failed to get 'PackageManager' instance.");
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && (obj = bundle.get("com.huawei.hms.version")) != null) {
                String valueOf = String.valueOf(obj);
                if (!TextUtils.isEmpty(valueOf)) {
                    return StringUtil.convertVersion2Integer(valueOf);
                }
            }
            HMSLog.m14110i("Util", "In getHmsVersion, Failed to read meta data for the HMS VERSION.");
            return 0;
        } catch (AndroidException unused) {
            HMSLog.m14112e("Util", "In getHmsVersion, Failed to read meta data for the HMS VERSION.");
            return 0;
        } catch (RuntimeException e) {
            HMSLog.m14111e("Util", "In getHmsVersion, Failed to read meta data for the HMS VERSION.", e);
            return 0;
        }
    }

    public static String getNetType(Context context) {
        return SystemUtils.getNetType(context);
    }

    public static String getProcessName(Context context, int i) {
        return UIUtil.getProcessName(context, i);
    }

    @Deprecated
    public static String getProductCountry() {
        int lastIndexOf;
        String systemProperties = getSystemProperties("ro.product.locale.region", "");
        if (TextUtils.isEmpty(systemProperties)) {
            String systemProperties2 = getSystemProperties("ro.product.locale", "");
            if (!TextUtils.isEmpty(systemProperties2) && (lastIndexOf = systemProperties2.lastIndexOf("-")) != -1) {
                return systemProperties2.substring(lastIndexOf + 1);
            }
            String localCountry = SystemUtils.getLocalCountry();
            return !TextUtils.isEmpty(localCountry) ? localCountry : "";
        }
        return systemProperties;
    }

    public static String getServiceActionMetadata(Context context) {
        String str = f11860d;
        if (str != null) {
            return str;
        }
        ResolveInfo resolveInfo = null;
        try {
            Intent intent = new Intent("com.huawei.hms.core.internal");
            intent.setPackage(context.getPackageName());
            resolveInfo = context.getPackageManager().resolveService(intent, 128);
        } catch (Exception e) {
            HMSLog.m14112e("Util", "Exception when getServiceActionMetadata. " + e.getMessage());
        }
        if (resolveInfo != null) {
            f11860d = "com.huawei.hms.core.internal";
            return "com.huawei.hms.core.internal";
        }
        f11860d = "";
        return "";
    }

    public static String getSystemProperties(String str, String str2) {
        return SystemUtils.getSystemProperties(str, str2);
    }

    public static Activity getValidActivity(Activity activity, Activity activity2) {
        if (activity == null || activity.isFinishing()) {
            if (activity2 == null || activity2.isFinishing()) {
                return null;
            }
            return activity2;
        }
        return activity;
    }

    public static boolean isAvailableLibExist(Context context) {
        Bundle bundle;
        Object obj;
        synchronized (f11859c) {
            if (!f11857a) {
                boolean z = false;
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    HMSLog.m14112e("Util", "In isAvailableLibExist, Failed to get 'PackageManager' instance.");
                    try {
                        Class.forName("com.huawei.hms.adapter.AvailableAdapter");
                        z = true;
                    } catch (ClassNotFoundException unused) {
                        HMSLog.m14112e("Util", "In isAvailableLibExist, Failed to find class AvailableAdapter.");
                    }
                    f11858b = z;
                    f11857a = true;
                } else {
                    try {
                        ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
                        if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && (obj = bundle.get("availableLoaded")) != null && String.valueOf(obj).equalsIgnoreCase("yes")) {
                            HMSLog.m14110i("Util", "available exist: true");
                            z = true;
                        }
                    } catch (AndroidException unused2) {
                        HMSLog.m14112e("Util", "In isAvailableLibExist, Failed to read meta data for the availableLoaded.");
                    } catch (RuntimeException e) {
                        HMSLog.m14111e("Util", "In isAvailableLibExist, Failed to read meta data for the availableLoaded.", e);
                    }
                    f11858b = z;
                    f11857a = true;
                }
            }
        }
        HMSLog.m14110i("Util", "available exist: " + f11858b);
        return f11858b;
    }

    public static boolean isChinaROM() {
        return SystemUtils.isChinaROM();
    }

    public static boolean isEMUI() {
        return SystemUtils.isEMUI();
    }

    public static void unBindServiceCatchException(Context context, ServiceConnection serviceConnection) {
        try {
            HMSLog.m14110i("Util", "Trying to unbind service from " + serviceConnection);
            context.unbindService(serviceConnection);
        } catch (Exception e) {
            HMSLog.m14112e("Util", "On unBindServiceException:" + e.getMessage());
        }
    }
}
