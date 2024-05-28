package com.huawei.secure.android.common.encrypt.utils;

import android.os.Build;
import android.util.Base64;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class EncryptUtil {

    /* renamed from: a */
    private static final String f11970a = "EncryptUtil";

    /* renamed from: b */
    private static final String f11971b = "RSA";

    /* renamed from: c */
    private static boolean f11972c = true;

    /* renamed from: d */
    private static boolean f11973d = true;

    /* JADX WARN: Removed duplicated region for block: B:23:0x001d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.security.SecureRandom m13961a() {
        /*
            java.lang.String r0 = "EncryptUtil"
            java.lang.String r1 = "generateSecureRandomNew "
            com.huawei.secure.android.common.encrypt.utils.C5106b.m13944a(r0, r1)
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.security.NoSuchAlgorithmException -> L12
            r1 = 26
            if (r0 < r1) goto L19
            java.security.SecureRandom r0 = java.security.SecureRandom.getInstanceStrong()     // Catch: java.security.NoSuchAlgorithmException -> L12
            goto L1a
        L12:
            java.lang.String r0 = "EncryptUtil"
            java.lang.String r1 = "getSecureRandomBytes: NoSuchAlgorithmException"
            com.huawei.secure.android.common.encrypt.utils.C5106b.m13942b(r0, r1)
        L19:
            r0 = 0
        L1a:
            r1 = 0
            if (r0 != 0) goto L26
            java.lang.String r2 = "SHA1PRNG"
            java.security.SecureRandom r0 = java.security.SecureRandom.getInstance(r2)     // Catch: java.lang.Throwable -> L24 java.security.NoSuchAlgorithmException -> L6b
            goto L26
        L24:
            r2 = move-exception
            goto L45
        L26:
            r2 = 1
            org.bouncycastle.crypto.engines.AESEngine r3 = new org.bouncycastle.crypto.engines.AESEngine     // Catch: java.lang.Throwable -> L24 java.security.NoSuchAlgorithmException -> L6b
            r3.<init>()     // Catch: java.lang.Throwable -> L24 java.security.NoSuchAlgorithmException -> L6b
            r4 = 256(0x100, float:3.59E-43)
            r5 = 384(0x180, float:5.38E-43)
            r6 = 32
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L24 java.security.NoSuchAlgorithmException -> L6b
            r0.nextBytes(r6)     // Catch: java.lang.Throwable -> L24 java.security.NoSuchAlgorithmException -> L6b
            org.bouncycastle.crypto.prng.SP800SecureRandomBuilder r7 = new org.bouncycastle.crypto.prng.SP800SecureRandomBuilder     // Catch: java.lang.Throwable -> L24 java.security.NoSuchAlgorithmException -> L6b
            r7.<init>(r0, r2)     // Catch: java.lang.Throwable -> L24 java.security.NoSuchAlgorithmException -> L6b
            org.bouncycastle.crypto.prng.SP800SecureRandomBuilder r2 = r7.setEntropyBitsRequired(r5)     // Catch: java.lang.Throwable -> L24 java.security.NoSuchAlgorithmException -> L6b
            org.bouncycastle.crypto.prng.SP800SecureRandom r0 = r2.buildCTR(r3, r4, r6, r1)     // Catch: java.lang.Throwable -> L24 java.security.NoSuchAlgorithmException -> L6b
            return r0
        L45:
            boolean r3 = com.huawei.secure.android.common.encrypt.utils.EncryptUtil.f11973d
            if (r3 == 0) goto L72
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "exception : "
            r3.append(r4)
            java.lang.String r2 = r2.getMessage()
            r3.append(r2)
            java.lang.String r2 = " , you should implementation bcprov-jdk15on library"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            java.lang.String r3 = "EncryptUtil"
            com.huawei.secure.android.common.encrypt.utils.C5106b.m13942b(r3, r2)
            com.huawei.secure.android.common.encrypt.utils.EncryptUtil.f11973d = r1
            goto L72
        L6b:
            java.lang.String r1 = "EncryptUtil"
            java.lang.String r2 = "NoSuchAlgorithmException"
            com.huawei.secure.android.common.encrypt.utils.C5106b.m13942b(r1, r2)
        L72:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.secure.android.common.encrypt.utils.EncryptUtil.m13961a():java.security.SecureRandom");
    }

    public static SecureRandom genSecureRandom() {
        if (!f11972c) {
            SecureRandom secureRandom = null;
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    secureRandom = SecureRandom.getInstanceStrong();
                } else {
                    secureRandom = SecureRandom.getInstance("SHA1PRNG");
                }
            } catch (NoSuchAlgorithmException unused) {
                C5106b.m13942b("EncryptUtil", "genSecureRandom: NoSuchAlgorithmException");
            }
            return secureRandom;
        }
        return m13961a();
    }

    public static byte[] generateSecureRandom(int i) {
        if (!f11972c) {
            byte[] bArr = new byte[i];
            SecureRandom secureRandom = null;
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    secureRandom = SecureRandom.getInstanceStrong();
                }
            } catch (NoSuchAlgorithmException unused) {
                C5106b.m13942b("EncryptUtil", "getSecureRandomBytes: NoSuchAlgorithmException");
            }
            if (secureRandom == null) {
                try {
                    secureRandom = SecureRandom.getInstance("SHA1PRNG");
                } catch (NoSuchAlgorithmException unused2) {
                    C5106b.m13942b("EncryptUtil", "getSecureRandomBytes getInstance: NoSuchAlgorithmException");
                    return new byte[0];
                } catch (Exception e) {
                    C5106b.m13942b("EncryptUtil", "getSecureRandomBytes getInstance: exception : " + e.getMessage());
                    return new byte[0];
                }
            }
            secureRandom.nextBytes(bArr);
            return bArr;
        }
        return m13960a(i);
    }

    public static String generateSecureRandomStr(int i) {
        return HexUtil.byteArray2HexStr(generateSecureRandom(i));
    }

    public static PrivateKey getPrivateKey(String str) {
        try {
            try {
                return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str, 0)));
            } catch (GeneralSecurityException e) {
                C5106b.m13942b("EncryptUtil", "load Key Exception:" + e.getMessage());
                return null;
            }
        } catch (IllegalArgumentException unused) {
            C5106b.m13942b("EncryptUtil", "base64 decode IllegalArgumentException");
            return null;
        } catch (Exception e2) {
            C5106b.m13942b("EncryptUtil", "base64 decode Exception" + e2.getMessage());
            return null;
        }
    }

    public static RSAPublicKey getPublicKey(String str) {
        try {
            try {
                return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
            } catch (GeneralSecurityException e) {
                C5106b.m13942b("EncryptUtil", "load Key Exception:" + e.getMessage());
                return null;
            }
        } catch (IllegalArgumentException unused) {
            C5106b.m13942b("EncryptUtil", "base64 decode IllegalArgumentException");
            return null;
        } catch (Exception e2) {
            C5106b.m13942b("EncryptUtil", "base64 decode Exception" + e2.getMessage());
            return null;
        }
    }

    public static boolean isBouncycastleFlag() {
        return f11972c;
    }

    public static void setBouncycastleFlag(boolean z) {
        C5106b.m13941c("EncryptUtil", "setBouncycastleFlag: " + z);
        f11972c = z;
    }

    /* renamed from: a */
    private static byte[] m13960a(int i) {
        SecureRandom m13961a = m13961a();
        if (m13961a == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        m13961a.nextBytes(bArr);
        return bArr;
    }
}
