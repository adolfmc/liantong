package com.alipay.sdk.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.encrypt.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2016f {

    /* renamed from: a */
    private static String f3765a = "DESede/CBC/PKCS5Padding";

    /* renamed from: a */
    public static byte[] m20829a(String str, byte[] bArr, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            Cipher cipher = Cipher.getInstance(f3765a);
            cipher.init(1, secretKeySpec, new IvParameterSpec(C2014d.m20833a(cipher, str2)));
            return cipher.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m20827b(String str, byte[] bArr, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            Cipher cipher = Cipher.getInstance(f3765a);
            cipher.init(2, secretKeySpec, new IvParameterSpec(C2014d.m20833a(cipher, str2)));
            return cipher.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m20830a(String str, String str2, String str3) {
        try {
            return C2011a.m20842a(m20829a(str, str2.getBytes(), str3));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static String m20828b(String str, String str2, String str3) {
        try {
            return new String(m20827b(str, C2011a.m20843a(str2), str3));
        } catch (Exception unused) {
            return null;
        }
    }
}
