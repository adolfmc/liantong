package com.alipay.sdk.encrypt;

import com.alipay.sdk.util.C2040c;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.encrypt.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2012b {
    /* renamed from: a */
    public static String m20837a(String str, String str2) {
        return m20838a(1, str, str2);
    }

    /* renamed from: b */
    public static String m20836b(String str, String str2) {
        return m20838a(2, str, str2);
    }

    /* renamed from: a */
    public static String m20838a(int i, String str, String str2) {
        byte[] bytes;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(i, secretKeySpec);
            if (i == 2) {
                bytes = C2011a.m20843a(str);
            } else {
                bytes = str.getBytes("UTF-8");
            }
            byte[] doFinal = cipher.doFinal(bytes);
            if (i == 2) {
                return new String(doFinal);
            }
            return C2011a.m20842a(doFinal);
        } catch (Exception e) {
            C2040c.m20715a(e);
            return null;
        }
    }
}
