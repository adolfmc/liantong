package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11176aw;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.mipush.sdk.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11100h {

    /* renamed from: a */
    private static int f21389a = -1;

    /* renamed from: a */
    public static EnumC11112q m5073a(Context context) {
        if (m5072a(context)) {
            return EnumC11112q.HUAWEI;
        }
        if (m5070c(context)) {
            return EnumC11112q.OPPO;
        }
        if (m5069d(context)) {
            return EnumC11112q.VIVO;
        }
        return EnumC11112q.OTHER;
    }

    /* renamed from: a */
    public static boolean m5072a(Context context) {
        try {
            if (context.getPackageManager().getServiceInfo(new ComponentName("com.huawei.hwid", "com.huawei.hms.core.service.HMSCoreService"), 128) != null) {
                if (m5074a()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m5074a() {
        try {
            String str = (String) C11176aw.m4810a("android.os.SystemProperties", "get", "ro.build.hw_emui_api_level", "");
            if (!TextUtils.isEmpty(str)) {
                if (Integer.parseInt(str) >= 9) {
                    return true;
                }
            }
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m5071b(Context context) {
        Object m4812a = C11176aw.m4812a(C11176aw.m4810a("com.google.android.gms.common.GoogleApiAvailability", "getInstance", new Object[0]), "isGooglePlayServicesAvailable", context);
        Object m4811a = C11176aw.m4811a("com.google.android.gms.common.ConnectionResult", "SUCCESS");
        if (m4811a != null && (m4811a instanceof Integer)) {
            int intValue = ((Integer) Integer.class.cast(m4811a)).intValue();
            if (m4812a != null) {
                if (m4812a instanceof Integer) {
                    f21389a = ((Integer) Integer.class.cast(m4812a)).intValue() == intValue ? 1 : 0;
                } else {
                    f21389a = 0;
                    AbstractC11049b.m5270c("google service is not avaliable");
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("is google service can be used");
            sb.append(f21389a > 0);
            AbstractC11049b.m5270c(sb.toString());
            return f21389a > 0;
        }
        AbstractC11049b.m5270c("google service is not avaliable");
        f21389a = 0;
        return false;
    }

    /* renamed from: c */
    public static boolean m5070c(Context context) {
        boolean z = false;
        Object m4810a = C11176aw.m4810a("com.xiaomi.assemble.control.COSPushManager", "isSupportPush", context);
        if (m4810a != null && (m4810a instanceof Boolean)) {
            z = ((Boolean) Boolean.class.cast(m4810a)).booleanValue();
        }
        AbstractC11049b.m5270c("color os push  is avaliable ? :" + z);
        return z;
    }

    /* renamed from: d */
    public static boolean m5069d(Context context) {
        boolean z = false;
        Object m4810a = C11176aw.m4810a("com.xiaomi.assemble.control.FTOSPushManager", "isSupportPush", context);
        if (m4810a != null && (m4810a instanceof Boolean)) {
            z = ((Boolean) Boolean.class.cast(m4810a)).booleanValue();
        }
        AbstractC11049b.m5270c("fun touch os push  is avaliable ? :" + z);
        return z;
    }
}
