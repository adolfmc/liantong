package com.baidu.mapsdkplatform.comapi.util;

import android.text.TextUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class AlgorithmUtil {
    private AlgorithmUtil() {
    }

    public static byte[] setUrlNeedInfo(String str, String str2, byte[] bArr) throws Exception {
        return m18186a(str, str2, bArr);
    }

    public static byte[] getUrlNeedInfo(String str, String str2, byte[] bArr) throws Exception {
        return m18185b(str, str2, bArr);
    }

    public static byte[] getDecryptInfo(String str, String str2, byte[] bArr) throws Exception {
        return m18184c(str, str2, bArr);
    }

    /* renamed from: a */
    private static byte[] m18186a(String str, String str2, byte[] bArr) throws Exception {
        if (str2 == null) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), C0108a.f85c);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(1, secretKeySpec, new IvParameterSpec(str.getBytes()));
        int length = bArr.length;
        while (length % 16 != 0) {
            length++;
        }
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            if (i < bArr.length) {
                bArr2[i] = bArr[i];
            } else {
                bArr2[i] = 0;
            }
        }
        return cipher.doFinal(bArr2);
    }

    /* renamed from: b */
    private static byte[] m18185b(String str, String str2, byte[] bArr) throws Exception {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("ASCII"), C0108a.f85c);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(str.getBytes()));
        return cipher.doFinal(bArr);
    }

    /* renamed from: c */
    private static byte[] m18184c(String str, String str2, byte[] bArr) throws Exception {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), C0108a.f85c);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(str.getBytes()));
        return cipher.doFinal(bArr);
    }
}
