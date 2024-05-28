package com.huawei.secure.android.common.encrypt.keystore.aes;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.utils.C5106b;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AesGcmKS {

    /* renamed from: a */
    private static final String f11934a = "GCMKS";

    /* renamed from: b */
    private static final String f11935b = "AndroidKeyStore";

    /* renamed from: c */
    private static final String f11936c = "AES/GCM/NoPadding";

    /* renamed from: d */
    private static final String f11937d = "";

    /* renamed from: e */
    private static final int f11938e = 12;

    /* renamed from: f */
    private static final int f11939f = 256;

    /* renamed from: g */
    private static Map<String, SecretKey> f11940g = new HashMap();

    /* renamed from: a */
    private static SecretKey m13983a(String str) {
        C5106b.m13941c("GCMKS", "load key");
        SecretKey secretKey = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            Key key = keyStore.getKey(str, null);
            if (key instanceof SecretKey) {
                secretKey = (SecretKey) key;
            } else {
                C5106b.m13941c("GCMKS", "generate key");
                KeyGenerator keyGenerator = KeyGenerator.getInstance(C0108a.f85c, "AndroidKeyStore");
                keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).build());
                secretKey = keyGenerator.generateKey();
            }
        } catch (IOException e) {
            C5106b.m13942b("GCMKS", "IOException : " + e.getMessage());
        } catch (InvalidAlgorithmParameterException e2) {
            C5106b.m13942b("GCMKS", "InvalidAlgorithmParameterException : " + e2.getMessage());
        } catch (KeyStoreException e3) {
            C5106b.m13942b("GCMKS", "KeyStoreException : " + e3.getMessage());
        } catch (NoSuchAlgorithmException e4) {
            C5106b.m13942b("GCMKS", "NoSuchAlgorithmException : " + e4.getMessage());
        } catch (NoSuchProviderException e5) {
            C5106b.m13942b("GCMKS", "NoSuchProviderException : " + e5.getMessage());
        } catch (UnrecoverableKeyException e6) {
            C5106b.m13942b("GCMKS", "UnrecoverableKeyException : " + e6.getMessage());
        } catch (CertificateException e7) {
            C5106b.m13942b("GCMKS", "CertificateException : " + e7.getMessage());
        } catch (Exception e8) {
            C5106b.m13942b("GCMKS", "Exception: " + e8.getMessage());
        }
        f11940g.put(str, secretKey);
        return secretKey;
    }

    /* renamed from: b */
    private static SecretKey m13982b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (f11940g.get(str) == null) {
            m13983a(str);
        }
        return f11940g.get(str);
    }

    public static String decrypt(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                return new String(decrypt(str, HexUtil.hexStr2ByteArray(str2)), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                C5106b.m13942b("GCMKS", "decrypt: UnsupportedEncodingException : " + e.getMessage());
                return "";
            }
        }
        C5106b.m13942b("GCMKS", "alias or encrypt content is null");
        return "";
    }

    public static String encrypt(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                return HexUtil.byteArray2HexStr(encrypt(str, str2.getBytes("UTF-8")));
            } catch (UnsupportedEncodingException e) {
                C5106b.m13942b("GCMKS", "encrypt: UnsupportedEncodingException : " + e.getMessage());
                return "";
            }
        }
        C5106b.m13942b("GCMKS", "alias or encrypt content is null");
        return "";
    }

    public static byte[] decrypt(String str, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!m13984a()) {
                C5106b.m13942b("GCMKS", "sdk version is too low");
                return bArr2;
            } else if (bArr.length <= 12) {
                C5106b.m13942b("GCMKS", "Decrypt source data is invalid.");
                return bArr2;
            } else {
                return decrypt(m13982b(str), bArr);
            }
        }
        C5106b.m13942b("GCMKS", "alias or encrypt content is null");
        return bArr2;
    }

    public static byte[] encrypt(String str, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!m13984a()) {
                C5106b.m13942b("GCMKS", "sdk version is too low");
                return bArr2;
            }
            return encrypt(m13982b(str), bArr);
        }
        C5106b.m13942b("GCMKS", "alias or encrypt content is null");
        return bArr2;
    }

    public static byte[] encrypt(SecretKey secretKey, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (bArr == null) {
            C5106b.m13942b("GCMKS", "content is null");
            return bArr2;
        } else if (secretKey == null) {
            C5106b.m13942b("GCMKS", "secret key is null");
            return bArr2;
        } else if (!m13984a()) {
            C5106b.m13942b("GCMKS", "sdk version is too low");
            return bArr2;
        } else {
            try {
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(1, secretKey);
                byte[] doFinal = cipher.doFinal(bArr);
                byte[] iv = cipher.getIV();
                if (iv != null && iv.length == 12) {
                    byte[] copyOf = Arrays.copyOf(iv, iv.length + doFinal.length);
                    System.arraycopy(doFinal, 0, copyOf, iv.length, doFinal.length);
                    return copyOf;
                }
                C5106b.m13942b("GCMKS", "IV is invalid.");
                return bArr2;
            } catch (InvalidKeyException e) {
                C5106b.m13942b("GCMKS", "InvalidKeyException : " + e.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e2) {
                C5106b.m13942b("GCMKS", "NoSuchAlgorithmException : " + e2.getMessage());
                return bArr2;
            } catch (BadPaddingException e3) {
                C5106b.m13942b("GCMKS", "BadPaddingException : " + e3.getMessage());
                return bArr2;
            } catch (IllegalBlockSizeException e4) {
                C5106b.m13942b("GCMKS", "IllegalBlockSizeException : " + e4.getMessage());
                return bArr2;
            } catch (NoSuchPaddingException e5) {
                C5106b.m13942b("GCMKS", "NoSuchPaddingException : " + e5.getMessage());
                return bArr2;
            } catch (Exception e6) {
                C5106b.m13942b("GCMKS", "Exception: " + e6.getMessage());
                return bArr2;
            }
        }
    }

    public static byte[] decrypt(SecretKey secretKey, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (secretKey == null) {
            C5106b.m13942b("GCMKS", "Decrypt secret key is null");
            return bArr2;
        } else if (bArr == null) {
            C5106b.m13942b("GCMKS", "content is null");
            return bArr2;
        } else if (!m13984a()) {
            C5106b.m13942b("GCMKS", "sdk version is too low");
            return bArr2;
        } else if (bArr.length <= 12) {
            C5106b.m13942b("GCMKS", "Decrypt source data is invalid.");
            return bArr2;
        } else {
            byte[] copyOf = Arrays.copyOf(bArr, 12);
            try {
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(2, secretKey, new GCMParameterSpec(128, copyOf));
                return cipher.doFinal(bArr, 12, bArr.length - 12);
            } catch (InvalidAlgorithmParameterException e) {
                C5106b.m13942b("GCMKS", "InvalidAlgorithmParameterException : " + e.getMessage());
                return bArr2;
            } catch (InvalidKeyException e2) {
                C5106b.m13942b("GCMKS", "InvalidKeyException : " + e2.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e3) {
                C5106b.m13942b("GCMKS", "NoSuchAlgorithmException : " + e3.getMessage());
                return bArr2;
            } catch (BadPaddingException e4) {
                C5106b.m13942b("GCMKS", "BadPaddingException : " + e4.getMessage());
                return bArr2;
            } catch (IllegalBlockSizeException e5) {
                C5106b.m13942b("GCMKS", "IllegalBlockSizeException : " + e5.getMessage());
                return bArr2;
            } catch (NoSuchPaddingException e6) {
                C5106b.m13942b("GCMKS", "NoSuchPaddingException : " + e6.getMessage());
                return bArr2;
            } catch (Exception e7) {
                C5106b.m13942b("GCMKS", "Exception: " + e7.getMessage());
                return bArr2;
            }
        }
    }

    /* renamed from: a */
    private static boolean m13984a() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
