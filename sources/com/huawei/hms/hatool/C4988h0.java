package com.huawei.hms.hatool;

import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.h0 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4988h0 {
    static {
        Charset.forName("UTF-8");
    }

    /* renamed from: a */
    public static String m14689a(String str, String str2) {
        try {
            return m14688a(str, str2.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            C5029v.m14455c("hmsSdk", "Unsupported encoding exception,utf-8");
            return "";
        }
    }

    /* renamed from: a */
    public static String m14688a(String str, byte[] bArr) {
        String str2;
        String str3;
        if (bArr == null || bArr.length == 0) {
            C5029v.m14451f("hmsSdk", "encrypt: content is empty or null");
            return "";
        }
        try {
            return HexUtil.byteArray2HexStr(m14686a(bArr, m14687a(HexUtil.hexStr2ByteArray(str))));
        } catch (NoSuchAlgorithmException unused) {
            str2 = "hmsSdk";
            str3 = "encrypt(): getInstance - No such algorithm,transformation";
            C5029v.m14451f(str2, str3);
            return "";
        } catch (InvalidKeySpecException unused2) {
            str2 = "hmsSdk";
            str3 = "encrypt(): Invalid key specification";
            C5029v.m14451f(str2, str3);
            return "";
        }
    }

    /* renamed from: a */
    private static PublicKey m14687a(byte[] bArr) {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr));
    }

    /* renamed from: a */
    private static byte[] m14686a(byte[] bArr, PublicKey publicKey) {
        String str;
        String str2;
        try {
            if (publicKey != null) {
                Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING");
                cipher.init(1, publicKey);
                return cipher.doFinal(bArr);
            }
            throw new UnsupportedEncodingException("The loaded public key is null");
        } catch (UnsupportedEncodingException unused) {
            str = "hmsSdk";
            str2 = "rsaEncrypt(): getBytes - Unsupported coding format!";
            C5029v.m14451f(str, str2);
            return new byte[0];
        } catch (InvalidKeyException unused2) {
            str = "hmsSdk";
            str2 = "rsaEncrypt(): init - Invalid key!";
            C5029v.m14451f(str, str2);
            return new byte[0];
        } catch (NoSuchAlgorithmException unused3) {
            str = "hmsSdk";
            str2 = "rsaEncrypt(): getInstance - No such algorithm,transformation";
            C5029v.m14451f(str, str2);
            return new byte[0];
        } catch (BadPaddingException unused4) {
            str = "hmsSdk";
            str2 = "rsaEncrypt():False filling parameters!";
            C5029v.m14451f(str, str2);
            return new byte[0];
        } catch (IllegalBlockSizeException unused5) {
            str = "hmsSdk";
            str2 = "rsaEncrypt(): doFinal - The provided block is not filled with";
            C5029v.m14451f(str, str2);
            return new byte[0];
        } catch (NoSuchPaddingException unused6) {
            str = "hmsSdk";
            str2 = "rsaEncrypt():  No such filling parameters ";
            C5029v.m14451f(str, str2);
            return new byte[0];
        }
    }
}
