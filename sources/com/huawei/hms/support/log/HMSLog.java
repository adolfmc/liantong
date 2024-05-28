package com.huawei.hms.support.log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.AndroidException;
import com.huawei.hms.base.log.ExtLogNode;
import com.huawei.hms.base.log.LogAdaptor;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HMSLog {

    /* renamed from: a */
    private static final LogAdaptor f11769a = new LogAdaptor();

    /* renamed from: a */
    private static String m14116a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 16384);
                return "HMS-" + packageInfo.versionName + "(" + packageInfo.versionCode + ")";
            } catch (AndroidException unused) {
                return "HMS-[unknown-version]";
            } catch (RuntimeException unused2) {
                return "HMS-[unknown-version]";
            }
        }
        return "HMS-[unknown-version]";
    }

    /* renamed from: d */
    public static void m14115d(String str, String str2) {
        f11769a.m15183a(3, str, str2);
    }

    /* renamed from: e */
    public static void m14112e(String str, String str2) {
        f11769a.m15183a(6, str, str2);
    }

    /* renamed from: i */
    public static void m14110i(String str, String str2) {
        f11769a.m15183a(4, str, str2);
    }

    public static void init(Context context, int i, String str) {
        LogAdaptor logAdaptor = f11769a;
        logAdaptor.m15181a(context, i, str);
        logAdaptor.m15179a(str, "============================================================================\n====== " + m14116a(context) + "\n============================================================================");
    }

    public static boolean isErrorEnable() {
        return f11769a.m15184a(6);
    }

    public static boolean isInfoEnable() {
        return f11769a.m15184a(4);
    }

    public static boolean isWarnEnable() {
        return f11769a.m15184a(5);
    }

    public static void setExtLogger(HMSExtLogger hMSExtLogger, boolean z) throws IllegalArgumentException {
        if (hMSExtLogger != null) {
            ExtLogNode extLogNode = new ExtLogNode(hMSExtLogger);
            if (z) {
                f11769a.m15180a(extLogNode);
                return;
            } else {
                f11769a.m15185a().mo15175a(extLogNode);
                return;
            }
        }
        throw new IllegalArgumentException("extLogger is not able to be null");
    }

    /* renamed from: w */
    public static void m14109w(String str, String str2) {
        f11769a.m15183a(5, str, str2);
    }

    /* renamed from: e */
    public static void m14111e(String str, String str2, Throwable th) {
        f11769a.m15177b(6, str, str2, th);
    }

    /* renamed from: e */
    public static void m14114e(String str, long j, String str2) {
        LogAdaptor logAdaptor = f11769a;
        logAdaptor.m15183a(6, str, "[" + j + "] " + str2);
    }

    /* renamed from: e */
    public static void m14113e(String str, long j, String str2, Throwable th) {
        LogAdaptor logAdaptor = f11769a;
        logAdaptor.m15177b(6, str, "[" + j + "] " + str2, th);
    }
}
