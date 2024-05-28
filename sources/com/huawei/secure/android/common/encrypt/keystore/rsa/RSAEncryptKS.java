package com.huawei.secure.android.common.encrypt.keystore.rsa;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.secure.android.common.encrypt.utils.C5106b;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class RSAEncryptKS {

    /* renamed from: a */
    private static final String f11941a = "RSAEncryptKS";

    /* renamed from: b */
    private static final String f11942b = "AndroidKeyStore";

    /* renamed from: c */
    private static final String f11943c = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    /* renamed from: d */
    private static final String f11944d = "";

    /* renamed from: e */
    private static final int f11945e = 2048;

    /* renamed from: f */
    private static final int f11946f = 3072;

    /* renamed from: a */
    private static byte[] m13978a(String str, byte[] bArr, boolean z) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!m13981a()) {
                C5106b.m13942b(f11941a, "sdk version is too low");
                return bArr2;
            }
            PublicKey m13976b = m13976b(str, z);
            if (m13976b == null) {
                C5106b.m13942b(f11941a, "Public key is null");
                return bArr2;
            }
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
                cipher.init(1, m13976b, new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT));
                return cipher.doFinal(bArr);
            } catch (InvalidAlgorithmParameterException e) {
                String str2 = f11941a;
                C5106b.m13942b(str2, "InvalidAlgorithmParameterException: " + e.getMessage());
                return bArr2;
            } catch (InvalidKeyException e2) {
                String str3 = f11941a;
                C5106b.m13942b(str3, "InvalidKeyException: " + e2.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e3) {
                String str4 = f11941a;
                C5106b.m13942b(str4, "NoSuchAlgorithmException: " + e3.getMessage());
                return bArr2;
            } catch (BadPaddingException e4) {
                String str5 = f11941a;
                C5106b.m13942b(str5, "BadPaddingException: " + e4.getMessage());
                return bArr2;
            } catch (IllegalBlockSizeException e5) {
                String str6 = f11941a;
                C5106b.m13942b(str6, "IllegalBlockSizeException: " + e5.getMessage());
                return bArr2;
            } catch (NoSuchPaddingException e6) {
                String str7 = f11941a;
                C5106b.m13942b(str7, "NoSuchPaddingException: " + e6.getMessage());
                return bArr2;
            } catch (Exception e7) {
                String str8 = f11941a;
                C5106b.m13942b(str8, "Exception: " + e7.getMessage());
                return bArr2;
            }
        }
        C5106b.m13942b(f11941a, "alias or content is null");
        return bArr2;
    }

    /* renamed from: b */
    private static PublicKey m13976b(String str, boolean z) {
        if (!m13977b(str)) {
            m13979a(str, z);
        }
        Certificate m13975c = m13975c(str);
        if (m13975c != null) {
            return m13975c.getPublicKey();
        }
        return null;
    }

    /* renamed from: c */
    private static Certificate m13975c(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.getCertificate(str);
        } catch (IOException e) {
            String str2 = f11941a;
            C5106b.m13942b(str2, "IOException: " + e.getMessage());
            return null;
        } catch (KeyStoreException e2) {
            String str3 = f11941a;
            C5106b.m13942b(str3, "KeyStoreException: " + e2.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e3) {
            String str4 = f11941a;
            C5106b.m13942b(str4, "NoSuchAlgorithmException: " + e3.getMessage());
            return null;
        } catch (CertificateException e4) {
            String str5 = f11941a;
            C5106b.m13942b(str5, "CertificateException: " + e4.getMessage());
            return null;
        } catch (Exception e5) {
            String str6 = f11941a;
            C5106b.m13942b(str6, "Exception: " + e5.getMessage());
            return null;
        }
    }

    @Deprecated
    public static String decrpyt(String str, String str2) {
        try {
            return new String(decrpyt(str, Base64.decode(str2, 0)), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            String str3 = f11941a;
            C5106b.m13942b(str3, "UnsupportedEncodingException: " + e.getMessage());
            return "";
        } catch (Exception e2) {
            String str4 = f11941a;
            C5106b.m13942b(str4, "Exception: " + e2.getMessage());
            return "";
        }
    }

    public static String decrpytNew(String str, String str2) {
        try {
            return new String(decrpytNew(str, Base64.decode(str2, 0)), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            String str3 = f11941a;
            C5106b.m13942b(str3, "UnsupportedEncodingException: " + e.getMessage());
            return "";
        } catch (Exception e2) {
            String str4 = f11941a;
            C5106b.m13942b(str4, "Exception: " + e2.getMessage());
            return "";
        }
    }

    @Deprecated
    public static String encrypt(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            return Base64.encodeToString(encrypt(str, str2.getBytes("UTF-8")), 0);
        } catch (UnsupportedEncodingException e) {
            String str3 = f11941a;
            C5106b.m13942b(str3, "UnsupportedEncodingException: " + e.getMessage());
            return "";
        }
    }

    public static String encryptNew(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            return Base64.encodeToString(encryptNew(str, str2.getBytes("UTF-8")), 0);
        } catch (UnsupportedEncodingException e) {
            String str3 = f11941a;
            C5106b.m13942b(str3, "UnsupportedEncodingException: " + e.getMessage());
            return "";
        }
    }

    @Deprecated
    public static byte[] encrypt(String str, byte[] bArr) {
        return m13978a(str, bArr, false);
    }

    public static byte[] encryptNew(String str, byte[] bArr) {
        return m13978a(str, bArr, true);
    }

    /* renamed from: b */
    private static boolean m13977b(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.getKey(str, null) != null;
        } catch (IOException e) {
            String str2 = f11941a;
            C5106b.m13942b(str2, "IOException: " + e.getMessage());
            return false;
        } catch (KeyStoreException e2) {
            String str3 = f11941a;
            C5106b.m13942b(str3, "KeyStoreException: " + e2.getMessage());
            return false;
        } catch (NoSuchAlgorithmException e3) {
            String str4 = f11941a;
            C5106b.m13942b(str4, "NoSuchAlgorithmException: " + e3.getMessage());
            return false;
        } catch (UnrecoverableKeyException e4) {
            String str5 = f11941a;
            C5106b.m13942b(str5, "UnrecoverableKeyException: " + e4.getMessage());
            return false;
        } catch (CertificateException e5) {
            String str6 = f11941a;
            C5106b.m13942b(str6, "CertificateException: " + e5.getMessage());
            return false;
        } catch (Exception e6) {
            String str7 = f11941a;
            C5106b.m13942b(str7, "Exception: " + e6.getMessage());
            return false;
        }
    }

    @Deprecated
    public static byte[] decrpyt(String str, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!m13981a()) {
                C5106b.m13942b(f11941a, "sdk version is too low");
                return bArr2;
            }
            PrivateKey m13980a = m13980a(str);
            if (m13980a == null) {
                C5106b.m13942b(f11941a, "Private key is null");
                return bArr2;
            }
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
                cipher.init(2, m13980a, new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT));
                return cipher.doFinal(bArr);
            } catch (InvalidAlgorithmParameterException e) {
                String str2 = f11941a;
                C5106b.m13942b(str2, "InvalidAlgorithmParameterException: " + e.getMessage());
                return bArr2;
            } catch (InvalidKeyException e2) {
                String str3 = f11941a;
                C5106b.m13942b(str3, "InvalidKeyException: " + e2.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e3) {
                String str4 = f11941a;
                C5106b.m13942b(str4, "NoSuchAlgorithmException: " + e3.getMessage());
                return bArr2;
            } catch (BadPaddingException e4) {
                String str5 = f11941a;
                C5106b.m13942b(str5, "BadPaddingException: " + e4.getMessage());
                return bArr2;
            } catch (IllegalBlockSizeException e5) {
                String str6 = f11941a;
                C5106b.m13942b(str6, "IllegalBlockSizeException: " + e5.getMessage());
                return bArr2;
            } catch (NoSuchPaddingException e6) {
                String str7 = f11941a;
                C5106b.m13942b(str7, "NoSuchPaddingException: " + e6.getMessage());
                return bArr2;
            } catch (Exception e7) {
                String str8 = f11941a;
                C5106b.m13942b(str8, "Exception: " + e7.getMessage());
                return bArr2;
            }
        }
        C5106b.m13942b(f11941a, "alias or encrypted content is null");
        return bArr2;
    }

    public static byte[] decrpytNew(String str, byte[] bArr) {
        return decrpyt(str, bArr);
    }

    /* renamed from: a */
    private static synchronized KeyPair m13979a(String str, boolean z) {
        synchronized (RSAEncryptKS.class) {
            KeyPair keyPair = null;
            if (m13977b(str)) {
                C5106b.m13942b(f11941a, "Key pair exits");
                return null;
            }
            C5106b.m13941c(f11941a, "generate key pair.");
            try {
                try {
                    try {
                        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                        if (!z) {
                            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 2).setDigests("SHA-256", "SHA-512").setEncryptionPaddings("OAEPPadding").setKeySize(2048).build());
                        } else {
                            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 2).setDigests("SHA-256", "SHA-512").setEncryptionPaddings("OAEPPadding").setKeySize(3072).build());
                        }
                        keyPair = keyPairGenerator.generateKeyPair();
                    } catch (NoSuchProviderException e) {
                        String str2 = f11941a;
                        C5106b.m13942b(str2, "NoSuchProviderException: " + e.getMessage());
                    } catch (Exception e2) {
                        String str3 = f11941a;
                        C5106b.m13942b(str3, "Exception: " + e2.getMessage());
                    }
                } catch (InvalidAlgorithmParameterException e3) {
                    String str4 = f11941a;
                    C5106b.m13942b(str4, "InvalidAlgorithmParameterException: " + e3.getMessage());
                }
            } catch (NoSuchAlgorithmException e4) {
                String str5 = f11941a;
                C5106b.m13942b(str5, "NoSuchAlgorithmException: " + e4.getMessage());
            }
            return keyPair;
        }
    }

    /* renamed from: a */
    private static PrivateKey m13980a(String str) {
        if (m13977b(str)) {
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                return (PrivateKey) keyStore.getKey(str, null);
            } catch (IOException e) {
                String str2 = f11941a;
                C5106b.m13942b(str2, "IOException: " + e.getMessage());
                return null;
            } catch (KeyStoreException e2) {
                String str3 = f11941a;
                C5106b.m13942b(str3, "KeyStoreException: " + e2.getMessage());
                return null;
            } catch (NoSuchAlgorithmException e3) {
                String str4 = f11941a;
                C5106b.m13942b(str4, "NoSuchAlgorithmException: " + e3.getMessage());
                return null;
            } catch (UnrecoverableKeyException e4) {
                String str5 = f11941a;
                C5106b.m13942b(str5, "UnrecoverableKeyException: " + e4.getMessage());
                return null;
            } catch (CertificateException e5) {
                String str6 = f11941a;
                C5106b.m13942b(str6, "CertificateException: " + e5.getMessage());
                return null;
            } catch (Exception e6) {
                String str7 = f11941a;
                C5106b.m13942b(str7, "Exception: " + e6.getMessage());
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m13981a() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
