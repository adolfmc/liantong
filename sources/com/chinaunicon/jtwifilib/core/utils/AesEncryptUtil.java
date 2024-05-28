package com.chinaunicon.jtwifilib.core.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AesEncryptUtil {
    private static final String KEY_ = "lxhf_zw_20220314";

    public static String encryptAesECB(String str, String str2) throws Exception {
        Cipher cipher = Cipher.getInstance(C0108a.f87e);
        cipher.init(1, new SecretKeySpec(str2.getBytes(), C0108a.f85c));
        return ByteUtil.toHexString(cipher.doFinal(str.getBytes()));
    }

    public static String decryptAesECB(String str, String str2) throws Exception {
        Cipher cipher = Cipher.getInstance(C0108a.f87e);
        cipher.init(2, new SecretKeySpec(str2.getBytes(), C0108a.f85c));
        return new String(cipher.doFinal(ByteUtil.hexStringToByte(str)));
    }

    public static String encryptAesCBC(String str, String str2, String str3) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(str2.getBytes(), C0108a.f85c), new IvParameterSpec(str3.getBytes()));
        return ByteUtil.toHexString(cipher.doFinal(str.getBytes()));
    }

    public static String decryptAesCBC(String str, String str2, String str3) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, new SecretKeySpec(str2.getBytes(), C0108a.f85c), new IvParameterSpec(str3.getBytes()));
        return new String(cipher.doFinal(ByteUtil.hexStringToByte(str)));
    }

    public static String encryptString(String str) throws Exception {
        return encryptAesECB(str, "lxhf_zw_20220314");
    }

    public static String decryptString(String str) throws Exception {
        return decryptAesECB(str, "lxhf_zw_20220314");
    }

    public static void main(String[] strArr) throws Exception {
        System.out.println(decryptAesECB(encryptAesECB("{\"appKey\":\"陈世美\",\"user\":\"123123\",\"platform\":\"ios\",\"version\":\"1\",\"uuid\":\"uuid\"}", "lxhf_zw_20220314"), "lxhf_zw_20220314"));
    }
}
