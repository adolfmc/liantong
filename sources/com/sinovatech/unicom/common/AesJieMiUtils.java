package com.sinovatech.unicom.common;

import android.util.Base64;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AesJieMiUtils {
    private static final String AESKEY = "qscenter!1234567";
    private static final String CIPHER_ALGORITHM = "AES";
    private static final String DEFAULT_VALUE = "0";
    private static final String KEY_ALGORITHM = "AES";
    public static final int SECRET_KEY_LENGTH = 16;
    private static final String TAG = AesJieMiUtils.class.getSimpleName() + " --> ";
    private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;

    public static String encrypt(String str) {
        try {
            Cipher cipher = Cipher.getInstance(C0108a.f85c);
            cipher.init(1, getSecretKey("qscenter!1234567"));
            return base64Encode(cipher.doFinal(str.getBytes(CHARSET_UTF8)));
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public static String decrypt(String str) {
        try {
            byte[] base64Decode = base64Decode(URLDecoder.decode(str, "UTF-8"));
            Cipher cipher = Cipher.getInstance(C0108a.f85c);
            cipher.init(2, getSecretKey("qscenter!1234567"));
            return new String(cipher.doFinal(base64Decode), CHARSET_UTF8);
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public static SecretKeySpec getSecretKey(String str) {
        return new SecretKeySpec(toMakeKey(str, 16, "0").getBytes(CHARSET_UTF8), C0108a.f85c);
    }

    private static String toMakeKey(String str, int i, String str2) {
        int length = str.length();
        if (length < i) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            for (int i2 = 0; i2 < i - length; i2++) {
                sb.append(str2);
            }
            return sb.toString();
        }
        return str;
    }

    public static byte[] base64Decode(String str) {
        return Base64.decode(str, 0);
    }

    public static String base64Encode(byte[] bArr) {
        try {
            return URLEncoder.encode(Base64.encodeToString(bArr, 0), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void handleException(Exception exc) {
        exc.printStackTrace();
        String str = TAG;
        Log.e(str, TAG + exc);
    }
}
