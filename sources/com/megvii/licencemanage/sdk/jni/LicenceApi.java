package com.megvii.licencemanage.sdk.jni;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class LicenceApi {
    public static native String nativeGetLicense(Context context, String str, int i, long[] jArr);

    public static native int nativeSetLicense(Context context, String str);

    static {
        System.loadLibrary("MegviiIDCardQuality_1.3.0");
    }
}
