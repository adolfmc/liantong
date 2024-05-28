package com.sdk.p293i;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.p290f.C6998d;
import com.sdk.p292h.C7006a;
import com.sdk.p306v.C7042b;
import java.security.MessageDigest;
import java.util.Stack;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.i.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7007a extends C7006a {

    /* renamed from: a */
    public static final String f18154a = "com.sdk.i.a";

    /* renamed from: b */
    public static boolean f18155b = C6998d.f18135a;

    static {
        new Stack();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static int m8163a() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Exception e) {
            LogUtils.m8186e(f18154a, e.getMessage(), Boolean.valueOf(f18155b));
            return -1;
        }
    }

    /* renamed from: a */
    public static String m8162a(Context context) {
        if (context == null) {
            LogUtils.m8184w(f18154a, "mContext 为空", Boolean.valueOf(f18155b));
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Exception e) {
            LogUtils.m8186e(f18154a, e.getMessage(), Boolean.valueOf(f18155b));
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m8161a(android.content.Context r2, java.lang.String r3) {
        /*
            if (r2 == 0) goto L36
            java.lang.Boolean r0 = com.sdk.p302r.C7037a.m8130a(r3)
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto Ld
            goto L36
        Ld:
            android.content.pm.PackageManager r0 = r2.getPackageManager()     // Catch: java.lang.Exception -> L26
            java.lang.String r2 = m8162a(r2)     // Catch: java.lang.Exception -> L26
            r1 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r2 = r0.getApplicationInfo(r2, r1)     // Catch: java.lang.Exception -> L26
            if (r2 == 0) goto L36
            android.os.Bundle r2 = r2.metaData     // Catch: java.lang.Exception -> L26
            if (r2 == 0) goto L36
            java.lang.Object r2 = r2.get(r3)     // Catch: java.lang.Exception -> L26
            goto L37
        L26:
            r2 = move-exception
            java.lang.String r3 = com.sdk.p293i.C7007a.f18154a
            java.lang.String r2 = r2.getMessage()
            boolean r0 = com.sdk.p293i.C7007a.f18155b
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            com.sdk.base.framework.utils.log.LogUtils.m8185i(r3, r2, r0)
        L36:
            r2 = 0
        L37:
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Boolean r3 = com.sdk.p302r.C7037a.m8130a(r2)
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L45
            java.lang.String r2 = com.sdk.p304t.C7039a.f18201c
        L45:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sdk.p293i.C7007a.m8161a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static String m8160a(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            if (i != 0) {
                str = str + ":";
            }
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                str = str + "0";
            }
            str = str + hexString;
        }
        return str;
    }

    /* renamed from: b */
    public static String m8159b(Context context, String str) {
        byte[] digest;
        if (str == null) {
            str = "md5";
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.m8186e(f18154a, e.getMessage(), Boolean.valueOf(f18155b));
        }
        String str2 = "";
        if (packageInfo != null) {
            try {
                byte[] byteArray = packageInfo.signatures[0].toByteArray();
                if (str.equalsIgnoreCase("sm3")) {
                    digest = C7042b.m8121a(byteArray);
                } else {
                    MessageDigest messageDigest = MessageDigest.getInstance(str);
                    messageDigest.update(byteArray);
                    digest = messageDigest.digest();
                }
                str2 = m8160a(digest);
                return str2;
            } catch (Exception e2) {
                LogUtils.m8186e(f18154a, e2.getMessage(), Boolean.valueOf(f18155b));
                return str2;
            }
        }
        return "";
    }
}
