package com.baidu.cloud.license.util;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.license.util.trw */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class RsaUtil {
    /* renamed from: a */
    public static String m20066a(String str, String str2) {
        byte[] m20081a = C2491nx.m20081a(str);
        byte[] m20081a2 = C2491nx.m20081a(str2);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, keyFactory.generatePrivate(new PKCS8EncodedKeySpec(m20081a2)));
        return new String(m20065a(m20081a, cipher));
    }

    /* renamed from: a */
    private static byte[] m20065a(byte[] bArr, Cipher cipher) {
        byte[] doFinal;
        try {
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 > 0) {
                    if (i3 > 128) {
                        doFinal = cipher.doFinal(bArr, i, 128);
                    } else {
                        doFinal = cipher.doFinal(bArr, i, i3);
                    }
                    byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                    i2++;
                    i = i2 << 7;
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
            }
        } catch (Exception unused) {
            return null;
        }
    }
}
