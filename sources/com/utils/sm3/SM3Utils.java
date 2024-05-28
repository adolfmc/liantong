package com.utils.sm3;

import com.utils.p366ak.UtilByte;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SM3Utils {
    private static final String TAG = "SM3Utils";

    public static boolean verifySM3Hash(byte[] bArr, String str) {
        SM3Digest sM3Digest = new SM3Digest();
        sM3Digest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[32];
        sM3Digest.doFinal(bArr2, 0);
        return UtilByte.toBase64(bArr2).equals(str);
    }

    public static byte[] doSM3Hash(byte[] bArr) {
        SM3Digest sM3Digest = new SM3Digest();
        sM3Digest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[32];
        sM3Digest.doFinal(bArr2, 0);
        return bArr2;
    }
}
