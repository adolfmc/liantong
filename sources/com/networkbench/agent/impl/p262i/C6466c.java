package com.networkbench.agent.impl.p262i;

import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.i.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6466c {

    /* renamed from: a */
    private static final String f16342a = "AES";

    /* renamed from: b */
    private static final String f16343b = "AES/CBC/PKCS5Padding";

    /* renamed from: c */
    private static String f16344c;

    /* renamed from: d */
    private static byte[] f16345d;

    /* renamed from: e */
    private static InterfaceC6393e f16346e = C6638h.f17124y;

    /* renamed from: a */
    public static byte[] m9920a(byte[] bArr) throws Exception {
        byte[] bArr2;
        if (bArr == null || bArr.length == 0 || (bArr2 = f16345d) == null) {
            return new byte[0];
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, C0108a.f85c);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(f16344c.getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    /* renamed from: b */
    public static byte[] m9918b(byte[] bArr) throws Exception {
        if (bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(f16345d, C0108a.f85c);
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(f16344c.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            throw new RuntimeException("failed to decrypt ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public static byte[] m9917c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: d */
    public static byte[] m9916d(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
            byte[] bArr2 = new byte[256];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read < 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public static void m9921a(String str) {
        if (str != null) {
            f16344c = str;
        }
    }

    /* renamed from: b */
    public static void m9919b(String str) {
        if (str != null) {
            f16345d = C6653u.m8702f(str);
        }
    }

    /* renamed from: a */
    public static String m9922a() {
        return f16344c;
    }
}
