package cn.finalteam.toolsfinal.coder;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RSACoder {
    public static final String CHIPER_ALGORITHM = "RSA/ECB/";
    public static final String KEY_ALGORITHM = "RSA";
    public static final int KEY_SIZE = 1024;
    public static final byte[] PUBLIC_EXPONENT = {1, 0, 1};

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum PADDING {
        NoPadding,
        PKCS1Padding
    }

    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024, new SecureRandom());
            return keyPairGenerator.genKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Error when init key pair, errmsg: " + e.getMessage(), e);
        }
    }

    private static RSAPublicKey generateRSAPublicKey(byte[] bArr, byte[] bArr2) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, bArr), new BigInteger(1, bArr2)));
        } catch (Exception e) {
            throw new RuntimeException("Error when generate rsaPubblicKey, errmsg: " + e.getMessage(), e);
        }
    }

    private static RSAPrivateKey generateRSAPrivateKey(byte[] bArr, byte[] bArr2) {
        try {
            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateKeySpec(new BigInteger(1, bArr), new BigInteger(1, bArr2)));
        } catch (Exception e) {
            throw new RuntimeException("Error when generate rsaPrivateKey, errmsg: " + e.getMessage(), e);
        }
    }

    private static byte[] encrypt(Key key, byte[] bArr, PADDING padding) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("RSA/ECB/");
            if (padding == null) {
                padding = PADDING.NoPadding;
            }
            sb.append(padding);
            Cipher cipher = Cipher.getInstance(sb.toString());
            cipher.init(1, key);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            throw new RuntimeException("Error when encrypt data, errmsg: " + e.getMessage(), e);
        }
    }

    public static byte[] encryptByPublicKey(byte[] bArr, byte[] bArr2, PADDING padding) {
        return encrypt(generateRSAPublicKey(bArr, PUBLIC_EXPONENT), bArr2, padding);
    }

    public static byte[] encryptByPrivateKey(byte[] bArr, byte[] bArr2, byte[] bArr3, PADDING padding) {
        return encrypt(generateRSAPrivateKey(bArr, bArr2), bArr3, padding);
    }

    private static byte[] decrypt(Key key, byte[] bArr, PADDING padding) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("RSA/ECB/");
            if (padding == null) {
                padding = PADDING.NoPadding;
            }
            sb.append(padding);
            Cipher cipher = Cipher.getInstance(sb.toString());
            cipher.init(2, key);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            throw new RuntimeException("Error when decrypt data, errmsg: " + e.getMessage(), e);
        }
    }

    public static byte[] decryptByPublicKey(byte[] bArr, byte[] bArr2, PADDING padding) {
        return decrypt(generateRSAPublicKey(bArr, PUBLIC_EXPONENT), bArr2, padding);
    }

    public static byte[] decryptByPrivateKey(byte[] bArr, byte[] bArr2, byte[] bArr3, PADDING padding) {
        return decrypt(generateRSAPrivateKey(bArr, bArr2), bArr3, padding);
    }
}
