package com.huawei.secure.android.common.encrypt.hash;

import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.utils.C5106b;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class SHA {

    /* renamed from: a */
    private static final String f11923a = "SHA";

    /* renamed from: b */
    private static final String f11924b = "SHA-256";

    /* renamed from: c */
    private static final String f11925c = "";

    /* renamed from: d */
    private static final String[] f11926d = {"SHA-256", "SHA-384", "SHA-512"};

    private SHA() {
    }

    /* renamed from: a */
    private static boolean m13988a(String str) {
        for (String str2 : f11926d) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String sha256Encrypt(String str) {
        return shaEncrypt(str, "SHA-256");
    }

    public static String shaEncrypt(String str, String str2) {
        byte[] bArr;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (!m13988a(str2)) {
                C5106b.m13942b(f11923a, "algorithm is not safe or legal");
                return "";
            }
            try {
                bArr = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                bArr = new byte[0];
                C5106b.m13942b(f11923a, "Error in generate SHA UnsupportedEncodingException");
            }
            return HexUtil.byteArray2HexStr(shaEncryptByte(bArr, str2));
        }
        C5106b.m13942b(f11923a, "content or algorithm is null.");
        return "";
    }

    public static byte[] shaEncryptByte(byte[] bArr, String str) {
        if (bArr != null && !TextUtils.isEmpty(str)) {
            if (!m13988a(str)) {
                C5106b.m13942b(f11923a, "algorithm is not safe or legal");
                return new byte[0];
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str);
                messageDigest.update(bArr);
                return messageDigest.digest();
            } catch (NoSuchAlgorithmException unused) {
                C5106b.m13942b(f11923a, "Error in generate SHA NoSuchAlgorithmException");
                return new byte[0];
            }
        }
        C5106b.m13942b(f11923a, "content or algorithm is null.");
        return new byte[0];
    }

    public static boolean validateSHA(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return false;
        }
        return str2.equals(shaEncrypt(str, str3));
    }

    public static boolean validateSHA256(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str2.equals(sha256Encrypt(str));
    }
}
