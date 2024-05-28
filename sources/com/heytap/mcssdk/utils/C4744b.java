package com.heytap.mcssdk.utils;

import android.text.TextUtils;
import com.heytap.mcssdk.p203a.C4712a;
import com.heytap.msp.push.encrypt.AESEncrypt;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.heytap.mcssdk.utils.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4744b {

    /* renamed from: a */
    public static String f10703a = null;

    /* renamed from: b */
    public static final String f10704b = "Y29tLm5lYXJtZS5tY3M=";

    /* renamed from: c */
    public static String f10705c = "";

    /* renamed from: a */
    private static String m15509a() {
        if (TextUtils.isEmpty(f10705c)) {
            f10705c = new String(C4712a.m15605b("Y29tLm5lYXJtZS5tY3M="));
        }
        byte[] m15507a = m15507a(m15508a(f10705c));
        return m15507a != null ? new String(m15507a, Charset.forName("UTF-8")) : "";
    }

    /* renamed from: a */
    public static byte[] m15508a(String str) {
        if (str == null) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return new byte[0];
        }
    }

    /* renamed from: a */
    public static byte[] m15507a(byte[] bArr) {
        int length = bArr.length % 2 == 0 ? bArr.length : bArr.length - 1;
        for (int i = 0; i < length; i += 2) {
            byte b = bArr[i];
            int i2 = i + 1;
            bArr[i] = bArr[i2];
            bArr[i2] = b;
        }
        return bArr;
    }

    /* renamed from: b */
    public static String m15506b(String str) {
        boolean z;
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            str2 = AbstractC4745c.m15502a(str, m15509a());
            C4746d.m15494b("sdkDecrypt desDecrypt des data " + str2);
            z = true;
        } catch (Exception e) {
            C4746d.m15494b("sdkDecrypt DES excepiton " + e.toString());
            z = false;
        }
        if (TextUtils.isEmpty(str2) ? false : z) {
            return str2;
        }
        try {
            str2 = AESEncrypt.decrypt("isvrbeT7qUywVEZ1Ia0/aUVA/TcFaeV0wC8qFLc8rg4=", str);
            f10703a = C0108a.f85c;
            C4747e.m15468c().m15471a(f10703a);
            C4746d.m15494b("sdkDecrypt desDecrypt aes data " + str2);
            return str2;
        } catch (Exception e2) {
            C4746d.m15494b("sdkDecrypt AES excepiton " + e2.toString());
            return str2;
        }
    }

    /* renamed from: c */
    public static String m15505c(String str) {
        boolean z;
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            str2 = AESEncrypt.decrypt("isvrbeT7qUywVEZ1Ia0/aUVA/TcFaeV0wC8qFLc8rg4=", str);
            C4746d.m15494b("sdkDecrypt aesDecrypt aes data " + str2);
            z = true;
        } catch (Exception e) {
            C4746d.m15494b("sdkDecrypt AES excepiton " + e.toString());
            z = false;
        }
        if (TextUtils.isEmpty(str2) ? false : z) {
            return str2;
        }
        try {
            str2 = AbstractC4745c.m15502a(str, m15509a());
            f10703a = "DES";
            C4747e.m15468c().m15471a(f10703a);
            C4746d.m15494b("sdkDecrypt aesDecrypt des data " + str2);
            return str2;
        } catch (Exception e2) {
            C4746d.m15494b("sdkDecrypt DES excepiton " + e2.toString());
            return str2;
        }
    }

    /* renamed from: d */
    public static String m15504d(String str) {
        C4746d.m15494b("sdkDecrypt start data " + str);
        if (TextUtils.isEmpty(f10703a)) {
            f10703a = C4747e.m15468c().m15469b();
        }
        if ("DES".equals(f10703a)) {
            C4746d.m15494b("sdkDecrypt start DES");
            return m15506b(str);
        }
        C4746d.m15494b("sdkDecrypt start AES");
        return m15505c(str);
    }
}
