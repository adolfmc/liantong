package com.huawei.secure.android.common.encrypt.aes;

import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.utils.C5106b;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class AesCbc {

    /* renamed from: a */
    private static final String f11882a = "security:";

    /* renamed from: b */
    private static final String f11883b = "AES/CBC/PKCS5Padding";

    /* renamed from: c */
    private static final String f11884c = "AES";

    /* renamed from: d */
    private static final String f11885d = "CBC";

    /* renamed from: e */
    private static final String f11886e = "";

    /* renamed from: f */
    private static final int f11887f = 16;

    /* renamed from: g */
    private static final int f11888g = 16;

    private AesCbc() {
    }

    /* renamed from: a */
    private static byte[] m14011a(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("CBC", "encrypt 5 content is null");
            return new byte[0];
        } else if (bArr == null) {
            C5106b.m13942b("CBC", "encrypt 5 key is null");
            return new byte[0];
        } else if (bArr.length < 16) {
            C5106b.m13942b("CBC", "encrypt 5 key error: 5 key length less than 16 bytes.");
            return new byte[0];
        } else if (bArr2 == null) {
            C5106b.m13942b("CBC", "encrypt 5 iv is null");
            return new byte[0];
        } else if (bArr2.length < 16) {
            C5106b.m13942b("CBC", "encrypt 5 iv error: 5 iv length less than 16 bytes.");
            return new byte[0];
        } else {
            try {
                return encrypt(str.getBytes("UTF-8"), bArr, bArr2);
            } catch (UnsupportedEncodingException e) {
                C5106b.m13942b("CBC", " cbc encrypt data error" + e.getMessage());
                return new byte[0];
            }
        }
    }

    /* renamed from: b */
    private static byte[] m14007b(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length - 16];
        System.arraycopy(bArr, 16, bArr2, 0, bArr.length - 16);
        return bArr2;
    }

    /* renamed from: c */
    private static byte[] m14005c(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 0, bArr2, 0, 16);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static byte[] m14004d(byte[] bArr) {
        String str = "";
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            C5106b.m13942b("CBC", "stripCryptHead: exception : " + e.getMessage());
        }
        if (!str.startsWith("security:")) {
            return new byte[0];
        }
        if (bArr.length > 9) {
            byte[] bArr2 = new byte[bArr.length - 9];
            System.arraycopy(bArr, 9, bArr2, 0, bArr2.length);
            return bArr2;
        }
        return new byte[0];
    }

    public static String decrypt(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("CBC", "decrypt 1 content is null");
            return "";
        } else if (TextUtils.isEmpty(str2)) {
            C5106b.m13942b("CBC", "decrypt 1 key is null");
            return "";
        } else {
            byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str2);
            if (hexStr2ByteArray.length < 16) {
                C5106b.m13942b("CBC", "decrypt 1 key error: 1 key length less than 16 bytes.");
                return "";
            }
            return decrypt(str, hexStr2ByteArray);
        }
    }

    public static String decryptWithCryptHead(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str) || bArr == null || bArr.length < 16) {
            return "";
        }
        String m14006c = m14006c(str);
        if ("".equals(m14006c)) {
            return "";
        }
        int indexOf = m14006c.indexOf(58);
        if (indexOf >= 0) {
            return decrypt(HexUtil.byteArray2HexStr(HexUtil.hexStr2ByteArray(m14006c.substring(indexOf + 1))), bArr, HexUtil.hexStr2ByteArray(m14006c.substring(0, indexOf)));
        }
        C5106b.m13942b("CBC", " cbc cipherText data missing colon");
        return "";
    }

    public static byte[] decryptWithCryptHeadReturnByte(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null && bArr2.length >= 16) {
            byte[] m14004d = m14004d(bArr);
            if (m14004d.length == 0) {
                return new byte[0];
            }
            int m14010a = m14010a(m14004d);
            if (m14010a >= 0) {
                byte[] copyOf = Arrays.copyOf(m14004d, m14010a);
                int length = (m14004d.length - copyOf.length) - 1;
                byte[] bArr3 = new byte[length];
                System.arraycopy(m14004d, m14010a + 1, bArr3, 0, length);
                return decrypt(bArr3, bArr2, copyOf);
            }
            C5106b.m13942b("CBC", " cbc cipherText data missing colon");
            return new byte[0];
        }
        return new byte[0];
    }

    public static String encrypt(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("CBC", "encrypt 1 content is null");
            return "";
        } else if (TextUtils.isEmpty(str2)) {
            C5106b.m13942b("CBC", "encrypt 1 key is null");
            return "";
        } else {
            byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str2);
            if (hexStr2ByteArray.length < 16) {
                C5106b.m13942b("CBC", "encrypt 1 key error: 1 key length less than 16 bytes.");
                return "";
            }
            return encrypt(str, hexStr2ByteArray);
        }
    }

    /* renamed from: b */
    private static String m14008b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return str.substring(6, 12) + str.substring(16, 26) + str.substring(32, 48);
        } catch (Exception e) {
            C5106b.m13942b("CBC", "getIv exception : " + e.getMessage());
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static String m14006c(String str) {
        return (TextUtils.isEmpty(str) || str.indexOf("security:") == -1) ? "" : str.substring(9);
    }

    public static String decrypt(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("CBC", "decrypt 2 content is null");
            return "";
        } else if (bArr == null) {
            C5106b.m13942b("CBC", "decrypt 2 key is null");
            return "";
        } else if (bArr.length < 16) {
            C5106b.m13942b("CBC", "decrypt 2 key error: 2 key length less than 16 bytes.");
            return "";
        } else {
            String m14008b = m14008b(str);
            String m14013a = m14013a(str);
            if (TextUtils.isEmpty(m14008b)) {
                C5106b.m13942b("CBC", "decrypt 2 iv is null");
                return "";
            } else if (TextUtils.isEmpty(m14013a)) {
                C5106b.m13942b("CBC", "decrypt 2 encrypt content is null");
                return "";
            } else {
                return decrypt(m14013a, bArr, HexUtil.hexStr2ByteArray(m14008b));
            }
        }
    }

    public static String encrypt(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("CBC", "encrypt 2 content is null");
            return "";
        } else if (bArr == null) {
            C5106b.m13942b("CBC", "encrypt 2 key is null");
            return "";
        } else if (bArr.length < 16) {
            C5106b.m13942b("CBC", "encrypt 2 key error: 2 key length less than 16 bytes.");
            return "";
        } else {
            byte[] generateSecureRandom = EncryptUtil.generateSecureRandom(16);
            byte[] m14011a = m14011a(str, bArr, generateSecureRandom);
            return (m14011a == null || m14011a.length == 0) ? "" : m14012a(HexUtil.byteArray2HexStr(generateSecureRandom), HexUtil.byteArray2HexStr(m14011a));
        }
    }

    public static String decryptWithCryptHead(byte[] bArr, byte[] bArr2) {
        try {
            return new String(decryptWithCryptHeadReturnByte(bArr, bArr2), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            C5106b.m13942b("CBC", "decryptWithCryptHead UnsupportedEncodingException ");
            return "";
        }
    }

    /* renamed from: a */
    private static String m14012a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            return str2.substring(0, 6) + str.substring(0, 6) + str2.substring(6, 10) + str.substring(6, 16) + str2.substring(10, 16) + str.substring(16) + str2.substring(16);
        } catch (Exception e) {
            C5106b.m13942b("CBC", "mix exception: " + e.getMessage());
            return "";
        }
    }

    public static String encrypt(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("CBC", "encrypt 3 content is null");
            return "";
        } else if (TextUtils.isEmpty(str2)) {
            C5106b.m13942b("CBC", "encrypt 3 key is null");
            return "";
        } else if (TextUtils.isEmpty(str3)) {
            C5106b.m13942b("CBC", "encrypt 3 iv is null");
            return "";
        } else {
            byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str2);
            byte[] hexStr2ByteArray2 = HexUtil.hexStr2ByteArray(str3);
            if (hexStr2ByteArray.length < 16) {
                C5106b.m13942b("CBC", "encrypt 3 key error: 3 key length less than 16 bytes.");
                return "";
            } else if (hexStr2ByteArray2.length < 16) {
                C5106b.m13942b("CBC", "encrypt 3 iv error: 3 iv length less than 16 bytes.");
                return "";
            } else {
                return encrypt(str, hexStr2ByteArray, hexStr2ByteArray2);
            }
        }
    }

    public static String decrypt(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("CBC", "decrypt 3 content is null");
            return "";
        } else if (TextUtils.isEmpty(str2)) {
            C5106b.m13942b("CBC", "decrypt 3 key is null");
            return "";
        } else if (TextUtils.isEmpty(str3)) {
            C5106b.m13942b("CBC", "decrypt 3 iv is null");
            return "";
        } else {
            byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str2);
            byte[] hexStr2ByteArray2 = HexUtil.hexStr2ByteArray(str3);
            if (hexStr2ByteArray.length < 16) {
                C5106b.m13942b("CBC", "decrypt 3 key error: 3 key length less than 16 bytes.");
                return "";
            } else if (hexStr2ByteArray2.length < 16) {
                C5106b.m13942b("CBC", "decrypt 3 iv error: 3 iv length less than 16 bytes.");
                return "";
            } else {
                return decrypt(str, hexStr2ByteArray, hexStr2ByteArray2);
            }
        }
    }

    /* renamed from: a */
    private static byte[] m14009a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* renamed from: a */
    private static String m14013a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return str.substring(0, 6) + str.substring(12, 16) + str.substring(26, 32) + str.substring(48);
        } catch (Exception e) {
            C5106b.m13942b("CBC", "get encryptword exception : " + e.getMessage());
            return "";
        }
    }

    /* renamed from: a */
    private static int m14010a(byte[] bArr) {
        return bArr[16] == 58 ? 16 : -1;
    }

    public static String encrypt(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("CBC", "encrypt 4 content is null");
            return "";
        } else if (bArr == null) {
            C5106b.m13942b("CBC", "encrypt 4 key is null");
            return "";
        } else if (bArr.length < 16) {
            C5106b.m13942b("CBC", "encrypt 4 key error: 4 key length less than 16 bytes.");
            return "";
        } else if (bArr2 == null) {
            C5106b.m13942b("CBC", "encrypt 4 iv is null");
            return "";
        } else if (bArr2.length < 16) {
            C5106b.m13942b("CBC", "encrypt 4 iv error: 4 iv length less than 16 bytes.");
            return "";
        } else {
            return HexUtil.byteArray2HexStr(m14011a(str, bArr, bArr2));
        }
    }

    public static String decrypt(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b("CBC", "decrypt 4 content is null");
            return "";
        } else if (bArr == null) {
            C5106b.m13942b("CBC", "decrypt 4 key is null");
            return "";
        } else if (bArr.length < 16) {
            C5106b.m13942b("CBC", "decrypt 4 key error: 4 key length less than 16 bytes.");
            return "";
        } else if (bArr2 == null) {
            C5106b.m13942b("CBC", "decrypt 4 iv is null");
            return "";
        } else if (bArr2.length < 16) {
            C5106b.m13942b("CBC", "decrypt 4 iv error: 4 iv length less than 16 bytes.");
            return "";
        } else {
            try {
                return new String(decrypt(HexUtil.hexStr2ByteArray(str), bArr, bArr2), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                C5106b.m13942b("CBC", " cbc decrypt data error" + e.getMessage());
                return "";
            }
        }
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            C5106b.m13942b("CBC", "encrypt 6 content is null");
            return new byte[0];
        } else if (bArr.length == 0) {
            C5106b.m13942b("CBC", "encrypt 6 content length is 0");
            return new byte[0];
        } else if (bArr2 == null) {
            C5106b.m13942b("CBC", "encrypt 6 key is null");
            return new byte[0];
        } else if (bArr2.length < 16) {
            C5106b.m13942b("CBC", "encrypt 6 key error: 6 key length less than 16 bytes.");
            return new byte[0];
        } else if (bArr3 == null) {
            C5106b.m13942b("CBC", "encrypt 6 iv is null");
            return new byte[0];
        } else if (bArr3.length < 16) {
            C5106b.m13942b("CBC", "encrypt 6 iv error: 6 iv length less than 16 bytes.");
            return new byte[0];
        } else {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, C0108a.f85c);
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(1, secretKeySpec, new IvParameterSpec(bArr3));
                return cipher.doFinal(bArr);
            } catch (NullPointerException e) {
                C5106b.m13942b("CBC", "NullPointerException: " + e.getMessage());
                return new byte[0];
            } catch (InvalidAlgorithmParameterException e2) {
                C5106b.m13942b("CBC", "InvalidAlgorithmParameterException: " + e2.getMessage());
                return new byte[0];
            } catch (InvalidKeyException e3) {
                C5106b.m13942b("CBC", "InvalidKeyException: " + e3.getMessage());
                return new byte[0];
            } catch (NoSuchAlgorithmException e4) {
                C5106b.m13942b("CBC", "NoSuchAlgorithmException: " + e4.getMessage());
                return new byte[0];
            } catch (BadPaddingException e5) {
                C5106b.m13942b("CBC", "BadPaddingException: " + e5.getMessage());
                return new byte[0];
            } catch (IllegalBlockSizeException e6) {
                C5106b.m13942b("CBC", "IllegalBlockSizeException: " + e6.getMessage());
                return new byte[0];
            } catch (NoSuchPaddingException e7) {
                C5106b.m13942b("CBC", "NoSuchPaddingException: " + e7.getMessage());
                return new byte[0];
            }
        }
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            C5106b.m13942b("CBC", "decrypt 6 content is null");
            return new byte[0];
        } else if (bArr.length == 0) {
            C5106b.m13942b("CBC", "decrypt 6 content length is 0");
            return new byte[0];
        } else if (bArr2 == null) {
            C5106b.m13942b("CBC", "decrypt 6 key is null");
            return new byte[0];
        } else if (bArr2.length < 16) {
            C5106b.m13942b("CBC", "decrypt 6 key error: 6 key length less than 16 bytes.");
            return new byte[0];
        } else if (bArr3 == null) {
            C5106b.m13942b("CBC", "decrypt 6 iv is null");
            return new byte[0];
        } else if (bArr3.length < 16) {
            C5106b.m13942b("CBC", "decrypt 6 iv error: 6 iv length less than 16 bytes.");
            return new byte[0];
        } else {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, C0108a.f85c);
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(2, secretKeySpec, new IvParameterSpec(bArr3));
                return cipher.doFinal(bArr);
            } catch (NullPointerException e) {
                C5106b.m13942b("CBC", "NullPointerException: " + e.getMessage());
                return new byte[0];
            } catch (InvalidAlgorithmParameterException e2) {
                C5106b.m13942b("CBC", "InvalidAlgorithmParameterException: " + e2.getMessage());
                return new byte[0];
            } catch (InvalidKeyException e3) {
                C5106b.m13942b("CBC", "InvalidKeyException: " + e3.getMessage());
                return new byte[0];
            } catch (NoSuchAlgorithmException e4) {
                C5106b.m13942b("CBC", "NoSuchAlgorithmException: " + e4.getMessage());
                return new byte[0];
            } catch (BadPaddingException e5) {
                C5106b.m13942b("CBC", "BadPaddingException: " + e5.getMessage());
                C5106b.m13942b("CBC", "key is not right");
                return new byte[0];
            } catch (IllegalBlockSizeException e6) {
                C5106b.m13942b("CBC", "IllegalBlockSizeException: " + e6.getMessage());
                return new byte[0];
            } catch (NoSuchPaddingException e7) {
                C5106b.m13942b("CBC", "NoSuchPaddingException: " + e7.getMessage());
                return new byte[0];
            }
        }
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) {
        byte[] generateSecureRandom = EncryptUtil.generateSecureRandom(16);
        return m14009a(generateSecureRandom, encrypt(bArr, bArr2, generateSecureRandom));
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        return decrypt(m14007b(bArr), bArr2, m14005c(bArr));
    }
}
