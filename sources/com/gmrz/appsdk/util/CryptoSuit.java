package com.gmrz.appsdk.util;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CryptoSuit {
    private static final int BASE64_FLAG = 25;

    public static String decrypt(String str, String str2) {
        try {
            byte[] bArr = get16Len(str2);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, new SecretKeySpec(bArr, C0108a.f85c), new IvParameterSpec(bArr));
            return new String(cipher.doFinal(Base64.decode(str, 25)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String str, String str2) {
        try {
            byte[] bArr = get16Len(str2);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, new SecretKeySpec(bArr, C0108a.f85c), new IvParameterSpec(bArr));
            return Base64.encodeToString(cipher.doFinal(str.getBytes()), 25);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] get16Len(String str) {
        byte[] bArr = new byte[16];
        byte[] bytes = str.getBytes();
        if (bytes.length > 16) {
            System.arraycopy(bytes, 0, bArr, 0, 16);
        } else {
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            for (int length = bytes.length; length < 16; length++) {
                bArr[length] = 70;
            }
        }
        return bArr;
    }
}
