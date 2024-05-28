package cn.finalteam.toolsfinal.coder;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class AESCoder {
    private static final String AES_MODE = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final byte[] ivBytes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static SecretKeySpec generateKey(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = str.getBytes("UTF-8");
        messageDigest.update(bytes, 0, bytes.length);
        return new SecretKeySpec(messageDigest.digest(), C0108a.f85c);
    }

    public static String encrypt(String str, String str2) throws GeneralSecurityException {
        try {
            return Base64.encodeToString(encrypt(generateKey(str), ivBytes, str2.getBytes("UTF-8")), 2);
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public static byte[] encrypt(SecretKeySpec secretKeySpec, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, secretKeySpec, new IvParameterSpec(bArr));
        return cipher.doFinal(bArr2);
    }

    public static String decrypt(String str, String str2) throws GeneralSecurityException {
        try {
            return new String(decrypt(generateKey(str), ivBytes, Base64.decode(str2, 2)), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public static byte[] decrypt(SecretKeySpec secretKeySpec, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(bArr));
        return cipher.doFinal(bArr2);
    }

    private AESCoder() {
    }
}
