package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.aes.utils;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AesEncryptUtil {
    public static String encrypt(String str, String str2, String str3) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(str2.getBytes("UTF-8"), C0108a.f85c), new IvParameterSpec(str3.getBytes("UTF-8")));
        return Base64.encodeToString(cipher.doFinal(str.getBytes("UTF-8")), 2);
    }

    public static String decrypt(String str, String str2, String str3) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, new SecretKeySpec(str2.getBytes("UTF-8"), C0108a.f85c), new IvParameterSpec(str3.getBytes("UTF-8")));
        return new String(cipher.doFinal(Base64.decode(str, 2)), "UTF-8");
    }

    public static String decrypt(String str) throws Exception {
        return decrypt(str, "123download4edop", "123download4edop");
    }
}
