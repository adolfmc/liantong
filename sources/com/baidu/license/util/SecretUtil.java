package com.baidu.license.util;

import com.baidu.license.C2600R;
import com.baidu.license.LicenseManager;
import java.nio.charset.StandardCharsets;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SecretUtil {
    static {
        System.loadLibrary("license");
    }

    /* renamed from: a */
    public static String m19637a() {
        String concat = LicenseManager.sContext.getString(C2600R.string.one_key).concat(LicenseManager.sContext.getString(C2600R.string.two_key));
        byte[] bytes = "Kk%_9fpjP}fSG~Sv\\*Z#QW]B#vYxXJf{C|tTbQ!bV|\u007fHb9JA}Sy]#j$|bP\u007fF`E}+=sU^DAq{FQFVkqYcJ^Ax#Ky`]w^Wu_`%=]Q@9|b[eB|uY\"uvVxP^${~+fJP'=w=eXC&".getBytes();
        if (bytes == null) {
            bytes = null;
        } else {
            int length = bytes.length;
            for (int i = 0; i < length; i++) {
                bytes[i] = (byte) (bytes[i] ^ 18);
            }
        }
        String str = new String(bytes);
        String str2 = new String(getSecretByte(""), StandardCharsets.UTF_8);
        String str3 = new String(getSecretByte2(), StandardCharsets.UTF_8);
        return concat.concat(str.concat(str2).concat(str3).concat(new String(getSecretByte3(), StandardCharsets.UTF_8)));
    }

    public static native byte[] getSecretByte(String str);

    public static native byte[] getSecretByte2();

    public static native byte[] getSecretByte3();
}
