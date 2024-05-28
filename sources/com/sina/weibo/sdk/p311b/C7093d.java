package com.sina.weibo.sdk.p311b;

import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.b.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7093d {

    /* renamed from: aj */
    private static final char[] f18306aj = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: f */
    public static String m8069f(String str) {
        try {
            return m8070a(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m8070a(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            char[] cArr = new char[32];
            int i = 0;
            for (int i2 = 0; i2 < 16; i2++) {
                byte b = digest[i2];
                int i3 = i + 1;
                cArr[i] = f18306aj[(b >>> 4) & 15];
                i = i3 + 1;
                cArr[i3] = f18306aj[b & 15];
            }
            return new String(cArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
