package com.huawei.secure.android.common.encrypt.hash;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.utils.C5106b;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.secure.android.common.encrypt.hash.PBKDF2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC5104PBKDF2 {

    /* renamed from: a */
    private static final String f11914a = "PBKDF2";

    /* renamed from: b */
    private static final String f11915b = "PBKDF2WithHmacSHA1";

    /* renamed from: c */
    private static final String f11916c = "PBKDF2WithHmacSHA256";

    /* renamed from: d */
    private static final String f11917d = "";

    /* renamed from: e */
    private static final int f11918e = 8;

    /* renamed from: f */
    private static final int f11919f = 16;

    /* renamed from: g */
    private static final int f11920g = 32;

    /* renamed from: h */
    private static final int f11921h = 10000;

    /* renamed from: i */
    private static final int f11922i = 1000;

    /* renamed from: a */
    private static byte[] m13989a(char[] cArr, byte[] bArr, int i, int i2, boolean z) {
        SecretKeyFactory secretKeyFactory;
        try {
            PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr, bArr, i, i2);
            if (z) {
                secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            } else {
                secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            }
            return secretKeyFactory.generateSecret(pBEKeySpec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            String str = f11914a;
            C5106b.m13942b(str, "pbkdf exception : " + e.getMessage());
            return new byte[0];
        }
    }

    public static byte[] pbkdf2(char[] cArr, byte[] bArr, int i, int i2) {
        return m13989a(cArr, bArr, i, i2, false);
    }

    @Deprecated
    public static String pbkdf2Encrypt(String str) {
        return pbkdf2Encrypt(str, 10000);
    }

    public static String pbkdf2EncryptNew(String str) {
        return pbkdf2EncryptNew(str, 10000);
    }

    public static byte[] pbkdf2SHA256(char[] cArr, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[0];
        if (Build.VERSION.SDK_INT < 26) {
            C5106b.m13942b(f11914a, "system version not high than 26");
            return bArr2;
        }
        return m13989a(cArr, bArr, i, i2, true);
    }

    @Deprecated
    public static boolean validatePassword(String str, String str2) {
        return validatePassword(str, str2, 10000);
    }

    public static boolean validatePasswordNew(String str, String str2) {
        return validatePasswordNew(str, str2, 10000);
    }

    @Deprecated
    public static String pbkdf2Encrypt(String str, int i) {
        return pbkdf2Encrypt(str, EncryptUtil.generateSecureRandom(8), i, 32);
    }

    public static String pbkdf2EncryptNew(String str, int i) {
        return pbkdf2EncryptNew(str, EncryptUtil.generateSecureRandom(16), i, 32);
    }

    @Deprecated
    public static boolean validatePassword(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str2.length() < 16) {
            return false;
        }
        return m13990a(pbkdf2(str.toCharArray(), HexUtil.hexStr2ByteArray(str2.substring(0, 16)), i, 256), HexUtil.hexStr2ByteArray(str2.substring(16)));
    }

    public static boolean validatePasswordNew(String str, String str2, int i) {
        byte[] pbkdf2SHA256;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str2.length() < 32) {
            return false;
        }
        String substring = str2.substring(0, 32);
        String substring2 = str2.substring(32);
        if (Build.VERSION.SDK_INT < 26) {
            pbkdf2SHA256 = pbkdf2(str.toCharArray(), HexUtil.hexStr2ByteArray(substring), i, 256);
        } else {
            pbkdf2SHA256 = pbkdf2SHA256(str.toCharArray(), HexUtil.hexStr2ByteArray(substring), i, 256);
        }
        return m13990a(pbkdf2SHA256, HexUtil.hexStr2ByteArray(substring2));
    }

    @Deprecated
    public static String pbkdf2Encrypt(String str, byte[] bArr, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b(f11914a, "pwd is null.");
            return "";
        } else if (i < 1000) {
            C5106b.m13942b(f11914a, "iterations times is not enough.");
            return "";
        } else if (bArr == null || bArr.length < 8) {
            C5106b.m13942b(f11914a, "salt parameter is null or length is not enough");
            return "";
        } else if (i2 < 32) {
            C5106b.m13942b(f11914a, "cipherLen length is not enough");
            return "";
        } else {
            byte[] pbkdf2 = pbkdf2(str.toCharArray(), bArr, i, i2 * 8);
            return HexUtil.byteArray2HexStr(bArr) + HexUtil.byteArray2HexStr(pbkdf2);
        }
    }

    public static String pbkdf2EncryptNew(String str, byte[] bArr, int i, int i2) {
        byte[] pbkdf2SHA256;
        if (TextUtils.isEmpty(str)) {
            C5106b.m13942b(f11914a, "pwd is null.");
            return "";
        } else if (i < 1000) {
            C5106b.m13942b(f11914a, "iterations times is not enough.");
            return "";
        } else if (bArr == null || bArr.length < 16) {
            C5106b.m13942b(f11914a, "salt parameter is null or length is not enough");
            return "";
        } else if (i2 < 32) {
            C5106b.m13942b(f11914a, "cipherLen length is not enough");
            return "";
        } else {
            if (Build.VERSION.SDK_INT < 26) {
                C5106b.m13941c(f11914a, "sha 1");
                pbkdf2SHA256 = pbkdf2(str.toCharArray(), bArr, i, i2 * 8);
            } else {
                C5106b.m13941c(f11914a, "sha 256");
                pbkdf2SHA256 = pbkdf2SHA256(str.toCharArray(), bArr, i, i2 * 8);
            }
            return HexUtil.byteArray2HexStr(bArr) + HexUtil.byteArray2HexStr(pbkdf2SHA256);
        }
    }

    /* renamed from: a */
    private static boolean m13990a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return false;
        }
        int length = bArr.length ^ bArr2.length;
        for (int i = 0; i < bArr.length && i < bArr2.length; i++) {
            length |= bArr[i] ^ bArr2[i];
        }
        return length == 0;
    }
}
