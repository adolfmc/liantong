package com.megvii.kas.livenessdetection.impl;

import android.util.Base64;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class EncodeImpl {
    private static native String nativeEncode(byte[] bArr, boolean z, boolean z2, int i, String str);

    /* renamed from: a */
    public static byte[] m13662a(byte[] bArr, boolean z, boolean z2, int i, String str) {
        String nativeEncode;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        if (z || z2 || str != null) {
            if (i >= 0 && (nativeEncode = nativeEncode(bArr, z, z2, i, str)) != null) {
                return Base64.decode(nativeEncode, 0);
            }
            return null;
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m13663a(byte[] bArr, boolean z, boolean z2, int i) {
        return m13662a(bArr, z, z2, i, null);
    }
}
