package com.tydic.softphone.utils;

import com.tydic.softphone.encoder.BASE64Decoder;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class AESUtils {
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    private static final String KEY = "5811e1a58f90ddd0";

    public static byte[] base64Decode(String str) throws Exception {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return new BASE64Decoder().decodeBuffer(str);
    }

    public static byte[] aesEncryptToBytes(String str, String str2) throws Exception {
        Cipher cipher = Cipher.getInstance(C0108a.f87e);
        cipher.init(1, new SecretKeySpec(str2.getBytes(), C0108a.f85c));
        return cipher.doFinal(str.getBytes("utf-8"));
    }

    public static String aesDecryptByBytes(byte[] bArr, String str) throws Exception {
        Cipher cipher = Cipher.getInstance(C0108a.f87e);
        cipher.init(2, new SecretKeySpec(str.getBytes(), C0108a.f85c));
        return new String(cipher.doFinal(bArr));
    }

    public static String aesDecrypt(String str, String str2) throws Exception {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return aesDecryptByBytes(base64Decode(str), str2);
    }
}
