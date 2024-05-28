package com.vivo.push.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.j */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class CryptographicTool {
    /* renamed from: a */
    public static String m5391a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (bArr[i] ^ 16);
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public static byte[] m5392a(String str, String str2, byte[] bArr) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), C0108a.f85c);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(str.getBytes("utf-8")));
        return cipher.doFinal(bArr);
    }
}
