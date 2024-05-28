package com.networkbench.agent.impl.util;

import com.example.asus.detectionandalign.animation.C4280b;
import com.sdk.p285a.C6960d;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.p */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6647p {

    /* renamed from: a */
    private static final String[] f17230a = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", C4280b.f10047a, "c", C6960d.f18019d, "e", "f"};

    /* renamed from: a */
    public static String m8789a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("UT SHA256 str is null");
        }
        return m8788a(str, "SHA-1");
    }

    /* renamed from: b */
    public static String m8786b(String str) {
        if (str == null) {
            throw new IllegalArgumentException("UT SHA256 str is null");
        }
        return m8788a(str, "SHA-256");
    }

    /* renamed from: c */
    public static String m8785c(String str) {
        if (str == null) {
            throw new IllegalArgumentException("UT SHA256 str is null");
        }
        return m8788a(str, "SHA-512");
    }

    /* renamed from: a */
    private static String m8788a(String str, String str2) {
        if (str != null && str.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str2);
                messageDigest.update(str.getBytes());
                byte[] digest = messageDigest.digest();
                StringBuffer stringBuffer = new StringBuffer();
                for (byte b : digest) {
                    String hexString = Integer.toHexString(b & 255);
                    if (hexString.length() == 1) {
                        stringBuffer.append('0');
                    }
                    stringBuffer.append(hexString);
                }
                return stringBuffer.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: d */
    public static String m8784d(String str) {
        try {
            return m8787a(MessageDigest.getInstance("md5").digest(str.getBytes())).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m8787a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(m8790a(b));
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r3 = r3;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m8790a(byte r3) {
        /*
            if (r3 >= 0) goto L4
            int r3 = r3 + 256
        L4:
            int r0 = r3 / 16
            int r3 = r3 % 16
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[] r2 = com.networkbench.agent.impl.util.C6647p.f17230a
            r0 = r2[r0]
            r1.append(r0)
            java.lang.String[] r0 = com.networkbench.agent.impl.util.C6647p.f17230a
            r3 = r0[r3]
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.util.C6647p.m8790a(byte):java.lang.String");
    }
}
