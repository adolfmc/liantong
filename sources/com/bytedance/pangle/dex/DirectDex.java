package com.bytedance.pangle.dex;

import android.os.Build;
import android.support.annotation.Keep;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DirectDex {
    private static native boolean native_init(int i);

    public static native Object native_load_direct_dex(String str);

    static {
        System.loadLibrary("zeus_direct_dex");
        native_init(Build.VERSION.SDK_INT);
    }
}
