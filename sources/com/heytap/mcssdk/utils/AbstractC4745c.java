package com.heytap.mcssdk.utils;

import android.util.Base64;
import java.nio.charset.Charset;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.heytap.mcssdk.utils.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC4745c {
    /* renamed from: a */
    public static String m15502a(String str, String str2) {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, m15503a(str2));
        return new String(cipher.doFinal(Base64.decode(str, 0)), Charset.defaultCharset()).trim();
    }

    /* renamed from: a */
    private static Key m15503a(String str) {
        return SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(Base64.decode(str, 0)));
    }
}
