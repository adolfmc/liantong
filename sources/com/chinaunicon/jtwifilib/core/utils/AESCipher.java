package com.chinaunicon.jtwifilib.core.utils;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AESCipher {
    private static final String IV_STRING = "A-16-Byte-String";
    private static final String KEY = "d165fc6bf49c0549";
    private static final String charset = "UTF-8";

    public static String aesEncryptString(String str) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return Base64.encodeToString(aesEncryptBytes(str.getBytes("UTF-8"), "d165fc6bf49c0549".getBytes("UTF-8")), 0);
    }

    public static byte[] aesEncryptBytes(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return cipherOperation(bArr, bArr2, 1);
    }

    public static byte[] aesDecryptBytes(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return cipherOperation(bArr, bArr2, 2);
    }

    private static byte[] cipherOperation(byte[] bArr, byte[] bArr2, int i) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, C0108a.f85c);
        IvParameterSpec ivParameterSpec = new IvParameterSpec("A-16-Byte-String".getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(i, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr);
    }
}
