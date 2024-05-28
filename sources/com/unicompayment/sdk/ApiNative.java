package com.unicompayment.sdk;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ApiNative {
    static {
        System.loadLibrary("UnicomFPSDK");
    }

    public static native Object processCmd(int i, Context context, Object[] objArr);
}
