package com.networkbench.agent.impl.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6637g {

    /* renamed from: a */
    private static final String f17095a = "AES";

    /* renamed from: b */
    private static final String f17096b = "AES/CBC/PKCS5Padding";

    /* renamed from: c */
    private static String f17097c;

    /* renamed from: d */
    private static byte[] f17098d;

    /* renamed from: e */
    private static byte[] f17099e;

    /* renamed from: a */
    public static void m9090a(String str) {
        if (str == null) {
            return;
        }
        f17097c = str;
        try {
            f17098d = str.getBytes();
        } catch (Exception e) {
            C6638h.f17124y.mo10121a("setAccessKey error", e);
        }
    }

    /* renamed from: a */
    public static String m9091a() {
        return f17097c;
    }

    /* renamed from: b */
    public static void m9087b(String str) {
        try {
            f17099e = C6653u.m8699g(str);
        } catch (Exception e) {
            C6638h.f17124y.mo10121a("setSecretKey error", e);
        }
    }

    /* renamed from: b */
    public static byte[] m9088b() {
        return f17099e;
    }

    /* renamed from: a */
    public static byte[] m9089a(byte[] bArr) throws Exception {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = f17099e;
        if (bArr2 == null || f17098d == null) {
            throw new IllegalArgumentException("key is required.");
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, C0108a.f85c);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(f17098d);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr);
    }
}
