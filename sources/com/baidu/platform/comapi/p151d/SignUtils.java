package com.baidu.platform.comapi.p151d;

import android.content.Context;
import android.content.pm.Signature;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.d.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SignUtils {

    /* renamed from: a */
    private static int f7550a = 621133959;

    /* renamed from: b */
    private static int m18046b(Context context) {
        Signature[] signatureArr;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                if (context.getPackageManager().getPackageInfo("com.baidu.BaiduMap", 134217728).signingInfo.hasMultipleSigners()) {
                    signatureArr = context.getPackageManager().getPackageInfo("com.baidu.BaiduMap", 134217728).signingInfo.getApkContentsSigners();
                } else {
                    signatureArr = context.getPackageManager().getPackageInfo("com.baidu.BaiduMap", 134217728).signingInfo.getSigningCertificateHistory();
                }
            } else {
                signatureArr = context.getPackageManager().getPackageInfo("com.baidu.BaiduMap", 64).signatures;
            }
            return signatureArr[0].hashCode();
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: c */
    private static boolean m18045c(Context context) {
        return m18046b(context) == f7550a;
    }

    /* renamed from: a */
    public static boolean m18047a(Context context) {
        return m18045c(context);
    }
}
