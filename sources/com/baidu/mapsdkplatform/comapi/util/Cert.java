package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import android.content.pm.Signature;
import android.os.Build;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.util.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Cert {
    /* renamed from: a */
    public static String m18176a(Context context) {
        String packageName = context.getPackageName();
        String m18175a = m18175a(context, packageName);
        return m18175a + ";" + packageName;
    }

    /* renamed from: a */
    private static String m18175a(Context context, String str) {
        Signature[] signatureArr;
        String str2 = "";
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                if (context.getPackageManager().getPackageInfo(str, 134217728).signingInfo.hasMultipleSigners()) {
                    signatureArr = context.getPackageManager().getPackageInfo(str, 134217728).signingInfo.getApkContentsSigners();
                } else {
                    signatureArr = context.getPackageManager().getPackageInfo(str, 134217728).signingInfo.getSigningCertificateHistory();
                }
            } else {
                signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
            }
            str2 = m18174a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str2.length(); i++) {
            stringBuffer.append(str2.charAt(i));
            if (i > 0 && i % 2 == 1 && i < str2.length() - 1) {
                stringBuffer.append(":");
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    static String m18174a(X509Certificate x509Certificate) {
        try {
            return C2955a.m18172a(m18173a(x509Certificate.getEncoded()));
        } catch (CertificateEncodingException unused) {
            return null;
        }
    }

    /* renamed from: a */
    static byte[] m18173a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Cert.java */
    /* renamed from: com.baidu.mapsdkplatform.comapi.util.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2955a {
        /* renamed from: a */
        public static String m18172a(byte[] bArr) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuilder sb = new StringBuilder(bArr.length * 2);
            for (int i = 0; i < bArr.length; i++) {
                sb.append(cArr[(bArr[i] & 240) >> 4]);
                sb.append(cArr[bArr[i] & 15]);
            }
            return sb.toString();
        }
    }
}
