package com.huawei.agconnect.config.impl;

import android.util.Log;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.agconnect.config.impl.i */
/* loaded from: E:\10762272_dexfile_execute.dex */
class C4779i {
    /* renamed from: a */
    public static SecretKey m15404a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (bArr.length == 16 && bArr2.length == 16 && bArr3.length == 16) {
            Log.d("AGC_SecretKey", "start generated key");
            SecretKey generateSecret = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(Hex.encodeHexString(m15405a(bArr, bArr2, bArr3)).toCharArray(), bArr4, i, 128));
            Log.d("AGC_SecretKey", "end generated key");
            return new SecretKeySpec(generateSecret.getEncoded(), C0108a.f85c);
        }
        throw new IllegalArgumentException("invalid data for generating the key.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m15408a(SecretKey secretKey, byte[] bArr) throws GeneralSecurityException {
        if (secretKey == null || bArr == null) {
            throw new NullPointerException("key or cipherText must not be null.");
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 1, 17);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKey, new IvParameterSpec(copyOfRange));
        return cipher.doFinal(bArr, copyOfRange.length + 1, (bArr.length - copyOfRange.length) - 1);
    }

    /* renamed from: a */
    private static byte[] m15407a(byte[] bArr, int i) {
        if (bArr != null) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                if (i < 0) {
                    bArr[i2] = (byte) (bArr[i2] << (-i));
                } else {
                    bArr[i2] = (byte) (bArr[i2] >> i);
                }
            }
            return bArr;
        }
        throw new NullPointerException("bytes must not be null.");
    }

    /* renamed from: a */
    private static byte[] m15406a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            throw new NullPointerException("left or right must not be null.");
        }
        if (bArr.length == bArr2.length) {
            byte[] bArr3 = new byte[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
            }
            return bArr3;
        }
        throw new IllegalArgumentException("left and right must be the same length.");
    }

    /* renamed from: a */
    public static byte[] m15405a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return m15406a(m15407a(m15406a(m15407a(bArr, -4), bArr2), 6), bArr3);
    }
}
