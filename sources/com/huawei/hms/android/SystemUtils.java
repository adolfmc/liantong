package com.huawei.hms.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SystemUtils {
    public static final String UNKNOWN = "unknown";

    /* renamed from: a */
    private static String m15240a() {
        return getSystemProperties("ro.product.locale", "");
    }

    /* renamed from: b */
    private static String m15239b() {
        return getSystemProperties("ro.product.locale.region", "");
    }

    public static String getAndoridVersion() {
        return getSystemProperties("ro.build.version.release", "unknown");
    }

    public static String getManufacturer() {
        return getSystemProperties("ro.product.manufacturer", "unknown");
    }

    public static long getMegabyte(double d) {
        double d2 = Build.VERSION.SDK_INT > 25 ? 1000.0d : 1024.0d;
        return (long) (d * d2 * d2);
    }

    public static String getPhoneModel() {
        return getSystemProperties("ro.product.model", "unknown");
    }

    public static String getSystemProperties(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            HMSLog.m14112e("SystemUtils", "An exception occurred while reading: getSystemProperties:" + str);
            return str2;
        }
    }

    public static boolean isChinaROM() {
        String m15239b = m15239b();
        if (!TextUtils.isEmpty(m15239b)) {
            return "cn".equalsIgnoreCase(m15239b);
        }
        String m15240a = m15240a();
        if (!TextUtils.isEmpty(m15240a)) {
            return m15240a.toLowerCase(Locale.US).contains("cn");
        }
        String localCountry = getLocalCountry();
        if (TextUtils.isEmpty(localCountry)) {
            return false;
        }
        return "cn".equalsIgnoreCase(localCountry);
    }

    public static boolean isEMUI() {
        StringBuilder sb = new StringBuilder();
        sb.append("is Emui :");
        int i = HwBuildEx.VERSION.EMUI_SDK_INT;
        sb.append(i);
        HMSLog.m14110i("SystemUtils", sb.toString());
        return i > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isSystemApp(android.content.Context r1, java.lang.String r2) {
        /*
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch: java.lang.RuntimeException -> Lb android.util.AndroidException -> L23
            r0 = 16384(0x4000, float:2.2959E-41)
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r0)     // Catch: java.lang.RuntimeException -> Lb android.util.AndroidException -> L23
            goto L3b
        Lb:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = "isSystemApp RuntimeException:"
            r2.append(r0)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "SystemUtils"
            com.huawei.hms.support.log.HMSLog.m14112e(r2, r1)
            goto L3a
        L23:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = "isSystemApp Exception: "
            r2.append(r0)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "SystemUtils"
            com.huawei.hms.support.log.HMSLog.m14112e(r2, r1)
        L3a:
            r1 = 0
        L3b:
            r2 = 1
            if (r1 == 0) goto L46
            android.content.pm.ApplicationInfo r1 = r1.applicationInfo
            int r1 = r1.flags
            r1 = r1 & r2
            if (r1 <= 0) goto L46
            goto L47
        L46:
            r2 = 0
        L47:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.android.SystemUtils.isSystemApp(android.content.Context, java.lang.String):boolean");
    }

    public static boolean isTVDevice() {
        return getSystemProperties("ro.build.characteristics", "default").equalsIgnoreCase("tv");
    }

    public static String getLocalCountry() {
        Locale locale = Locale.getDefault();
        return locale != null ? locale.getCountry() : "";
    }

    public static String getNetType(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) ? "" : activeNetworkInfo.getTypeName();
    }
}
