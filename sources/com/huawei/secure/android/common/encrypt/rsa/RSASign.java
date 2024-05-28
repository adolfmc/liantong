package com.huawei.secure.android.common.encrypt.rsa;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.secure.android.common.encrypt.utils.C5106b;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class RSASign {

    /* renamed from: a */
    private static final String f11959a = "SHA256WithRSA";

    /* renamed from: b */
    private static final String f11960b = "SHA256WithRSA/PSS";

    /* renamed from: c */
    private static final String f11961c = "RSASign";

    /* renamed from: d */
    private static final String f11962d = "UTF-8";

    /* renamed from: e */
    private static final String f11963e = "";

    /* renamed from: a */
    private static String m13967a(String str, String str2, boolean z) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            PrivateKey privateKey = EncryptUtil.getPrivateKey(str2);
            if (z) {
                return newSign(str, privateKey);
            }
            return sign(str, privateKey);
        }
        C5106b.m13942b(f11961c, "sign content or key is null");
        return "";
    }

    public static boolean isBuildVersionHigherThan23() {
        return Build.VERSION.SDK_INT > 23;
    }

    public static String newSign(String str, String str2) {
        if (!isBuildVersionHigherThan23()) {
            C5106b.m13942b(f11961c, "sdk version is too low");
            return "";
        }
        return m13967a(str, str2, true);
    }

    public static boolean newVerifySign(String str, String str2, String str3) {
        if (!isBuildVersionHigherThan23()) {
            C5106b.m13942b(f11961c, "sdk version is too low");
            return false;
        }
        return m13969a(str, str2, str3, true);
    }

    @Deprecated
    public static String sign(String str, String str2) {
        return m13967a(str, str2, false);
    }

    @Deprecated
    public static boolean verifySign(String str, String str2, String str3) {
        return m13969a(str, str2, str3, false);
    }

    @Deprecated
    public static String sign(String str, PrivateKey privateKey) {
        return m13966a(str, privateKey, false);
    }

    @Deprecated
    public static boolean verifySign(String str, String str2, PublicKey publicKey) {
        return m13968a(str, str2, publicKey, false);
    }

    public static byte[] sign(byte[] bArr, PrivateKey privateKey, boolean z) {
        Signature signature;
        byte[] bArr2 = new byte[0];
        if (bArr != null && privateKey != null && RSAEncrypt.isPrivateKeyLengthRight((RSAPrivateKey) privateKey)) {
            try {
                if (z) {
                    signature = Signature.getInstance("SHA256WithRSA/PSS");
                    signature.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
                } else {
                    signature = Signature.getInstance("SHA256WithRSA");
                }
                signature.initSign(privateKey);
                signature.update(bArr);
                return signature.sign();
            } catch (InvalidAlgorithmParameterException e) {
                String str = f11961c;
                C5106b.m13942b(str, "sign InvalidAlgorithmParameterException: " + e.getMessage());
                return bArr2;
            } catch (InvalidKeyException e2) {
                String str2 = f11961c;
                C5106b.m13942b(str2, "sign InvalidKeyException: " + e2.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e3) {
                String str3 = f11961c;
                C5106b.m13942b(str3, "sign NoSuchAlgorithmException: " + e3.getMessage());
                return bArr2;
            } catch (SignatureException e4) {
                String str4 = f11961c;
                C5106b.m13942b(str4, "sign SignatureException: " + e4.getMessage());
                return bArr2;
            } catch (Exception e5) {
                String str5 = f11961c;
                C5106b.m13942b(str5, "sign Exception: " + e5.getMessage());
                return bArr2;
            }
        }
        C5106b.m13942b(f11961c, "content or privateKey is null , or length is too short");
        return bArr2;
    }

    public static boolean verifySign(byte[] bArr, byte[] bArr2, PublicKey publicKey, boolean z) {
        Signature signature;
        if (bArr != null && publicKey != null && bArr2 != null && RSAEncrypt.isPublicKeyLengthRight((RSAPublicKey) publicKey)) {
            try {
                if (z) {
                    signature = Signature.getInstance("SHA256WithRSA/PSS");
                    signature.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
                } else {
                    signature = Signature.getInstance("SHA256WithRSA");
                }
                signature.initVerify(publicKey);
                signature.update(bArr);
                return signature.verify(bArr2);
            } catch (GeneralSecurityException e) {
                String str = f11961c;
                C5106b.m13942b(str, "check sign exception: " + e.getMessage());
                return false;
            } catch (Exception e2) {
                String str2 = f11961c;
                C5106b.m13942b(str2, "exception : " + e2.getMessage());
                return false;
            }
        }
        C5106b.m13942b(f11961c, "content or publicKey is null , or length is too short");
        return false;
    }

    public static String newSign(String str, PrivateKey privateKey) {
        if (!isBuildVersionHigherThan23()) {
            C5106b.m13942b(f11961c, "sdk version is too low");
            return "";
        }
        return m13966a(str, privateKey, true);
    }

    public static boolean newVerifySign(String str, String str2, PublicKey publicKey) {
        if (!isBuildVersionHigherThan23()) {
            C5106b.m13942b(f11961c, "sdk version is too low");
            return false;
        }
        return m13968a(str, str2, publicKey, true);
    }

    /* renamed from: a */
    private static String m13966a(String str, PrivateKey privateKey, boolean z) {
        try {
            return Base64.encodeToString(sign(str.getBytes("UTF-8"), privateKey, z), 0);
        } catch (UnsupportedEncodingException e) {
            String str2 = f11961c;
            C5106b.m13942b(str2, "sign UnsupportedEncodingException: " + e.getMessage());
            return "";
        }
    }

    /* renamed from: a */
    private static boolean m13969a(String str, String str2, String str3, boolean z) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str2)) {
            RSAPublicKey publicKey = EncryptUtil.getPublicKey(str3);
            if (z) {
                return newVerifySign(str, str2, publicKey);
            }
            return verifySign(str, str2, publicKey);
        }
        C5106b.m13942b(f11961c, "content or public key or sign value is null");
        return false;
    }

    /* renamed from: a */
    private static boolean m13968a(String str, String str2, PublicKey publicKey, boolean z) {
        try {
            return verifySign(str.getBytes("UTF-8"), Base64.decode(str2, 0), publicKey, z);
        } catch (UnsupportedEncodingException e) {
            String str3 = f11961c;
            C5106b.m13942b(str3, "verifySign UnsupportedEncodingException: " + e.getMessage());
            return false;
        } catch (Exception e2) {
            String str4 = f11961c;
            C5106b.m13942b(str4, "base64 decode Exception : " + e2.getMessage());
            return false;
        }
    }

    public static boolean verifySign(ByteBuffer byteBuffer, byte[] bArr, PublicKey publicKey, boolean z) {
        Signature signature;
        if (byteBuffer != null && publicKey != null && bArr != null && RSAEncrypt.isPublicKeyLengthRight((RSAPublicKey) publicKey)) {
            try {
                if (z) {
                    signature = Signature.getInstance("SHA256WithRSA/PSS");
                    signature.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
                } else {
                    signature = Signature.getInstance("SHA256WithRSA");
                }
                signature.initVerify(publicKey);
                signature.update(byteBuffer);
                return signature.verify(bArr);
            } catch (GeneralSecurityException e) {
                String str = f11961c;
                C5106b.m13942b(str, "check sign exception: " + e.getMessage());
                return false;
            } catch (Exception e2) {
                String str2 = f11961c;
                C5106b.m13942b(str2, "exception : " + e2.getMessage());
                return false;
            }
        }
        C5106b.m13942b(f11961c, "content or publicKey is null , or length is too short");
        return false;
    }

    public static byte[] sign(ByteBuffer byteBuffer, PrivateKey privateKey, boolean z) {
        Signature signature;
        byte[] bArr = new byte[0];
        if (byteBuffer != null && privateKey != null && RSAEncrypt.isPrivateKeyLengthRight((RSAPrivateKey) privateKey)) {
            try {
                if (z) {
                    signature = Signature.getInstance("SHA256WithRSA/PSS");
                    signature.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
                } else {
                    signature = Signature.getInstance("SHA256WithRSA");
                }
                signature.initSign(privateKey);
                signature.update(byteBuffer);
                bArr = signature.sign();
                String str = f11961c;
                C5106b.m13941c(str, "result is : " + Arrays.toString(bArr));
                return bArr;
            } catch (InvalidAlgorithmParameterException e) {
                String str2 = f11961c;
                C5106b.m13942b(str2, "sign InvalidAlgorithmParameterException: " + e.getMessage());
                return bArr;
            } catch (InvalidKeyException e2) {
                String str3 = f11961c;
                C5106b.m13942b(str3, "sign InvalidKeyException: " + e2.getMessage());
                return bArr;
            } catch (NoSuchAlgorithmException e3) {
                String str4 = f11961c;
                C5106b.m13942b(str4, "sign NoSuchAlgorithmException: " + e3.getMessage());
                return bArr;
            } catch (SignatureException e4) {
                String str5 = f11961c;
                C5106b.m13942b(str5, "sign SignatureException: " + e4.getMessage());
                return bArr;
            } catch (Exception e5) {
                String str6 = f11961c;
                C5106b.m13942b(str6, "sign Exception: " + e5.getMessage());
                return bArr;
            }
        }
        C5106b.m13942b(f11961c, "content or privateKey is null , or length is too short");
        return bArr;
    }
}
