package com.bytedance.applog;

import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.j2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3609j2 {

    /* renamed from: a */
    public static final char[] f8529a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: a */
    public static String m17257a(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(str.getBytes("UTF-8"));
                    byte[] digest = messageDigest.digest();
                    if (digest != null) {
                        return m17255a(digest, 0, digest.length);
                    }
                    throw new NullPointerException("bytes is null");
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m17255a(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i < 0 || i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException();
            }
            int i3 = i2 * 2;
            char[] cArr = new char[i3];
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = bArr[i5 + i] & 255;
                int i7 = i4 + 1;
                char[] cArr2 = f8529a;
                cArr[i4] = cArr2[i6 >> 4];
                i4 = i7 + 1;
                cArr[i7] = cArr2[i6 & 15];
            }
            return new String(cArr, 0, i3);
        }
        throw new NullPointerException("bytes is null");
    }

    /* renamed from: a */
    public static String m17256a(byte[] bArr) {
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(bArr);
                    byte[] digest = messageDigest.digest();
                    if (digest != null) {
                        return m17255a(digest, 0, digest.length);
                    }
                    throw new NullPointerException("bytes is null");
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
