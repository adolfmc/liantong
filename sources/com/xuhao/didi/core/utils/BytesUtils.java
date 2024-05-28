package com.xuhao.didi.core.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class BytesUtils {
    public static String toHexStringForLog(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (int i = 0; i < bArr.length; i++) {
                String str = Integer.toHexString(bArr[i] & 255) + " ";
                if (str.length() == 2) {
                    str = "0" + str;
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
