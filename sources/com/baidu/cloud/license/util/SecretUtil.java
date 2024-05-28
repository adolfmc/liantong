package com.baidu.cloud.license.util;

import java.nio.charset.StandardCharsets;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SecretUtil {
    private static native byte[] getSecretByte(String str);

    private static native byte[] getSecretByte2();

    private static native byte[] getSecretByte3();

    static {
        System.loadLibrary("playerlicense");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m20083a() {
        String concat = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALCwRZyG7UhmHNjqeWmnqBmYG0Q49SRdd2NZgVY+AYOJeUcmR1Drpuh/++30mBV2qu1Db94/fHNfjw".concat("BPqfHGM062hnYQ5NFe84Uj6OAWg3IieuMzwq6HRTROUIH2muGi1glcpjxaZquQOODT0ont1lDamcRvDgnUqByOz6lynbuBAgMBAAECgYEAkmxSE+iBJZlx2JVfrp5BI");
        String str = new String(m20082a("Kk%_9fpjP}fSG~Sv\\*Z#QW]B#vYxXJf{C|tTbQ!bV|\u007fHb9JA}Sy]#j$|bP\u007fF`E}+=sU^DAq{FQFVkqYcJ^Ax#Ky`]w^Wu_`%=]Q@9|b[eB|uY\"uvVxP^${~+fJP'=w=eXC&".getBytes()));
        String str2 = new String(getSecretByte(""), StandardCharsets.UTF_8);
        String str3 = new String(getSecretByte2(), StandardCharsets.UTF_8);
        return concat.concat(str.concat(str2).concat(str3).concat(new String(getSecretByte3(), StandardCharsets.UTF_8)));
    }

    /* renamed from: a */
    private static byte[] m20082a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 18);
        }
        return bArr;
    }
}
