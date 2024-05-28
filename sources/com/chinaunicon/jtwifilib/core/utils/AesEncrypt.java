package com.chinaunicon.jtwifilib.core.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AesEncrypt {
    public static String aesEncrypt(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            int blockSize = cipher.getBlockSize();
            cipher.init(1, new SecretKeySpec(str2.getBytes(), C0108a.f85c));
            return Hex.encodeHexStr(cipher.doFinal(getBytes4ClearText(blockSize, str.getBytes())));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String aesDecrypt(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(2, new SecretKeySpec(str2.getBytes(), C0108a.f85c));
            str.toCharArray();
            return new String(getBytes4CipherText(cipher.doFinal(Hex.decodeHex(str.toCharArray()))));
        } catch (Exception unused) {
            return "";
        }
    }

    private static byte[] getBytes4CipherText(byte[] bArr) {
        int i = 0;
        while (true) {
            if (i >= bArr.length) {
                i = 0;
                break;
            } else if (bArr[i] == 0) {
                break;
            } else {
                i++;
            }
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    private static byte[] getBytes4ClearText(int i, byte[] bArr) {
        int length = bArr.length;
        int i2 = length % i;
        if (i2 == 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[(i + length) - i2];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }
}
