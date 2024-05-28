package com.huawei.secure.android.common.encrypt.hash;

import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.utils.C5106b;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class HMACSHA256 {

    /* renamed from: a */
    private static final String f11910a = "HMACSHA256";

    /* renamed from: b */
    private static final String f11911b = "HmacSHA256";

    /* renamed from: c */
    private static final String f11912c = "";

    /* renamed from: d */
    private static final int f11913d = 32;

    public static byte[] hmacEncrypt(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null) {
            if (bArr2.length < 32) {
                C5106b.m13942b(f11910a, "hmac key length is not right");
                return new byte[0];
            }
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
                Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
                mac.init(secretKeySpec);
                return mac.doFinal(bArr);
            } catch (InvalidKeyException | NoSuchAlgorithmException e) {
                String str = f11910a;
                C5106b.m13942b(str, "hmacsha256 encrypt exception" + e.getMessage());
                return new byte[0];
            }
        }
        C5106b.m13942b(f11910a, "content or key is null.");
        return new byte[0];
    }

    public static String hmacSHA256Encrypt(String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? "" : hmacSHA256Encrypt(str, HexUtil.hexStr2ByteArray(str2));
    }

    public static String hmacSHA256Encrypt(String str, byte[] bArr) {
        byte[] bArr2;
        if (TextUtils.isEmpty(str) || bArr == null) {
            return "";
        }
        if (bArr.length < 32) {
            C5106b.m13942b(f11910a, "hmac key length is not right");
            return "";
        }
        try {
            bArr2 = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            String str2 = f11910a;
            C5106b.m13942b(str2, "hmacsha256 encrypt exception" + e.getMessage());
            bArr2 = new byte[0];
        }
        return HexUtil.byteArray2HexStr(hmacEncrypt(bArr2, bArr));
    }
}
