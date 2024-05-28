package com.huawei.secure.android.common.encrypt.keystore.rsa;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
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
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class RSASignKS {

    /* renamed from: a */
    private static final String f11947a = "RSASignKS";

    /* renamed from: b */
    private static final String f11948b = "AndroidKeyStore";

    /* renamed from: c */
    private static final String f11949c = "SHA256withRSA/PSS";

    /* renamed from: d */
    private static final String f11950d = "";

    /* renamed from: e */
    private static final int f11951e = 2048;

    /* renamed from: f */
    private static final int f11952f = 3072;

    /* renamed from: a */
    private static byte[] m13972a(String str, byte[] bArr, boolean z) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!isBuildVersionHigherThan22()) {
                C5106b.m13942b(f11947a, "sdk version is too low");
                return bArr2;
            }
            KeyStore.Entry m13970b = m13970b(str, z);
            if (!(m13970b instanceof KeyStore.PrivateKeyEntry)) {
                C5106b.m13942b(f11947a, "Not an instance of a PrivateKeyEntry");
                return bArr2;
            }
            try {
                Signature signature = Signature.getInstance("SHA256withRSA/PSS");
                signature.initSign(((KeyStore.PrivateKeyEntry) m13970b).getPrivateKey());
                signature.update(bArr);
                return signature.sign();
            } catch (InvalidKeyException e) {
                String str2 = f11947a;
                C5106b.m13942b(str2, "InvalidKeyException: " + e.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e2) {
                String str3 = f11947a;
                C5106b.m13942b(str3, "NoSuchAlgorithmException: " + e2.getMessage());
                return bArr2;
            } catch (SignatureException e3) {
                String str4 = f11947a;
                C5106b.m13942b(str4, "SignatureException: " + e3.getMessage());
                return bArr2;
            } catch (Exception e4) {
                String str5 = f11947a;
                C5106b.m13942b(str5, "Exception: " + e4.getMessage());
                return bArr2;
            }
        }
        C5106b.m13942b(f11947a, "alias or content is null");
        return bArr2;
    }

    /* renamed from: b */
    private static KeyStore.Entry m13970b(String str, boolean z) {
        if (!m13974a(str)) {
            m13973a(str, z);
        }
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.getEntry(str, null);
        } catch (IOException e) {
            String str2 = f11947a;
            C5106b.m13942b(str2, "IOException: " + e.getMessage());
            return null;
        } catch (KeyStoreException e2) {
            String str3 = f11947a;
            C5106b.m13942b(str3, "KeyStoreException: " + e2.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e3) {
            String str4 = f11947a;
            C5106b.m13942b(str4, "NoSuchAlgorithmException: " + e3.getMessage());
            return null;
        } catch (UnrecoverableEntryException e4) {
            String str5 = f11947a;
            C5106b.m13942b(str5, "UnrecoverableEntryException: " + e4.getMessage());
            return null;
        } catch (CertificateException e5) {
            String str6 = f11947a;
            C5106b.m13942b(str6, "CertificateException: " + e5.getMessage());
            return null;
        }
    }

    public static boolean isBuildVersionHigherThan22() {
        return Build.VERSION.SDK_INT >= 23;
    }

    @Deprecated
    public static String sign(String str, String str2) {
        try {
            return Base64.encodeToString(sign(str, str2.getBytes("UTF-8")), 0);
        } catch (UnsupportedEncodingException e) {
            String str3 = f11947a;
            Log.e(str3, "sign UnsupportedEncodingException : " + e.getMessage());
            return "";
        }
    }

    public static String signNew(String str, String str2) {
        try {
            return Base64.encodeToString(signNew(str, str2.getBytes("UTF-8")), 0);
        } catch (UnsupportedEncodingException e) {
            String str3 = f11947a;
            Log.e(str3, "sign UnsupportedEncodingException : " + e.getMessage());
            return "";
        }
    }

    @Deprecated
    public static boolean verifySign(String str, String str2, String str3) {
        try {
            return verifySign(str, str2.getBytes("UTF-8"), Base64.decode(str3, 0));
        } catch (UnsupportedEncodingException e) {
            String str4 = f11947a;
            Log.e(str4, "verifySign UnsupportedEncodingException: " + e.getMessage());
            return false;
        } catch (Exception e2) {
            String str5 = f11947a;
            C5106b.m13942b(str5, "base64 decode Exception" + e2.getMessage());
            return false;
        }
    }

    public static boolean verifySignNew(String str, String str2, String str3) {
        try {
            return verifySignNew(str, str2.getBytes("UTF-8"), Base64.decode(str3, 0));
        } catch (UnsupportedEncodingException e) {
            String str4 = f11947a;
            Log.e(str4, "verifySign UnsupportedEncodingException: " + e.getMessage());
            return false;
        } catch (Exception e2) {
            String str5 = f11947a;
            C5106b.m13942b(str5, "base64 decode Exception" + e2.getMessage());
            return false;
        }
    }

    @Deprecated
    public static byte[] sign(String str, byte[] bArr) {
        return m13972a(str, bArr, false);
    }

    public static byte[] signNew(String str, byte[] bArr) {
        return m13972a(str, bArr, true);
    }

    @Deprecated
    public static boolean verifySign(String str, byte[] bArr, byte[] bArr2) {
        return m13971a(str, bArr, bArr2, false);
    }

    public static boolean verifySignNew(String str, byte[] bArr, byte[] bArr2) {
        return m13971a(str, bArr, bArr2, true);
    }

    /* renamed from: a */
    private static boolean m13971a(String str, byte[] bArr, byte[] bArr2, boolean z) {
        if (!TextUtils.isEmpty(str) && bArr != null && bArr2 != null) {
            if (!isBuildVersionHigherThan22()) {
                C5106b.m13942b(f11947a, "sdk version is too low");
                return false;
            }
            KeyStore.Entry m13970b = m13970b(str, z);
            if (!(m13970b instanceof KeyStore.PrivateKeyEntry)) {
                C5106b.m13942b(f11947a, "Not an instance of a PrivateKeyEntry");
                return false;
            }
            try {
                Signature signature = Signature.getInstance("SHA256withRSA/PSS");
                signature.initVerify(((KeyStore.PrivateKeyEntry) m13970b).getCertificate());
                signature.update(bArr);
                return signature.verify(bArr2);
            } catch (InvalidKeyException e) {
                String str2 = f11947a;
                C5106b.m13942b(str2, "InvalidKeyException: " + e.getMessage());
                return false;
            } catch (NoSuchAlgorithmException e2) {
                String str3 = f11947a;
                C5106b.m13942b(str3, "NoSuchAlgorithmException: " + e2.getMessage());
                return false;
            } catch (SignatureException e3) {
                String str4 = f11947a;
                C5106b.m13942b(str4, "SignatureException: " + e3.getMessage());
                return false;
            } catch (Exception e4) {
                String str5 = f11947a;
                C5106b.m13942b(str5, "Exception: " + e4.getMessage());
                return false;
            }
        }
        C5106b.m13942b(f11947a, "alias or content or sign value is null");
        return false;
    }

    /* renamed from: a */
    private static synchronized KeyPair m13973a(String str, boolean z) {
        synchronized (RSASignKS.class) {
            KeyPair keyPair = null;
            if (m13974a(str)) {
                C5106b.m13942b(f11947a, "Key pair exits");
                return null;
            }
            try {
                try {
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                    if (!z) {
                        keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 12).setDigests("SHA-256", "SHA-512").setSignaturePaddings("PSS").setKeySize(2048).build());
                    } else {
                        keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 12).setDigests("SHA-256", "SHA-512").setSignaturePaddings("PSS").setKeySize(3072).build());
                    }
                    keyPair = keyPairGenerator.generateKeyPair();
                } catch (InvalidAlgorithmParameterException e) {
                    String str2 = f11947a;
                    C5106b.m13942b(str2, "InvalidAlgorithmParameterException: " + e.getMessage());
                } catch (NoSuchProviderException e2) {
                    String str3 = f11947a;
                    C5106b.m13942b(str3, "NoSuchProviderException: " + e2.getMessage());
                }
            } catch (NoSuchAlgorithmException e3) {
                String str4 = f11947a;
                C5106b.m13942b(str4, "NoSuchAlgorithmException: " + e3.getMessage());
            }
            return keyPair;
        }
    }

    /* renamed from: a */
    private static boolean m13974a(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.getKey(str, null) != null;
        } catch (IOException e) {
            String str2 = f11947a;
            C5106b.m13942b(str2, "IOException: " + e.getMessage());
            return false;
        } catch (KeyStoreException e2) {
            String str3 = f11947a;
            C5106b.m13942b(str3, "KeyStoreException: " + e2.getMessage());
            return false;
        } catch (NoSuchAlgorithmException e3) {
            String str4 = f11947a;
            C5106b.m13942b(str4, "NoSuchAlgorithmException: " + e3.getMessage());
            return false;
        } catch (UnrecoverableKeyException e4) {
            String str5 = f11947a;
            C5106b.m13942b(str5, "UnrecoverableKeyException: " + e4.getMessage());
            return false;
        } catch (CertificateException e5) {
            String str6 = f11947a;
            C5106b.m13942b(str6, "CertificateException: " + e5.getMessage());
            return false;
        }
    }
}
