package com.xiaomi.push;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11425h {

    /* renamed from: a */
    private static final byte[] f22942a = {100, 23, 84, 114, 72, 0, 4, 97, 73, 97, 2, 52, 84, 102, 18, 32};

    /* renamed from: a */
    public static byte[] m3410a(byte[] bArr, byte[] bArr2) {
        return m3411a(bArr, 2).doFinal(bArr2);
    }

    /* renamed from: b */
    public static byte[] m3409b(byte[] bArr, byte[] bArr2) {
        return m3411a(bArr, 1).doFinal(bArr2);
    }

    /* renamed from: a */
    private static Cipher m3411a(byte[] bArr, int i) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, C0108a.f85c);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(f22942a);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(i, secretKeySpec, ivParameterSpec);
        return cipher;
    }
}
