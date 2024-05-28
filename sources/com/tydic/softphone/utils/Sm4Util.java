package com.tydic.softphone.utils;

import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.legacy.math.linearalgebra.ByteUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Sm4Util {
    public static final String ALGORITHM_NAME = "SM4";
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    public static final int DEFAULT_KEY_SIZE = 128;
    private static final String ENCODING = "UTF-8";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static Cipher generateEcbCipher(String str, int i, byte[] bArr) throws Exception {
        Security.removeProvider("BC");
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(str, "BC");
        cipher.init(i, new SecretKeySpec(bArr, "SM4"));
        return cipher;
    }

    public static byte[] generateKey() throws Exception {
        return generateKey(128);
    }

    public static byte[] generateKey(int i) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("SM4", "BC");
        keyGenerator.init(i, new SecureRandom());
        return keyGenerator.generateKey().getEncoded();
    }

    public static String generateStrKey(int i) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("SM4", "BC");
        keyGenerator.init(i, new SecureRandom());
        return ByteUtils.toHexString(keyGenerator.generateKey().getEncoded());
    }

    public static String encryptEcb(String str, String str2) throws Exception {
        return ByteUtils.toHexString(encrypt_Ecb_Padding(ByteUtils.fromHexString(str), str2.getBytes("UTF-8")));
    }

    public static byte[] encrypt_Ecb_Padding(byte[] bArr, byte[] bArr2) throws Exception {
        return generateEcbCipher("SM4/ECB/PKCS5Padding", 1, bArr).doFinal(bArr2);
    }

    public static String decryptEcb(String str, String str2) throws Exception {
        return new String(decrypt_Ecb_Padding(ByteUtils.fromHexString(str), ByteUtils.fromHexString(str2)), "UTF-8");
    }

    public static byte[] decrypt_Ecb_Padding(byte[] bArr, byte[] bArr2) throws Exception {
        return generateEcbCipher("SM4/ECB/PKCS5Padding", 2, bArr).doFinal(bArr2);
    }

    public static boolean verifyEcb(String str, String str2, String str3) throws Exception {
        return Arrays.equals(decrypt_Ecb_Padding(ByteUtils.fromHexString(str), ByteUtils.fromHexString(str2)), str3.getBytes("UTF-8"));
    }

    public static void main(String[] strArr) throws Exception {
        System.out.println("c27aa727b678ab002df558ed22f3311c");
        System.out.println(encryptEcb("c27aa727b678ab002df558ed22f3311c", "{\"answer_stamp\":\"2021-04-28 17:06:10\",\"appid\":\"100001\",\"billsec\":\"33\",\"call_id\":\"uNhdxNkFvsmyacU5SXR9c6U\",\"call_uuid\":\"614796fe-e4cc-4001-a35d-53e6486f046e\",\"callee_id_number\":\"18514235065\",\"caller_attribution\":\"917\",\"caller_id_number\":\"18604076813\",\"direction\":\"inbound\",\"duration\":\"45\",\"end_stamp\":\"2021-04-28 17:06:43\",\"hangup_cause\":\"NORMAL_CLEARING\",\"outside_call\":\"1\",\"record_session\":\"http://10.169.38.248:8083/fs228/2021-04-28/2021-04-28-17-05-58_G9psS518514235065_18604076813.wav\",\"start_stamp\":\"2021-04-28 17:05:58\",\"states\":\"2\"}"));
        System.out.println(decryptEcb("c27aa727b678ab002df558ed22f3311c", "e02ac5f79c66156a4ce9b125176adb111808b8991244495d2dc1fcec7b2ae4588dd444d6b865b2c72930c8663025c163ee28b61df7d7a63f7d01cd94456c34c2ea9351fd332fe4bda45627af2811d08d7dc9ddb16547a180dbba8eb47541d473"));
    }
}
