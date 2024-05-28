package com.huawei.secure.android.common.encrypt.aes;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.utils.C5106b;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class AesGcm {

    /* renamed from: a */
    private static final String f11889a = "security:";

    /* renamed from: b */
    private static final String f11890b = "AES/GCM/NoPadding";

    /* renamed from: c */
    private static final String f11891c = "AES";

    /* renamed from: d */
    private static final String f11892d = "GCM";

    /* renamed from: e */
    private static final String f11893e = "";

    /* renamed from: f */
    private static final int f11894f = 16;

    /* renamed from: g */
    private static final int f11895g = 12;

    /* renamed from: h */
    private static final int f11896h = 2;

    private AesGcm() {
    }

    /* renamed from: a */
    private static byte[] m14002a(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("GCM", "encrypt 5 content is null");
            return new byte[0];
        } else if (bArr == null) {
            C5106b.m13942b("GCM", "encrypt 5 key is null");
            return new byte[0];
        } else if (bArr.length < 16) {
            C5106b.m13942b("GCM", "encrypt 5 key error: 5 key length less than 16 bytes.");
            return new byte[0];
        } else if (bArr2 == null) {
            C5106b.m13942b("GCM", "encrypt 5 iv is null");
            return new byte[0];
        } else if (bArr2.length < 12) {
            C5106b.m13942b("GCM", "encrypt 5 iv error: 5 iv length less than 16 bytes.");
            return new byte[0];
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "encrypt 5 build version not higher than 19");
            return new byte[0];
        } else {
            try {
                return encrypt(str.getBytes("UTF-8"), bArr, bArr2);
            } catch (UnsupportedEncodingException e) {
                C5106b.m13942b("GCM", "GCM encrypt data error" + e.getMessage());
                return new byte[0];
            }
        }
    }

    /* renamed from: b */
    private static byte[] m13998b(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length - 12];
        System.arraycopy(bArr, 12, bArr2, 0, bArr.length - 12);
        return bArr2;
    }

    /* renamed from: c */
    private static byte[] m13997c(byte[] bArr) {
        byte[] bArr2 = new byte[12];
        System.arraycopy(bArr, 0, bArr2, 0, 12);
        return bArr2;
    }

    public static String decrypt(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("GCM", "decrypt 1 content is null");
            return "";
        } else if (TextUtils.isEmpty(str2)) {
            C5106b.m13942b("GCM", "decrypt 1 key is null");
            return "";
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "decrypt 1 build version not higher than 19");
            return "";
        } else {
            byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str2);
            if (hexStr2ByteArray.length < 16) {
                C5106b.m13942b("GCM", "decrypt 1 key error: 1 key length less than 16 bytes.");
                return "";
            }
            return decrypt(str, hexStr2ByteArray);
        }
    }

    public static String decryptWithCryptHead(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str) || bArr == null || bArr.length < 16) {
            return "";
        }
        String m14006c = AesCbc.m14006c(str);
        if ("".equals(m14006c)) {
            return "";
        }
        int indexOf = m14006c.indexOf(58);
        if (indexOf >= 0) {
            return decrypt(HexUtil.byteArray2HexStr(HexUtil.hexStr2ByteArray(m14006c.substring(indexOf + 1))), bArr, HexUtil.hexStr2ByteArray(m14006c.substring(0, indexOf)));
        }
        C5106b.m13942b("GCM", " gcm cipherText data missing colon");
        return "";
    }

    public static byte[] decryptWithCryptHeadReturnByte(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null && bArr2.length >= 16) {
            byte[] m14004d = AesCbc.m14004d(bArr);
            if (m14004d.length == 0) {
                return new byte[0];
            }
            int m14001a = m14001a(m14004d);
            if (m14001a >= 0) {
                byte[] copyOf = Arrays.copyOf(m14004d, m14001a);
                int length = (m14004d.length - copyOf.length) - 1;
                byte[] bArr3 = new byte[length];
                System.arraycopy(m14004d, m14001a + 1, bArr3, 0, length);
                return decrypt(bArr3, bArr2, copyOf);
            }
            C5106b.m13942b("GCM", " gcm cipherText data missing colon");
            return new byte[0];
        }
        return new byte[0];
    }

    public static String encrypt(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("GCM", "encrypt 1 content is null");
            return "";
        } else if (TextUtils.isEmpty(str2)) {
            C5106b.m13942b("GCM", "encrypt 1 key is null");
            return "";
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "encrypt 1 build version not higher than 19");
            return "";
        } else {
            byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str2);
            if (hexStr2ByteArray.length < 16) {
                C5106b.m13942b("GCM", "encrypt key error: key length less than 16 bytes.");
                return "";
            }
            return encrypt(str, hexStr2ByteArray);
        }
    }

    public static AlgorithmParameterSpec getGcmAlgorithmParams(byte[] bArr) {
        if (Build.VERSION.SDK_INT < 21) {
            return new IvParameterSpec(bArr);
        }
        return new GCMParameterSpec(128, bArr);
    }

    public static boolean isBuildVersionHigherThan19() {
        return Build.VERSION.SDK_INT >= 19;
    }

    /* renamed from: b */
    private static String m13999b(String str) {
        if (!TextUtils.isEmpty(str) && str.length() >= 24) {
            return str.substring(0, 24);
        }
        C5106b.m13942b("GCM", "IV is invalid.");
        return "";
    }

    public static String decrypt(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("GCM", "decrypt 2 content is null");
            return "";
        } else if (bArr == null) {
            C5106b.m13942b("GCM", "decrypt 2 key is null");
            return "";
        } else if (bArr.length < 16) {
            C5106b.m13942b("GCM", "decrypt 2 key error: 2 key length less than 16 bytes.");
            return "";
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "decrypt 2 build version not higher than 19");
            return "";
        } else {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, C0108a.f85c);
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                String m13999b = m13999b(str);
                String m14003a = m14003a(str);
                if (TextUtils.isEmpty(m13999b)) {
                    C5106b.m13942b("GCM", "decrypt 2 iv is null");
                    return "";
                } else if (TextUtils.isEmpty(m14003a)) {
                    C5106b.m13942b("GCM", "decrypt 2 encrypt content is null");
                    return "";
                } else {
                    cipher.init(2, secretKeySpec, getGcmAlgorithmParams(HexUtil.hexStr2ByteArray(m13999b)));
                    return new String(cipher.doFinal(HexUtil.hexStr2ByteArray(m14003a)), "UTF-8");
                }
            } catch (UnsupportedEncodingException | NullPointerException | GeneralSecurityException e) {
                C5106b.m13942b("GCM", "GCM decrypt data exception: " + e.getMessage());
                return "";
            }
        }
    }

    public static String decryptWithCryptHead(byte[] bArr, byte[] bArr2) {
        try {
            return new String(decryptWithCryptHeadReturnByte(bArr, bArr2), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            C5106b.m13942b("GCM", "UnsupportedEncodingException");
            return "";
        }
    }

    public static String encrypt(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("GCM", "encrypt 2 content is null");
            return "";
        } else if (bArr == null) {
            C5106b.m13942b("GCM", "encrypt 2 key is null");
            return "";
        } else if (bArr.length < 16) {
            C5106b.m13942b("GCM", "encrypt 2 key error: 2 key length less than 16 bytes.");
            return "";
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "encrypt 2 build version not higher than 19");
            return "";
        } else {
            byte[] generateSecureRandom = EncryptUtil.generateSecureRandom(12);
            byte[] m14002a = m14002a(str, bArr, generateSecureRandom);
            if (m14002a == null || m14002a.length == 0) {
                return "";
            }
            String byteArray2HexStr = HexUtil.byteArray2HexStr(generateSecureRandom);
            String byteArray2HexStr2 = HexUtil.byteArray2HexStr(m14002a);
            return byteArray2HexStr + byteArray2HexStr2;
        }
    }

    /* renamed from: a */
    private static byte[] m14000a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* renamed from: a */
    private static String m14003a(String str) {
        return (TextUtils.isEmpty(str) || str.length() < 24) ? "" : str.substring(24);
    }

    /* renamed from: a */
    private static int m14001a(byte[] bArr) {
        return bArr[12] == 58 ? 12 : -1;
    }

    public static String encrypt(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("GCM", "encrypt 3 content is null");
            return "";
        } else if (TextUtils.isEmpty(str2)) {
            C5106b.m13942b("GCM", "encrypt 3 key is null");
            return "";
        } else if (TextUtils.isEmpty(str3)) {
            C5106b.m13942b("GCM", "encrypt 3 iv is null");
            return "";
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "encrypt 3 build version not higher than 19");
            return "";
        } else {
            byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str2);
            byte[] hexStr2ByteArray2 = HexUtil.hexStr2ByteArray(str3);
            if (hexStr2ByteArray.length < 16) {
                C5106b.m13942b("GCM", "encrypt 3 key error: 3 key length less than 16 bytes.");
                return "";
            } else if (hexStr2ByteArray2.length < 12) {
                C5106b.m13942b("GCM", "encrypt 3 iv error: 3 iv length less than 16 bytes.");
                return "";
            } else {
                return encrypt(str, hexStr2ByteArray, hexStr2ByteArray2);
            }
        }
    }

    public static String decrypt(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("GCM", "decrypt 3 content is null");
            return "";
        } else if (TextUtils.isEmpty(str2)) {
            C5106b.m13942b("GCM", "decrypt 3 key is null");
            return "";
        } else if (TextUtils.isEmpty(str3)) {
            C5106b.m13942b("GCM", "decrypt 3 iv is null");
            return "";
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "decrypt 3 build version not higher than 19");
            return "";
        } else {
            byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str2);
            byte[] hexStr2ByteArray2 = HexUtil.hexStr2ByteArray(str3);
            if (hexStr2ByteArray.length < 16) {
                C5106b.m13942b("GCM", "decrypt 3 key error: 3 key length less than 16 bytes.");
                return "";
            } else if (hexStr2ByteArray2.length < 12) {
                C5106b.m13942b("GCM", "decrypt 3 iv error: 3 iv length less than 16 bytes.");
                return "";
            } else {
                return decrypt(str, hexStr2ByteArray, hexStr2ByteArray2);
            }
        }
    }

    public static String encrypt(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("GCM", "encrypt 4 content is null");
            return "";
        } else if (bArr == null) {
            C5106b.m13942b("GCM", "encrypt 4 key is null");
            return "";
        } else if (bArr.length < 16) {
            C5106b.m13942b("GCM", "encrypt 4 key error: 3 key length less than 16 bytes.");
            return "";
        } else if (bArr2 == null) {
            C5106b.m13942b("GCM", "encrypt 4 iv is null");
            return "";
        } else if (bArr2.length < 12) {
            C5106b.m13942b("GCM", "encrypt 3 iv error: 3 iv length less than 16 bytes.");
            return "";
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "encrypt 4 build version not higher than 19");
            return "";
        } else {
            return HexUtil.byteArray2HexStr(m14002a(str, bArr, bArr2));
        }
    }

    public static String decrypt(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("GCM", "decrypt 4 content is null");
            return "";
        } else if (bArr == null) {
            C5106b.m13942b("GCM", "decrypt 4 key is null");
            return "";
        } else if (bArr.length < 16) {
            C5106b.m13942b("GCM", "decrypt 4 key error: 4 key length less than 16 bytes.");
            return "";
        } else if (bArr2 == null) {
            C5106b.m13942b("GCM", "decrypt 4 iv is null");
            return "";
        } else if (bArr2.length < 12) {
            C5106b.m13942b("GCM", "decrypt 4 iv error: 4 iv length less than 16 bytes.");
            return "";
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "decrypt 4 build version not higher than 19");
            return "";
        } else {
            try {
                return new String(decrypt(HexUtil.hexStr2ByteArray(str), bArr, bArr2), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                C5106b.m13942b("GCM", "GCM decrypt data exception: " + e.getMessage());
                return "";
            }
        }
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            C5106b.m13942b("GCM", "encrypt 6 content is null");
            return new byte[0];
        } else if (bArr.length == 0) {
            C5106b.m13942b("GCM", "encrypt 6 content length is 0");
            return new byte[0];
        } else if (bArr2 == null) {
            C5106b.m13942b("GCM", "encrypt 6 key is null");
            return new byte[0];
        } else if (bArr2.length < 16) {
            C5106b.m13942b("GCM", "encrypt 6 key error: 6 key length less than 16 bytes.");
            return new byte[0];
        } else if (bArr3 == null) {
            C5106b.m13942b("GCM", "encrypt 6 iv is null");
            return new byte[0];
        } else if (bArr3.length < 12) {
            C5106b.m13942b("GCM", "encrypt 6 iv error: 6 iv length less than 16 bytes.");
            return new byte[0];
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "encrypt 6 build version not higher than 19");
            return new byte[0];
        } else {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, C0108a.f85c);
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(1, secretKeySpec, getGcmAlgorithmParams(bArr3));
                return cipher.doFinal(bArr);
            } catch (NullPointerException e) {
                C5106b.m13942b("GCM", "GCM encrypt data error" + e.getMessage());
                return new byte[0];
            } catch (GeneralSecurityException e2) {
                C5106b.m13942b("GCM", "GCM encrypt data error" + e2.getMessage());
                return new byte[0];
            }
        }
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            C5106b.m13942b("GCM", "decrypt 6 content is null");
            return new byte[0];
        } else if (bArr.length == 0) {
            C5106b.m13942b("GCM", "decrypt 6 content length is 0");
            return new byte[0];
        } else if (bArr2 == null) {
            C5106b.m13942b("GCM", "decrypt 6 key is null");
            return new byte[0];
        } else if (bArr2.length < 16) {
            C5106b.m13942b("GCM", "decrypt 6 key error: 6 key length less than 16 bytes.");
            return new byte[0];
        } else if (bArr3 == null) {
            C5106b.m13942b("GCM", "decrypt 6 iv is null");
            return new byte[0];
        } else if (bArr3.length < 12) {
            C5106b.m13942b("GCM", "decrypt 6 iv error: 6 iv length less than 16 bytes.");
            return new byte[0];
        } else if (!isBuildVersionHigherThan19()) {
            C5106b.m13942b("GCM", "decrypt 6 build version not higher than 19");
            return new byte[0];
        } else {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, C0108a.f85c);
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(2, secretKeySpec, getGcmAlgorithmParams(bArr3));
                return cipher.doFinal(bArr);
            } catch (GeneralSecurityException e) {
                C5106b.m13942b("GCM", "GCM decrypt data exception: " + e.getMessage());
                return new byte[0];
            }
        }
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) {
        byte[] generateSecureRandom = EncryptUtil.generateSecureRandom(12);
        return m14000a(generateSecureRandom, encrypt(bArr, bArr2, generateSecureRandom));
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        return decrypt(m13998b(bArr), bArr2, m13997c(bArr));
    }
}
