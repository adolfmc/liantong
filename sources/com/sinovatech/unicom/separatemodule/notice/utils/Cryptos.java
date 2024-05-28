package com.sinovatech.unicom.separatemodule.notice.utils;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Cryptos {
    private static final String AES = "AES";
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final int DEFAULT_AES_KEYSIZE = 128;
    private static final int DEFAULT_IVSIZE = 16;
    private static SecureRandom random = new SecureRandom();

    public static byte[] aesEncrypt(byte[] bArr, byte[] bArr2) {
        return aes(bArr, bArr2, 1);
    }

    public static byte[] aesEncrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return aes(bArr, bArr2, bArr3, 1);
    }

    public static String aesDecrypt(byte[] bArr, byte[] bArr2) {
        return new String(aes(bArr, bArr2, 2));
    }

    public static String aesDecrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return new String(aes(bArr, bArr2, bArr3, 2));
    }

    private static byte[] aes(byte[] bArr, byte[] bArr2, int i) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, C0108a.f85c);
            Cipher cipher = Cipher.getInstance(C0108a.f85c);
            cipher.init(i, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    private static byte[] aes(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, C0108a.f85c);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(i, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (GeneralSecurityException e) {
            System.out.println(e);
            return null;
        }
    }

    public static byte[] generateAesKey() {
        return generateAesKey(128);
    }

    public static byte[] generateAesKey(int i) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(C0108a.f85c);
            keyGenerator.init(i);
            return keyGenerator.generateKey().getEncoded();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static byte[] generateIV() {
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        return bArr;
    }
}
