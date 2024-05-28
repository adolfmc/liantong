package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.rsa.utils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RSAEncryptUtil {
    public static final String KEY_ALGORITHM = "RSA";
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final int MAX_ENCRYPT_BLOCK = 117;
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    public static final String PUBLIC_KEY = "RSAPublicKey";

    public static Map<String, String> genKeyPair() {
        HashMap hashMap;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
            String encodeToString = Base64.getEncoder().encodeToString(((RSAPublicKey) generateKeyPair.getPublic()).getEncoded());
            String encodeToString2 = Base64.getEncoder().encodeToString(((RSAPrivateKey) generateKeyPair.getPrivate()).getEncoded());
            hashMap = new HashMap(2);
            try {
                hashMap.put("RSAPublicKey", encodeToString);
                hashMap.put("RSAPrivateKey", encodeToString2);
            } catch (Exception e) {
                e = e;
                e.getStackTrace();
                return hashMap;
            }
        } catch (Exception e2) {
            e = e2;
            hashMap = null;
        }
        return hashMap;
    }

    public static byte[] decryptByPrivateKey(byte[] bArr, String str) throws Exception {
        byte[] doFinal;
        PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(str)));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, generatePrivate);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = length - i;
            if (i3 > 0) {
                if (i3 > 128) {
                    doFinal = cipher.doFinal(bArr, i, 128);
                } else {
                    doFinal = cipher.doFinal(bArr, i, i3);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i2++;
                i = i2 * 128;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static String decryptDataOnJava(String str, String str2) {
        try {
            return new String(decryptByPrivateKey(Base64.getDecoder().decode(str), str2));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] encryptByPublicKey(byte[] bArr, String str) throws Exception {
        byte[] doFinal;
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(str)));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, generatePublic);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = length - i;
            if (i3 > 0) {
                if (i3 > 117) {
                    doFinal = cipher.doFinal(bArr, i, 117);
                } else {
                    doFinal = cipher.doFinal(bArr, i, i3);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i2++;
                i = i2 * 117;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static String addSign(String str, String str2) throws Exception {
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(str));
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign((RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(pKCS8EncodedKeySpec));
        signature.update(str2.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    public static boolean verifySign(String str, String str2, String str3) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(str));
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initVerify((RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec));
        byte[] decode = Base64.getDecoder().decode(str3);
        signature.update(str2.getBytes(StandardCharsets.UTF_8));
        return signature.verify(decode);
    }
}
