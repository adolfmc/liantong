package com.baidu.license;

import android.content.Context;
import com.baidu.license.license.BDLicenseSdk;
import p001a.p002a.p003a.p057f.AuthenticationManager;
import p001a.p002a.p003a.p057f.Base64Utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LicenseManager implements INotProguard {
    public static Context sContext;

    public static byte[] getARLicense() {
        AuthenticationManager.C0700oi c0700oi = AuthenticationManager.f2089d;
        if (c0700oi != null) {
            return Base64Utils.m22354a(c0700oi.f2094b);
        }
        return new byte[0];
    }

    public static IBDLicenseSdk getBDLicenseSdk() {
        return BDLicenseSdk.C2607oi.f4999a;
    }

    public static Context getContext() {
        return sContext;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void init(android.content.Context r7, java.lang.String r8) {
        /*
            com.baidu.license.LicenseManager.sContext = r7
            p001a.p002a.p003a.p057f.AuthenticationManager.m22351a(r7, r8)
            com.baidu.license.SDKHttpConfig.init(r7)
            com.baidu.license.SDKHttpConfig.appId = r8
            java.lang.String r8 = r7.getPackageName()
            com.baidu.license.SDKHttpConfig.packageName = r8
            java.lang.String r8 = "1.7.0"
            com.baidu.license.SDKHttpConfig.sdkVersion = r8
            a.a.a.f.oi$oi r8 = p001a.p002a.p003a.p057f.AuthenticationManager.f2089d
            r0 = 0
            if (r8 == 0) goto L42
            long r1 = java.lang.System.currentTimeMillis()
            r3 = 0
            java.text.SimpleDateFormat r8 = new java.text.SimpleDateFormat     // Catch: java.lang.Exception -> L38
            java.lang.String r5 = "yyyy-MM-dd HH:mm:ss"
            java.util.Locale r6 = java.util.Locale.getDefault()     // Catch: java.lang.Exception -> L38
            r8.<init>(r5, r6)     // Catch: java.lang.Exception -> L38
            a.a.a.f.oi$oi r5 = p001a.p002a.p003a.p057f.AuthenticationManager.f2089d     // Catch: java.lang.Exception -> L38
            java.lang.String r5 = r5.f2095c     // Catch: java.lang.Exception -> L38
            java.util.Date r8 = r8.parse(r5)     // Catch: java.lang.Exception -> L38
            long r3 = r8.getTime()     // Catch: java.lang.Exception -> L38
            goto L3c
        L38:
            r8 = move-exception
            r8.printStackTrace()
        L3c:
            int r8 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r8 <= 0) goto L42
            r8 = 1
            goto L43
        L42:
            r8 = r0
        L43:
            if (r8 != 0) goto L5c
            java.lang.String r8 = "account_sp"
            android.content.SharedPreferences r8 = r7.getSharedPreferences(r8, r0)
            java.lang.String r0 = "license"
            java.lang.String r1 = ""
            java.lang.String r8 = r8.getString(r0, r1)
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto L5c
            p001a.p002a.p003a.p057f.AuthenticationManager.m22349a(r8)
        L5c:
            boolean r7 = com.baidu.license.util.NetHelper.isNetworkConnected(r7)
            if (r7 == 0) goto L8b
            java.util.HashMap r7 = p001a.p002a.p003a.p004a.RequestParameterUtil.m22368a()
            java.lang.String r8 = com.baidu.license.SDKHttpConfig.getSignStr()
            java.lang.String r0 = p001a.p002a.p003a.p057f.AuthenticationManager.m22352a()
            java.lang.String r1 = "utf-8"
            java.lang.String r8 = p001a.p002a.p003a.p004a.RequestParameterUtil.m22364a(r8, r0, r1)
            java.lang.String r0 = "sign"
            r7.put(r0, r8)
            com.baidu.license.api.ApiService r8 = p001a.p002a.p003a.p004a.ApiManager.m22371a()
            retrofit2.Call r7 = r8.reqLicenseValid(r7)
            com.baidu.license.util.AuthenticationManager$1 r8 = new com.baidu.license.util.AuthenticationManager$1
            r8.<init>()
            r7.enqueue(r8)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.license.LicenseManager.init(android.content.Context, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0056 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isExpire() {
        /*
            a.a.a.f.oi$oi r0 = p001a.p002a.p003a.p057f.AuthenticationManager.f2090e
            r1 = 0
            if (r0 == 0) goto L2c
            long r3 = java.lang.System.currentTimeMillis()
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat     // Catch: java.lang.Exception -> L23
            java.lang.String r5 = "yyyy-MM-dd HH:mm:ss"
            java.util.Locale r6 = java.util.Locale.getDefault()     // Catch: java.lang.Exception -> L23
            r0.<init>(r5, r6)     // Catch: java.lang.Exception -> L23
            a.a.a.f.oi$oi r5 = p001a.p002a.p003a.p057f.AuthenticationManager.f2090e     // Catch: java.lang.Exception -> L23
            java.lang.String r5 = r5.f2095c     // Catch: java.lang.Exception -> L23
            java.util.Date r0 = r0.parse(r5)     // Catch: java.lang.Exception -> L23
            long r1 = r0.getTime()     // Catch: java.lang.Exception -> L23
            goto L27
        L23:
            r0 = move-exception
            r0.printStackTrace()
        L27:
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r0 >= 0) goto L56
            goto L58
        L2c:
            a.a.a.f.oi$oi r0 = p001a.p002a.p003a.p057f.AuthenticationManager.f2089d
            if (r0 == 0) goto L58
            long r3 = java.lang.System.currentTimeMillis()
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat     // Catch: java.lang.Exception -> L4d
            java.lang.String r5 = "yyyy-MM-dd HH:mm:ss"
            java.util.Locale r6 = java.util.Locale.getDefault()     // Catch: java.lang.Exception -> L4d
            r0.<init>(r5, r6)     // Catch: java.lang.Exception -> L4d
            a.a.a.f.oi$oi r5 = p001a.p002a.p003a.p057f.AuthenticationManager.f2089d     // Catch: java.lang.Exception -> L4d
            java.lang.String r5 = r5.f2095c     // Catch: java.lang.Exception -> L4d
            java.util.Date r0 = r0.parse(r5)     // Catch: java.lang.Exception -> L4d
            long r1 = r0.getTime()     // Catch: java.lang.Exception -> L4d
            goto L51
        L4d:
            r0 = move-exception
            r0.printStackTrace()
        L51:
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r0 >= 0) goto L56
            goto L58
        L56:
            r0 = 0
            goto L59
        L58:
            r0 = 1
        L59:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.license.LicenseManager.isExpire():boolean");
    }

    public static boolean isExplainSuccess() {
        return (AuthenticationManager.f2089d == null && AuthenticationManager.f2090e == null) ? false : true;
    }

    public static boolean isValid(Context context, String str) {
        return AuthenticationManager.m22351a(context, SDKHttpConfig.appId).m22348b(str);
    }

    public static boolean isValid(String str) {
        return AuthenticationManager.m22351a(sContext, SDKHttpConfig.appId).m22348b(str);
    }
}
