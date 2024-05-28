package com.sdk.p290f;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.sdk.base.framework.bean.DeviceUtils;
import com.sdk.base.framework.bean.PInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.f.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6993a {
    /* JADX WARN: Removed duplicated region for block: B:22:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.sdk.base.framework.bean.AInfo m8169a(android.content.Context r6, java.lang.String r7) {
        /*
            com.sdk.base.framework.bean.AInfo r0 = new com.sdk.base.framework.bean.AInfo
            r0.<init>()
            java.lang.String r1 = com.sdk.p293i.C7007a.f18154a
            r1 = 0
            if (r6 != 0) goto L16
            java.lang.String r2 = com.sdk.p293i.C7007a.f18154a
            boolean r3 = com.sdk.p293i.C7007a.f18155b
            java.lang.String r4 = "getAppLable"
            java.lang.String r5 = "mContext 为空"
            com.sdk.p292h.C7006a.m8164a(r2, r4, r5, r3)
            goto L3d
        L16:
            android.content.pm.PackageManager r2 = r6.getPackageManager()     // Catch: java.lang.Exception -> L2d
            java.lang.String r3 = r6.getPackageName()     // Catch: java.lang.Exception -> L2d
            r4 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r3 = r2.getApplicationInfo(r3, r4)     // Catch: java.lang.Exception -> L2d
            java.lang.CharSequence r2 = r2.getApplicationLabel(r3)     // Catch: java.lang.Exception -> L2d
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L2d
            goto L3e
        L2d:
            r2 = move-exception
            java.lang.String r3 = com.sdk.p293i.C7007a.f18154a
            java.lang.String r2 = r2.getMessage()
            boolean r4 = com.sdk.p293i.C7007a.f18155b
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            com.sdk.base.framework.utils.log.LogUtils.m8186e(r3, r2, r4)
        L3d:
            r2 = r1
        L3e:
            r0.setN(r2)
            r2 = -1
            if (r6 != 0) goto L45
            goto L67
        L45:
            android.content.pm.PackageManager r3 = r6.getPackageManager()     // Catch: java.lang.Exception -> L57
            java.lang.String r4 = com.sdk.p293i.C7007a.m8162a(r6)     // Catch: java.lang.Exception -> L57
            r5 = 1
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r4, r5)     // Catch: java.lang.Exception -> L57
            if (r3 == 0) goto L67
            int r2 = r3.versionCode     // Catch: java.lang.Exception -> L57
            goto L67
        L57:
            r3 = move-exception
            java.lang.String r4 = com.sdk.p293i.C7007a.f18154a
            java.lang.String r3 = r3.getMessage()
            boolean r5 = com.sdk.p293i.C7007a.f18155b
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            com.sdk.base.framework.utils.log.LogUtils.m8186e(r4, r3, r5)
        L67:
            r0.setC(r2)
            if (r6 != 0) goto L6d
            goto L8f
        L6d:
            android.content.pm.PackageManager r2 = r6.getPackageManager()
            java.lang.String r3 = r6.getPackageName()     // Catch: java.lang.Exception -> L7f
            r4 = 0
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r4)     // Catch: java.lang.Exception -> L7f
            if (r2 == 0) goto L8f
            java.lang.String r1 = r2.versionName     // Catch: java.lang.Exception -> L7f
            goto L8f
        L7f:
            r2 = move-exception
            java.lang.String r3 = com.sdk.p293i.C7007a.f18154a
            java.lang.String r2 = r2.getMessage()
            boolean r4 = com.sdk.p293i.C7007a.f18155b
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            com.sdk.base.framework.utils.log.LogUtils.m8186e(r3, r2, r4)
        L8f:
            r0.setV(r1)
            java.lang.String r1 = com.sdk.p293i.C7007a.m8162a(r6)
            r0.setPk(r1)
            java.lang.String r6 = com.sdk.p293i.C7007a.m8159b(r6, r7)
            r0.setCode(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sdk.p290f.C6993a.m8169a(android.content.Context, java.lang.String):com.sdk.base.framework.bean.AInfo");
    }

    /* renamed from: a */
    public static String m8170a(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return ("SimInfo:  " + telephonyManager.getSimOperatorName()) + "  " + telephonyManager.getSimOperator();
    }

    /* renamed from: a */
    public static PInfo m8171a() {
        PInfo pInfo = new PInfo();
        pInfo.setOs("Android");
        pInfo.setN("PhoneInfo:" + DeviceUtils.getPhoneBrand() + " : " + DeviceUtils.getPhoneModel() + " Android " + DeviceUtils.getVersionRelease() + "\n");
        return pInfo;
    }
}
