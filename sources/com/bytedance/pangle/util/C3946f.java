package com.bytedance.pangle.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.f */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3946f {

    /* renamed from: a */
    public static String f9371a = "DES/ECB/NoPadding";

    /* renamed from: b */
    public static String f9372b = "DESede/ECB/NoPadding";

    /* renamed from: c */
    private static final char[] f9373c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public static String m16640a(String str) {
        return m16637b(m16638a(str.getBytes(), "MD5"));
    }

    /* renamed from: a */
    private static byte[] m16638a(byte[] bArr, String str) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private static String m16637b(byte[] bArr) {
        int length;
        if (bArr != null && (length = bArr.length) > 0) {
            char[] cArr = new char[length << 1];
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i + 1;
                char[] cArr2 = f9373c;
                cArr[i] = cArr2[(bArr[i2] >>> 4) & 15];
                i = i3 + 1;
                cArr[i3] = cArr2[bArr[i2] & 15];
            }
            return new String(cArr);
        }
        return null;
    }

    /* renamed from: a */
    public static String m16639a(byte[] bArr) {
        return m16637b(m16638a(bArr, "MD5"));
    }
}
