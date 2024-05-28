package com.xiaomi.push;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.bb */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11184bb {
    /* renamed from: a */
    public static String m4750a(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        return m4749a(objArr, str, 0, objArr.length);
    }

    /* renamed from: a */
    public static String m4749a(Object[] objArr, String str, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(i3 * ((objArr[i] == null ? 16 : objArr[i].toString().length()) + str.length()));
        for (int i4 = i; i4 < i2; i4++) {
            if (i4 > i) {
                stringBuffer.append(str);
            }
            if (objArr[i4] != null) {
                stringBuffer.append(objArr[i4]);
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static String m4752a(Iterator<?> it, String str) {
        if (it == null) {
            return null;
        }
        if (it.hasNext()) {
            Object next = it.next();
            if (!it.hasNext()) {
                return next.toString();
            }
            StringBuffer stringBuffer = new StringBuffer(256);
            if (next != null) {
                stringBuffer.append(next);
            }
            while (it.hasNext()) {
                if (str != null) {
                    stringBuffer.append(str);
                }
                Object next2 = it.next();
                if (next2 != null) {
                    stringBuffer.append(next2);
                }
            }
            return stringBuffer.toString();
        }
        return "";
    }

    /* renamed from: a */
    public static String m4753a(Collection<?> collection, String str) {
        if (collection == null) {
            return null;
        }
        return m4752a(collection.iterator(), str);
    }

    /* renamed from: a */
    public static String m4758a(int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt(62)));
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static String m4757a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(m4755a(str));
            return String.format("%1$032X", new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException unused) {
            return str;
        }
    }

    /* renamed from: a */
    public static String m4751a(byte[] bArr) {
        String str = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            str = String.format("%1$032X", new BigInteger(1, messageDigest.digest()));
        } catch (Exception unused) {
        }
        return str.toLowerCase();
    }

    /* renamed from: b */
    public static String m4748b(String str) {
        if (str != null) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                messageDigest.update(m4755a(str));
                return String.format("%1$032X", new BigInteger(1, messageDigest.digest()));
            } catch (NoSuchAlgorithmException unused) {
                return str;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static byte[] m4755a(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    /* renamed from: b */
    public static String m4747b(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    /* renamed from: a */
    public static boolean m4756a(String str) {
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt < 0 || charAt > 127) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    /* renamed from: a */
    public static String m4754a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        if (i <= 0 || length < i) {
            i = length / 3;
            if (i <= 1) {
                i = 1;
            }
            if (i > 3) {
                i = 3;
            }
        }
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            if (i3 % i == 0) {
                sb.append("*");
            } else {
                sb.append(str.charAt(i2));
            }
            i2 = i3;
        }
        return sb.toString();
    }
}
