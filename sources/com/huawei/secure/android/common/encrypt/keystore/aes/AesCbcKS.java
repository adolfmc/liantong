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
import javax.crypto.spec.IvParameterSpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AesCbcKS {

    /* renamed from: a */
    private static final String f11927a = "CBCKS";

    /* renamed from: b */
    private static final String f11928b = "AndroidKeyStore";

    /* renamed from: c */
    private static final String f11929c = "AES/CBC/PKCS7Padding";

    /* renamed from: d */
    private static final String f11930d = "";

    /* renamed from: e */
    private static final int f11931e = 16;

    /* renamed from: f */
    private static final int f11932f = 256;

    /* renamed from: g */
    private static Map<String, SecretKey> f11933g = new HashMap();

    /* renamed from: a */
    private static synchronized SecretKey m13986a(String str) {
        SecretKey secretKey;
        synchronized (AesCbcKS.class) {
            C5106b.m13941c("CBCKS", "load key");
            secretKey = null;
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                                    keyStore.load(null);
                                    Key key = keyStore.getKey(str, null);
                                    if (key != null && (key instanceof SecretKey)) {
                                        secretKey = (SecretKey) key;
                                    } else {
                                        C5106b.m13941c("CBCKS", "generate key");
                                        KeyGenerator keyGenerator = KeyGenerator.getInstance(C0108a.f85c, "AndroidKeyStore");
                                        keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("CBC").setEncryptionPaddings("PKCS7Padding").setKeySize(256).build());
                                        secretKey = keyGenerator.generateKey();
                                    }
                                } catch (KeyStoreException e) {
                                    C5106b.m13942b("CBCKS", "KeyStoreException: " + e.getMessage());
                                } catch (NoSuchAlgorithmException e2) {
                                    C5106b.m13942b("CBCKS", "NoSuchAlgorithmException: " + e2.getMessage());
                                }
                            } catch (IOException e3) {
                                C5106b.m13942b("CBCKS", "IOException: " + e3.getMessage());
                            } catch (Exception e4) {
                                C5106b.m13942b("CBCKS", "Exception: " + e4.getMessage());
                            }
                        } catch (UnrecoverableKeyException e5) {
                            C5106b.m13942b("CBCKS", "UnrecoverableKeyException: " + e5.getMessage());
                        }
                    } catch (NoSuchProviderException e6) {
                        C5106b.m13942b("CBCKS", "NoSuchProviderException: " + e6.getMessage());
                    }
                } catch (InvalidAlgorithmParameterException e7) {
                    C5106b.m13942b("CBCKS", "InvalidAlgorithmParameterException: " + e7.getMessage());
                }
            } catch (CertificateException e8) {
                C5106b.m13942b("CBCKS", "CertificateException: " + e8.getMessage());
            }
            f11933g.put(str, secretKey);
        }
        return secretKey;
    }

    /* renamed from: b */
    private static SecretKey m13985b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (f11933g.get(str) == null) {
            m13986a(str);
        }
        return f11933g.get(str);
    }

    public static String decrypt(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                return new String(decrypt(str, HexUtil.hexStr2ByteArray(str2)), "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                C5106b.m13942b("CBCKS", "encrypt: UnsupportedEncodingException");
                return "";
            }
        }
        C5106b.m13942b("CBCKS", "alias or encrypt content is null");
        return "";
    }

    public static String encrypt(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            C5106b.m13942b("CBCKS", "encrypt 1 content is null");
            return "";
        }
        try {
            return HexUtil.byteArray2HexStr(encrypt(str, str2.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException unused) {
            C5106b.m13942b("CBCKS", "encrypt: UnsupportedEncodingException");
            return "";
        }
    }

    public static byte[] encrypt(String str, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!m13987a()) {
                C5106b.m13942b("CBCKS", "sdk version is too low");
                return bArr2;
            }
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                SecretKey m13985b = m13985b(str);
                if (m13985b == null) {
                    C5106b.m13942b("CBCKS", "encrypt secret key is null");
                    return bArr2;
                }
                cipher.init(1, m13985b);
                byte[] doFinal = cipher.doFinal(bArr);
                byte[] iv = cipher.getIV();
                if (iv != null && iv.length == 16) {
                    byte[] copyOf = Arrays.copyOf(iv, iv.length + doFinal.length);
                    System.arraycopy(doFinal, 0, copyOf, iv.length, doFinal.length);
                    return copyOf;
                }
                C5106b.m13942b("CBCKS", "IV is invalid.");
                return bArr2;
            } catch (InvalidKeyException e) {
                C5106b.m13942b("CBCKS", "InvalidKeyException: " + e.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e2) {
                C5106b.m13942b("CBCKS", "NoSuchAlgorithmException: " + e2.getMessage());
                return bArr2;
            } catch (BadPaddingException e3) {
                C5106b.m13942b("CBCKS", "BadPaddingException: " + e3.getMessage());
                return bArr2;
            } catch (IllegalBlockSizeException e4) {
                C5106b.m13942b("CBCKS", "IllegalBlockSizeException: " + e4.getMessage());
                return bArr2;
            } catch (NoSuchPaddingException e5) {
                C5106b.m13942b("CBCKS", "NoSuchPaddingException: " + e5.getMessage());
                return bArr2;
            } catch (Exception e6) {
                C5106b.m13942b("CBCKS", "Exception: " + e6.getMessage());
                return bArr2;
            }
        }
        C5106b.m13942b("CBCKS", "alias or encrypt content is null");
        return bArr2;
    }

    public static byte[] decrypt(String str, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!m13987a()) {
                C5106b.m13942b("CBCKS", "sdk version is too low");
                return bArr2;
            } else if (bArr.length <= 16) {
                C5106b.m13942b("CBCKS", "Decrypt source data is invalid.");
                return bArr2;
            } else {
                SecretKey m13985b = m13985b(str);
                if (m13985b == null) {
                    C5106b.m13942b("CBCKS", "decrypt secret key is null");
                    return bArr2;
                }
                byte[] copyOf = Arrays.copyOf(bArr, 16);
                try {
                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                    cipher.init(2, m13985b, new IvParameterSpec(copyOf));
                    return cipher.doFinal(bArr, 16, bArr.length - 16);
                } catch (InvalidAlgorithmParameterException e) {
                    C5106b.m13942b("CBCKS", "InvalidAlgorithmParameterException: " + e.getMessage());
                    return bArr2;
                } catch (InvalidKeyException e2) {
                    C5106b.m13942b("CBCKS", "InvalidKeyException: " + e2.getMessage());
                    return bArr2;
                } catch (NoSuchAlgorithmException e3) {
                    C5106b.m13942b("CBCKS", "NoSuchAlgorithmException: " + e3.getMessage());
                    return bArr2;
                } catch (BadPaddingException e4) {
                    C5106b.m13942b("CBCKS", "BadPaddingException: " + e4.getMessage());
                    return bArr2;
                } catch (IllegalBlockSizeException e5) {
                    C5106b.m13942b("CBCKS", "IllegalBlockSizeException: " + e5.getMessage());
                    return bArr2;
                } catch (NoSuchPaddingException e6) {
                    C5106b.m13942b("CBCKS", "NoSuchPaddingException: " + e6.getMessage());
                    return bArr2;
                } catch (Exception e7) {
                    C5106b.m13942b("CBCKS", "Exception: " + e7.getMessage());
                    return bArr2;
                }
            }
        }
        C5106b.m13942b("CBCKS", "alias or encrypt content is null");
        return bArr2;
    }

    /* renamed from: a */
    private static boolean m13987a() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
