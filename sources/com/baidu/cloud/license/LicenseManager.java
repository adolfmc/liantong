package com.baidu.cloud.license;

import android.content.Context;
import com.baidu.cloud.license.util.C2492oi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LicenseManager implements INotProguard {
    private static Context sContext;

    public static void init(Context context, String str) {
        sContext = context;
        C2492oi.m20078a(context, str);
        SDKHttpConfig.setAppId(str);
        SDKHttpConfig.setPackageName(context.getPackageName());
        SDKHttpConfig.setSdkVersion("2.7.1");
        C2492oi.m20079a(context);
    }

    public static boolean isExplainSuccess() {
        return C2492oi.m20075b();
    }

    public static boolean isExpire() {
        return C2492oi.m20073c();
    }

    public static Context getContext() {
        return sContext;
    }

    public static boolean isValid(Context context, String str) {
        C2492oi.m20078a(context, SDKHttpConfig.getAppId());
        return C2492oi.m20076a(str);
    }

    public static boolean isValid(String str) {
        C2492oi.m20078a(getContext(), SDKHttpConfig.getAppId());
        return C2492oi.m20076a(str);
    }

    public static boolean isValid() {
        C2492oi.m20078a(getContext(), SDKHttpConfig.getAppId());
        return C2492oi.m20080a();
    }

    public static byte[] getARLicense() {
        return C2492oi.m20071d();
    }
}
