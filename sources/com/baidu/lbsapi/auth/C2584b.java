package com.baidu.lbsapi.auth;

import android.content.Context;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.lbsapi.auth.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2584b {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.lbsapi.auth.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2585a {
        /* renamed from: a */
        public static String m19666a(byte[] bArr) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuilder sb = new StringBuilder(bArr.length * 2);
            for (int i = 0; i < bArr.length; i++) {
                sb.append(cArr[(bArr[i] & 240) >> 4]);
                sb.append(cArr[bArr[i] & 15]);
            }
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m19673a() {
        return Locale.getDefault().getLanguage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static String m19672a(Context context) {
        String packageName = context.getPackageName();
        String m19671a = m19671a(context, packageName);
        return m19671a + ";" + packageName;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x009d A[LOOP:0: B:23:0x009d->B:32:0x00bd, LOOP_START, PHI: r1 
      PHI: (r1v1 int) = (r1v0 int), (r1v2 int) binds: [B:22:0x009b, B:32:0x00bd] A[DONT_GENERATE, DONT_INLINE]] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m19671a(android.content.Context r5, java.lang.String r6) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            r3 = 28
            r4 = 64
            if (r2 < r3) goto L48
            android.content.pm.PackageManager r2 = r5.getPackageManager()     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            r3 = 134217728(0x8000000, float:3.85186E-34)
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r6, r3)     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            android.content.pm.SigningInfo r2 = r2.signingInfo     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            if (r2 != 0) goto L24
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            android.content.pm.PackageInfo r5 = r5.getPackageInfo(r6, r4)     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
        L21:
            android.content.pm.Signature[] r5 = r5.signatures     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            goto L51
        L24:
            boolean r2 = r2.hasMultipleSigners()     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            if (r2 == 0) goto L39
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            android.content.pm.PackageInfo r5 = r5.getPackageInfo(r6, r3)     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            android.content.pm.SigningInfo r5 = r5.signingInfo     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            android.content.pm.Signature[] r5 = r5.getApkContentsSigners()     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            goto L51
        L39:
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            android.content.pm.PackageInfo r5 = r5.getPackageInfo(r6, r3)     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            android.content.pm.SigningInfo r5 = r5.signingInfo     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            android.content.pm.Signature[] r5 = r5.getSigningCertificateHistory()     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            goto L51
        L48:
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            android.content.pm.PackageInfo r5 = r5.getPackageInfo(r6, r4)     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            goto L21
        L51:
            java.lang.String r6 = "X.509"
            java.security.cert.CertificateFactory r6 = java.security.cert.CertificateFactory.getInstance(r6)     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            r5 = r5[r1]     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            byte[] r5 = r5.toByteArray()     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            r2.<init>(r5)     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            java.security.cert.Certificate r5 = r6.generateCertificate(r2)     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            java.security.cert.X509Certificate r5 = (java.security.cert.X509Certificate) r5     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            java.lang.String r0 = m19670a(r5)     // Catch: java.security.cert.CertificateException -> L6d android.content.pm.PackageManager.NameNotFoundException -> L7d
            goto L96
        L6d:
            r5 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r2 = "getFingerPrint："
            r6.append(r2)
            java.lang.String r5 = r5.toString()
            goto L8c
        L7d:
            r5 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r2 = "getFingerPrint："
            r6.append(r2)
            java.lang.String r5 = r5.toString()
        L8c:
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            com.baidu.lbsapi.auth.C2583a.m19676a(r5)
        L96:
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            if (r0 == 0) goto Lc0
        L9d:
            int r6 = r0.length()
            if (r1 >= r6) goto Lc0
            char r6 = r0.charAt(r1)
            r5.append(r6)
            if (r1 <= 0) goto Lbd
            int r6 = r1 % 2
            r2 = 1
            if (r6 != r2) goto Lbd
            int r6 = r0.length()
            int r6 = r6 - r2
            if (r1 >= r6) goto Lbd
            java.lang.String r6 = ":"
            r5.append(r6)
        Lbd:
            int r1 = r1 + 1
            goto L9d
        Lc0:
            java.lang.String r5 = r5.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.C2584b.m19671a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    static String m19670a(X509Certificate x509Certificate) {
        try {
            return C2585a.m19666a(m19669a(x509Certificate.getEncoded()));
        } catch (CertificateEncodingException e) {
            C2583a.m19676a("getFingerprintAs：" + e.toString());
            return null;
        }
    }

    /* renamed from: a */
    static byte[] m19669a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            C2583a.m19676a("generateSHA1：" + e.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static String[] m19668b(Context context) {
        String packageName = context.getPackageName();
        String[] m19667b = m19667b(context, packageName);
        if (m19667b == null || m19667b.length <= 0) {
            return null;
        }
        String[] strArr = new String[m19667b.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = m19667b[i] + ";" + packageName;
            if (C2583a.f4967a) {
                C2583a.m19676a("mcode" + strArr[i]);
            }
        }
        return strArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b9  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String[] m19667b(android.content.Context r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.C2584b.m19667b(android.content.Context, java.lang.String):java.lang.String[]");
    }
}
