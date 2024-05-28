package com.p343ta.utdid2.p344a.p345a;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ta.utdid2.a.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10304a {
    /* renamed from: a */
    public static String m6456a(String str) {
        byte[] bArr;
        try {
            bArr = m6452a(m6457a(), str.getBytes());
        } catch (Exception unused) {
            bArr = null;
        }
        if (bArr != null) {
            return m6453a(bArr);
        }
        return null;
    }

    /* renamed from: b */
    public static String m6450b(String str) {
        try {
            return new String(m6449b(m6457a(), m6455a(str)));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static byte[] m6457a() throws Exception {
        return C10312f.m6438a(new byte[]{33, 83, -50, -89, -84, -114, 80, 99, 10, 63, 22, -65, -11, 30, 101, -118});
    }

    /* renamed from: a */
    private static byte[] m6452a(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, C0108a.f85c);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, new IvParameterSpec(m6451b()));
        return cipher.doFinal(bArr2);
    }

    /* renamed from: b */
    private static byte[] m6449b(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, C0108a.f85c);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(m6451b()));
        return cipher.doFinal(bArr2);
    }

    /* renamed from: a */
    private static byte[] m6455a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    /* renamed from: a */
    private static String m6453a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            m6454a(stringBuffer, b);
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private static void m6454a(StringBuffer stringBuffer, byte b) {
        stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15));
        stringBuffer.append("0123456789ABCDEF".charAt(b & 15));
    }

    /* renamed from: b */
    private static byte[] m6451b() {
        try {
            byte[] decode = C10305b.decode("IUQSvE6r1TfFPdPEjfklLw==".getBytes("UTF-8"), 2);
            if (decode != null) {
                return C10312f.m6438a(decode);
            }
        } catch (Exception unused) {
        }
        return new byte[16];
    }
}
