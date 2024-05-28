package com.heytap.msp.push.encrypt;

import com.heytap.mcssdk.utils.C4746d;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AESEncrypt {
    private static final String ALGORITHM = "AES";
    private static final String IV_CONNECT = "%IV1%";
    private static final int KEY_BYTE_SIZE = 256;
    public static final String SDK_APP_SECRET = "isvrbeT7qUywVEZ1Ia0/aUVA/TcFaeV0wC8qFLc8rg4=";
    private static final String TRANSFORMATION = "AES/CTR/NoPadding";

    public static String decrypt(String str, String str2) {
        String[] split = str2.split("%IV1%");
        byte[] m15456b = C4751a.m15456b(split[0]);
        byte[] m15456b2 = C4751a.m15456b(split[1]);
        SecretKeySpec secretKeySpec = new SecretKeySpec(C4751a.m15456b(str), C0108a.f85c);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(m15456b2));
        return new String(cipher.doFinal(m15456b));
    }

    public static String encrypt(String str) {
        try {
            return encrypt("isvrbeT7qUywVEZ1Ia0/aUVA/TcFaeV0wC8qFLc8rg4=", str);
        } catch (Exception e) {
            C4746d.m15494b(e.getLocalizedMessage());
            return "";
        }
    }

    public static String encrypt(String str, String str2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(C4751a.m15456b(str), C0108a.f85c);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(1, secretKeySpec);
        String m15452d = C4751a.m15452d(cipher.getIV());
        String m15452d2 = C4751a.m15452d(cipher.doFinal(str2.getBytes()));
        return m15452d2 + "%IV1%" + m15452d;
    }

    public static String genKey() {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(C0108a.f85c);
        keyGenerator.init(256);
        return C4751a.m15452d(keyGenerator.generateKey().getEncoded());
    }
}
