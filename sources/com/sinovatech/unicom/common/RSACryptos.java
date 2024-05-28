package com.sinovatech.unicom.common;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import javax.crypto.Cipher;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RSACryptos {
    public static final int DEFAULT_BUFFERSIZE = 117;
    public static final int DEFAULT_KEY_SIZE = 1024;
    public static final byte[] DEFAULT_SPLIT = "#PART#".getBytes();
    public static final String RSA = "RSA";
    public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    public static final String idcardpublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQsYZvdJYlXd6EubopAUyLyk0FxsS9hP3VTyi2DILBVfEfxjr9IMc1CSuyHDa9kESIh8rlgyB8DvnWW90zc+xKxPnA2edPRGpPDdLHEy78PD6FUXFBM6xhLUlaZhUZiLyR+vtkgtNoSPuDh8Q3Uk6uPUBjslqvz5JV7uVktuz0kwIDAQAB";
    public static final String pingjiaUnicomKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCy0tZaZgUJqvoBfcVXoGuquEq3vaU8EDjRlqSE+YALleDp4VWvRQ1ME4u7PDAFlhnDxR8wd6QIXt1rXezGHleT8Zvr+mUdY3YtpstcEylk4cCuWMlDYPy30MzIj2s1ZSk1gFMY5vTCHBOYPiQpiSCAiqd5R41fD+a2uPNhYOhIeQIDAQAB";
    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB";
    public static final String publicidcardKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJCxhm90liVd3oS5uikBTIvKTQXGxL2E/dVPKLYMgsFV8R/GOv0gxzUJK7IcNr2QRIiHyuWDIHwO+dZb3TNz7ErE+cDZ509Eak8N0scTLvw8PoVRcUEzrGEtSVpmFRmIvJH6+2SC02hI+4OHxDdSTq49QGOyWq/PklXu5WS27PSTAgMBAAECgYA9FoYwhiNh8Uih9pvO9hpwJl/wYWPss7pLFZmViqClqXgctx9ugSk5jf6huuGtlLwqfFJAJeVQGy6FvtEcCQQoQ2kb089XOVijf04XltEqFCUVxkUnE+ENtcKCnOSwNXFNQbP25caAyT/0Sf6FqsXAUkSX0EaUMa9e0k76YWsM2QJBAOpIKlVRPET03Q+nnqhMnwZz0Jp7+ytOVTrXCxZcMy1dzRK08y7S97OCbp+pCUYGrKo+NMjk57HLmqF6Vch+NZUCQQCeG01AEAlsupkQx1PIl9adc8qL8fVCqhqTmZBG39UQM0lVoBBBkUv5nxzTuaobwaKXNbepQVXw+yQPJK3yISeHAkEAlvtY5NDMcXgIOr2APt/aIDNk/RnnXRpHTPsm9wsGJDduIJ8ilUt6PGJTXmt2QX2tqq0aIVl7g5Y+GdCYFfRYHQI/HkMbhieLpkQRCCUe5EYrzfdbzW2ChEAK1jWOaAJvxaoLX1hDxEkLQbwyyFPBO47UkBy4Cq12xalMPZnHsZCnAkEAwIt5qL9RTy9LYDq4NI00zKB+/8iuKtery+V+sdE7xrCfI6tDH1Y/qSz5IcdTq2Da0XokZtZkx2sjPYFaF7T49A==";

    public static byte[] encryptByPublicKey(byte[] bArr, byte[] bArr2) throws Exception {
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, generatePublic);
        return cipher.doFinal(bArr);
    }

    public static byte[] decryptByPrivateKey(byte[] bArr, byte[] bArr2) throws Exception {
        PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, generatePrivate);
        return cipher.doFinal(bArr);
    }

    public static byte[] encryptByPublicKeyForSpilt(byte[] bArr, byte[] bArr2) throws Exception {
        int length = bArr.length;
        if (length <= 117) {
            return encryptByPublicKey(bArr, bArr2);
        }
        ArrayList<Byte> arrayList = new ArrayList();
        int i = 0;
        byte[] bArr3 = new byte[117];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            bArr3[i2] = bArr[i4];
            i2++;
            if (i2 == 117 || i4 == length - 1) {
                i3++;
                if (i3 != 1) {
                    for (byte b : DEFAULT_SPLIT) {
                        arrayList.add(Byte.valueOf(b));
                    }
                }
                for (byte b2 : encryptByPublicKey(bArr3, bArr2)) {
                    arrayList.add(Byte.valueOf(b2));
                }
                if (i4 == length - 1) {
                    bArr3 = null;
                    i2 = 0;
                } else {
                    bArr3 = new byte[Math.min(117, (length - i4) - 1)];
                    i2 = 0;
                }
            }
        }
        byte[] bArr4 = new byte[arrayList.size()];
        for (Byte b3 : arrayList) {
            bArr4[i] = b3.byteValue();
            i++;
        }
        return bArr4;
    }

    public static byte[] decryptByPrivateKeyForSpilt(byte[] bArr, byte[] bArr2) throws Exception {
        boolean z;
        int length = DEFAULT_SPLIT.length;
        if (length <= 0) {
            return decryptByPrivateKey(bArr, bArr2);
        }
        int length2 = bArr.length;
        ArrayList<Byte> arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length2) {
            byte b = bArr[i2];
            if (i2 == length2 - 1) {
                byte[] bArr3 = new byte[length2 - i3];
                System.arraycopy(bArr, i3, bArr3, 0, bArr3.length);
                for (byte b2 : decryptByPrivateKey(bArr3, bArr2)) {
                    arrayList.add(Byte.valueOf(b2));
                }
                i3 = i2 + length;
                i2 = i3 - 1;
                z = false;
            } else {
                if (b == DEFAULT_SPLIT[0]) {
                    if (length <= 1) {
                        z = true;
                    } else if (i2 + length < length2) {
                        z = false;
                        for (int i4 = 1; i4 < length && DEFAULT_SPLIT[i4] == bArr[i2 + i4]; i4++) {
                            if (i4 == length - 1) {
                                z = true;
                            }
                        }
                    }
                }
                z = false;
            }
            if (z) {
                byte[] bArr4 = new byte[i2 - i3];
                System.arraycopy(bArr, i3, bArr4, 0, bArr4.length);
                for (byte b3 : decryptByPrivateKey(bArr4, bArr2)) {
                    arrayList.add(Byte.valueOf(b3));
                }
                int i5 = i2 + length;
                i3 = i5;
                i2 = i5 - 1;
            }
            i2++;
        }
        byte[] bArr5 = new byte[arrayList.size()];
        for (Byte b4 : arrayList) {
            bArr5[i] = b4.byteValue();
            i++;
        }
        return bArr5;
    }
}
