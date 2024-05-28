package com.huawei.hms.framework.network.grs.p220h;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.h.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4952b {

    /* renamed from: a */
    private static final String f11323a = "b";

    /* renamed from: b */
    private static final Pattern f11324b = Pattern.compile("[0-9]*[a-z|A-Z]*[一-龥]*");

    /* renamed from: a */
    public static String m14861a(String str) {
        return m14860a(str, "SHA-256");
    }

    /* renamed from: a */
    private static String m14860a(String str, String str2) {
        String str3;
        String str4;
        try {
        } catch (UnsupportedEncodingException unused) {
            str3 = f11323a;
            str4 = "encrypt UnsupportedEncodingException";
        }
        try {
            return m14859a(MessageDigest.getInstance(str2).digest(str.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException unused2) {
            str3 = f11323a;
            str4 = "encrypt NoSuchAlgorithmException";
            Logger.m15045w(str3, str4);
            return null;
        }
    }

    /* renamed from: a */
    private static String m14859a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static String m14858b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int i = 1;
        if (str.length() == 1) {
            return "*";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < str.length(); i2++) {
            String str2 = str.charAt(i2) + "";
            if (f11324b.matcher(str2).matches()) {
                if (i % 2 == 0) {
                    str2 = "*";
                }
                i++;
            }
            stringBuffer.append(str2);
        }
        return stringBuffer.toString();
    }
}
