package com.alipay.security.mobile.module.p110a.p111a;

import com.alipay.security.mobile.module.p110a.C2081a;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.a.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2084c {

    /* renamed from: a */
    private static String f3987a = "idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#";

    /* renamed from: a */
    public static String m20564a() {
        String str = new String();
        for (int i = 0; i < f3987a.length() - 1; i += 4) {
            str = str + f3987a.charAt(i);
        }
        return str;
    }

    /* renamed from: a */
    public static String m20562a(String str, String str2) {
        try {
            PBEKeySpec m20563a = m20563a(str);
            byte[] bytes = str2.getBytes();
            byte[] m20560b = m20560b();
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(m20563a).getEncoded(), C0108a.f85c);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(m20560b));
            byte[] salt = m20563a.getSalt();
            ByteBuffer allocate = ByteBuffer.allocate(salt.length + cipher.getOutputSize(bytes.length));
            allocate.put(salt);
            cipher.doFinal(ByteBuffer.wrap(bytes), allocate);
            return m20561a(allocate.array());
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m20561a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15));
            stringBuffer.append("0123456789ABCDEF".charAt(b & 15));
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private static PBEKeySpec m20563a(String str) {
        Class<?> cls = Class.forName(new String(C2082a.m20566a("amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20=")));
        Object newInstance = cls.newInstance();
        byte[] bArr = new byte[16];
        Method method = cls.getMethod("nextBytes", bArr.getClass());
        method.setAccessible(true);
        method.invoke(newInstance, bArr);
        return new PBEKeySpec(str.toCharArray(), bArr, 10, 128);
    }

    /* renamed from: b */
    public static String m20559b(String str, String str2) {
        byte[] doFinal;
        try {
            PBEKeySpec m20563a = m20563a(str);
            int length = str2.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr[i] = Integer.valueOf(str2.substring(i2, i2 + 2), 16).byteValue();
            }
            byte[] m20560b = m20560b();
            if (bArr.length <= 16) {
                doFinal = null;
            } else {
                SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(m20563a.getPassword(), Arrays.copyOf(bArr, 16), 10, 128)).getEncoded(), C0108a.f85c);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(2, secretKeySpec, new IvParameterSpec(m20560b));
                doFinal = cipher.doFinal(bArr, 16, bArr.length - 16);
            }
        } catch (Exception unused) {
        }
        if (doFinal != null) {
            String str3 = new String(doFinal);
            if (C2081a.m20571c(str3)) {
                return str3;
            }
            return null;
        }
        throw new Exception();
    }

    /* renamed from: b */
    private static byte[] m20560b() {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 48; i += 2) {
                sb.append("AsAgAtA5A6AdAgABABACADAfAsAdAfAsAgAaAgA3A5A6=8=0".charAt(i));
            }
            return C2082a.m20566a(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
