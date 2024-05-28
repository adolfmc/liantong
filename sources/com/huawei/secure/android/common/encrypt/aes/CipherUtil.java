package com.huawei.secure.android.common.encrypt.aes;

import com.huawei.secure.android.common.encrypt.utils.C5106b;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CipherUtil {

    /* renamed from: a */
    private static final String f11897a = "CipherUtil";

    /* renamed from: b */
    private static final String f11898b = "AES/GCM/NoPadding";

    /* renamed from: c */
    private static final String f11899c = "AES/CBC/PKCS5Padding";

    /* renamed from: d */
    private static final String f11900d = "AES";

    /* renamed from: e */
    private static final String f11901e = "";

    /* renamed from: f */
    private static final int f11902f = 16;

    /* renamed from: g */
    private static final int f11903g = 12;

    /* renamed from: h */
    private static final int f11904h = 16;

    /* renamed from: a */
    private static Cipher m13995a(byte[] bArr, byte[] bArr2, int i) {
        return m13994a(bArr, bArr2, i, "AES/CBC/PKCS5Padding");
    }

    /* renamed from: b */
    private static Cipher m13993b(byte[] bArr, byte[] bArr2, int i) {
        return m13994a(bArr, bArr2, i, "AES/GCM/NoPadding");
    }

    public static Cipher getAesCbcDecryptCipher(byte[] bArr, Cipher cipher) {
        return getAesCbcDecryptCipher(bArr, cipher.getIV());
    }

    public static Cipher getAesCbcEncryptCipher(byte[] bArr) {
        return getAesCbcEncryptCipher(bArr, EncryptUtil.generateSecureRandom(16));
    }

    public static int getAesCbcEncryptContentLen(byte[] bArr, byte[] bArr2) {
        return getAesCbcEncryptContentLen(bArr, bArr2, EncryptUtil.generateSecureRandom(16));
    }

    public static Cipher getAesGcmDecryptCipher(byte[] bArr, Cipher cipher) {
        return getAesGcmDecryptCipher(bArr, cipher.getIV());
    }

    public static Cipher getAesGcmEncryptCipher(byte[] bArr) {
        byte[] generateSecureRandom = EncryptUtil.generateSecureRandom(12);
        C5106b.m13944a("CipherUtil", "getEncryptCipher: iv is : " + HexUtil.byteArray2HexStr(generateSecureRandom));
        return getAesGcmEncryptCipher(bArr, generateSecureRandom);
    }

    public static int getAesGcmEncryptContentLen(byte[] bArr, byte[] bArr2) {
        return getAesGcmEncryptContentLen(bArr, bArr2, EncryptUtil.generateSecureRandom(12));
    }

    public static int getContent(Cipher cipher, byte[] bArr, byte[] bArr2) {
        if (cipher != null && bArr != null) {
            try {
                return cipher.doFinal(bArr, 0, bArr.length, bArr2);
            } catch (BadPaddingException unused) {
                C5106b.m13942b("CipherUtil", "getContent: BadPaddingException");
                return -1;
            } catch (IllegalBlockSizeException unused2) {
                C5106b.m13942b("CipherUtil", "getContent: IllegalBlockSizeException");
                return -1;
            } catch (ShortBufferException unused3) {
                C5106b.m13942b("CipherUtil", "getContent: ShortBufferException");
                return -1;
            }
        }
        C5106b.m13942b("CipherUtil", "getEncryptCOntent: cipher is null or content is null");
        return -1;
    }

    /* renamed from: a */
    private static Cipher m13994a(byte[] bArr, byte[] bArr2, int i, String str) {
        AlgorithmParameterSpec ivParameterSpec;
        if (bArr != null && bArr.length >= 16 && bArr2 != null && bArr2.length >= 12 && AesGcm.isBuildVersionHigherThan19()) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, C0108a.f85c);
                Cipher cipher = Cipher.getInstance(str);
                if ("AES/GCM/NoPadding".equals(str)) {
                    ivParameterSpec = AesGcm.getGcmAlgorithmParams(bArr2);
                } else {
                    ivParameterSpec = new IvParameterSpec(bArr2);
                }
                cipher.init(i, secretKeySpec, ivParameterSpec);
                return cipher;
            } catch (GeneralSecurityException e) {
                C5106b.m13942b("CipherUtil", "GCM encrypt data error" + e.getMessage());
                return null;
            }
        }
        C5106b.m13942b("CipherUtil", "gcm encrypt param is not right");
        return null;
    }

    public static Cipher getAesCbcDecryptCipher(byte[] bArr, byte[] bArr2) {
        return m13995a(bArr, bArr2, 2);
    }

    public static Cipher getAesCbcEncryptCipher(byte[] bArr, byte[] bArr2) {
        return m13995a(bArr, bArr2, 1);
    }

    public static int getAesCbcEncryptContentLen(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return m13996a(getAesCbcEncryptCipher(bArr2, bArr3), bArr);
    }

    public static Cipher getAesGcmDecryptCipher(byte[] bArr, byte[] bArr2) {
        return m13993b(bArr, bArr2, 2);
    }

    public static int getAesGcmEncryptContentLen(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return m13996a(getAesGcmEncryptCipher(bArr2, bArr3), bArr);
    }

    public static Cipher getAesGcmEncryptCipher(byte[] bArr, byte[] bArr2) {
        return m13993b(bArr, bArr2, 1);
    }

    public static int getContent(Cipher cipher, byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        if (cipher != null && bArr != null) {
            return cipher.doFinal(bArr, i, i2, bArr2, i3);
        }
        C5106b.m13942b("CipherUtil", "getEncryptCOntent: cipher is null or content is null");
        return -1;
    }

    public static byte[] getContent(Cipher cipher, byte[] bArr) {
        if (cipher != null && bArr != null) {
            try {
                return cipher.doFinal(bArr, 0, bArr.length);
            } catch (BadPaddingException unused) {
                C5106b.m13942b("CipherUtil", "getContent: BadPaddingException");
                return new byte[0];
            } catch (IllegalBlockSizeException unused2) {
                C5106b.m13942b("CipherUtil", "getContent: IllegalBlockSizeException");
                return new byte[0];
            }
        }
        C5106b.m13942b("CipherUtil", "getEncryptCOntent: cipher is null or content is null");
        return new byte[0];
    }

    /* renamed from: a */
    private static int m13996a(Cipher cipher, byte[] bArr) {
        if (cipher == null || bArr == null) {
            return -1;
        }
        return cipher.getOutputSize(bArr.length);
    }
}
